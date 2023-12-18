package com.example.funitureOnlineShop.option;

import com.example.funitureOnlineShop.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "option_tb")
public class Option {
    // ** PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // ** 옵션 이름
    @Column(length = 100, nullable = false)
    private String optionName;
    // ** 옵션 가격
    private Long price;
    // ** 상품의 재고 수량
    private Long stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public Option(Long id, String optionName, Long price, Long quantity, Long stockQuantity, Product product) {
        this.id = id;
        this.optionName = optionName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.product = product;
    }

    public Option toUpdate(Product product) {
        Option option = new Option();
        this.product = product;
        return option;
    }

    public void updateFromDTO(OptionResponse.FindAllDTO optionDTO){

        this.optionName = optionDTO.getOptionName();
        this.price = optionDTO.getPrice();
        this.stockQuantity = optionDTO.getStockQuantity();
    }
    public void updateStockQuantity(Long newStockQuantity) {
        this.stockQuantity = newStockQuantity;
    }
}
