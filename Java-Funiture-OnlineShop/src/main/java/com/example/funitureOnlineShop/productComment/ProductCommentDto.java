package com.example.funitureOnlineShop.productComment;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductCommentDto {
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
    // 상품 id
    private Long optionId;
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
    public static ProductCommentDto toSaveDto(ProductComment comment) {
        return new ProductCommentDto(
                comment.getId(),
                comment.getStar(),
                comment.getUser().getUsername(),
                comment.getContents(),
                comment.getCreateTime(),
                comment.getUpdateTime(),
                comment.getOption().getId(),
                comment.getUser().getId());
    }

    // 작성일을 기준으로 리스트를 정렬 (최신순)
    public static void sortByCreateDate(List<ProductCommentDto> comments) {
        comments.sort(Comparator.comparing(ProductCommentDto::getCreateTime).reversed());
    }
}
