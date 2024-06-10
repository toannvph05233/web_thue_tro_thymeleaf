package com.booking_house_be.controller;

import com.booking_house_be.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private IReviewService reviewService;

    @GetMapping("/house/{houseId}")
    public ResponseEntity<?> findAllByHouseId(@PathVariable int houseId,
                                              @RequestParam(name = "page", defaultValue = "0") int page,
                                              @RequestParam(name = "size", defaultValue = "5") int size) {
        try {
            return ResponseEntity.ok(reviewService.findAllByHouseId(houseId, page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }

    @GetMapping("/avg-rating/{houseId}")
    public Double avgRating(@PathVariable int houseId){
        return reviewService.avgRating(houseId);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<?> findAllByAccountId(@PathVariable int accountId) {
        try {
            return ResponseEntity.ok(reviewService.findAllByAccountId(accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }
}
