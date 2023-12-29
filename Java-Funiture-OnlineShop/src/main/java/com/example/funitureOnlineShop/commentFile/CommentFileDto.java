package com.example.funitureOnlineShop.commentFile;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentFileDto {
    // 파일 경로
    private String filePath;
    // 파일 명
    private String fileName;
    // 파일 형식
    private String fileType;
    // 랜덤 키
    private String uuid;
    // 파일 크기
    private Long fileSize;
    // 상품 후기(ProductComment) id
    private Long commentId;

    public CommentFile toEntity(){
        return CommentFile.builder()
                .filePath(filePath)
                .fileName(fileName)
                .uuid(uuid)
                .fileType(fileType)
                .fileSize(fileSize)
                .build();
    }

    public static CommentFileDto toFileDto(CommentFile commentFile){
        return new CommentFileDto(
                commentFile.getFilePath(),
                commentFile.getFileName(),
                commentFile.getFileType(),
                commentFile.getUuid(),
                commentFile.getFileSize(),
                commentFile.getProductComment().getId());
    }
}
