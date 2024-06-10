package com.booking_house_be.service.impl;

import com.booking_house_be.entity.Review;
import com.booking_house_be.repository.IReviewRepo;
import com.booking_house_be.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService {
    @Autowired
    private IReviewRepo reviewRepo;

    @Override
    public Page<Review> findAllByHouseId(int houseId, int page, int size) {
        return reviewRepo.findAllByHouseId(houseId, PageRequest.of(page, size, Sort.by("createAt").descending()));
    }

    @Override
    public Double avgRating(int houseId) {
        Double avg = reviewRepo.avgRating(houseId);
        return avg == null ? 0.0 : Math.round(avg * 10) / 10.0;
    }

    @Override
    public List<Review> findAllByAccountId(int accountId) {
        return reviewRepo.findAllByAccountId(accountId);
    }
}
