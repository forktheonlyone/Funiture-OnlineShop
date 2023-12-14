package com.example.funitureOnlineShop.order.orderstatus;

import com.example.funitureOnlineShop.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", unique = true)
    private Order order;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isOrdered; // 주문 여부


    @Builder
    public OrderStatus(Order order, boolean isOrdered) {
        this.order = order;
        this.isOrdered = isOrdered;
    }
}