package com.example.FunitureOnlineShop.cart;


import com.example.core.security.CustomUserDetails;
import com.example.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart/add")
    public ResponseEntity<?> addCart(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                     @RequestBody @Valid List<CartRequest.SaveDTO> saveDTOS,
                                     Error error){
        cartService.addCart(saveDTOS,customUserDetails.getUser());

        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(null);
        return ResponseEntity.ok(apiResult);
    }

    @GetMapping("/carts")
    public ResponseEntity<?> carts(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                   Error error){
        CartResponse.FindAllDTO findAllDTO = cartService.findAll();
        ApiUtils.ApiResult<?> apiResult= ApiUtils.success(findAllDTO);
        return ResponseEntity.ok(apiResult);
    }

    @PutMapping
    public ResponseEntity<?> updateCart(
            @RequestBody @Valid List<CartRequest.updateDTO> requestDTOS,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        CartResponse.UpdateDTO response = cartService.update(requestDTOS, customUserDetails.getUser());
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<Void> deleteFromCart(
            @PathVariable Long cartId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        cartService.deleteFromCart(cartId, customUserDetails.getUser());
        return ResponseEntity.noContent().build();
    }
}
