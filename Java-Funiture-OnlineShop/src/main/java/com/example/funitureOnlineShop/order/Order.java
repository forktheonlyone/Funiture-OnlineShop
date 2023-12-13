package com.example.funitureOnlineShop.order;

import com.example.funitureOnlineShop.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
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

    @Builder
    public Order(Long id, User user) {
        this.id = id;
        this.user = user;
    }
}
