package com.example.funitureOnlineShop.home;

import com.example.funitureOnlineShop.Board.BoardDTO;
import com.example.funitureOnlineShop.Board.BoardService;
import com.example.funitureOnlineShop.category.CategoryResponse;
import com.example.funitureOnlineShop.category.CategoryService;
import com.example.funitureOnlineShop.orderCheck.OrderCheckDto;
import com.example.funitureOnlineShop.product.ProductResponse;
import com.example.funitureOnlineShop.product.ProductService;
import com.example.funitureOnlineShop.productComment.ProductCommentResponse;
import com.example.funitureOnlineShop.productComment.ProductCommentService;
import com.example.funitureOnlineShop.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ProductService productService;
    private final ProductCommentService productCommentService;
    private final BoardService boardService;
    private final CategoryService categoryService;
    private final UserService userService;

    // 메인 홈페이지
    @GetMapping(value = {"/", ""})
    public String home() {
        return "index";
    }
    // 카테고리 생성
    @GetMapping("/categorycreate")
    public String categoryCreate(Model model) {
        List<CategoryResponse.FindAllDto> categories = categoryService.findAllSuper();
        model.addAttribute("categories", categories);
        return "categorycreate";
    }

    @GetMapping("/category/updateForm")
    public String categoryUdate(Model model) {
        List<CategoryResponse.FindAllDto> dtos = categoryService.findAllSuper();
        model.addAttribute("categories", dtos);

        return "categoryUpdate";
    }



    // !!----------< 게시판 관련 페이지 > -----------
    @GetMapping("/notice")
    public String noticePaging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDTO> boards = boardService.paging(pageable);

        int blockLimit = 3;
        int startPage = (int) Math.ceil((double) pageable.getPageNumber() / blockLimit - 1) * blockLimit + 1;
        int endPage = (startPage + blockLimit - 1) < boards.getTotalPages() ? (startPage + blockLimit - 1) : boards.getTotalPages();

        model.addAttribute("boardList", boards);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "noticePage";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO,
                       @RequestParam MultipartFile[] files) throws IOException {
        boardDTO.setCreateTime(LocalDateTime.now());
        boardService.save(boardDTO, files);
        System.out.println("○ 게시글 저장 ○");

        return "redirect:/board/";
    }

    @GetMapping("/board/create")
    public String boardCreateForm() {
        return "createboard";
    }

    // !!----------< 상품 관련 페이지 > -----------

    // 전체 상품 확인
    @GetMapping("/productAll")
    public String showAllProduct()    {
        return "allProductPage";
    }

    // 카테고리 클릭시 특정 카테고리 상품 확인
    @GetMapping("/category/show/{id}")
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


    // !!----------< 유저 관련 페이지 > -----------
    // 각 유저의 마이페이지
    @GetMapping("/myPage")
    public String showUserInfo() {
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

    @GetMapping("/product/add")
    public String showProductCreate() {
        return "productCreate";
    }

    @GetMapping("/admin/product/update")
    public String showProductUpdate() {
        return "productUpdate";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "join";
    }

    @GetMapping("/adminPage")
    public String adminPage(){
        return "redirect:adminPage";
    }
}
