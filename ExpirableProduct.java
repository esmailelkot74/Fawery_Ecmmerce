package model;

import java.time.LocalDate;

public class ExpirableProduct extends Product implements Expirable {
    private LocalDate expiryDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
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

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
} 