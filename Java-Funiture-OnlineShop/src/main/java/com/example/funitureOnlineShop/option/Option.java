package com.example.funitureOnlineShop.option;

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

    //@ManyToOne(fetch = FetchType.LAZY)
    //private Product product;

    @Builder
    public Option(Long id, String optionName, Long price, Long quantity) {
        this.id = id;
        this.optionName = optionName;
        this.price = price;
        this.quantity = quantity;
    }
}
