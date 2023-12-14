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
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private OptionRepository optionRepository;
    private FileProductRepository fileProductRepository;

    // ------------<파일경로>-------------
    // !!!!!!!!!! 꼭 반드시 테스트시 파일 경로 특히 사용자명 확인할것 !!!!!!!!!!
    private final String filePath = "C:/Users/soone/Desktop/FunitureOnlineShopFiles/";

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
                if (file.isEmpty()){
                    continue;
                }

                // ** 파일명 추출
                String originalFileName = file.getOriginalFilename();

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
                String path = filePath + originalFileName;

                // 경로에 파일을 저장 (DB의 저장이 아님)
                file.transferTo(new File(path));

                FileProduct fileProduct = FileProduct.builder()
                        .filePath(filePath)
                        .fileName(originalFileName)
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

    public ProductResponse.FindByIdDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new Exception404("해당 상품을 찾을 수 없습니다. : " + id));

        List<Option> optionList = optionRepository.findByProductId(product.getId());

        return new ProductResponse.FindByIdDTO(product, optionList);
    }

    @Transactional
    public ProductResponse.FindByIdDTO update(Long id, ProductResponse.FindByIdDTO findByIdDTO) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new Exception404("해당 상품을 찾을 수 없습니다. : " + id));

        product.update(findByIdDTO);

        productRepository.save(product);

        // 수정된 제품에 해당하는 옵션 리스트를 가져옴
        List<Option> optionList = optionRepository.findByProductId(product.getId());

        // 수정된 제품 정보를 FindByIdDTO 객체로 변환하여 반환
        return new ProductResponse.FindByIdDTO(product, optionList);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
