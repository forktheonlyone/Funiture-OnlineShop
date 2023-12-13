package com.example.funitureOnlineShop.Board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void save(BoardDTO requestDTO, MultipartFile[] files) {

    }

    public Page<BoardDTO> paging(Pageable pageable){

        // ** 페이지 시작 번호
        int page = pageable.getPageNumber() - 1;

        // ** 페이지에 포함될 게시물 개수
        int size = 10;

        Page<Board> boards = boardRepository.findAll(
                PageRequest.of(page, size));
        return boards.map(board -> new BoardDTO(
                board.getId(),
                board.getTitle(),
                board.getContents(),
                board.getCreateTime(),
                board.getUpdateTime()));
    }


}
