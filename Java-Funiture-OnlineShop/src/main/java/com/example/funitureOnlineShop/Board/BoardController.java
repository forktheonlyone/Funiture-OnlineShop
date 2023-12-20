package com.example.funitureOnlineShop.Board;

import com.example.funitureOnlineShop.BoardFile.BoardFile;
import com.example.funitureOnlineShop.core.error.exception.Exception500;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> save(@RequestBody BoardDTO requestDTO,
                                       @RequestParam MultipartFile[] files) throws IOException {

        requestDTO.setCreateTime(LocalDateTime.now());
        //boardService.save(requestDTO, files);

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
    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> update( @PathVariable Long id,
                                          @RequestBody BoardDTO boardDTO,
                                          @RequestParam MultipartFile[] files) {
        try {
            boardService.update(id, boardDTO, files);
            return ResponseEntity.ok("게시물이 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            throw new Exception500("게시물 업데이트에 실패했습니다.");
        }
    }

    @DeleteMapping("/files/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteBoardFile(@PathVariable Long id) {
        try {
            boardService.deleteByBoardFile(id);
            return ResponseEntity.ok("게시물 파일이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            throw new Exception500("게시물 파일 삭제에 실패했습니다.");
        }
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            boardService.deleteById(id);
            return ResponseEntity.ok("게시물이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            throw new Exception500("게시물 삭제에 실패했습니다.");
        }
    }
}
