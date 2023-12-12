package com.example.FunitureOnlineShop.option;

import com.example.FunitureOnlineShop.product.Product;
import com.example.FunitureOnlineShop.product.ProductRepository;
import com.example.core.error.exception.Exception500;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // ** 개별 옵션 검색, 없을 시 예외처리
    public List<OptionResponse.FindByProductIdDTO> findByProductId(Long id) {
        List<Option> optionList = optionRepository.findByProductId(id);
        if (optionList.isEmpty()) {
            throw new Exception500("상품에 해당하는 옵션이 없습니다. 상품 ID: " + id);
        }
        List<OptionResponse.FindByProductIdDTO> dtos =
                optionList.stream().map(OptionResponse.FindByProductIdDTO::new)
                        .collect(Collectors.toList());
        return dtos;
    }
    // ** 전체 옵션 검색, 없을 시 예외처리
    public List<OptionResponse.FindAllDTO> findAll(Long id){
        List<Option> optionList = optionRepository.findAll();
        if (optionList.isEmpty()) {
            throw new Exception500("옵션이 없습니다.");
        }
        List<OptionResponse.FindAllDTO> dtos =
                optionList.stream().map(OptionResponse.FindAllDTO::new)
                        .collect(Collectors.toList());
        return dtos;
    }
}
