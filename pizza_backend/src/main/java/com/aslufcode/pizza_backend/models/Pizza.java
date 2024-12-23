package com.aslufcode.pizza_backend.models;

import java.util.List;

public class Pizza {
    private String name;
    private String crust;
    private String sauce;
    private List<String> toppings;
    private double price;

    public Pizza(String name, String crust, String sauce, List<String> toppings, double price) {
        this.name = name;
        this.crust = crust;
        this.sauce = sauce;
        this.toppings = toppings;
        this.price = price;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
