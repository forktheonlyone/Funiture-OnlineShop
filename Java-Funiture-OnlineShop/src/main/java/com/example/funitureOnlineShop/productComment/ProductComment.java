package com.example.funitureOnlineShop.productComment;

import com.example.funitureOnlineShop.product.Product;
import com.example.funitureOnlineShop.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table
public class ProductComment {
    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 옵션 명
    @Column(length = 100, nullable = false)
    private String OptionName;
    // 내용
    @Column(length = 1000)
    private String contents;
    // 별점( 1 ~ 5점 )
    @Column(length = 1, nullable = false)
    private int star;
    // 생성일
    @Column(length = 30, nullable = false)
    private LocalDateTime createTime;
    // 수정일
    @Column(length = 30, nullable = false)
    private LocalDateTime updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public ProductComment(Long id, String optionName, String contents, int star, LocalDateTime createTime, LocalDateTime updateTime, User user, Product product) {
        this.id = id;
        OptionName = optionName;
        this.contents = contents;
        this.star = star;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.user = user;
        this.product = product;
    }
}
