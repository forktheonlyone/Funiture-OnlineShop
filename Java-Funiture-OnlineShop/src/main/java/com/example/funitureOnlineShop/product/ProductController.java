package com.example.funitureOnlineShop.product;

import com.example.funitureOnlineShop.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    // 상품 생성
    @PostMapping("/product")
    public ResponseEntity<?> save(ProductResponse.SaveByIdDTO productResponseFind,
                                  @RequestParam MultipartFile[] files) throws IOException {
        productService.save(productResponseFind, files);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(productResponseFind);
        return ResponseEntity.ok(apiResult);
    }

    // 상품 찾기
    @GetMapping("/product/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ProductResponse.FindByIdDTO productDTOS = productService.findById(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(productDTOS);
        return ResponseEntity.ok(apiResult);
    }

    // 상품 수정
    @PutMapping("/product/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, ProductResponse.FindByIdDTO findByIdDTO) {
        ProductResponse.FindByIdDTO updatedProduct = productService.update(id, findByIdDTO);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(updatedProduct);
        return ResponseEntity.ok(apiResult);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.delete(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(id);
        return ResponseEntity.ok(apiResult);
    }
}