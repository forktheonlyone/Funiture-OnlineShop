package com.example.funitureOnlineShop.category;

import com.example.funitureOnlineShop.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category/save")
    public ResponseEntity<?> save(@RequestBody CategoryResponse categoryResponse) {
        categoryService.save(categoryResponse);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(categoryResponse);
        return ResponseEntity.ok(categoryResponse);
    }
}
