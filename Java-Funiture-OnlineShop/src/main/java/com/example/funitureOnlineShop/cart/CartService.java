package com.example.funitureOnlineShop.cart;
import com.example.funitureOnlineShop.core.error.exception.Exception400;
import com.example.funitureOnlineShop.core.error.exception.Exception401;
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

    public CartResponse.FindAllDTO findAll() {
        List<Cart> cartList = cartRepository.findAll();
        return new CartResponse.FindAllDTO(cartList);
    }


    // ** 카트에 상품 추가
    @Transactional
    public void addCartList(List<CartRequest.SaveDTO> saveDTOS, User user) {
        // ** 동일한 데이터(상품)를 묶어줌
        Set<Long> optionsId = new HashSet<>();

        // ** 동일한 상품 예외처리 - 상품이 이미 추가 되었는지 확인하고 예외처리
        for (CartRequest.SaveDTO cart : saveDTOS) {
            if (!optionsId.add(cart.getOptionId())) {
                throw new Exception401("이미 동일한 상품 옵션이 있습니다" + cart.getOptionId());
            }
        }

        // Option의 id값을 가져와서 비교 - 상품찾기
        List<Cart> cartList = saveDTOS.stream().map(cartDTO -> {
            //입력받은 상품들을 Stream으로 변환 후 map
            Option option = optionRepository.findById(cartDTO.getOptionId()).orElseThrow(
                    () -> new Exception404("해당 상품 옵션을 찾을 수 없습니다." + cartDTO.getOptionId()) //없으면 예외처리
            );
            return cartDTO.toEntity(option, user);
        }).collect(Collectors.toList());

        // 카트에 상품 저장
        cartList.forEach(cart -> {
            // 카트 리스트를 반복하면서 각각의 카트 정보를 저장
            try {
                cartRepository.save(cart);
            } catch (Exception e) {
                throw new Exception500("카트에 담던 중 오류가 발생했습니다." + e.getMessage());
            }
        });

    }

    @Transactional
    public CartResponse.UpdateDTO update(List<CartRequest.UpdateDTO> requestDTO, User user) {
        // 현재 사용자의 ID를 기반으로 카트에서 상품을 조회
        List<Cart> cartList = cartRepository.findAllByUserId(user.getId());

        // 카트에 있는 상품들의 ID를 리스트로 추출
        List<Long> cartIds = cartList.stream().map(cart -> cart.getId()).collect(Collectors.toList());

        // 업데이트 요청으로부터 업데이트할 카트 상품들의 ID를 추출
        List<Long> requestIds = requestDTO.stream().map(dto -> dto.getCartId()).collect(Collectors.toList());

        // 카트에 상품이 없을 경우 404 예외
        if (cartIds.size() == 0) { // 자료가 없음
            throw new Exception404("주문 가능한 상품이 없습니다.");
        }

        // 데이터가 없을떄 400에러
        if (requestIds.stream().distinct().count() != requestIds.size()) {
            //distinct 동일한 키 제외
            throw new Exception400("동일한 카트 아이디를 주문할 수 없습니다.");
        }

        // 카트에 담긴 아이디 비교해봄
        // 업데이트 요청된 각 상품의 ID가 카트에 있는지 확인하고, 없다면 400 예외
        for (Long requestId : requestIds) {
            if (!cartIds.contains(requestId)) {
                throw new Exception400("카트에 없는 상품은 주문할 수 없습니다." + requestId);
            }
        }
        // 모든 예외처리 완료

        // 업데이트 요청에 따라 카트의 각 상품의 수량을 업데이트
        for (CartRequest.UpdateDTO updateDTO : requestDTO) {
            for (Cart cart : cartList) {
                if (cart.getId() == updateDTO.getCartId()) {
                    cart.update(updateDTO.getQuantity(), cart.getOption().getPrice() * updateDTO.getQuantity());
                }
            }
        }

        // 위는 계속 요청


        // 이제 응답 받을 차례 - 객체 생성후 사용 가능
        return new CartResponse.UpdateDTO(cartList);
    }


    @Transactional
    public void deleteCartList(List<CartResponse.DeleteDTO> deleteDTO, User user) {
        // 사용자의 id를 기반으로 카트에서 상품을 조회
        List<Cart> cartList = cartRepository.findAllByUserId(user.getId());

        // 카트에 있는 상품들의 id를 리스트로 추출함
        List<Long> cartIds = cartList.stream().map(cart -> cart.getId()).collect(Collectors.toList());

        // 삭제 요청으로부터 삭제할 카트 상품들의 id를 추출함
        List<Long> requestIds = deleteDTO.stream().map(dto -> dto.getCartId()).collect(Collectors.toList());


        // 카트의 상품이 없을 경우
        if (cartIds.size() == 0) { // 자료가 없음
            throw new Exception404("카트에 상품이 없습니다.");
        }

        // 카트에 담긴 아이디 비교해봄
        // 삭제 요청된 각 상품의 id가 카트에 있는지 확인 하고, 없다면 404 예외처리
        for (Long requestId : requestIds) {
            if (!cartIds.contains(requestId)) {
                throw new Exception400("카트에 없는 상품은 삭제 할 수 없습니다." + requestId);
            }
        }

        Long userId = user.getId();

        // 각 삭제 요청에 대한 사용자의 카트에서 해당 상품을 삭제함
        for (CartResponse.DeleteDTO dto : deleteDTO) {
            Long cartId = dto.getCartId();
            cartRepository.deleteByUserIdAndId(userId, cartId);
        }

    }
}