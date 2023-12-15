package com.example.funitureOnlineShop.productComment;

import com.example.funitureOnlineShop.commentFile.CommentFile;
import com.example.funitureOnlineShop.option.Option;
import com.example.funitureOnlineShop.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table
public class ProductComment {
    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private Option option;

    @OneToMany(mappedBy = "productComment", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentFile> commentFiles = new ArrayList<>();

    @Builder
    public ProductComment(Long id, String contents, int star, LocalDateTime createTime, LocalDateTime updateTime, User user, Option option, List<CommentFile> commentFiles) {
        this.id = id;
        this.contents = contents;
        this.star = star;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.user = user;
        this.option = option;
        this.commentFiles = commentFiles;
    }

    public void updateFromEntity(User user, Option option) {
        this.user = user;
        this.option = option;
        this.createTime = LocalDateTime.now();
    }

    public void updateFromDto(ProductCommentDto commentDto) {
        this.contents = commentDto.getContents();
        this.star = commentDto.getStar();
        this.updateTime = commentDto.getUpdateTime();
        this.commentFiles.clear();
    }
}
