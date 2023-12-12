package com.example.FunitureOnlineShop.option;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/options")
public class OptionController {
    private final OptionService optionService;

    @PostMapping("/option/save")
    public ResponseEntity save(@RequestBody @Valid OptionResponse.FindByProductIdDTO optionDTO) {

        Option option = optionService.save(optionDTO);

        return ResponseEntity.ok(option);
    }
}
