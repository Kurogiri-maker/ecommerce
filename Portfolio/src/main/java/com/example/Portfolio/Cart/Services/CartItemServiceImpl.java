package com.example.Portfolio.Cart.Services;

import com.example.Portfolio.Cart.Models.CartItem;
import com.example.Portfolio.Cart.Repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void clearCartItem(Long id) {
        Optional<CartItem> cartItem= this.cartItemRepository.findById(id);
        if(cartItem.isPresent()){
            this.cartItemRepository.deleteById(id);
        }
    }
}
