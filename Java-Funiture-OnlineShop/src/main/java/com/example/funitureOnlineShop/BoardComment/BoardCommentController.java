package com.example.funitureOnlineShop.BoardComment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class BoardCommentController {
    private final BoardCommentService boardCommentService;

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute BoardCommentDTO commentDTO) {

        System.out.println(commentDTO);
        BoardComment comment = boardCommentService.save(commentDTO);

        List<BoardCommentDTO> all = boardCommentService.findAll(commentDTO.getBoardId());

        if(comment != null) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("게시글이 없음.", HttpStatus.NOT_FOUND);
        }
    }

}
