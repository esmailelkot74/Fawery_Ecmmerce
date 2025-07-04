package service;

import model.Product;
import service.ShippingItem;
import service.ShippingService;
import java.util.*;
import java.util.stream.Collectors;

public class CheckoutService {
    private static final double SHIPPING_FEE_PER_KG = 30.0; // Flat fee for demo
    private ShippingService shippingService = new ShippingService();

    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        double subtotal = 0;
        List<ShippingItem> shippingItems = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();
            if (product.isExpired()) {
                throw new IllegalStateException(product.getName() + " is expired");
            }
            if (qty > product.getQuantity()) {
                throw new IllegalStateException(product.getName() + " is out of stock");
            }
            subtotal += product.getPrice() * qty;
            if (product.requiresShipping()) {
                shippingItems.add(new ShippingItem() {
                    @Override
                    public String getName() {
                        return qty + "x " + product.getName();
                    }
                    @Override
                    public double getWeight() {
                        return product.getWeight() * qty;
                    }
                });
            }
        }
        double totalWeight = shippingItems.stream().mapToDouble(ShippingItem::getWeight).sum();
        double shippingFee = totalWeight > 0 ? SHIPPING_FEE_PER_KG : 0;
        double amount = subtotal + shippingFee;
        if (customer.getBalance() < amount) {
            throw new IllegalStateException("Customer's balance is insufficient.");
        }
        // Print shipment notice if needed
        if (!shippingItems.isEmpty()) {
            shippingService.ship(shippingItems);
        }
        // Print checkout receipt
        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            System.out.printf("%dx %s %.0f\n", entry.getValue(), entry.getKey().getName(), entry.getKey().getPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f\n", subtotal);
        System.out.printf("Shipping %.0f\n", shippingFee);
        System.out.printf("Amount %.0f\n", amount);
        // Deduct from customer balance and update product stock
        customer.setBalance(customer.getBalance() - amount);
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            entry.getKey().setQuantity(entry.getKey().getQuantity() - entry.getValue());
        }
        System.out.printf("Customer balance after payment: %.0f\n", customer.getBalance());
        System.out.println("END.");
        cart.clear();
    }
} 