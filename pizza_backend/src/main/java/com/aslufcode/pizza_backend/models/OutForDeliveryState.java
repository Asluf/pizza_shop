package com.aslufcode.pizza_backend.models;

public class OutForDeliveryState implements OrderState {
    @Override
    public void next(Order order) {
        order.setState(new DeliveredState());
    }

    @Override
    public void prev(Order order) {
        order.setState(new PreparingState());
    }

    @Override
    public String getStatus() {
        return "Out for Delivery";
    }
}
