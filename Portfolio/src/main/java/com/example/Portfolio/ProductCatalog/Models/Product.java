package com.example.Portfolio.ProductCatalog.Models;


import com.example.Portfolio.Cart.Models.Cart;
import com.example.Portfolio.OrderManagement.Models.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private ProductCategory category;
    private ProductSubCategory subcategory;
    private ProductSubDivision subdivision;
    private float price;
    private int quantity;
    private String manufacturer;
    private Date createdAt;
    private Date updatedAt;
    private String description;
    private String imageURL;
    private boolean isActive;


    @JsonIgnore
    @ManyToMany(cascade =CascadeType.ALL, fetch =FetchType.LAZY, mappedBy = "items")
    private List<Cart> carts = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade =CascadeType.ALL, fetch = FetchType.LAZY ,mappedBy = "items")
    private List<Order> orders = new ArrayList<>();

}
