package com.example.funitureOnlineShop.productComment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product_comment")
public class ProductCommentController {

    private final ProductCommentService productCommentService;
}