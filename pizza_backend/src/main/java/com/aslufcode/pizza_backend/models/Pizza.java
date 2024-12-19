package com.aslufcode.pizza_backend.models;

import java.util.List;

public class Pizza {
    private String crust;
    private String sauce;
    private String cheese;
    private List<String> toppings;

    // Getters and Setters
    public String getCrust() { return crust; }
    public void setCrust(String crust) { this.crust = crust; }
    public String getSauce() { return sauce; }
    public void setSauce(String sauce) { this.sauce = sauce; }
    public String getCheese() { return cheese; }
    public void setCheese(String cheese) { this.cheese = cheese; }
    public List<String> getToppings() { return toppings; }
    public void setToppings(List<String> toppings) { this.toppings = toppings; }

    @Override
    public String toString() {
        return "Pizza [Crust=" + crust + ", Sauce=" + sauce + ", Cheese=" + cheese + ", Toppings=" + toppings + "]";
    }
}
