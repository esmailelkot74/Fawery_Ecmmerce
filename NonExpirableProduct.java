package model;

public class NonExpirableProduct extends Product {
    public NonExpirableProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public boolean isExpired() {
        return false;
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