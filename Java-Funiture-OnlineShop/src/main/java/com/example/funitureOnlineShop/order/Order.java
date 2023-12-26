package com.example.funitureOnlineShop.order;

import com.example.funitureOnlineShop.cart.Cart;
import com.example.funitureOnlineShop.order.item.Item;
import com.example.funitureOnlineShop.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_tb",
        indexes = {
                @Index(name = "order_user_id_index", columnList = "user_id")
        })
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    // ** 작업자 : 아현 (Tid, cart 추가)
    @OneToOne
    private Cart cart;

    private String tid;


    @Builder
    public Order(Long id, User user, List<Item> orderItems, LocalDateTime orderDate) {
        this.id = id;
        this.user = user;
        this.orderItems = orderItems;
        this.orderDate = orderDate;
    }


}
