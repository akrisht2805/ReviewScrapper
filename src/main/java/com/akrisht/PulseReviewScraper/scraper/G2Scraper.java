package com.akrisht.PulseReviewScraper.scraper;

import com.akrisht.PulseReviewScraper.entity.Review;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.NoSuchElementException;

@Component
public class G2Scraper {

    public List<Review> scrape(String companyName, String startDateStr, String endDateStr) throws Exception {
        WebDriver driver = new ChromeDriver();
        String url = "https://www.g2.com/products/" + companyName.toLowerCase() + "/reviews";
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".paper")));

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(3000);

        List<Review> reviews = new ArrayList<>();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        List<WebElement> reviewElements = driver.findElements(By.cssSelector(".paper"));
        System.out.println("Found " + reviewElements.size() + " reviews");

        for (WebElement el : reviewElements) {
            Review review = new Review();

            String title = getTextSafe(el, "h3");
            String description = getTextSafe(el, "p");
            String reviewer = getTextSafe(el, ".author");
            String dateStr = getTextSafe(el, "time");

            // Parse date and filter
            try {
                LocalDate reviewDate = LocalDate.parse(dateStr, formatter);
                if (reviewDate.isBefore(startDate) || reviewDate.isAfter(endDate)) continue;

                review.setTitle(title);
                review.setDescription(description);
                review.setReviewer(reviewer);
                review.setRating(5);
                review.setDate(reviewDate.toString());

                reviews.add(review);
            } catch (Exception e) {
                System.out.println("Skipping review due to date parsing error: " + dateStr);
            }
        }

        driver.quit();
        return reviews;
    }

    private String getTextSafe(WebElement el, String cssSelector) {
        try {
            return el.findElement(By.cssSelector(cssSelector)).getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }
}
