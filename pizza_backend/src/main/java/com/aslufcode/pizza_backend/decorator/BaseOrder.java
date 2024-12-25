package com.aslufcode.pizza_backend.decorator;

import com.aslufcode.pizza_backend.models.Order;

public class BaseOrder implements OrderComponent {
    private final Order order;

    public BaseOrder(Order order) {
        this.order = order;
    }

    @Override
    public double calculatePrice() {
        return order.getPrice();
    }

    @Override
    public String getDescription() {
        return order.getPizzaName();
    }

    public Order getOrder() {
        return order;
    }
}
