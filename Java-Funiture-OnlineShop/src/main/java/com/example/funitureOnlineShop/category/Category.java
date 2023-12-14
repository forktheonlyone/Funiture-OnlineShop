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

    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "largecategory_id")
    private Category largeCategory;

    @OneToMany(mappedBy = "largecategory_id")
    private List<Category> detailCategory;

    @Builder
    public Category(Long id, String categoryName, Category largeCategory, List<Category> detailCategory) {
        this.id = id;
        this.categoryName = categoryName;
        this.largeCategory = largeCategory;
        this.detailCategory = detailCategory;
    }
}
