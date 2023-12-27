package com.example.funitureOnlineShop.orderCheck;

import com.example.funitureOnlineShop.option.Option;
import com.example.funitureOnlineShop.product.Product;
import com.example.funitureOnlineShop.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public OrderCheck(Long id, String tid, Long quantity, Long price, Option option, User user) {
        this.id = id;
        this.tid = tid;
        this.quantity = quantity;
        this.price = price;
        this.option = option;
        this.user = user;
    }
}
