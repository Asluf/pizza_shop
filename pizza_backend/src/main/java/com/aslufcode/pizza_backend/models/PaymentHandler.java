package com.aslufcode.pizza_backend.models;

public class PaymentHandler extends OrderHandler {
    @Override
    public void handle(Order order) {
        System.out.println("Payment processed for order: " + order.getPizzaName());
        if (nextHandler != null) {
            nextHandler.handle(order);
        }
    }
}
