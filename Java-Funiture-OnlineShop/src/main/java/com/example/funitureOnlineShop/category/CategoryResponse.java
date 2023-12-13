package com.example.funitureOnlineShop.category;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class CategoryResponse {

    private Long id;

    private String categoryName;

    private Long largeCategoryId;

    private List<Long> detailCategoryId;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.largeCategoryId = category.getLargeCategory() !=
                null ? category.getLargeCategory().getId() : null;
        this.detailCategoryId = category.getDetailCategory() !=
                null ? category.getDetailCategory().stream()
                .map(Category::getId).collect(Collectors.toList()) : null;
    }
    public Category toEntity(Category largeCategory, List<Category> detailCategory) {
        return Category.builder()
                .categoryName(categoryName)
                .largeCategory(largeCategory)
                .detailCategory(detailCategory)
                .build();
    }
}
