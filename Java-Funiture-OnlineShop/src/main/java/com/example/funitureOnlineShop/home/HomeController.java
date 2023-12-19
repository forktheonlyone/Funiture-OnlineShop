package com.example.funitureOnlineShop.home;

import com.example.funitureOnlineShop.core.security.CustomUserDetails;
import com.example.funitureOnlineShop.orderCheck.OrderCheck;
import com.example.funitureOnlineShop.orderCheck.OrderCheckDto;
import com.example.funitureOnlineShop.product.ProductResponse;
import com.example.funitureOnlineShop.product.ProductService;
import com.example.funitureOnlineShop.productComment.ProductComment;
import com.example.funitureOnlineShop.productComment.ProductCommentResponse;
import com.example.funitureOnlineShop.productComment.ProductCommentService;
import com.example.funitureOnlineShop.user.UserResponse;
import com.example.funitureOnlineShop.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ProductService productService;
    private final ProductCommentService productCommentService;
    private final UserService userService;

    // 메인 홈페이지
    @GetMapping(value = {"/", ""})
    public String home() {
        return "index";
    }

    // !!----------< 상품 관련 페이지 > -----------

    // 전체 상품 확인
    @GetMapping("/productAll")
    public String showAllProduct()    {
        return "allProductPage";
    }

    // 카테고리 클릭시 특정 카테고리 상품 확인
    @GetMapping("/category/{id}")
    public String showProductByCategory(@PathVariable Long id) {
        return "productCategoryPage";
    }

    // !!----------< 장바구니 관련 페이지 > -----------

    // 로그인한 사용자의 장바구니 확인
    @GetMapping("/cart")
    public String showCart() {
        return "cartPage";
    }


    // !!----------< 결제 관련 페이지 > -----------
    // 결제 상세 페이지
    @GetMapping("/order")
    public String showOrder() {
        return "orderPage";
    }

    // !!----------< 고객센터 관련 페이지 > -----------
    // 공지사항 페이지
    @GetMapping("/notice")
    public String showNotice() {
        return "noticePage";
    }

    // !!----------< 유저 관련 페이지 > -----------
    // 각 유저의 마이페이지
    @GetMapping("/myPage")
    public String showUserInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        UserResponse.UserDTO userDTO = userService.getUserInfo(customUserDetails.getUser().getId());
        model.addAttribute("user", userDTO);
        return "myPage";
    }

    @GetMapping("/product_comment/update/{id}")
    public String updateCommentForm(@PathVariable Long id, Model model){
        ProductCommentResponse.CommentDto dto = productCommentService.findById(id);
        model.addAttribute("comment", dto);
        return "commentUpdate";
    }

    @GetMapping("/product_comment/save/{id}")
    public String writeComment(@PathVariable Long id, Model model){
        OrderCheckDto orderCheckDto = productCommentService.findOrderCheck(id);
        model.addAttribute("orderCheck", orderCheckDto);
        return "productReview";
    }

    @GetMapping("/board/create")
    public String boardCreateForm() {
        return "createboard";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/product/show/{id}")
    public String showProduct(@PathVariable Long id, Model model) {
        ProductResponse.FindByIdDTO findByIdDTO = productService.findById(id);
        model.addAttribute("product", findByIdDTO);
        return "productPage";
    }

    @GetMapping("/payments/cancel")
    public String payCancel() {
        return "paycancel";
    }

    @GetMapping("/payments/index")
    public String payIndex() {
        return "payindex";
    }

    @GetMapping("/payments/response")
    public String payResponse() {
        return "payresponse";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/product/add")
    public String showProductCreate() {
        return "productCreate";
    }
}
