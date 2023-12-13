package com.example.funitureOnlineShop.BoardComment;

import com.example.funitureOnlineShop.Board.Board;
import com.example.funitureOnlineShop.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public BoardComment(Long id, String contents, Board board, User user) {
        this.id = id;
        this.contents = contents;
        this.board = board;
        this.user = user;
    }

    public BoardComment toUpdate(Board board) {
        BoardComment boardComment = new BoardComment();
        this.board = board;
        return boardComment;
    }
}
