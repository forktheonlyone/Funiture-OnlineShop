package com.example.funitureOnlineShop.option;

import com.example.funitureOnlineShop.core.error.exception.Exception500;
import com.example.funitureOnlineShop.core.security.CustomUserDetails;
import com.example.funitureOnlineShop.core.utils.ApiUtils;
import com.example.funitureOnlineShop.orderCheck.OrderCheck;
import com.example.funitureOnlineShop.orderCheck.OrderCheckDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/options")
public class OptionController {
    private final OptionService optionService;
    // ** 옵션 저장
    @PostMapping("/products/{productId}/save")
    public ResponseEntity<?> save(@PathVariable Long productId, @RequestBody @Valid OptionResponse.FindByProductIdDTO requestDTO) {
        // URL 경로에서 추출한 productId를 DTO에 설정
        requestDTO.setProductId(productId);

        System.out.println(requestDTO.getOptionName());
        System.out.println(requestDTO.getPrice());
        System.out.println(requestDTO.getStockQuantity());

        optionService.save(requestDTO);

        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(null);
        return ResponseEntity.ok(apiResult);
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

    // ** 옵션 수량 업데이트
    @PostMapping("/updateStock/{id}")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @RequestParam Long newStockQuantity) {
        optionService.updateStock(id, newStockQuantity);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success("옵션 수량이 업데이트되었습니다.");
        return ResponseEntity.ok(apiResult);
    }

    @GetMapping("/ordercheck/{id}")
    public ResponseEntity<?> getOrderDetail(@PathVariable Long id,
                                            @AuthenticationPrincipal CustomUserDetails customUserDetails){
        OrderCheckDto orderCheckDtos = optionService.findOrderChecks(id);

        return ResponseEntity.ok(orderCheckDtos);
    }

    // ** 주문 취소 시 재고 복구
    @PostMapping("/restoreStock/{id}")
    public ResponseEntity<?> restoreStockOnOrderCancel(@PathVariable Long id) {
        OrderCheckDto orderCheckDtos = optionService.findOrderChecks(id);
        try {
            optionService.restoreStockOnOrderCancel(orderCheckDtos.toEntity());
            ApiUtils.ApiResult<?> apiResult = ApiUtils.success("주문 취소 시 재고가 복구되었습니다.");
            return ResponseEntity.ok(apiResult);
        } catch (Exception e) {
            e.printStackTrace();
            ApiUtils.ApiResult<?> apiResult = ApiUtils.success(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResult);
        }
    }
}
