package com.aslufcode.pizza_backend.models;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {
    private String name;
    private String crust;
    private String sauce;
    private List<String> toppings = new ArrayList<>();
    private double basePrice = 1000.0;

    public PizzaBuilder setCrust(String crust) {
        this.crust = crust;
        switch (crust.toLowerCase()) {
            case "thin crust":
                this.basePrice += 200.0;
                break;
            case "thick crust":
                this.basePrice += 300.0;
                break;
            case "stuffed crust":
                this.basePrice += 500.0;
                break;
            case "kurakkan crust":
                this.basePrice += 400.0;
                break;
            case "rice flour crust":
                this.basePrice += 350.0;
                break;
            default:
                break;
        }
        return this;
    }

    public PizzaBuilder setSauce(String sauce) {
        this.sauce = sauce;
        switch (sauce.toLowerCase()) {
            case "tomato basil":
                this.basePrice += 100.0;
                break;
            case "bbq":
                this.basePrice += 150.0;
                break;
            case "spicy peri peri":
                this.basePrice += 200.0;
                break;
            case "coconut sambol sauce":
                this.basePrice += 150.0;
                break;
            case "tikka masala sauce":
                this.basePrice += 250.0;
                break;
            default:
                break;
        }
        return this;
    }

    public PizzaBuilder addTopping(String topping) {
        this.toppings.add(topping);
        this.basePrice += 100.0;
        return this;
    }

    public PizzaBuilder setNameAutomatically() {
        if (this.name == null || this.name.isEmpty()) {
            StringBuilder generatedName = new StringBuilder();
            if (this.crust != null)
                generatedName.append(this.crust).append(" ");
            if (this.sauce != null)
                generatedName.append(this.sauce).append(" ");
            if (!this.toppings.isEmpty()) {
                generatedName.append("with ");
                for (String topping : this.toppings) {
                    generatedName.append(topping).append(", ");
                }
                // Remove the trailing comma and space
                generatedName.setLength(generatedName.length() - 2);
            } else {
                generatedName.append("Plain");
            }
            this.name = generatedName.toString();
        }
        return this;
    }

    public PizzaBuilder setName(String name) {
        this.name = name;
        return this;
    }

    // Build the final Pizza object
    public Pizza build() {
        setNameAutomatically();
        return new Pizza(name, crust, sauce, toppings, basePrice);
    }
}
