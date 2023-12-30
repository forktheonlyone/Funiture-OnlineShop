package com.example.funitureOnlineShop.home;

import com.example.funitureOnlineShop.category.CategoryResponse;
import com.example.funitureOnlineShop.category.CategoryService;
import com.example.funitureOnlineShop.core.error.exception.Exception500;
import com.example.funitureOnlineShop.order.OrderResponse;
import com.example.funitureOnlineShop.order.OrderService;
import com.example.funitureOnlineShop.orderCheck.OrderCheckDto;
import com.example.funitureOnlineShop.product.ProductResponse;
import com.example.funitureOnlineShop.product.ProductService;
import com.example.funitureOnlineShop.comment.ProductCommentResponse;
import com.example.funitureOnlineShop.comment.ProductCommentService;
import com.example.funitureOnlineShop.user.UserResponse;
import com.example.funitureOnlineShop.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ProductService productService;
    private final ProductCommentService productCommentService;
    private final CategoryService categoryService;
    private final OrderService orderService;
    private final UserService userService;

    // 메인 홈페이지
    @GetMapping(value = {"/", ""})
    public String home() {
        return "index";
    }

// ----------< 메뉴 페이지 관련 >-----------------------------

    // 로그인한 회원의 장바구니 확인 페이지
    @GetMapping("/cartPage")
    public String showCart() {
        return "cartPage";
    }

    // 관리자 페이지
    @GetMapping("/adminPage")
    public String adminPage(){
        return "/adminPage";
    }

    // 각 유저의 마이페이지
    @GetMapping("/myPage")
    public String showUserInfo() {
        return "myPage";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/join")
    public String joinForm() {
        return "join";
    }

    // 메뉴 페이지
    @GetMapping("/menu")
    public String menu(Model model) {
        List<CategoryResponse.FindAllDto> categories = categoryService.findAllSuper();
        model.addAttribute("categories", categories);

        List<CategoryResponse.FindAllDto> parents = new ArrayList<>();
        for (CategoryResponse.FindAllDto dto : categories) {
            parents.addAll(categoryService.findAllSon(dto.getId()));
        }
        model.addAttribute("parents", parents);

        List<CategoryResponse.FindAllDto> sons = new ArrayList<>();
        for (CategoryResponse.FindAllDto dto : parents) {
            sons.addAll(categoryService.findAllSon(dto.getId()));
        }
        model.addAttribute("sons", sons);

        return "menu";
    }

    // 주문 내역 페이지
    @GetMapping("/orderDetail")
    public String orderDetail() {
        return "orderCheckPage";
    }

// ----------< 카테고리 페이지 관련 >-----------------------------

    // 카테고리 생성 페이지
    @GetMapping("/categoryCreate")
    public String categoryCreate(Model model) {
        List<CategoryResponse.FindAllDto> categories = categoryService.findAllSuper();
        model.addAttribute("categories", categories);
        return "categoryCreate";
    }

    // 카테고리 수정/삭제 페이지
    @GetMapping("/categoryUpdate")
    public String categoryUdate(Model model) {
        List<CategoryResponse.FindAllDto> dtos = categoryService.findAllSuper();
        model.addAttribute("categories", dtos);

        return "categoryUpdate";
    }

//-------------< 상품 관련 페이지 > -----------

    // 상품 상세 페이지
    @GetMapping("/product/show/{id}")
    public String showProduct(@PathVariable Long id, Model model) {
        ProductResponse.FindByIdDTO dto = productService.findById(id);
        List<ProductCommentResponse.CommentDto> commentDtos = productCommentService.commentList(id);
        model.addAttribute("product", dto);
        model.addAttribute("comments", commentDtos);
        return "productDetail";
    }

    // 카테고리의 상품 목록 페이지
    @GetMapping("/category/show/{categoryId}")
    public String showProductByCategory(@PathVariable Long categoryId, Model model) {
        Page<ProductResponse.FindByCategoryForAllDTOS> products = productService.findByCategoryId(categoryId, PageRequest.of(0, 10));
        model.addAttribute("products", products);
        model.addAttribute("categoryId", categoryId);
        return "productCategoryPage";
    }

    // !< 관리자용 > 상품 신규 생성 페이지
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/product/add")
    public String showProductCreate(Model model) {
        List<CategoryResponse.FindAllDto> categories = categoryService.findAllSuper();
        model.addAttribute("categories", categories);
        return "productCreate";
    }

    // !< 관리자용 > 상품 수정 페이지
    @GetMapping("/product/update/{categoryId}")
    public String showProductUpdate(@PathVariable Long categoryId,
                                    @RequestParam(required = false, defaultValue = "0") int page,
                                    @RequestParam(required = false, defaultValue = "10") int size,
                                    Model model) {
        List<CategoryResponse.FindAllDto> categories = categoryService.findAllSuper();
        Page<ProductResponse.FindByCategoryForAllDTOS> categoryProducts = productService.findByCategoryId(categoryId, PageRequest.of(page, size));
        model.addAttribute("categories", categories);
        model.addAttribute("categoryProducts", categoryProducts);
        return "productUpdate";
    }

//-------------< 결제 관련 페이지 > -----------

    // 결제 페이지
    @GetMapping("/payments/index")
    public String payIndex() {
        return "payIndex";
    }

    // 결제 응답 페이지
    @GetMapping("/payments/response")
    public String payResponse() {
        return "payResponse";
    }

    // 결제 취소 페이지
    @GetMapping("/payments/cancel")
    public String payCancel() {
        return "payCancel";
    }

    // 결제 취소 확인 페이지
    @GetMapping("/payCancelCheck/{id}")
    public String returnOrder(@PathVariable Long id,Model model) {
        model.addAttribute("id", id);
        return "payCancelCheck";
    }

//-------------< 상품 후기 관련 페이지 > -----------

    // 상품 후기 생성 페이지
    @GetMapping("/product_comment/save/{id}")
    public String writeComment(@PathVariable Long id, Model model){
        OrderCheckDto dto = productCommentService.findOrderCheck(id);
        model.addAttribute("orderCheck", dto);
        return "commentCreate";
    }

    // 상품 후기 수정 페이지
    @GetMapping("/product_comment/update/{id}")
    public String updateCommentForm(@PathVariable Long id, Model model){
        ProductCommentResponse.CommentDto dto = productCommentService.findById(id);
        model.addAttribute("comment", dto);
        return "commentUpdate";
    }
}
