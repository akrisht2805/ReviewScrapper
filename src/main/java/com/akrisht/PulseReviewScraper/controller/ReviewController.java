package com.akrisht.PulseReviewScraper.controller;

import com.akrisht.PulseReviewScraper.entity.Review;
import com.akrisht.PulseReviewScraper.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    public ReviewService reviewService;

    @GetMapping
    public String writeReviewsToFile(
            @RequestParam String companyName,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String source
    ) throws Exception {
        String filePath = reviewService.getReviewsToFile(source, companyName, startDate, endDate);
        return "Reviews written to: " + filePath;
    }
}
