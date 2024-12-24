package com.aslufcode.pizza_backend.models;

public class StockCheckHandler extends OrderHandler {
    @Override
    public void handle(Order order) {
        System.out.println("Stock checked for order: " + order.getPizzaName());
        if (nextHandler != null) {
            nextHandler.handle(order);
        }
    }
}
