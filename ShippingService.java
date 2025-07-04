package service;

import java.util.List;

public class ShippingService {
    public void ship(List<ShippingItem> items) {
        if (items == null || items.isEmpty()) return;
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        for (ShippingItem item : items) {
            System.out.printf("%s\n", item.getName());
            totalWeight += item.getWeight();
        }
        System.out.printf("Total package weight %.2fkg\n", totalWeight);
    }
} 