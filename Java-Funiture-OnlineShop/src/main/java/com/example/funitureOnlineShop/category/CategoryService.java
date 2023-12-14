package com.example.funitureOnlineShop.category;

import com.example.funitureOnlineShop.core.error.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void save(CategoryResponse categoryResponse) {
        Category largeCategory = null;
        if (categoryResponse.getLargeCategoryId() != null) {
            largeCategory = categoryRepository.findById(categoryResponse.getLargeCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid largeCategoryId:" + categoryResponse.getLargeCategoryId()));
        }
        List<Category> detailCategory = null;
        if (categoryResponse.getDetailCategoryId() != null) {
            detailCategory = categoryResponse.getDetailCategoryId().stream()
                    .map(id -> categoryRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Invalid detailCategoryId:" + id)))
                    .collect(Collectors.toList());
        }
        Category category = categoryResponse.toEntity(largeCategory, detailCategory);
        categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow( () -> new Exception404("해당 카테고리를 찾지 못했습니다. : " + id));
    }
}
