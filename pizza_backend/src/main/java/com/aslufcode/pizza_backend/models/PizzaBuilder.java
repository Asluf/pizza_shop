package com.aslufcode.pizza_backend.models;

public class PizzaBuilder {
    private String name;
    private String crust;
    private String sauce;
    private String cheese;
    private StringBuilder toppings = new StringBuilder();
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

    public PizzaBuilder setCheese(String cheese) {
        this.cheese = cheese;
        switch (cheese.toLowerCase()) {
            case "mozzarella":
                this.basePrice += 150.0;
                break;
            case "cheddar":
                this.basePrice += 200.0;
                break;
            case "parmesan":
                this.basePrice += 250.0;
                break;
            case "gouda":
                this.basePrice += 300.0;
                break;
            default:
                break;
        }
        return this;
    }

    public PizzaBuilder addTopping(String topping) {
        if (this.toppings.length() > 0) {
            this.toppings.append(", ");
        }
        this.toppings.append(topping);
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
            if (this.cheese != null)
                generatedName.append(this.cheese).append(" ");
            if (this.toppings.length() > 0) {
                generatedName.append("with ").append(this.toppings);
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

    public Pizza build() {
        setNameAutomatically();
        return new Pizza(name, crust, sauce, cheese, toppings.toString(), basePrice);
    }
}
