package com.example.funitureOnlineShop.BoardComment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class BoardCommentController {
    private final BoardCommentService boardCommentService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody BoardCommentDTO commentDTO) {

        System.out.println(commentDTO);
        BoardComment comment = boardCommentService.save(commentDTO);

        List<BoardCommentDTO> all = boardCommentService.findAll(commentDTO.getBoardId());

        if(comment != null) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("게시글이 없음.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<List<BoardCommentDTO>> getComments(@PathVariable Long boardId) {
        List<BoardCommentDTO> comments = boardCommentService.findAll(boardId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateComment(@RequestBody BoardCommentDTO boardCommentDTO) {
        try {
            boardCommentService.update(boardCommentDTO);
            return new ResponseEntity<>("댓글이 성공적으로 수정되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("댓글 수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        try {
            boardCommentService.delete(id);
            return new ResponseEntity<>("댓글이 성공적으로 삭제되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("댓글 삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
