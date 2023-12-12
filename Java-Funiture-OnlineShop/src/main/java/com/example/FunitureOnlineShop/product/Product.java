package com.example.FunitureOnlineShop.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String productName;

    @Column(length = 1000, nullable = false)
    private String description;

    @Column(length = 100, nullable = false)
    private String image;

    @Column(nullable = false)
    private Long price;

    @Column(length = 100, nullable = false)
    private Long onSale;

    @Column(nullable = false)
    private Long point;

    @Column(nullable = false)
    private Long deliveryFee;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;


    @Builder
    public Product(
            Long id, String productName, String description, String image,
            Long price, Long onSale, Long point, Long deliveryFee, Category category) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.image = image;
        this.price = price;
        this.onSale = onSale;
        this.point = point;
        this.deliveryFee = deliveryFee;
        this.category = category;
    }


}