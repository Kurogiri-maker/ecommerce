package com.example.Portfolio.OrderManagement.Services;

import com.example.Portfolio.OrderManagement.Models.Order;

import java.util.List;

public interface OrderService {


    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order saveOrder(Order product);

    void deleteOrder(Long id);
}
