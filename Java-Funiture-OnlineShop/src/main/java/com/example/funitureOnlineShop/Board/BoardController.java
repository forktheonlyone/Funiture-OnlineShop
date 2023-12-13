package com.example.funitureOnlineShop.Board;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody BoardDTO requestDTO,
                                       @RequestParam MultipartFile[] files) throws IOException {

        requestDTO.setCreateTime(LocalDateTime.now());
        boardService.save(requestDTO, files);

        return ResponseEntity.ok("게시물이 성공적으로 저장되었습니다.");
    }
}
