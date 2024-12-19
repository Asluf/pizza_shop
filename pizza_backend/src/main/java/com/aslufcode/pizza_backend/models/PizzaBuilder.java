package com.aslufcode.pizza_backend.models;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {
    private String crust;
    private String sauce;
    private String cheese;
    private List<String> toppings = new ArrayList<>();

    public PizzaBuilder setCrust(String crust) {
        this.crust = crust;
        return this;
    }

    public PizzaBuilder setSauce(String sauce) {
        this.sauce = sauce;
        return this;
    }

    public PizzaBuilder setCheese(String cheese) {
        this.cheese = cheese;
        return this;
    }

    public PizzaBuilder addTopping(String topping) {
        this.toppings.add(topping);
        return this;
    }

    public Pizza build() {
        Pizza pizza = new Pizza();
        pizza.setCrust(this.crust);
        pizza.setSauce(this.sauce);
        pizza.setCheese(this.cheese);
        pizza.setToppings(this.toppings);
        return pizza;
    }
}
