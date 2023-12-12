package com.example.funitureOnlineShop.option;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;

    public Option save(OptionResponse.FindByProductIdDTO optionDTO) {
        Optional<>
    }
}
