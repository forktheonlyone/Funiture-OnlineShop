package com.example.funitureOnlineShop.category;

import lombok.Getter;
import lombok.Setter;

public class CategoryRequest {

    @Setter
    @Getter
    public static class SaveDto {

        private Long superCategory_id;
        private String categoryName;

        public Category toEntity(){
            return Category.builder()
                    .categoryName(categoryName)
                    .build();
        }
    }
}
