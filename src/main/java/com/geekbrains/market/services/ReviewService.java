package com.geekbrains.market.services;

import com.geekbrains.market.entities.Review;
import com.geekbrains.market.entities.User;
import com.geekbrains.market.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public boolean isUserCanWriteReview(Long productId, User user) {
        return reviewRepository.findByProductIdAndUser(productId, user) == null;
    }
}