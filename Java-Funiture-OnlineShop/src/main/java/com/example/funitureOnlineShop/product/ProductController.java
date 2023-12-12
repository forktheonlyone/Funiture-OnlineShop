package com.example.funitureOnlineShop.product;

import com.example.funitureOnlineShop.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    // 상품 생성
    @PostMapping("/product/{id}/save")
    public ResponseEntity<?> save(@PathVariable Long id, ProductResponse.FindByCategoryIdDTO productResponseFind) {
        Category category = productResponseFind.setCategoryId(id);
        productService.save(productResponseFind);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(category);
        return ResponseEntity.ok(apiResult);
    }

    // 상품 찾기
    @PostMapping("/product/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ProductResponse.FindByIdDTO productDTOS = productService.findById(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(productDTOS);
        return ResponseEntity.ok(apiResult);
    }

    // 상품 수정
    @PostMapping("/product/{id}/update")
    public ResponseEntity<?> update(@PathVariable Long id, ProductResponse.FindByIdDTO findByIdDTO) {
        ProductResponse.FindByIdDTO updatedProduct = productService.update(id, findByIdDTO);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(updatedProduct);
        return ResponseEntity.ok(apiResult);
    }
}