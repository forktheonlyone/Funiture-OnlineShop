package com.example.funitureOnlineShop.orderCheck;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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
    // 옵션 id
    private Long optionId;
    // 회원 id
    private Long userId;

    public static OrderCheckDto toOrderCheckDto(OrderCheck orderCheck) {
        return new OrderCheckDto(orderCheck.getId(),
                orderCheck.getTid(),
                orderCheck.getQuantity(),
                orderCheck.getOption().getProduct().getProductName(),
                orderCheck.getOption().getOptionName(),
                orderCheck.getOption().getId(),
                orderCheck.getUser().getId());
    }
}
