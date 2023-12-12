package com.example.funitureOnlineShop.option;

import com.example.funitureOnlineShop.core.utils.ApiUtils;
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
    // ** 옵션 저장
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody @Valid OptionResponse.FindByProductIdDTO requestDTO) {

        Option option = optionService.save(requestDTO);
        return ResponseEntity.ok(option);
    }
    // ** 개별 옵션 검색
    @GetMapping("/products/{id}")
    public ResponseEntity<?> findByid(@PathVariable Long id){
        List<OptionResponse.FindByProductIdDTO> optionResponses = optionService.findByProductId(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(optionResponses);
        return ResponseEntity.ok(apiResult);
    }
    // ** 전체 옵션 검색
    @GetMapping("/products")
    public ResponseEntity<?> findAll(){
        List<OptionResponse.FindAllDTO> optionResponses =
                optionService.findAll();
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(optionResponses);
        return ResponseEntity.ok(apiResult);
    }
    // ** 옵션 변경
    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @ModelAttribute OptionResponse.FindAllDTO optionDto) {
        optionService.update(optionDto);

        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(optionDto);
        return ResponseEntity.ok(apiResult);
    }
    // ** 옵션 삭제
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        optionService.delete(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(id);
        return ResponseEntity.ok(apiResult);
    }


}
