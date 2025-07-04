package model;

import java.time.LocalDate;

public class NonShippableProduct extends Product implements Expirable {
    private LocalDate expiryDate;
    private boolean expirable;

    public NonShippableProduct(String name, double price, int quantity) {
        super(name, price, quantity);
        this.expirable = false;
    }

    public NonShippableProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
        this.expirable = true;
    }

    @Override
    public boolean isExpired() {
        if (!expirable) return false;
        return expiryDate.isBefore(LocalDate.now());
    }

    @Override
    public boolean requiresShipping() {
        return false;
    }

    @Override
    public double getWeight() {
        return 0;
    }
} 