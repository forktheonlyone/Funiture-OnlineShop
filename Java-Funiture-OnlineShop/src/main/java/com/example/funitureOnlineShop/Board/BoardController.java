package com.example.funitureOnlineShop.Board;

import com.example.funitureOnlineShop.BoardFile.BoardFile;
import com.example.funitureOnlineShop.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody BoardDTO requestDTO,
                                       @RequestParam MultipartFile[] files) throws IOException {

        requestDTO.setCreateTime(LocalDateTime.now());
        boardService.save(requestDTO, files);

        return ResponseEntity.status(HttpStatus.CREATED).body("게시물이 성공적으로 저장되었습니다.");
    }

    @GetMapping(value = {"/paging", "/"})
    public ResponseEntity<?> paging(@PageableDefault(page = 1) Pageable pageable){
        Page<BoardDTO> page = boardService.paging(pageable);

        int blockLimit = 3;
        int startPage = (int) (Math.ceil((double) pageable.getPageNumber() / blockLimit) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < page.getTotalPages()) ? (startPage + blockLimit - 1) : page.getTotalPages();

        Map<String, Object> response = new HashMap<>();
        response.put("boardList", page.getContent());
        response.put("startPage", startPage);
        response.put("endPage", endPage);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByid(@RequestBody Long id, @PageableDefault(page = 1) Pageable pageable){
        BoardDTO dto = boardService.findById(id);
        List<BoardFile> files = boardService.findByBoardId(id);

        Map<String, Object> response = new HashMap<>();
        response.put("board", dto);
        response.put("page", pageable.getPageNumber());
        response.put("files", files != null ? files : Collections.emptyList());

        return ResponseEntity.ok(response);
    }

}
