package com.geekbrains.decembermarket.services;

import com.geekbrains.decembermarket.entities.Order;
import com.geekbrains.decembermarket.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }
}
