package com.example.Portfolio.Cart.Services;

import com.example.Portfolio.Cart.Models.Cart;
import com.example.Portfolio.Cart.Repositories.CartRepository;
import com.example.Portfolio.CustomerManagement.Models.User;
import com.example.Portfolio.CustomerManagement.Repositories.UserRepository;
import com.example.Portfolio.ProductCatalog.Models.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final UserRepository userRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createCart(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            Cart cart = new Cart();
            cart.setTotal(0.0);
            cart.setUser(user.get());
            cartRepository.save(cart);
        }
    }

    @Override
    public Cart getCart(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(cartRepository::findByUser).orElse(null);
    }

    @Override
    public Cart addItemToCart(Long cartId, Product item) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isPresent()){
           Cart updatedCart = cart.get();
           updatedCart.addProductToCart(item);
           return cartRepository.save(updatedCart);

        }else{
            return null;
        }
    }

    @Override
    public void removeItemFromCart(Long cartId, Product item) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isPresent()){
            Cart updatedCart = cart.get();
            updatedCart.deleteProductFromCart(item);
            cartRepository.save(updatedCart);        }
    }

    @Override
    public void clearCart(Long cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isPresent()){
            Cart updatedCart = cart.get();
            updatedCart.getItems().clear();
            cartRepository.save(updatedCart);
        }
    }

    @Override
    public double calculateTotal(Long userId) {
        double total = 0.0;
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            Cart cart = cartRepository.findByUser(user.get());
            for (Product item: cart.getItems()) {
                total += item.getPrice();
            }
        }
        return total;
    }

    @Override
    public void applyDiscount(Long userId, String discountCode) {

    }
}
