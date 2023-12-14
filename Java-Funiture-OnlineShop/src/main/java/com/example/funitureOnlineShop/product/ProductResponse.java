package com.example.funitureOnlineShop.product;

import com.example.funitureOnlineShop.category.Category;
import com.example.funitureOnlineShop.option.Option;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class ProductResponse {

    @Setter
    @Getter
    public static class OptionDTO {
        private Long id;
        private String optionName;
        private Long price;
        private Long quantity;

        public OptionDTO(Option option) {
            this.id = option.getId();
            this.optionName = option.getOptionName();
            this.price = option.getPrice();
            this.quantity = option.getQuantity();
        }
    }

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

        List<OptionDTO> optionList;


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
    }

    @NoArgsConstructor
    @Setter
    @Getter
    public static class SaveByIdDTO {

        private Long id;

        private String productName;

        private String description;

        private String image;

        private Long price;

        private Long onSale;

        private Long point;

        private Long deliveryFee;

        private Long categoryId;

        List<OptionDTO> optionList;

        public Product toEntity() {
            return Product.builder()
                    .productName(productName)
                    .description(description)
                    .image(image)
                    .price(price)
                    .onSale(onSale)
                    .point(point)
                    .deliveryFee(deliveryFee)
                    .build();
        }
    }
}
