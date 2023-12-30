package com.example.funitureOnlineShop.payments;

import lombok.Getter;
import lombok.Setter;

public class NicepayRequest {

    @Getter
    @Setter
    public static class orderDto{
        // 주문 id
        private Long id;
        // 받는 이 이름
        private String receiverName;
        // 받는 이 주소
        private String address;
        // 받는 이 전화번호
        private String phoneNumber;
        // 수량
        private Long quantity;
        private Long userId;

    }
}
