package com.example.funitureOnlineShop.category;

import com.example.funitureOnlineShop.core.error.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 카테고리 저장
    @Transactional
    public void save(CategoryRequest.SaveDto saveDto) {
        // 최상위 카테고리 추가 시
        if (saveDto.getSuperCategory_id() == 0L){
            categoryRepository.save(saveDto.toEntity());
        // 하위 카테고리 추가 시
        } else {
            // 요청 보낸 상위 카테고리가 없을 경우
            Optional<Category> optionalCategory = categoryRepository.findById(saveDto.getSuperCategory_id());
            if (optionalCategory.isEmpty())
                throw new Exception404("해당하는 상위 카테고리를 찾을 수 없습니다.");
            // 카테고리 저장
            Category category = categoryRepository.save(saveDto.toEntity());
            category.updateSuperCategory(optionalCategory.get());
        }
    }

    // 등록된 모든 카테고리 탐색
    public List<CategoryResponse.FindAllDto> findAll() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(CategoryResponse.FindAllDto::new).collect(Collectors.toList());
    }

    // 요청 받은 카테고리와 그 상하위 카테고리 탐색
    public CategoryResponse.FindByIdDto findById(Long id) {
        // 요청 받은 카테고리
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new Exception404("해당 카테고리를 찾을 수 없습니다."));
        // 하위 카테고리
        List<Category> subCategories = categoryRepository.findBySuperCategoryId(id);

        return new CategoryResponse.FindByIdDto(category, subCategories);
    }

    // 카테고리의 이름과 그 상위 카테고리 수정
    @Transactional
    public void update(CategoryRequest.UpdateDto updateDto) {
        // 수정할 카테고리 존재?
        categoryRepository.findById(updateDto.getId()).orElseThrow(
                () -> new Exception404("해당 카테고리를 찾을 수 없습니다."));
        // 연결한 상위 카테고리 존재?
        Category superCategory = categoryRepository.findById(updateDto.getSuperCategory_id()).orElseThrow(
                () -> new Exception404("해당 상위 카테고리를 찾을 수 없습니다."));
        // 수정 된 카테고리
        Category newCategory = Category.builder()
                .id(updateDto.getId())
                .categoryName(updateDto.getCategoryName())
                .superCategory(superCategory)
                .build();

        categoryRepository.save(newCategory);
    }
}
