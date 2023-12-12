package com.example.FunitureOnlineShop.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;
/*
    public ResponseEntity<?> save(ProductResponse productResponse) {

        productService.save(productResponse.toEntity());
    }

 */
}
