package com.example.funitureOnlineShop.BoardComment;

import com.example.funitureOnlineShop.Board.Board;
import com.example.funitureOnlineShop.Board.BoardRepository;
import com.example.funitureOnlineShop.core.error.exception.Exception500;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardCommentService {
    private final BoardCommentRepository boardCommentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public BoardComment save(BoardCommentDTO boardCommentDTO){
        Optional<Board> optionalBoard =
                boardRepository.findById(boardCommentDTO.getBoardId());
        return optionalBoard.map(board -> {
            BoardComment entity = boardCommentDTO.toEntity();
            entity.toUpdate(board);
            return boardCommentRepository.save(entity);
        }).orElseThrow(() -> new Exception500("게시글이 존재하지 않습니다."));
    }

    public List<BoardCommentDTO> findAll(Long boardId) {
        Board board = boardRepository.findById(boardId).get();
        List<BoardComment> commentsList = boardCommentRepository.findAllByBoardOrderByIdDesc(board);

        List<BoardCommentDTO> DTOlist = new ArrayList<>();
        for(BoardComment comments : commentsList){
            BoardCommentDTO boardCommentDTO = BoardCommentDTO.toboardCommentDTO(comments, boardId);
            DTOlist.add(boardCommentDTO);
        }
        return DTOlist;
    }

    public Optional<BoardComment> findById(Long id){
        return boardCommentRepository.findById(id);
    }

    @Transactional
    public void update(BoardCommentDTO boardCommentDTO){
        Optional<BoardComment> boardComment = boardCommentRepository.findById(boardCommentDTO.getId());

        BoardComment comment = boardComment.get();

        comment.updateDTO(boardCommentDTO);
        boardCommentRepository.save(comment);
    }

    @Transactional
    public void delete(Long id){
        boardCommentRepository.deleteById(id);
    }

}
