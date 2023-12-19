package com.example.funitureOnlineShop.productComment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;


public class ProductCommentResponse {

    @Setter
    @Getter
    @AllArgsConstructor
    public static class CommentDto {
        // PK
        private Long id;
        // 별점( 1 ~ 5점 )
        private int star;
        // 작성자 명
        private String writer;
        // 내용
        private String contents;
        // 작성일
        private LocalDateTime createTime;
        // 수정일
        private LocalDateTime updateTime;
        // 주문 내역 id
        private Long orderCheckId;
        // 상품 id
        private Long optionId;
        // 작성자 id
        private Long userId;

        // dto로 변경
        public static CommentDto toDto(ProductComment comment) {
            return new CommentDto(
                    comment.getId(),
                    comment.getStar(),
                    comment.getOrderCheck().getUser().getUsername(),
                    comment.getContents(),
                    comment.getCreateTime(),
                    comment.getUpdateTime(),
                    comment.getOrderCheck().getId(),
                    comment.getOrderCheck().getOption().getId(),
                    comment.getOrderCheck().getUser().getId());
        }

        // 작성일을 기준으로 리스트를 정렬 (최신순)
        public static void sortByCreateDate(List<CommentDto> comments) {
            comments.sort(Comparator.comparing(CommentDto::getCreateTime).reversed());
        }
    }
}
