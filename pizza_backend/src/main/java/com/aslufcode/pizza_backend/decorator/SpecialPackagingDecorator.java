package com.aslufcode.pizza_backend.decorator;

public class SpecialPackagingDecorator implements OrderComponent {
    private final OrderComponent orderComponent;

    public SpecialPackagingDecorator(OrderComponent orderComponent) {
        this.orderComponent = orderComponent;
    }

    @Override
    public double calculatePrice() {
        return orderComponent.calculatePrice() + 100.0;
    }

    @Override
    public String getDescription() {
        return orderComponent.getDescription() + " with Special Packaging";
    }
}
