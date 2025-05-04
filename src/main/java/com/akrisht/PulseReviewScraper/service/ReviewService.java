package com.akrisht.PulseReviewScraper.service;

import com.akrisht.PulseReviewScraper.entity.Review;
import com.akrisht.PulseReviewScraper.scraper.G2Scraper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private G2Scraper scrapers;


    public String getReviewsToFile(String source, String companyName, String startDate, String endDate) throws Exception {
//        Validate dates
        validateDates(startDate, endDate);

//        Validate company names
        validateSource(source);

        List<Review> reviews = scrapers.scrape(companyName, startDate, endDate);

        String outputDir = "output";
        Files.createDirectories(Paths.get(outputDir));

        String filename = String.format("%s/reviews_%s_%s.json", outputDir, companyName, source);
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(filename), reviews);

        return filename;
    }

    public void validateSource(final String source) {
        if (!source.equalsIgnoreCase("G2") && !source.equalsIgnoreCase("Capterra")) {
            throw new IllegalArgumentException("Invalid source. Only 'G2' and 'Capterra' are allowed.");
        }
    }


    public void validateDates(final String startDate, final String endDate) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            if (start.isAfter(end)) {
                throw new IllegalArgumentException("Start date must not be after end date.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format is YYYY-MM-DD.");
        }
    }
}
