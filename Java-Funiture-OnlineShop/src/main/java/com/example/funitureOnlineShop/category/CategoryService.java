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

    @Transactional
    public void save(CategoryRequest.SaveDto saveDto) {
        // 최상위 카테고리 추가 시
        if (saveDto.getSuperCategory_id() == 0L){
            categoryRepository.save(saveDto.toEntity());
        // 하위 카테고리 추가 시
        } else {
            // 요청 보낸 상위 카테고리가 없을 경우
            Optional<Category> optionalCategory = categoryRepository.findById(saveDto.getSuperCategory_id());
            if (!optionalCategory.isPresent())
                throw new Exception404("해당하는 상위 카테고리를 찾을 수 없습니다.");
            // 카테고리 저장
            Category category = categoryRepository.save(saveDto.toEntity());
            category.updateSuperCategory(optionalCategory.get());
        }
    }

    public List<CategoryResponse.FindAllDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryResponse.FindAllDto::new).collect(Collectors.toList());
    }

    public CategoryResponse.FindByIdDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new Exception404("해당 카테고리를 찾을 수 없습니다."));
        List<Category> subCategories = categoryRepository.findBySuperCategoryId(id);
        return new CategoryResponse.FindByIdDto(category, subCategories);
    }
}
