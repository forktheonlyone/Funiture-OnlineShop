package com.example.cart;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void addCart(List<CartRequest.SaveDTO> saveDTOS, User user) {
    }
}
