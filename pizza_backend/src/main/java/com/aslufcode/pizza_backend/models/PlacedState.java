package com.aslufcode.pizza_backend.models;

public class PlacedState implements OrderState {
    @Override
    public void next(Order order) {
        order.setState(new PreparingState());
    }

    @Override
    public void prev(Order order) {
        System.out.println("The order is already in its initial state.");
    }

    @Override
    public String getStatus() {
        return "Placed";
    }
}
