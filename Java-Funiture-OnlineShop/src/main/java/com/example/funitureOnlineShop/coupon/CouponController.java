package com.example.funitureOnlineShop.coupon;

import com.example.funitureOnlineShop.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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

    @PostMapping("/own/{id}")
    public ResponseEntity<?> own(@PathVariable Long id, @RequestBody @Valid String couponName) {
        couponService.own(id, couponName);

        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        couponService.delete(id);

        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @PostMapping("/delete_all")
    public ResponseEntity<?> deleteAll(@RequestBody @Valid String couponName) {
        couponService.deleteAll(couponName);

        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findByUser(@PathVariable Long id) {
        
    }
}
