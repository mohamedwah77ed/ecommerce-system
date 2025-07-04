/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.commerce.system;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ECommerceSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Product cheese = new ExpirableShippableProduct("Cheese", 50, 5, LocalDate.of(2025, 7, 5), 1.0);
        Product tv = new ShippableProduct("TV", 5000, 2, 10.0);
        Product biscuits = new ExpirableProduct("Biscuits", 20, 10, LocalDate.of(2025, 7, 1));
        Product mobileCard = new Product("Mobile Card", 10, 100) {
        };
        Customer customer = new Customer("Mohamed", 6000);
        try {
            customer.getCart().addItem(cheese, 2);
            customer.getCart().addItem(tv, 1);
            customer.getCart().addItem(mobileCard, 3);
            // customer.getCart().addItem(biscuits, 1); // Uncomment to test expired
        } catch (Exception e) {
            System.out.println("Error while adding to cart: " + e.getMessage());
            return;
        }
        // CHECKOUT SECTION
        try {
            Cart cart = customer.getCart();

            if (cart.isEmpty()) {
                throw new Exception("Cart is empty!");
            }

            double totalWeight = 0;
            double subtotal = 0;
            List<Shippable> shippableItems = new ArrayList<>();

            System.out.println("** Shipment notice **");

            for (CartItem item : cart.getItems()) {
                Product product = item.product;

                // Check expiration
                if (product instanceof Expirable && ((Expirable) product).isExpired()) {
                    throw new Exception("Product " + product.getName() + " is expired.");
                }

                // Check quantity
                if (item.quantity > product.getQuantity()) {
                    throw new Exception("Product " + product.getName() + " is out of stock.");
                }

                // Calculate subtotal
                double itemTotal = item.getTotalPrice();
                subtotal += itemTotal;

                // Collect shippable items and print them
                if (product instanceof Shippable) {
                    double productWeight = ((Shippable) product).getWeight();
                    totalWeight += productWeight;
                    System.out.printf("%dx %-12s %.0fg%n", item.quantity, product.getName(), productWeight * 1000);
                    shippableItems.add((Shippable) product);
                }
            }

            if (!shippableItems.isEmpty()) {
                System.out.printf("Total package weight: %.1fkg%n", totalWeight);
            }

            // Calculate shipping
            double shipping = totalWeight * 5;
            double total = subtotal + shipping;

            // Check balance
            if (customer.getBalance() < total) {
                throw new Exception("Insufficient balance.");
            }

            // Deduct and update stock
            customer.deduct(total);
            for (CartItem item : cart.getItems()) {
                item.product.decreaseQuantity(item.quantity);
            }

            // Print receipt
            System.out.println("\n** Checkout receipt **");
            for (CartItem item : cart.getItems()) {
                double lineTotal = item.getTotalPrice();
                System.out.printf("%dx %-12s %.0f%n", item.quantity, item.product.getName(), lineTotal);
            }
            System.out.println("----------------------");
            System.out.printf("Subtotal         %.0f%n", subtotal);
            System.out.printf("Shipping         %.0f%n", shipping);
            System.out.printf("Amount           %.0f%n", total);
            System.out.printf("Customer balance %.0f%n", customer.getBalance());

            // Ship items
            if (!shippableItems.isEmpty()) {
                ShippingService shippingService = new ShippingService();
                shippingService.shipItems(shippableItems);
            }

        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }

    }

}
