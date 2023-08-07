package com.example.Portfolio.Cart.Repositories;

import com.example.Portfolio.Cart.Models.Cart;
import com.example.Portfolio.CustomerManagement.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByUser(User user);
}
