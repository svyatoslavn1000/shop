package com.geekbrains.market.repositories;

import com.geekbrains.market.entities.Review;
import com.geekbrains.market.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.product.id = ?1 and r.user = ?2")
    Review findByProductIdAndUser(Long productId, User user);
}
