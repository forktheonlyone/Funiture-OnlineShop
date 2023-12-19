package com.example.funitureOnlineShop.productComment;

import com.example.funitureOnlineShop.core.security.CustomUserDetails;
import com.example.funitureOnlineShop.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product_comment")
public class ProductCommentController {

    private final ProductCommentService productCommentService;

    // 상품 후기 저장
    @PostMapping("/save")
    public ResponseEntity<?> save(@ModelAttribute ProductCommentRequest.SaveDto saveDto,
                                  @RequestParam MultipartFile[] files) throws IOException {
        ProductComment comment = productCommentService.save(saveDto, files);

        if (comment != null) {
            return ResponseEntity.ok(ApiUtils.success(saveDto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 상품 후기 탐색
    @GetMapping("/comments/{id}")
    public ResponseEntity<?> commentList(@PathVariable Long id){
        List<ProductCommentResponse.CommentDto> commentDtos = productCommentService.commentList(id);

        return ResponseEntity.ok(ApiUtils.success(commentDtos));
    }

    // 상품 후기 삭제
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @AuthenticationPrincipal CustomUserDetails customUserDetails){
        productCommentService.delete(id, customUserDetails.getUser());

        return ResponseEntity.ok(ApiUtils.success(null));
    }

    // 상품 후기 수정
    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute ProductCommentRequest.UpdateDto updateDto,
                                    @RequestParam MultipartFile[] files,
                                    @AuthenticationPrincipal CustomUserDetails customUserDetails) throws IOException {
        ProductComment comment = productCommentService.update(updateDto, files, customUserDetails.getUser());

        if (comment != null) {
            return ResponseEntity.ok(ApiUtils.success(updateDto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
