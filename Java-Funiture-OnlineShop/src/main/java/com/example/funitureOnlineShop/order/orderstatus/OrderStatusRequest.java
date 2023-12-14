package com.example.funitureOnlineShop.order.orderstatus;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderStatusRequest {
    @Data
    @ToString
    public static class savedto{
        private Long id;
        private Long orderId;
        private boolean isOrdered;

        public savedto(OrderStatus orderStatus, boolean isOrdered) {
            this.id = orderStatus.getId();
            this.orderId = orderStatus.getOrder().getId();
            this.isOrdered = orderStatus.isOrdered();
        }
    }

    @Data
    @ToString
    public static class OrderStatusUpdateRequest{
        private Long orderId;
        private boolean isOrdered;
        public OrderStatusUpdateRequest(Long orderId, boolean isOrdered) {
            this.orderId = orderId;
            this.isOrdered = isOrdered;
        }
    }
}
