package com.example.funitureOnlineShop.option;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class OptionResponse {
    private Long id;

    private Long productId;

    // private Product product;

    private String optionName;

    private Long Price;

    private Long quantity;

    @NoArgsConstructor
    @Getter
    @Setter
    public static class FindByProductIdDTO {
        private Long id;

        private Long productId;

        private String optionName;

        private Long Price;

        private Long quantity;

        public FindByProductIdDTO(Option option) {
            this.id = option.getId();
            // this.productId = option.getProduct().getId();
            this.optionName = option.getOptionName();
            this.Price = option.getPrice();
            this.quantity = option.getQuantity();
        }

        public Option toEntity() {
            return Option.builder()
                    .optionName(optionName)
                    .price(Price)
                    .quantity(quantity)
                    .build();
        }
    }
    @NoArgsConstructor
    @Getter
    @Setter
    public static class FindAllDTO{
        private Long id;

        private Long productId;

        private String optionName;

        private Long Price;

        private Long quantity;

        public FindAllDTO(Option option){
            this.id = option.getId();
            //this.productId = option.getProduct().getId();
            this.optionName = option.getOptionName();
            this.Price = option.getPrice();
            this.quantity = option.getQuantity();
        }

        public Option toEntity(){
            return Option.builder()
                    .optionName(optionName)
                    .price(Price)
                    .quantity(quantity)
                    .build();
        }
    }
}
