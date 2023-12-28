package com.example.funitureOnlineShop.cart;

import com.example.funitureOnlineShop.core.security.CustomUserDetails;
import com.example.funitureOnlineShop.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> addCart(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                     @RequestBody @Valid List<CartRequest.SaveDTO> saveDTOS,
                                     Error error){
        cartService.addCartList(saveDTOS,customUserDetails.getUser());

        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(null);
        return ResponseEntity.ok(apiResult);
    }

    @GetMapping("/carts")
    public ResponseEntity<?> carts(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                   Error error){
        CartResponse.FindAllDto findAllDTO = cartService.findAll();
        ApiUtils.ApiResult<?> apiResult= ApiUtils.success(findAllDTO);
        return ResponseEntity.ok(apiResult);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCart(
            @RequestBody @Valid List<CartRequest.UpdateDTO> requestDTOS,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        CartResponse.UpdateDTO response = cartService.update(requestDTOS, customUserDetails.getUser());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCartList(
            @RequestBody @Valid List<CartResponse.DeleteDTO> deleteDTO,
            @AuthenticationPrincipal CustomUserDetails customUserDetails, // 유저 정보확인
            Error error) { // 인증받은 애들만 메소드에 접근할 수 있음
        cartService.deleteCartList(deleteDTO, customUserDetails.getUser().getId());

        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(null);
        return ResponseEntity.ok(apiResult);
    }

    @GetMapping("/myCart")
    public ResponseEntity<?> showCart(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        CartResponse.FindAllDto dto = cartService.findAllByUserId(customUserDetails.getUser().getId());

        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(dto);
        return ResponseEntity.ok(apiResult);
    }
}
