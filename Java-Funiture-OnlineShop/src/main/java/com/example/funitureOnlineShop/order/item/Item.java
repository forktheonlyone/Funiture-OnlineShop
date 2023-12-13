package com.example.funitureOnlineShop.order.item;

import com.example.funitureOnlineShop.option.Option;
import com.example.funitureOnlineShop.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "item_tb",
        indexes = {
                @Index(name = "item_option_id_index", columnList = "option_id"),
                @Index(name = "item_order_id_index", columnList = "order_id")
        })
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Option option;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long price;

    @Builder
    public Item(Long id, Option option, Order order, Long quantity, Long price) {
        this.id = id;
        this.option = option;
        this.order = order;
        this.quantity = quantity;
        this.price = price;
    }
}
