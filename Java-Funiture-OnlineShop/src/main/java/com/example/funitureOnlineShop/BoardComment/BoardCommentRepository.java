package com.example.funitureOnlineShop.BoardComment;

import com.example.funitureOnlineShop.Board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
    List<BoardComment> findAllByBoardOrderByIdDesc(Board board);
}
