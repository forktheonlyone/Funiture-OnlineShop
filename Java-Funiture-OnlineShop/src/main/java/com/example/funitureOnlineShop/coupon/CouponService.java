package com.example.funitureOnlineShop.coupon;

import com.example.funitureOnlineShop.product.Product;
import com.example.funitureOnlineShop.product.ProductRepository;
import com.example.funitureOnlineShop.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Coupon save(CouponRequest.SaveDto couponDto) {
        Optional<Product> optionalProduct = productRepository.findById(couponDto.getProductId());
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            //Hibernate.initialize(product);

            Coupon coupon = Coupon.builder()
                    .couponName(couponDto.getCouponName())
                    .onSale(couponDto.getOnSale())
                    .expirationDate(couponDto.getExpirationDate())
                    .product(product)
                    .build();
            couponRepository.save(coupon);
            return coupon;
        } else {
            return null;
        }
    }
}
