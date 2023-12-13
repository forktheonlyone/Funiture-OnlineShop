package com.example.funitureOnlineShop.BoardFile;

import com.example.funitureOnlineShop.Board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {
    List<BoardFile> findByBoardId(Long boardId);
}
