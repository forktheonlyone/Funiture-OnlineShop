package com.example.FunitureOnlineShop.option;

import com.example.FunitureOnlineShop.product.Product;
import com.example.FunitureOnlineShop.product.ProductRepository;
import com.example.core.error.exception.Exception500;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;
    private final ProductRepository productRepository;

    // ** 상품ID를 기반으로 옵션을 저장, 없을 시 예외처리
    @Transactional
    public Option save(OptionResponse.FindByProductIdDTO optionDTO) {
        Optional<Product> option = productRepository.findById(optionDTO.getProductId());
        Product product = option.orElseThrow(() ->
                new Exception500("상품을 찾을 수 없습니다. 상품 ID: " + optionDTO.getProductId()));

        Option optionEntity = optionDTO.toEntity();
        optionEntity.toUpdate(product);

        return optionRepository.save(optionEntity);
    }


    public List<OptionResponse.FindByProductIdDTO> findByProductId(Long id) {
        List<Option> optionList = optionRepository.findByProductId(id);
        List<OptionResponse.FindByProductIdDTO> dtos =

    }
}
