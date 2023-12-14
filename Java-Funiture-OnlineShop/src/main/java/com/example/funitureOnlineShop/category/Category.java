package com.example.funitureOnlineShop.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 카테고리 ID
    private Long id;

    @Column(nullable = false)
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "superCategory_id")
    private Category superCategory;

    @OneToMany(mappedBy = "superCategory", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Category> subCategories;

    @Builder
    public Category(Long id, String categoryName, Category superCategory, List<Category> subCategories) {
        this.id = id;
        this.categoryName = categoryName;
        this.superCategory = superCategory;
        this.subCategories = subCategories;
    }

    public void updateSuperCategory(Category superCategory) {
        this.superCategory = superCategory;
    }
}
