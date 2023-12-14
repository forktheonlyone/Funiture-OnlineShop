package com.example.funitureOnlineShop.fileProduct;

import com.example.funitureOnlineShop.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class FileProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ** 파일 경로
    private String filePath;

    // ** 파일 이름
    private String fileName;

    // ** 파일 포멧
    private String fileType;

    // ** 파일 uuid
    private String uuid;

    // ** 파일 크기
    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    @Builder
    public FileProduct(Long id, String filePath, String fileName, String fileType, String uuid, Long fileSize, Product product) {
        this.id = id;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileType = fileType;
        this.uuid = uuid;
        this.fileSize = fileSize;
        this.product = product;
    }
}
