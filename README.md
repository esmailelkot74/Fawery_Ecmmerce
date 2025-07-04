# Fawry E-Commerce System

## Overview
This is a sample e-commerce system for the Fawry Rise Journey Full Stack Development Internship Challenge. It demonstrates:
- Product management (expirable, shippable, and regular products)
- Cart and checkout logic
- Shipping service
- Error handling for edge cases

## Project Structure
```
model/                # Product models and interfaces
service/              # Service classes and interfaces
Cart.java             # Cart logic
Customer.java         # Customer logic
Main.java             # Demo and test cases
```

## How to Build and Run
1. Make sure you have Java 8+ installed.
2. Compile all files:
   ```
   javac model/*.java service/*.java *.java
   ```
3. Run the main class:
   ```
   java Main
   ```

## Example Output
```
** Shipment notice **
2x Cheese 200g
1x Biscuits 700g
Total package weight 1.10kg
** Checkout receipt **
2x Cheese 200g 100
1x Biscuits 700g 150
1x Mobile scratch card 50
----------------------
Subtotal 350
Shipping 30
Amount 380
Customer balance after payment: 620
END.
```

## Notes
- All edge cases are handled (expired, out of stock, insufficient balance, empty cart).
- You can test more cases by editing `Main.java`. 