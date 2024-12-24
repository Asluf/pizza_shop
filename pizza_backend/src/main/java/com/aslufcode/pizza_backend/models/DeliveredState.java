package com.aslufcode.pizza_backend.models;

public class DeliveredState implements OrderState {
    @Override
    public void next(Order order) {
        System.out.println("The order has already been delivered.");
    }

    @Override
    public void prev(Order order) {
        order.setState(new OutForDeliveryState());
    }

    @Override
    public String getStatus() {
        return "Delivered";
    }
}
