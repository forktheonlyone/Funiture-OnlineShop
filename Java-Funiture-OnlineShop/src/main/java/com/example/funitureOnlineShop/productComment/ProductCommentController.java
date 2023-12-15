package com.example.funitureOnlineShop.productComment;

import com.example.funitureOnlineShop.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product_comment")
public class ProductCommentController {

    private final ProductCommentService productCommentService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@ModelAttribute ProductCommentDto commentDto,
                                  @RequestParam MultipartFile[] files) throws IOException {
        ProductComment comment = productCommentService.save(commentDto, files);

        if (comment != null) {
            return ResponseEntity.ok(ApiUtils.success(commentDto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<?> commentList(@PathVariable Long id){
        List<ProductCommentDto> commentDtos = productCommentService.commentList(id);

        return ResponseEntity.ok(ApiUtils.success(commentDtos));
    }
}
