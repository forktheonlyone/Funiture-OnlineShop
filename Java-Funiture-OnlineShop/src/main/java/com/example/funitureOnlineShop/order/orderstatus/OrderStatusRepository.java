package com.example.funitureOnlineShop.order.orderstatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
    Optional<OrderStatus> findByOrderId(Long orderId);
}
