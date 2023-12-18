package com.example.funitureOnlineShop.Board;

import com.example.funitureOnlineShop.category.Category;
import com.example.funitureOnlineShop.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Board {
    // ** PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // ** 제목
    @Column(length = 40, nullable = false)
    private String title;
    // ** 내용
    @Column(nullable = false)
    private String contents;
    // ** 최초 작성 시간
    private LocalDateTime createTime;
    // ** 최근 수정 시간
    private LocalDateTime updateTime;

    // ** User의 FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Board(Long id, String title, String contents, LocalDateTime createTime, LocalDateTime updateTime, User user, Category category) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.user = user;
        this.category = category;
    }

    public void updateFromDTO(BoardDTO boardDTO) {
        this.title = boardDTO.getTitle();
        this.contents = boardDTO.getContents();
    }
}
