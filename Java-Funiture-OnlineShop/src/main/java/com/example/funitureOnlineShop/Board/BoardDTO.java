package com.example.funitureOnlineShop.Board;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id;
    // ** 제목
    private String title;
    // ** 내용
    private String contents;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public Board toEntity(){
        return Board.builder()
                .id(id)
                .title(title)
                .contents(contents)
                .updateTime(createTime)
                .updateTime(updateTime)
                .build();
    }
}
