package com.example.funitureOnlineShop.product;

import com.example.funitureOnlineShop.category.Category;
import com.example.funitureOnlineShop.category.CategoryRepository;
import com.example.funitureOnlineShop.core.error.exception.Exception400;
import com.example.funitureOnlineShop.core.error.exception.Exception404;
import com.example.funitureOnlineShop.fileProduct.FileProduct;
import com.example.funitureOnlineShop.fileProduct.FileProductRepository;
import com.example.funitureOnlineShop.fileProduct.FileProductResponse;
import com.example.funitureOnlineShop.option.Option;
import com.example.funitureOnlineShop.option.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    /*
    // 상품 수정 서비스
    @Transactional
    public ProductResponse.FindByIdDTO update(Long id, ProductResponse.FindByIdDTO findByIdDTO) {
        Product product = getProduct(id);

        product.update(findByIdDTO);

        productRepository.save(product);

        // 수정된 제품에 해당하는 옵션 리스트를 가져옴
        List<Option> optionList = optionRepository.findByProductId(product.getId());

        // 상품 id에 따른 FileProduct를 찾는 코드
        List<FileProduct> fileProductOpt = fileProductRepository.findByProductId(id);
        FileProductResponse fileProductResponse = null; // 초기값을 null로 설정
        if (fileProductOpt.isPresent()) {
            FileProduct fileProduct = fileProductOpt.get();
            fileProductResponse = new FileProductResponse();
            fileProductResponse.setFilePath(fileProduct.getFilePath());
            fileProductResponse.setFileName(fileProduct.getFileName());
        }// 수정된 제품 정보를 FindByIdDTO 객체로 변환하여 반환
        return new ProductResponse.FindByIdDTO(product, optionList, fileProductResponse);
    }

     */

    // 삭제 서비스
    @Transactional
    public void delete(Long id) {
        getProduct(id);
        productRepository.deleteById(id);
    }

    public Page<ProductResponse.FindByCategoryIdDTO> findProductsByCategory(Long categoryId, int page, int size) {
        Page<Product> productPage = productRepository.findByCategoryId(categoryId, PageRequest.of(page - 1, size));
        return productPage.map(ProductResponse.FindByCategoryIdDTO::new);
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

/*
    public Page<ProductResponse.findByCategoryForAllDTOS> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(product -> new ProductResponse.findByCategoryForAllDTOS(
                        product.getId(),
                        product.getProductName(),
                        product.getPrice()
                ));
    }

 */

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

    public Page<ProductResponse.findByCategoryForAllDTOS> findByCategoryId(Long categoryId, PageRequest pageRequest) {
        // 해당 카테고리에 속한 상품들을 조회
        Page<Product> products = productRepository.findByCategoryId(categoryId, pageRequest);

        // 상품 엔티티를 DTO로 변환
        Page<ProductResponse.findByCategoryForAllDTOS> findByCategoryForAllDTOS = products.map(product -> {
            List<FileProduct> fileProducts = fileProductRepository.findByProductId(product.getId());

            List<FileProductResponse> files = new ArrayList<>();
            if (!fileProducts.isEmpty()) {
                // FileProduct를 FileProductResponse로 변환
                FileProductResponse fileProductResponse = new FileProductResponse(fileProducts.get(0));
                files.add(fileProductResponse);
            }

            return new ProductResponse.findByCategoryForAllDTOS(
                    product.getId(),
                    product.getProductName(),
                    product.getPrice(),
                    files
            );
        });

        return findByCategoryForAllDTOS;
    }

}
