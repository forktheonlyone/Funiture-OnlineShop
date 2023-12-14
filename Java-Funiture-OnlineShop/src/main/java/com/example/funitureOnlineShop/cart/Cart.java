package com.example.funitureOnlineShop.cart;

import com.example.funitureOnlineShop.option.Option;
import com.example.funitureOnlineShop.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user; // 구매자


    @OneToOne
    private Option option;

    // 카트에 담긴 총 가격
    private Long price;

    // 카트에 담긴 총 상품 개수
    private Long quantity;

    @Builder
    public Cart(Long id, User user, Option option, Long price, Long quantity) {
        this.id = id;
        this.user = user;
        this.option = option;
        this.price = price;
        this.quantity = quantity;
    }

    public void update(Long quantity, Long price){
        this.quantity = quantity;
        this.price = price;
    }
}
