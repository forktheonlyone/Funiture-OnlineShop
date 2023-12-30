package com.example.funitureOnlineShop.product;

import com.example.funitureOnlineShop.productFile.ProductFile;
import com.example.funitureOnlineShop.productFile.ProductFileResponse;
import com.example.funitureOnlineShop.option.Option;
import lombok.AllArgsConstructor;
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

    @AllArgsConstructor
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

        private List<ProductFileResponse> files;

        public static FindByIdDTO toDto(Product product, List<Option> options, List<ProductFile> files) {
            return new FindByIdDTO(
                    product.getId(),
                    product.getProductName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getDeliveryFee(),
                    product.getCategory().getId(),
                    options.stream().map(OptionDTO::new).collect(Collectors.toList()),
                    files.stream().map(ProductFileResponse::toDto).collect(Collectors.toList())
            );
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class FindByCategoryForAllDTOS {

        private Long id;
        private String productName;
        private Long price;
        private ProductFileResponse file;

        public FindByCategoryForAllDTOS(Long id, String productName, Long price, ProductFileResponse file) {
            this.id = id;
            this.productName = productName;
            this.price = price;
            this.file = file;
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
    public static class UpdateDTO {
        private Long id;
        private String productName;
        private String description;
        private Long price;
        private Long deliveryFee;
    }

}

