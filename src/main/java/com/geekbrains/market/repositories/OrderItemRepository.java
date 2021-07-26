package com.geekbrains.market.repositories;

import com.geekbrains.market.entities.OrderItem;
import com.geekbrains.market.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("select o from OrderItem o where o.product.id = ?1 and o.order.user = ?2")
    OrderItem findByProductIdAndUser(Long productId, User user);
}
