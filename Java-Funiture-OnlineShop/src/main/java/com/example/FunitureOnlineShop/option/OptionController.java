package com.example.FunitureOnlineShop.option;

import com.example.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/options")
public class OptionController {
    private final OptionService optionService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody @Valid OptionResponse.FindByProductIdDTO optionDTO) {

        Option option = optionService.save(optionDTO);
        return ResponseEntity.ok(option);
    }

    @GetMapping("/products/{id}/options")
    public ResponseEntity<?> findByid(@PathVariable Long id){
        List<OptionResponse.FindByProductIdDTO> optionResponses = optionService.findByProductId(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(optionResponses);
        return ResponseEntity.ok(apiResult);
    }
}
