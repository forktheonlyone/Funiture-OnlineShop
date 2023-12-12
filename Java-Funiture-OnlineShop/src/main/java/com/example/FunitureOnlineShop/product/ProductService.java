package com.example.FunitureOnlineShop.product;

import com.example.core.error.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Transactional
    public void save(ProductResponse.FindByCategoryIdDTO productResponseFind) {
        Category category = categoryRepository.findById(productResponseFind.getCategoryId())
                .orElseThrow( () -> new Exception404(
                        "해당 카테고리를 찾을 수 없습니다. : " + productResponseFind.getCategoryId()));
        Product product = productResponseFind.toEntity(category);
        productRepository.save(product);
    }

}
