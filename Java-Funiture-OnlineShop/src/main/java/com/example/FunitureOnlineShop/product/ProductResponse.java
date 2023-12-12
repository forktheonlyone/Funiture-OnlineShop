package com.example.FunitureOnlineShop.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Setter
@Getter
public class ProductResponse {

    private Long id;

    private String productName;

    private String description;

    private String image;

    private Long price;

    private Long onSale;

    private Long point;

    private String deliveryFee;

    private String categoryId;

    /*
    public Product toEntity(Category category) {
        return Product.builder()
                .productName(productName)
                .description(description)
                .image(image)
                .price(price)
                .onSale(onSale)
                .point(point)
                .deliveryFee(deliveryFee)
                .category(category)
                .build();
    }

     */
}
