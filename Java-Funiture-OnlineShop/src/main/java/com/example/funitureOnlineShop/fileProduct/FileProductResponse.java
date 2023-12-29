package com.example.funitureOnlineShop.fileProduct;

import com.example.funitureOnlineShop.product.Product;
import lombok.*;

import java.io.File;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class FileProductResponse {

    // 이미지 id
    private Long id;

    // ** 파일 경로
    private String filePath;

    // ** 파일 이름
    private String fileName;

    // uuid (랜덤 키)
    private String uuid;

    // ** 파일 포멧
    private String fileType;

    // ** 파일 크기
    private Long fileSize;

    // ** 상품 id
    private Long productId;

    public FileProductResponse(FileProduct fileProduct) {
        this.id = fileProduct.getId();
        this.filePath = fileProduct.getFilePath();
        this.fileName = fileProduct.getFileName();
        this.uuid = fileProduct.getUuid();
        this.fileType = fileProduct.getFileType();
        this.fileSize = fileProduct.getFileSize();
        this.productId = fileProduct.getProduct().getId();
    }

    public FileProduct toEntity() {
        return FileProduct.builder()
                .filePath(filePath)
                .fileName(fileName)
                .uuid(uuid)
                .fileType(fileType)
                .fileSize(fileSize)
                .build();
    }

    public static FileProductResponse toDto(FileProduct fileProduct) {
        return new FileProductResponse(
                fileProduct.getId(),
                fileProduct.getFilePath(),
                fileProduct.getFileName(),
                fileProduct.getUuid(),
                fileProduct.getFileType(),
                fileProduct.getFileSize(),
                fileProduct.getProduct().getId());
    }
}
