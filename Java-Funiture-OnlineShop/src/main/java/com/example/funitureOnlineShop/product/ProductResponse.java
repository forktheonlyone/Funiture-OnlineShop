package com.example.funitureOnlineShop.product;

import com.example.funitureOnlineShop.category.Category;
import com.example.funitureOnlineShop.fileProduct.FileProductResponse;
import com.example.funitureOnlineShop.option.Option;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductResponse {

    @Setter
    @Getter
    public static class OptionDTO {
        private Long id;
        private String optionName;
        private Long price;
        private Long stockQuantity;
        private Long productId;

        public OptionDTO(Option option) {
            this.id = option.getId();
            this.optionName = option.getOptionName();
            this.price = option.getPrice();
            this.stockQuantity = option.getStockQuantity();
            this.productId = option.getProduct().getId();
        }
    }

    @NoArgsConstructor
    @Setter
    @Getter
    public static class FindByCategoryIdDTO {

        private Long id;

        private String productName;

        private String description;

        private Long price;

        private Long deliveryFee;

        private Long categoryId;


        public FindByCategoryIdDTO(Product product) {
            this.id = product.getId();
            this.productName = product.getProductName();
            this.description = product.getDescription();
            this.price = product.getPrice();
            this.deliveryFee = product.getDeliveryFee();
            this.categoryId = product.getCategory().getId();
        }


        public Product toEntity(Category category) {
            return Product.builder()
                    .id(id)
                    .productName(productName)
                    .description(description)
                    .price(price)
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

        private Long price;

        private Long deliveryFee;

        private Long categoryId;

        private List<OptionDTO> optionList;

        private List<FileProductResponse> fileProductList;

        public FindByIdDTO(Product product, List<Option> optionList, List<FileProductResponse> fileProductList) {
            this.id = product.getId();
            this.productName = product.getProductName();
            this.description = product.getDescription();
            this.price = product.getPrice();
            this.deliveryFee = product.getDeliveryFee();
            this.categoryId = product.getCategory().getId();
            this.optionList = optionList.stream().map(OptionDTO::new)
                    .collect(Collectors.toList());
            this.fileProductList = (fileProductList != null) ? fileProductList : new ArrayList<>();
        }
    }

    @NoArgsConstructor
    @Setter
    @Getter
    public static class SaveByIdDTO {

        private String productName;

        private String description;

        private Long price;

        private Long deliveryFee;

        private Long categoryId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class FindAllDTO {

        private Long id;
        private String productName;
        private Long price;

        public FindAllDTO(Long id, String productName, Long price) {
            this.id = id;
            this.productName = productName;
            this.price = price;
        }
    }

}
