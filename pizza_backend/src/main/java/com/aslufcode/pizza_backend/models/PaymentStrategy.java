
package com.aslufcode.pizza_backend.models;

public interface PaymentStrategy {
    void pay(double amount);
}
