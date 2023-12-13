package com.example.funitureOnlineShop.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByMemberId(Long id);
    Optional<Cart> findByIdAndUserId(Long cartId, Long UserId);
    List<Cart> findByUserId(Long userId);
}
