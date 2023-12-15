package com.example.funitureOnlineShop.commentFile;

import com.example.funitureOnlineShop.productComment.ProductComment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CommentFile {
    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 파일 경로
    private String filePath;
    // 파일 명
    private String fileName;
    // uuid
    private String uuid;
    // 파일 형식
    private String fileType;
    // 파일 크기
    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ProductComment productComment;

    @Builder
    public CommentFile(Long id, String filePath, String fileName, String uuid, String fileType, Long fileSize, ProductComment productComment) {
        this.id = id;
        this.filePath = filePath;
        this.fileName = fileName;
        this.uuid = uuid;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.productComment = productComment;
    }

    public void updateFromBoard(ProductComment comment){
        this.productComment = comment;
    }
}
