package com.aslufcode.pizza_backend.models;

public class Feedback {
    private String userEmail;
    private int pizzaId;
    private String pizzaName;
    private int orderId;
    private String message;

    public Feedback(String userEmail, int pizzaId, String pizzaName, int orderId, String message) {
        this.userEmail = userEmail;
        this.pizzaId = pizzaId;
        this.pizzaName = pizzaName;
        this.orderId = orderId;
        this.message = message;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
