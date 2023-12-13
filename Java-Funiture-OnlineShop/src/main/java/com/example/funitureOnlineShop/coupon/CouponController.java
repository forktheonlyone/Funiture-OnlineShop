package com.example.funitureOnlineShop.coupon;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@ModelAttribute CouponRequest.SaveDto couponDto) {
        Coupon coupon = couponService.save(couponDto);

        if (coupon != null){
            return ResponseEntity.ok().body(couponDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
