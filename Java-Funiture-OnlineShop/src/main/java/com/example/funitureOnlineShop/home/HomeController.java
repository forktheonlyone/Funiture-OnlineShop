package com.example.funitureOnlineShop.home;

import com.example.funitureOnlineShop.cart.CartResponse;
import com.example.funitureOnlineShop.cart.CartService;
import com.example.funitureOnlineShop.core.error.exception.Exception404;
import com.example.funitureOnlineShop.core.security.CustomUserDetails;
import com.example.funitureOnlineShop.product.Product;
import com.example.funitureOnlineShop.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ProductService productService;
    private final CartService cartService;

    // 메인 홈페이지
    @GetMapping(value = {"/", ""})
    public String home() {
        return "index";
    }

    // !!----------< 상품 관련 페이지 > -----------

    // 전체 상품 확인
    @GetMapping("/product")
    public String showAllProduct(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "allProductPage";
    }

    // 카테고리 클릭시 특정 카테고리 상품 확인
    @GetMapping("/category/{id}")
    public String showProductByCategory(@PathVariable Long id, Model model) {
        List<Product> products = productService.findByCategory(id);
        model.addAttribute("products", products);
        return "productCategoryPage";
    }

    // !!----------< 장바구니 관련 페이지 > -----------

    @GetMapping("/cart")
    public String showCart(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        try{
            CartResponse.FindAllDTO cart = cartService.findAll(customUserDetails.getUser());
            model.addAttribute("cart", cart);
        }catch (Exception e){
            model.addAttribute("message", "장바구니가 비어 있습니다.");
        }
        return "cartPage";
    }
}
