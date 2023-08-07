package com.example.Portfolio.Cart.Models;

import com.example.Portfolio.CustomerManagement.Models.User;
import com.example.Portfolio.ProductCatalog.Models.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "carts_products",
            joinColumns = { @JoinColumn(name = "cart_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private List<Product> items = new ArrayList<>();


    private double total;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public void addProductToCart(Product product){
        this.items.add(product);
    }

    public void deleteProductFromCart(Product product){
        this.items.remove(product);
    }
}
