package com.example.funitureOnlineShop.product;

import com.example.funitureOnlineShop.category.Category;
import com.example.funitureOnlineShop.category.CategoryRepository;
import com.example.funitureOnlineShop.core.error.exception.Exception400;
import com.example.funitureOnlineShop.core.error.exception.Exception404;
import com.example.funitureOnlineShop.fileProduct.FileProduct;
import com.example.funitureOnlineShop.fileProduct.FileProductRepository;
import com.example.funitureOnlineShop.option.Option;
import com.example.funitureOnlineShop.option.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OptionRepository optionRepository;
    private final FileProductRepository fileProductRepository;
    private final ModelMapper modelMapper;

    // ------------<파일경로>-------------
    // !!!!!!!!!! 꼭 반드시 테스트시 파일 경로 특히 사용자명 확인할것 !!!!!!!!!!
    private final String filePath = "C:/Users/soone/Desktop/FunitureOnlineShopFiles/";

    // 상품 저장 서비스
    @Transactional
    public Product save(ProductResponse.SaveByIdDTO saveByIdDTO, MultipartFile[] files) throws IOException {
        Optional<Category> optionalCategory =
                categoryRepository.findById(saveByIdDTO.getCategoryId());

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            Product productEntity = saveByIdDTO.toEntity();
            productEntity.assignToCategory(category);

            Long id = productRepository.save(saveByIdDTO.toEntity()).getId();
            Product product = productRepository.findById(id).get();

            // 업로드시 파일이 없을 경우의 예외처리
            if (files == null || files.length == 0 || files[0].isEmpty()) {
                return product;
            }

            // 파일 정보 저장
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }

                // ** 파일명 추출
                String originalFileName = file.getOriginalFilename();

                // ** uuid 생성
                String uuid = UUID.randomUUID().toString();

                // ** 확장자 추출
                String formatType = originalFileName.substring(
                        originalFileName.lastIndexOf("."));

                // ** 확장자 검사
                if (!formatType.equals(".jpg") && !formatType.equals(".jpeg") && !formatType.equals(".png")) {
                    throw new Exception400("파일 확장자는 .jpg, .jpeg, .png 만 가능합니다.");
                }

                Path uploadPath = Paths.get(filePath);

                // 경로가 없다면 `filePath`의 경로로 경로 생성.
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // 경로 지정
                String path = filePath + uuid + originalFileName;

                // 경로에 파일을 저장 (DB의 저장이 아님)
                file.transferTo(new File(path));

                FileProduct fileProduct = FileProduct.builder()
                        .filePath(filePath)
                        .fileName(originalFileName)
                        .uuid(uuid)
                        .fileType(formatType)
                        .fileSize(file.getSize())
                        .product(product)
                        .build();

                fileProductRepository.save(fileProduct);
            }
            return product;
        }
        return null;
    }

    // 상품 수정 서비스
    @Transactional
    public ProductResponse.FindByIdDTO update(Long id, ProductResponse.FindByIdDTO findByIdDTO) {
        Product product = getProduct(id);

        product.update(findByIdDTO);

        productRepository.save(product);

        // 수정된 제품에 해당하는 옵션 리스트를 가져옴
        List<Option> optionList = optionRepository.findByProductId(product.getId());

        // 수정된 제품 정보를 FindByIdDTO 객체로 변환하여 반환
        return new ProductResponse.FindByIdDTO(product, optionList);
    }

    // ID로 상품검색 서비스
    public ProductResponse.FindByIdDTO findById(Long id) {
        Product product = getProduct(id);

        List<Option> optionList = optionRepository.findByProductId(product.getId());

        return new ProductResponse.FindByIdDTO(product, optionList);
    }

    // 삭제 서비스
    @Transactional
    public void delete(Long id) {
        getProduct(id);
        productRepository.deleteById(id);
    }

    public Page<ProductResponse.FindByIdDTO> findProductsByCategory(Long categoryId, int page, int size) {
        Page<Product> productPage = productRepository.findByCategoryId(categoryId, PageRequest.of(page - 1, size));
        return productPage.map(product -> modelMapper.map(product, ProductResponse.FindByIdDTO.class));
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


    @Transactional(readOnly = true)
    public Page<ProductResponse.FindAllDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(product -> new ProductResponse.FindAllDTO(
                        product.getId(),
                        product.getProductName(),
                        product.getImage(),
                        product.getPrice(),
                        product.getOnSale()
                ));
    }
}
