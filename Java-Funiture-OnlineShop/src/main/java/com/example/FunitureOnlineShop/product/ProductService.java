package com.example.FunitureOnlineShop.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {
    private ProductRepository productRepository;
/*
    public void save(Product product) {
        productRepository.save(product);


    }
    */
}
