package com.example.funitureOnlineShop.BoardComment;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardCommentDTO {

    private Long id;

    private String contents;

    private Long boardId;

    private Long userId;

    public static BoardCommentDTO toboardCommentDTO(BoardComment comments, Long boardId) {
        BoardCommentDTO boardCommentDTO = new BoardCommentDTO();
        boardCommentDTO.setId(comments.getId());
        boardCommentDTO.setContents(comments.getContents());
        boardCommentDTO.setBoardId(comments.getBoard().getId());
        boardCommentDTO.setUserId(comments.getUser().getId());
        return boardCommentDTO;
    }

    public BoardComment toEntity(){
        return BoardComment.builder()
                .contents(contents)
                .build();
    }
}
