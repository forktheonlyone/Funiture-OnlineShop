package com.example.funitureOnlineShop.option;

import com.example.FunitureOnlineShop.product.Product;
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
    // ** 옵션 수량
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public Option(Long id, String optionName, Long price, Long quantity, Product product) {
        this.id = id;
        this.optionName = optionName;
        this.price = price;
        this.quantity = quantity;
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
        this.quantity = optionDTO.getQuantity();
    }
}
