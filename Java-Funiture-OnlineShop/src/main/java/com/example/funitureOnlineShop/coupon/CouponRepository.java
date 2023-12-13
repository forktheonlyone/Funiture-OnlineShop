package com.example.funitureOnlineShop.coupon;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    List<Coupon> findAllByCouponName(String couponName);
}
