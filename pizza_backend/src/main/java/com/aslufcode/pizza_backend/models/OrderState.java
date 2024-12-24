package com.aslufcode.pizza_backend.models;

public interface OrderState {
    void next(Order order);
    void prev(Order order);
    String getStatus();
}

