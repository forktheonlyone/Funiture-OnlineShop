package com.example.funitureOnlineShop.cart;

import com.example.funitureOnlineShop.core.error.exception.Exception404;
import com.example.funitureOnlineShop.option.Option;
import com.example.funitureOnlineShop.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "cartitem_tb")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Long cartQuantity;

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "update_by")
    private String updatedBy;

    /**
     * CartItem 엔티티의 경우 초기 가입시 로그인 된 아이디를 사용하여 생성자와 수정자를 설정하는
     * JpaAuditing LoginIdAuditorAware 를 사용하는 불가능하기 때문에
     * 엔티티 최초 persist 시점에 생성하여 입력하도록 한다.
     */
    @PrePersist
    public void setUp(){
        this.createdBy = "system";
        this.updatedBy = "system";
    }

    /**
     * 최초 상품을 추가 할 때 사용 할 생성자 메소드.
     * @param item
     * @param cartQuantity
     */
    @Builder
    public CartItem(Cart cart, Long cartQuantity) {
        this.cart = cart;
        this.cartQuantity = cartQuantity;
    }

    private void setQuantity(Long cartQuantity, Option optionQuantity){
        if(optionQuantity.getQuantity() < cartQuantity)
            throw new Exception404("남은 재고량이 없습니다.");
        else
            this.cartQuantity = cartQuantity;
    }

    public void changeCart(Cart cart){
        this.cart=cart;
    }

    public void addCartQuantity(Long cartQuantity){
        this.cartQuantity += cartQuantity;
    }

    public void subCartQuantity(Long cartQuantity){
        if(this.cartQuantity - cartQuantity < 0){
            log.error("0 보다 작은 수량은 담을 수 없습니다.");
            throw new Exception404("남은 재고량이 없습니다.");
        }else {
            this.cartQuantity -= cartQuantity;
        }
    }
}
