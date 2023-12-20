package com.example.funitureOnlineShop.Board;

import com.example.funitureOnlineShop.user.User;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id;

    private Long userId;

    private String title;

    private String contents;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    public Board toEntity(){
        return Board.builder()
                .id(id)
                .title(title)
                .contents(contents)
                .createTime(createTime)
                .updateTime(updateTime)
                .build();
    }
    public static BoardDTO toBoardDTO(Board board){
        return new BoardDTO(
                board.getId(),
                board.getUser().getId(),
                board.getTitle(),
                board.getContents(),
                board.getCreateTime(),
                board.getUpdateTime());
    }

    @Getter
    @Setter
    public static class BoardMapper {
        private Long categoryId;

        public static BoardDTO mapToDTO(Board board) {
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setId(board.getId());
            boardDTO.setTitle(board.getTitle());
            boardDTO.setCreateTime(board.getCreateTime());
            return boardDTO;
        }


        public static List<BoardDTO> mapToDTOs(List<Board> boards) {
            return boards.stream()
                    .map(BoardMapper::mapToDTO)
                    .collect(Collectors.toList());
        }
    }
}
