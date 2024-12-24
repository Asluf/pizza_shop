package com.aslufcode.pizza_backend.controllers;

import com.aslufcode.pizza_backend.dao.OrderDao;
import com.aslufcode.pizza_backend.models.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderDao orderDao;

    @PostMapping("/create")
    public boolean createOrder(@RequestBody Order order) {
        return orderDao.saveOrder(order);
    }

    @GetMapping("/{userEmail}")
    public List<Order> getOrdersByUser(@PathVariable String userEmail) {
        return orderDao.getOrdersByUser(userEmail);
    }
}
