package com.example.Portfolio.Cart.Services;

import com.example.Portfolio.Cart.Models.Cart;
import com.example.Portfolio.ProductCatalog.Models.Product;

public interface CartService {

    void createCart(Long userId);

    Cart getCart(Long userId);

    Cart addItemToCart(Long cartId, Product item );

    void removeItemFromCart(Long cartId, Product item);

    void clearCart(Long cartId);

    double calculateTotal(Long cartId);

    void applyDiscount(Long userId, String discountCode);



}
