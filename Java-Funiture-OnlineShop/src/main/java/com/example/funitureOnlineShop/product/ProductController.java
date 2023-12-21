package com.example.funitureOnlineShop.product;

import com.example.funitureOnlineShop.core.utils.ApiUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    // 상품 생성
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/product/save")
    public ResponseEntity<?> save(@RequestParam("productData") String productDataJson,
                                  @RequestParam("files") MultipartFile[] files) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductResponse.SaveByIdDTO productResponseFind = objectMapper.readValue(productDataJson, ProductResponse.SaveByIdDTO.class);

            logger.info("Product save requested with data: {}", productResponseFind);

            productService.save(productResponseFind, files);
            ApiUtils.ApiResult<?> apiResult = ApiUtils.success(productResponseFind);
            return ResponseEntity.ok(apiResult);
        } catch (IOException e) {
            logger.error("Error parsing product data: ", e);
            return ResponseEntity.badRequest().body("Error parsing product data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error saving product: ", e);
            return ResponseEntity.internalServerError().body("Error saving product: " + e.getMessage());
        }
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