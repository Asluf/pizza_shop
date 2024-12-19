package com.aslufcode.pizza_backend.models;

public class DigitalWalletPayment implements PaymentStrategy {
    private String walletId;

    public DigitalWalletPayment(String walletId) {
        this.walletId = walletId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Digital Wallet: " + walletId);
    }
}
