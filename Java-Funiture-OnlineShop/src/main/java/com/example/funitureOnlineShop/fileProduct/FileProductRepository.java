package com.example.funitureOnlineShop.fileProduct;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileProductRepository extends JpaRepository<FileProduct, Long> {
    List<FileProduct> findByProductId(Long productId);
}
