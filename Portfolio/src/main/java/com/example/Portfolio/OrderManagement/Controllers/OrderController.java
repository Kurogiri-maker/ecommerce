package com.example.Portfolio.OrderManagement.Controllers;

import com.example.Portfolio.OrderManagement.Models.Order;
import com.example.Portfolio.OrderManagement.Services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> products =  orderService.getAllOrders();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) throws RuntimeException{
        try {
            Order order = orderService.getOrderById(id);
            return new ResponseEntity<>(order,HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Order> saveOrder(@RequestBody Order createdOrder){
        orderService.saveOrder(createdOrder);
        if(createdOrder != null){
            return new ResponseEntity<>(createdOrder,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
/*
    @PatchMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id,@RequestBody OrderDTO dto){
        if(orderService.getOrdertById(id) != null){
            Order order = orderService.getOrderById(id);
            Order updatedOrder = orderMapper.updateOrderFromDto(dto,order);
            orderService.saveOrder(updatedOrder);
            return new ResponseEntity<>(updatedOrder,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

 */

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        if(orderService.getOrderById(id) != null){
            orderService.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
