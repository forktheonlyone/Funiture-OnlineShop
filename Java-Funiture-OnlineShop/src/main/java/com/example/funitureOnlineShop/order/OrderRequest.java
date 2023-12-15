package com.example.funitureOnlineShop.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderRequest {

    private Long userId;
    private List<OrderItemRequest> items;

    @Data
    public static class OrderItemRequest {
        private Long optionId;
        private Long quantity;

        public OrderItemRequest(Long optionId, Long quantity) {
            this.optionId = optionId;
            this.quantity = quantity;
        }
    }
}