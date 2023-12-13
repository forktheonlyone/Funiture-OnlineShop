package com.example.funitureOnlineShop.coupon;

import com.example.funitureOnlineShop.product.Product;
import com.example.funitureOnlineShop.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3, nullable = false)
    private int onSale;

    @Column(length = 30, nullable = false)
    private Date expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public Coupon(Long id, int onSale, Date expirationDate, User user, Product product) {
        this.id = id;
        this.onSale = onSale;
        this.expirationDate = expirationDate;
        this.user = user;
        this.product = product;
    }
}
