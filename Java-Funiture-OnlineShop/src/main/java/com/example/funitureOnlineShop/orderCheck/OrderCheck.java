package com.example.funitureOnlineShop.orderCheck;

import com.example.funitureOnlineShop.cart.Cart;
import com.example.funitureOnlineShop.option.Option;
import com.example.funitureOnlineShop.product.Product;
import com.example.funitureOnlineShop.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class OrderCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tid;

    private Long quantity;

    private Long price;

    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public OrderCheck(Long id, String tid, Long quantity, Long price, LocalDateTime orderDate, Cart cart, User user) {
        this.id = id;
        this.tid = tid;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = orderDate;
        this.cart = cart;
        this.user = user;
    }

    public static void sortByCreateDate(List<OrderCheck> orderChecks) {
        orderChecks.sort(Comparator.comparing(OrderCheck::getOrderDate).reversed());
    }
}
