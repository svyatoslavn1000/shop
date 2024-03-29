package com.geekbrains.market.entities;

import com.geekbrains.market.beans.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phone;

    public Order(User user, Cart cart, String address, String phone) {
        this.user = user;
        this.items = new ArrayList<>();
        this.address = address;
        this.phone = phone;
        for (OrderItem i : cart.getItems()) {
            i.setOrder(this);
            this.items.add(i);
        }
        this.price = new BigDecimal(cart.getPrice().doubleValue());
        cart.clear();
    }
}
