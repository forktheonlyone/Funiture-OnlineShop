package com.example.funitureOnlineShop.order.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findAllByOrderId(Long id);
}
