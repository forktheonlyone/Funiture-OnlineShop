package com.example.funitureOnlineShop.product;

import com.example.funitureOnlineShop.core.utils.ApiUtils;
import com.example.funitureOnlineShop.fileProduct.FileProduct;
import com.example.funitureOnlineShop.fileProduct.FileProductRepository;
import com.example.funitureOnlineShop.fileProduct.FileProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;
    private final FileProductRepository fileProductRepository;
    private final String filePath = "C:/Users/G/Desktop/GitHub/Funiture-OnlineShop/Product Files/";

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

/*
    // 전체 상품 조회
    @GetMapping("/products")
    public ResponseEntity<?> findAll(@RequestParam(required = false, defaultValue = "0") int page,
                                     @RequestParam(required = false, defaultValue = "10") int size) {
        Page<ProductResponse.findByCategoryForAllDTOS> products = productService.findAll(PageRequest.of(page, size));
        ApiUtils.ApiResult<Page<ProductResponse.findByCategoryForAllDTOS>> apiResult = ApiUtils.success(products);
        return ResponseEntity.ok(apiResult);
    }

 */

    // 상품과 해당 상품의 리뷰 찾기
    @GetMapping("/product/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ProductResponse.FindByIdDTO productDTOS = productService.findById(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(productDTOS);
        return ResponseEntity.ok(apiResult);
    }

    // 카테고리별 상품 조회
    @GetMapping("/products/{categoryId}")
    public ResponseEntity<?> findByCategoryId(@PathVariable Long categoryId,
                                              @RequestParam(required = false, defaultValue = "0") int page,
                                              @RequestParam(required = false, defaultValue = "10") int size) {
        Page<ProductResponse.FindByCategoryForAllDTOS> products = productService.findByCategoryId(categoryId, PageRequest.of(page, size));
        ApiUtils.ApiResult<Page<ProductResponse.FindByCategoryForAllDTOS>> apiResult = ApiUtils.success(products);
        return ResponseEntity.ok(apiResult);
    }

    // 이미지들 찾기
    @GetMapping("/product/image/{id}")
    public ResponseEntity<?> getImage(@PathVariable Long id) throws IOException {

        FileProductResponse fileDto = productService.findByIdFile(id);

        File file = new File(filePath + fileDto.getUuid() + fileDto.getFileName());

        return ResponseEntity.ok()
                .header("Content-type", Files.probeContentType(file.toPath()))
                .body(FileCopyUtils.copyToByteArray(file));
    }

    // 상품 수정
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/product/update")
    public ResponseEntity<?> update(@RequestBody ProductResponse.UpdateDTO updateDTO) {
        ProductResponse.FindByIdDTO updatedProduct = productService.update(updateDTO);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(updatedProduct);
        return ResponseEntity.ok(apiResult);
    }

    // 상품 삭제
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.delete(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(id);
        return ResponseEntity.ok(apiResult);
    }
}