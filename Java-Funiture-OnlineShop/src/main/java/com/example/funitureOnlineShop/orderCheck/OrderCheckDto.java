package com.example.funitureOnlineShop.orderCheck;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCheckDto {
    // PK
    private Long id;
    // 거래 id
    private String tid;
    // 수량
    private Long quantity;
    // 상품명
    private String productName;
    // 옵션명
    private String optionName;
    // 가격
    private Long price;
    // 주문일자
    private LocalDateTime orderDate;
    // 옵션 id
    private Long cartId;
    // 회원 id
    private Long userId;
    // 상품 후기 적힘?
    private Long commentId;

    public static OrderCheckDto toOrderCheckDto(OrderCheck orderCheck, Long commentId) {
        return new OrderCheckDto(orderCheck.getId(),
                orderCheck.getTid(),
                orderCheck.getQuantity(),
                orderCheck.getCart().getOption().getProduct().getProductName(),
                orderCheck.getCart().getOption().getOptionName(),
                orderCheck.getCart().getOption().getPrice(),
                orderCheck.getOrderDate(),
                orderCheck.getCart().getId(),
                orderCheck.getUser().getId(),
                commentId);
    }

    public OrderCheck toEntity(){
        return OrderCheck.builder()
                .tid(tid)
                .quantity(quantity)
                .price(price)
                .orderDate(orderDate)
                .build();
    }
}
