package com.example.funitureOnlineShop.product;

import com.example.funitureOnlineShop.core.utils.ApiUtils;
import com.example.funitureOnlineShop.fileProduct.FileProduct;
import com.example.funitureOnlineShop.fileProduct.FileProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;
    private final FileProductRepository fileProductRepository;
    private final String filePath = "C:/Users/soone/Desktop/FunitureOnlineShopFiles/";

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

    // 상품 찾기
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
        Page<ProductResponse.findByCategoryForAllDTOS> products = productService.findByCategoryId(categoryId, PageRequest.of(page, size));
        ApiUtils.ApiResult<Page<ProductResponse.findByCategoryForAllDTOS>> apiResult = ApiUtils.success(products);
        return ResponseEntity.ok(apiResult);
    }



    /*
    // 상품 수정
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/product/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, ProductResponse.FindByIdDTO findByIdDTO) {
        ProductResponse.FindByIdDTO updatedProduct = productService.update(id, findByIdDTO);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(updatedProduct);
        return ResponseEntity.ok(apiResult);
    }

     */

    // 상품 삭제
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.delete(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(id);
        return ResponseEntity.ok(apiResult);
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {
        FileProduct fileProduct = fileProductRepository.findByProductId(id).get(0);

        // UUID와 originalFileName을 결합하여 파일을 찾습니다.
        String fileName = fileProduct.getUuid() + fileProduct.getFileName();
        File imgFile = new File(filePath + fileName);

        if (!imgFile.exists() || !imgFile.isFile()) {
            throw new FileNotFoundException("File not found: " + imgFile.getAbsolutePath());
        }

        byte[] imageBytes = Files.readAllBytes(imgFile.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/product/images/{id}")
    public ResponseEntity<String> getImages(@PathVariable Long id) {
        List<FileProduct> fileProducts = fileProductRepository.findByProductId(id);

        if (fileProducts.isEmpty()) {
            return new ResponseEntity<>("FileProduct not found for id: " + id, HttpStatus.NOT_FOUND);
        }

        FileProduct fileProduct = fileProducts.get(0);
        String fileName = fileProduct.getUuid() + fileProduct.getFileName();
        File imgFile = new File(filePath + fileName);

        if (!imgFile.exists() || !imgFile.isFile()) {
            return new ResponseEntity<>("File not found: " + imgFile.getAbsolutePath(), HttpStatus.NOT_FOUND);
        }

        byte[] imageBytes;
        try {
            imageBytes = Files.readAllBytes(imgFile.toPath());
        } catch (IOException e) {
            return new ResponseEntity<>("Error reading file: " + imgFile.getAbsolutePath(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
        String imageBase64URI = "data:image/jpeg;base64," + imageBase64;

        return new ResponseEntity<>(imageBase64URI, HttpStatus.OK);
    }


}