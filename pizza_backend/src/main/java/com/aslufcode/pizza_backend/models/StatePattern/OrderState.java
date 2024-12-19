package com.aslufcode.pizza_backend.models.StatePattern;

public interface OrderState {
    void nextState();
    void notifyUser();
}
