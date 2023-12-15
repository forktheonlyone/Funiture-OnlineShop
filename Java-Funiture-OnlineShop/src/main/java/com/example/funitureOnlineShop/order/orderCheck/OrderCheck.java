package com.example.funitureOnlineShop.order.orderCheck;

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

    private String optionName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public OrderCheck(Long id, String tid, String optionName, Product product, User user) {
        this.id = id;
        this.tid = tid;
        this.optionName = optionName;
        this.product = product;
        this.user = user;
    }
}
