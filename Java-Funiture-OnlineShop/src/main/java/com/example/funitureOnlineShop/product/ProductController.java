package com.example.funitureOnlineShop.product;

import com.example.funitureOnlineShop.core.error.exception.Exception401;
import com.example.funitureOnlineShop.core.error.exception.Exception403;
import com.example.funitureOnlineShop.core.security.CustomUserDetails;
import com.example.funitureOnlineShop.core.utils.ApiUtils;
import com.example.funitureOnlineShop.user.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    // 상품 생성
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/product/save")
    public ResponseEntity<ApiUtils.ApiResult<Long>> save(ProductResponse.SaveByIdDTO productResponseFind,
                                  @RequestParam MultipartFile[] files) throws IOException {
        // 상품 저장 후 생성된 Product 객체를 반환받습니다.
        Product product = productService.save(productResponseFind, files);

        // Product 객체로부터 ID를 추출합니다.
        Long productId = product.getId();

        // ApiUtils의 success 메서드를 사용하여 성공 응답을 생성합니다.
        ApiUtils.ApiResult<Long> apiResult = ApiUtils.success(productId);

        // 생성된 응답을 클라이언트로 반환합니다.
        return ResponseEntity.ok(apiResult);
    }


    // 전체 상품 조회
    @GetMapping("/products")
    public ResponseEntity<?> findAll(@RequestParam(required = false, defaultValue = "0") int page,
                                     @RequestParam(required = false, defaultValue = "10") int size) {
        Page<ProductResponse.FindAllDTO> products = productService.findAll(PageRequest.of(page, size));
        ApiUtils.ApiResult<Page<ProductResponse.FindAllDTO>> apiResult = ApiUtils.success(products);
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/product/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, ProductResponse.FindByIdDTO findByIdDTO) {
        ProductResponse.FindByIdDTO updatedProduct = productService.update(id, findByIdDTO);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(updatedProduct);
        return ResponseEntity.ok(apiResult);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.delete(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(id);
        return ResponseEntity.ok(apiResult);
    }
}