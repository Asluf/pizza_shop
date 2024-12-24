package com.aslufcode.pizza_backend.models;

public class DeliveryHandler extends OrderHandler {
    @Override
    public void handle(Order order) {
        System.out.println("Order is out for delivery: " + order.getPizzaName());
        if (nextHandler != null) {
            nextHandler.handle(order);
        }
    }
}