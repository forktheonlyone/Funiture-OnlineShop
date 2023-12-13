package com.example.funitureOnlineShop.coupon;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class CouponRequest {

    @Setter
    @Getter
    public static class SaveDto{
        private Long productId;
        private String couponName;
        private int onSale;
        private Date expirationDate;
        private Long quantity;

        public Coupon toEntity(){
            return Coupon.builder()
                    .couponName(couponName)
                    .onSale(onSale)
                    .expirationDate(expirationDate)
                    .build();
        }
    }
}
