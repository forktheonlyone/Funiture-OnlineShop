package com.example.funitureOnlineShop.coupon;

import com.example.funitureOnlineShop.core.error.exception.Exception404;
import com.example.funitureOnlineShop.core.error.exception.Exception422;
import com.example.funitureOnlineShop.product.Product;
import com.example.funitureOnlineShop.product.ProductRepository;
import com.example.funitureOnlineShop.user.User;
import com.example.funitureOnlineShop.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CouponService {

    private final CouponRepository couponRepository;

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Coupon save(CouponRequest.SaveDto couponDto) {
        Optional<Product> optionalProduct = productRepository.findById(couponDto.getProductId());
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            Hibernate.initialize(product);

            Coupon coupon = Coupon.builder()
                    .couponName(couponDto.getCouponName())
                    .onSale(couponDto.getOnSale())
                    .expirationDate(couponDto.getExpirationDate())
                    .product(product)
                    .build();
            for (long i = 0L; i < couponDto.getQuantity(); i++) {
                couponRepository.save(coupon);
            }
            return coupon;
        } else {
            return null;
        }
    }

    @Transactional
    public void own(Long id, String couponName) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            Hibernate.initialize(user);

            List<Coupon> coupons = couponRepository.findAllByCouponName(couponName);
            Coupon unownedCoupon = null;

            Date currentDate = new Date();
            if (coupons.get(0).getExpirationDate().after(currentDate))
                throw new Exception422("만료일이 지난 쿠폰입니다.");

            for (Coupon coupon : coupons){
                if (coupon.getUser() != null && user.getId().equals(coupon.getUser().getId()))
                    throw new Exception422("이미 소유하고 있는 쿠폰입니다.");

                if (coupon.getUser() == null)
                    unownedCoupon = coupon;
            }

            if (unownedCoupon == null)
                throw new Exception404("쿠폰이 모두 분배되었습니다.");

            unownedCoupon.updateFromUser(user);
        }
    }
}
