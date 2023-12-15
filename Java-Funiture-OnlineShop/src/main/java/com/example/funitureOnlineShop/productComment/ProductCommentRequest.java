package com.example.funitureOnlineShop.productComment;

import lombok.*;

import java.time.LocalDateTime;

public class ProductCommentRequest {

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveDto {
        // PK
        private Long id;
        // 옵션 명
        private String optionName;
        // 작성자 명
        private String writer;
        // 내용
        private String contents;
        // 작성일
        private LocalDateTime createTime;
        // 수정일
        private LocalDateTime updateTime;
        // 상품 id
        private Long productId;
        // 작성자 id
        private Long userId;

        public ProductComment toEntity() {
            return ProductComment.builder()
                    .id(id)
                    .contents(contents)
                    .createTime(createTime)
                    .updateTime(LocalDateTime.now())
                    .build();
        }

        // dto로 변경
        public static SaveDto toSaveDto(ProductComment comment) {
            return new ProductCommentRequest.SaveDto(
                    comment.getId(),
                    comment.getOptionName(),
                    comment.getUser().getUsername(),
                    comment.getContents(),
                    comment.getCreateTime(),
                    comment.getUpdateTime(),
                    comment.getProduct().getId(),
                    comment.getUser().getId());
        }
    }
}
