package com.aslufcode.pizza_backend.models;

public class PreparingState implements OrderState {
    @Override
    public void next(Order order) {
        order.setState(new OutForDeliveryState());
    }

    @Override
    public void prev(Order order) {
        order.setState(new PlacedState());
    }

    @Override
    public String getStatus() {
        return "Preparing";
    }
}
