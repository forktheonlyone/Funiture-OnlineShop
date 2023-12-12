package com.example.FunitureOnlineShop.product;

import com.example.FunitureOnlineShop.option.OptionResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class ProductResponse {

    @NoArgsConstructor
    @Setter
    @Getter
    public static class FindByCategoryIdDTO {

        private Long id;

        private String productName;

        private String description;

        private String image;

        private Long price;

        private Long onSale;

        private Long point;

        private Long deliveryFee;

        private Long categoryId;


        public FindByCategoryIdDTO(Product product) {
            this.id = product.getId();
            this.productName = product.getProductName();
            this.description = product.getDescription();
            this.image = product.getImage();
            this.price = product.getPrice();
            this.onSale = product.getOnSale();
            this.point = product.getPoint();
            this.deliveryFee = product.getDeliveryFee();
            this.categoryId = product.getCategory().getId();
        }


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
    }
    @NoArgsConstructor
    @Setter
    @Getter
    public static class FindByIdDTO {

        private Long id;

        private String productName;

        private String description;

        private String image;

        private Long price;

        private Long onSale;

        private Long point;

        private Long deliveryFee;

        private Long categoryId;


        public FindByIdDTO(Product product, List<Option> optionList) {
            this.id = product.getId();
            this.productName = product.getProductName();
            this.description = product.getDescription();
            this.image = product.getImage();
            this.price = product.getPrice();
            this.onSale = product.getOnSale();
            this.point = product.getPoint();
            this.deliveryFee = product.getDeliveryFee();
            this.categoryId = product.getCategory().getId();
            this.optionList = optionList.stream().map(OptionDTO::new)
                    .collect(Collectors.toList());
        }
/*
    public static class FindByIdDTO {
        private Long id;

        private String productName;

        private String description;

        private String image;

        private Long price;

        private Long onSale;

        private Long point;

        private Long deliveryFee;

        private Long categoryId;

        private List<Category> categoryList;

        public FindByIdDTO(Product product, List<Category> categoryList) {
            this.id = product.getId();
            this.productName = product.getProductName();
            this.description = product.getDescription();
            this.image = product.getImage();
            this.price = product.getPrice();
            this.onSale = product.getOnSale();
            this.point = product.getPoint();
            this.deliveryFee = product.getDeliveryFee();
            this.categoryId = product.;
            this.categoryList = categoryList;
        }
    }

 */
}
