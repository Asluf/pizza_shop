package com.aslufcode.pizza_backend.services;

import com.aslufcode.pizza_backend.observer.OrderObserver;
import com.aslufcode.pizza_backend.observer.OrderSubject;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderSubject orderSubject = new OrderSubject();

    public void addObserver(OrderObserver observer) {
        orderSubject.attach(observer);
    }

    public void removeObserver(OrderObserver observer) {
        orderSubject.detach(observer);
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        orderSubject.setOrderStatus(newStatus);
    }
}
