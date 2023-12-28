package com.example.funitureOnlineShop.product;

import com.example.funitureOnlineShop.category.Category;
import com.example.funitureOnlineShop.category.CategoryRepository;
import com.example.funitureOnlineShop.core.error.exception.Exception400;
import com.example.funitureOnlineShop.core.error.exception.Exception404;
import com.example.funitureOnlineShop.core.error.exception.Exception500;
import com.example.funitureOnlineShop.core.utils.ApiUtils;
import com.example.funitureOnlineShop.fileProduct.FileProduct;
import com.example.funitureOnlineShop.fileProduct.FileProductRepository;
import com.example.funitureOnlineShop.fileProduct.FileProductResponse;
import com.example.funitureOnlineShop.option.Option;
import com.example.funitureOnlineShop.option.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;
    private final FileProductRepository fileProductRepository;
    private final CategoryRepository categoryRepository;

    // ------------<파일경로>-------------
    // !!!!!!!!!! 꼭 반드시 테스트시 파일 경로 특히 사용자명 확인할것 !!!!!!!!!!
    private final String filePath = "C:/Users/soone/Desktop/FunitureOnlineShopFiles/";

    @Transactional
    public Product save(ProductResponse.SaveByIdDTO saveByIdDTO, MultipartFile[] files) throws IOException {
        // categoryId를 사용하여 Category 엔티티를 찾음
        Category category = categoryRepository.findById(saveByIdDTO.getCategoryId())
                .orElseThrow( () -> new Exception404("해당 카테고리가 존재하지 않습니다."));

        // 상품 엔티티 생성 및 카테고리 할당
        Product productEntity = Product.builder()
                .productName(saveByIdDTO.getProductName())
                .description(saveByIdDTO.getDescription())
                .price(saveByIdDTO.getPrice())
                .deliveryFee(saveByIdDTO.getDeliveryFee())
                .category(category) // 찾은 Category 설정
                .build();

        // 상품 엔티티 저장
        Product savedProduct = productRepository.save(productEntity);

        // 파일 처리 로직
        if (files != null && files.length > 0 && !files[0].isEmpty()) {
            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;

                String originalFileName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String formatType = originalFileName.substring(originalFileName.lastIndexOf("."));

                if (!formatType.equals(".jpg") && !formatType.equals(".jpeg") && !formatType.equals(".png")) {
                    throw new Exception400("파일 확장자는 .jpg, .jpeg, .png 만 가능합니다.");
                }

                Path uploadPath = Paths.get(filePath);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String savedFileName = uuid + originalFileName;
                String savedFilePath = Paths.get(filePath, savedFileName).toString();
                file.transferTo(new File(savedFilePath));

                FileProduct fileProduct = FileProduct.builder()
                        .filePath(savedFilePath)
                        .fileName(originalFileName)
                        .uuid(uuid)
                        .fileType(formatType)
                        .fileSize(file.getSize())
                        .product(savedProduct)
                        .build();

                fileProductRepository.save(fileProduct);
            }
        }

        return savedProduct;
    }


    // 상품 수정 서비스
    @Transactional
    public ProductResponse.FindByIdDTO update(Long id, ProductResponse.UpdateDTO updateDTO) {
        Product product = getProduct(id);

        product.update(updateDTO);

        productRepository.save(product);

        // 수정된 제품에 해당하는 옵션 리스트를 가져옴
        List<Option> optionList = optionRepository.findByProductId(product.getId());

        // 상품 id에 따른 FileProduct를 찾는 코드
        List<FileProduct> fileProductList = fileProductRepository.findByProductId(id);
        List<FileProductResponse> fileProductResponseList = new ArrayList<>(); // FileProductResponse 리스트 생성
        for (FileProduct fileProduct : fileProductList) {
            FileProductResponse fileProductResponse = new FileProductResponse();
            fileProductResponse.setFilePath(fileProduct.getFilePath());
            fileProductResponse.setFileName(fileProduct.getFileName());
            fileProductResponseList.add(fileProductResponse);
        }

        // 수정된 제품 정보를 FindByIdDTO 객체로 변환하여 반환
        return new ProductResponse.FindByIdDTO(product, optionList, fileProductResponseList);
    }



    // 삭제 서비스
    @Transactional
    public void delete(Long id) {
        getProduct(id);
        productRepository.deleteById(id);
    }

    // 상품 전체 찾기 서비스
    public List<Product> findAll() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new Exception404("등록된 상품이 존재하지 않습니다.");
        }
        return products;
    }

    // 상품 id 찾는 로직
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 상품을 찾을 수 없습니다."));
    }

    // ID로 특정 상품 하나 찾기
    @Transactional
    public ProductResponse.FindByIdDTO findById(Long id) {
        Product product = getProduct(id);
        List<Option> optionList = optionRepository.findByProductId(product.getId());

        // 상품 id에 따른 FileProduct들을 찾는 코드
        List<FileProduct> fileProductList = fileProductRepository.findByProductId(id);
        List<FileProductResponse> fileProductResponseList = new ArrayList<>(); // 리스트 초기화

        // 각 FileProduct에 대해 FileProductResponse를 생성하고 리스트에 추가합니다.
        for (FileProduct fileProduct : fileProductList) {
            FileProductResponse fileProductResponse = new FileProductResponse();
            String fullFilePath = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/product/")
                    .path(fileProduct.getProduct().getId().toString())
                    .path("/image")
                    .toUriString();
            fileProductResponse.setFilePath(fullFilePath);
            fileProductResponse.setFileName(fileProduct.getFileName());

            fileProductResponseList.add(fileProductResponse); // 리스트에 추가
        }
        return new ProductResponse.FindByIdDTO(product, optionList, fileProductResponseList);
    }

    @Transactional
    public Page<ProductResponse.FindByCategoryForAllDTOS> findByCategoryId(Long categoryId, PageRequest pageRequest) {
        Page<Product> products = productRepository.findByCategoryId(categoryId, pageRequest);

        Page<ProductResponse.FindByCategoryForAllDTOS> findByCategoryForAllDTOS = products.map(product -> {
            List<FileProduct> fileProducts = fileProductRepository.findByProductId(product.getId());

            FileProductResponse file = null;
            if (!fileProducts.isEmpty()) {
                // 첫 번째 파일 프로덕트만 가져와서 DTO에 설정
                file = new FileProductResponse(fileProducts.get(0));
            }

            // DTO 생성자에 파일을 단일 객체로 전달
            return new ProductResponse.FindByCategoryForAllDTOS(
                    product.getId(),
                    product.getProductName(),
                    product.getPrice(),
                    file  // 변경된 부분: 리스트 대신 단일 객체
            );
        });

        return findByCategoryForAllDTOS;
    }
}

