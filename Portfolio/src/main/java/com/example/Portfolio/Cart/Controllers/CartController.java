package com.example.Portfolio.Cart.Controllers;

import com.example.Portfolio.Cart.Models.Cart;
import com.example.Portfolio.Cart.Services.CartService;
import com.example.Portfolio.ProductCatalog.Models.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
@Slf4j
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId){
        Cart cart =  cartService.getCart(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/get/{cartId}/total")
    public double calculateTotal(@PathVariable Long cartId){
        return cartService.calculateTotal(cartId);
    }

    @PatchMapping("/clear/{cartId}")
    public ResponseEntity<?> clearCart(@PathVariable Long cartId){
        cartService.clearCart(cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/save/{cartId}")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long cartId, @RequestBody Product item){
        cartService.addItemToCart(cartId,item);
        Cart updatedCart = cartService.getCart(cartId);
        return new ResponseEntity<>(updatedCart,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<?> deleteItemFromCart(@PathVariable Long cartId, @RequestBody Product item){
        cartService.removeItemFromCart(cartId,item);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
