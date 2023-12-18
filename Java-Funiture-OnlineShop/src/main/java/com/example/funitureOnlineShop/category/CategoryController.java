package com.example.funitureOnlineShop.category;

import com.example.funitureOnlineShop.Board.BoardDTO;
import com.example.funitureOnlineShop.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 저장
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid CategoryRequest.SaveDto saveDto) {
        categoryService.save(saveDto);

        return ResponseEntity.ok(ApiUtils.success(null));
    }

    // 등록된 카테고리 모두 조회
    @GetMapping("/categories")
    public ResponseEntity<?> findAll() {
        List<CategoryResponse.FindAllDto> categories = categoryService.findAll();

        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(categories);
        return ResponseEntity.ok(apiResult);
    }

    // 요청 보낸 카테고리의 상위 카테고리와 하위 카테고리들 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        CategoryResponse.FindByIdDto categoryDto = categoryService.findById(id);

        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(categoryDto);
        return ResponseEntity.ok(apiResult);
    }

    // 카테고리의 이름과 그 상위 카테고리 수정
    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute CategoryRequest.UpdateDto updateDto ) {
        categoryService.update(updateDto);

        return ResponseEntity.ok(ApiUtils.success(null));
    }

    // 카테고리 삭제
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.delete(id);

        return ResponseEntity.ok(ApiUtils.success(null));
    }
    // 게시판 카테고리 추가
    @GetMapping("/notices/{categoryId}")
    public ResponseEntity<?> getNoticesByCategory(@PathVariable Long categoryId) {
        List<BoardDTO> notices = categoryService.findNoticesByCategory(categoryId);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(notices);
        return ResponseEntity.ok(apiResult);
    }
}
