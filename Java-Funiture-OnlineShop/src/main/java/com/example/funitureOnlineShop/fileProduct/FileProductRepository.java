package com.example.funitureOnlineShop.fileProduct;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileProductRepository extends JpaRepository<FileProduct, Long> {
    Optional<FileProduct> findByProductId(Long productId);
}
