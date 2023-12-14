package com.example.funitureOnlineShop.home;

import com.example.funitureOnlineShop.Board.BoardDTO;
import com.example.funitureOnlineShop.Board.BoardService;
import com.example.funitureOnlineShop.cart.CartResponse;
import com.example.funitureOnlineShop.cart.CartService;
import com.example.funitureOnlineShop.core.error.exception.Exception400;
import com.example.funitureOnlineShop.core.error.exception.Exception401;
import com.example.funitureOnlineShop.core.error.exception.Exception404;
import com.example.funitureOnlineShop.core.error.exception.Exception500;
import com.example.funitureOnlineShop.core.security.CustomUserDetails;
import com.example.funitureOnlineShop.product.Product;
import com.example.funitureOnlineShop.product.ProductService;
import com.example.funitureOnlineShop.user.User;
import com.example.funitureOnlineShop.user.UserRequest;
import com.example.funitureOnlineShop.user.UserResponse;
import com.example.funitureOnlineShop.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private final BoardService boardService;
    private final UserService userService;

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

    // 로그인한 사용자의 장바구니 확인
    @GetMapping("/cart")
    public String showCart(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if (customUserDetails == null) {
            throw new Exception401("로그인이 필요한 서비스입니다.");
        }
        try {
            CartResponse.FindAllDTO cart = cartService.findAllByUserId(customUserDetails.getUser().getId());
            if (cart == null) {
                throw new Exception404("장바구니가 비어 있습니다.");
            }
            model.addAttribute("cart", cart);
        } catch (Exception e) {
            throw new Exception500("서버 에러입니다.");
        }
        return "cartPage";
    }


    // !!----------< 결제 관련 페이지 > -----------

    // !!!!!!!!!!! 기능 구현 필요 !!!!!!!!!!!!!
    // 결제 상세 페이지
    @GetMapping("/order")
    public String showOrder(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        return "";
    }

    // !!----------< 고객센터 관련 페이지 > -----------

    // !!!!!!!!!!! Qna와 Notice 분리 후 수정 필요 !!!!!!!!!!!!!
    // 자주 묻는 질문 페이지
    @GetMapping("/qna")
    public String showQna(Model model, @PageableDefault(page = 1)Pageable pageable) {
        if (pageable.getPageNumber() < 1) {
            throw new Exception400("유효하지 않은 페이지 번호입니다.");
        }
        try {
            Page<BoardDTO> page = boardService.paging(pageable);
            int blockLimit = 3;
            int startPage = (int) (Math.ceil((double) pageable.getPageNumber() / blockLimit) - 1) * blockLimit + 1;
            int endPage = ((startPage + blockLimit - 1) < page.getTotalPages()) ? (startPage + blockLimit - 1) : page.getTotalPages();

            model.addAttribute("boardList", page.getContent());
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
        } catch (Exception e){
            throw new Exception500("서버 에러입니다." + e.getMessage());
        }
        return "qnaPage";
    }

    // !!!!!!!!!!! Qna와 Notice 분리 후 수정 필요 !!!!!!!!!!!!!
    // 공지사항 페이지
    @GetMapping("/notice")
    public String showNotice(Model model, @PageableDefault(page = 1)Pageable pageable) {
        if (pageable.getPageNumber() < 1) {
            throw new Exception400("유효하지 않은 페이지 번호입니다.");
        }
        try {
        Page<BoardDTO> page = boardService.paging(pageable);
        int blockLimit = 3;
        int startPage = (int) (Math.ceil((double) pageable.getPageNumber() / blockLimit) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < page.getTotalPages()) ? (startPage + blockLimit - 1) : page.getTotalPages();

        model.addAttribute("boardList", page.getContent());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        } catch (Exception e){
            throw new Exception500("서버 에러입니다." + e.getMessage());
        }
        return "noticePage";
    }

    // !!----------< 유저 관련 페이지 > -----------
    // 각 유저의 마이페이지
    @GetMapping("/myPage")
    public String showUserInfo(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if (customUserDetails == null) {
            throw new Exception401("로그인이 필요한 서비스입니다.");
        }
        try {
            User user = customUserDetails.getUser();
            UserRequest.UserInfoDto userInfoDto = new UserRequest.UserInfoDto();
            userInfoDto.setEmail(user.getEmail());
            userInfoDto.setUsername(user.getUsername());
            UserResponse.UserDTO userDTO = userService.getUserInfo(userInfoDto);
            model.addAttribute("userInfo", userDTO);
        } catch (Exception e) {
            throw new Exception500("서버 에러입니다." + e.getMessage());
        }
        return "myPage";
    }
}
