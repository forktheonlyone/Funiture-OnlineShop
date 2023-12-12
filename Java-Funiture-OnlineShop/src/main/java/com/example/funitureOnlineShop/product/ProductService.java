package com.example.funitureOnlineShop.product;

import com.example.funitureOnlineShop.core.error.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private OptionRepository optionRepository;

    @Transactional
    public void save(ProductResponse.FindByCategoryIdDTO productResponseFind) {
        Category category = categoryRepository.findById(productResponseFind.getCategoryId())
                .orElseThrow( () -> new Exception404(
                        "해당 카테고리를 찾을 수 없습니다. : " + productResponseFind.getCategoryId()));
        Product product = productResponseFind.toEntity(category);
        productRepository.save(product);
    }

    public ProductResponse.FindByIdDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new Exception404("해당 상품을 찾을 수 없습니다. : " + id));

        List<Option> optionList = optionRepository.findByProductId(product.getId());

        return new ProductResponse.FindByIdDTO(product, optionList);
    }
}
