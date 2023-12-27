package com.example.funitureOnlineShop.product;

import com.example.funitureOnlineShop.category.Category;
import com.example.funitureOnlineShop.fileProduct.FileProduct;
import com.example.funitureOnlineShop.option.Option;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long deliveryFee;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Option> options = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FileProduct> files =  new ArrayList<>();

    @Builder
    public Product(Long id, String productName, String description, Long price, Long deliveryFee, Category category, List<Option> options, List<FileProduct> files) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.deliveryFee = deliveryFee;
        this.category = category;
        this.options = options;
        this.files = files;
    }

    public Product assignToCategory(Category category) {
        Product product = new Product();
        this.category = category;
        return product;
    }

    public void update(ProductResponse.FindByIdDTO findByIdDTO) {
        this.productName = findByIdDTO.getProductName();
        this.description = findByIdDTO.getDescription();
        this.price = findByIdDTO.getPrice();
        this.deliveryFee = findByIdDTO.getDeliveryFee();
    }

}