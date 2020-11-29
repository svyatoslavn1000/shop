package com.geekbrains.decembermarket.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders_items")
@NoArgsConstructor
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.price = product.getPrice();
    }

    public void increment(){
        this.quantity ++;
        this.price = new BigDecimal(quantity*product.getPrice().doubleValue());
    }

    public void decrement(){
        this.quantity --;
        this.price = new BigDecimal(quantity*product.getPrice().doubleValue());
    }

    public boolean isEmpty(){
        return quantity == 0;
    }
}