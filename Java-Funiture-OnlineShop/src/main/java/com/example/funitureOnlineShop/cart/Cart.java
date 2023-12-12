package com.example.funitureOnlineShop.cart;

import com.example.funitureOnlineShop.option.Option;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToOne
    private Option option;

    private Long price;

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
