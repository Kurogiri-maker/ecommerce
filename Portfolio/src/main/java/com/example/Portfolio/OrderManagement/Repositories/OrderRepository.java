package com.example.Portfolio.OrderManagement.Repositories;

import com.example.Portfolio.OrderManagement.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
