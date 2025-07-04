import model.*;
import service.CheckoutService;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create products
        Product cheese = new ShippableProduct("Cheese 200g", 100, 5, 0.2);
        Product biscuits = new ShippableProduct("Biscuits 700g", 150, 2, 0.7);
        Product tv = new ShippableProduct("TV", 5000, 3, 8.0);
        Product scratchCard = new NonExpirableProduct("Mobile scratch card", 50, 10);
        // Expired product for testing
        Product expiredCheese = new ExpirableProduct("Old Cheese", 80, 2, LocalDate.now().minusDays(1));

        // Create customer
        Customer customer = new Customer("Ali", 1000);

        // Create cart
        Cart cart = new Cart();
        try {
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(scratchCard, 1);
            // Uncomment to test expired product error:
            // cart.add(expiredCheese, 1);
        } catch (Exception e) {
            System.out.println("Error adding to cart: " + e.getMessage());
        }

        // Checkout
        CheckoutService checkoutService = new CheckoutService();
        try {
            checkoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Checkout error: " + e.getMessage());
        }
    }
} 