package com.example.FunitureOnlineShop.cart;

import com.example.FunitureOnlineShop.option.Option;
import lombok.Data;

public class CartRequest {

    @Data
    public static class SaveDTO {
        private Long optionId;

        private Long quantity;

        public Cart toEntity(Option option, User user){
            return Cart.builder()
                    .option(option)
                    .user(user)
                    .quantity(quantity)
                    .price(option.getPrice() * quantity)
                    .build();
        }
    }

    @Data
    public static class updateDTO {
        private Long cartId;
        private Long quantity;
    }
}
