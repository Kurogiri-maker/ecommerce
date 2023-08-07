package com.example.Portfolio.Cart.Repositories;

import com.example.Portfolio.Cart.Models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
