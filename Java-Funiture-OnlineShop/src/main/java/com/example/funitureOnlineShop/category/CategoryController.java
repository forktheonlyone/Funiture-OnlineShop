package com.example.funitureOnlineShop.category;

import com.example.funitureOnlineShop.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 저장
    @PostMapping("/category/save")
    public ResponseEntity<?> save(@RequestBody CategoryResponse categoryResponse) {
        categoryService.save(categoryResponse);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(categoryResponse);
        return ResponseEntity.ok(categoryResponse);
    }

    // 등록된 카테고리 모두 조회
    @GetMapping("/categories")
    public ResponseEntity<?> findAll() {
        List<Category> categories = categoryService.findAll();
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(categories);
        return ResponseEntity.ok(apiResult);
    }

    // 등록된 카테고리들의 id번호로 특정한 카테고리 조회
    @GetMapping("/category/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(category);
        return ResponseEntity.ok(apiResult);
    }
}
