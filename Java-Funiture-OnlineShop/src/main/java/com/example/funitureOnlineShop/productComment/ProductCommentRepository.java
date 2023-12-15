package com.example.funitureOnlineShop.productComment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
    List<ProductComment> findAllByOptionId(Long id);
}
