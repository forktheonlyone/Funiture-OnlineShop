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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String contents;

    @Column(length = 1, nullable = false)
    private int star;

    @Column(length = 30, nullable = false)
    private LocalDateTime createTime;

    @Column(length = 30, nullable = false)
    private LocalDateTime updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public ProductComment(Long id, String contents, int star, LocalDateTime createTime, LocalDateTime updateTime, User user, Product product) {
        this.id = id;
        this.contents = contents;
        this.star = star;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.user = user;
        this.product = product;
    }
}
