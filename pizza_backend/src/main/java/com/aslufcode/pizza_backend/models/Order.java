package com.aslufcode.pizza_backend.models;

public class Order {
    private int orderId;
    private String userEmail;
    private String mobile;
    private int pizzaId;
    private String pizzaName;
    private String crust;
    private String sauce;
    private String cheese;
    private String toppings;
    private double price;
    private int quantity;
    private OrderState state;
    private boolean specialPackaging;

    public Order() {
        this.state = new PlacedState();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
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

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void setStateFromString(String status) {
        switch (status.toLowerCase()) {
            case "placed":
                this.state = new PlacedState();
                break;
            case "preparing":
                this.state = new PreparingState();
                break;
            case "out for delivery":
                this.state = new OutForDeliveryState();
                break;
            case "delivered":
                this.state = new DeliveredState();
                break;
            default:
                throw new IllegalArgumentException("Unknown order status: " + status);
        }
    }

    public void nextState() {
        state.next(this);
    }

    public void prevState() {
        state.prev(this);
    }

    public String getStatus() {
        return state.getStatus();
    }

    public boolean isSpecialPackaging() {
        return specialPackaging;
    }

    public void setSpecialPackaging(boolean specialPackaging) {
        this.specialPackaging = specialPackaging;
    }
}