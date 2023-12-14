package com.example.funitureOnlineShop.cart;
import com.example.funitureOnlineShop.core.error.exception.Exception400;
import com.example.funitureOnlineShop.core.error.exception.Exception404;
import com.example.funitureOnlineShop.core.error.exception.Exception500;
import com.example.funitureOnlineShop.core.security.CustomUserDetails;
import com.example.funitureOnlineShop.option.Option;
import com.example.funitureOnlineShop.option.OptionRepository;

import com.example.funitureOnlineShop.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final OptionRepository optionRepository;

    public void addCart(List<CartRequest.SaveDTO> saveDTOS, User user) {
        Set<Long> optionsId = new HashSet<>();

        for(CartRequest.SaveDTO cart : saveDTOS){
            if(!optionsId.add(cart.getOptionId()));{
                throw new Exception400("이미 동일한 상품의옵션이 있습니다." + cart.getOptionId());
            }
        }

        List<Cart> cartList = saveDTOS.stream().map(cartDTO -> {
            Option option = optionRepository.findById(cartDTO.getOptionId()).orElseThrow(
                    () -> new Exception404("해당 상품 옵션을 찾을 수 없습니다." + cartDTO.getOptionId()));
            return cartDTO.toEntity(option,user);
        }).collect(Collectors.toList());

        cartList.forEach(cart -> {
            try{
                cartRepository.save(cart);
            } catch (Exception e){
                throw new Exception500("카트저장에 실패하였습니다."+e.getMessage());
            }});
    }

    public CartResponse.FindAllDTO findAll() {
        List<Cart> cartList = cartRepository.findAll();
        return new CartResponse.FindAllDTO(cartList);
    }

    @Transactional
    public CartResponse.UpdateDTO update(List<CartRequest.updateDTO> requestDTOS, User user) {
        List<Cart> cartList = cartRepository.findAllByUserId(user.getId());

        List<Long> cartIds = cartList.stream().map(cart -> cart.getId()).collect(Collectors.toList());
        List<Long> requestIds = requestDTOS.stream().map(dto -> dto.getCartId()).collect(Collectors.toList());

        if(cartIds.size() == 0){
            throw new Exception404("주문 가능한 상품이 없습니다.");
        }
        if(requestIds.stream().distinct().count() != requestIds.size()){
            throw new Exception400("동일한 카트 아이디를 주문할 수 없습니다.");
        }

        for(Long requestId : requestIds){
            if(!cartIds.contains(requestId)){
                throw new Exception400("카트에 없는 상품은 주문할 수 없습니다."+requestId);
            }
        }

        for(CartRequest.updateDTO updateDTO : requestDTOS){
            for(Cart cart : cartList){
                if(cart.getId() == updateDTO.getCartId()){
                    cart.update(updateDTO.getQuantity(), cart.getPrice() * cart.getQuantity());
                }
            }
        }
        return new CartResponse.UpdateDTO(cartList);
    }



    @Transactional
    public void deleteFromCart(Long cartId, User user) {
        Cart cart = cartRepository.findByIdAndUserId(cartId, user.getId())
                .orElseThrow(() -> new Exception404("해당 카트를 찾을 수 없습니다." + cartId));

        try {
            cartRepository.delete(cart);
        } catch (Exception e) {
            throw new Exception500("카트에서 상품을 삭제하는데 실패하였습니다." + e.getMessage());
        }
    }
}
