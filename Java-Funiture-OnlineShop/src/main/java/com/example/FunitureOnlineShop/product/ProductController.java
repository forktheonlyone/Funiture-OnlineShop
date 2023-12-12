package com.example.FunitureOnlineShop.product;

import com.example.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product/{id}/save")
    public ResponseEntity<?> save(@PathVariable Long id, ProductResponse.FindByCategoryIdDTO productResponseFind) {
        Category category = productResponseFind.setCategoryId(id);
        productService.save(productResponse.toEntity());
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(category);
    }
}
