/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.commerce.system;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Cart {

    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock.");
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double calculateSubtotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public double calculateShipping() {
        double shipping = 0;
        for (CartItem item : items) {
            if (item.product instanceof Shippable) {
                Shippable shipItem = (Shippable) item.product;
                shipping += 5 * shipItem.getWeight();
            }
        }
        return shipping;
    }

    public List<Shippable> getShippableItems() {
        List<Shippable> list = new ArrayList<>();
        for (CartItem item : items) {
            if (item.product instanceof Shippable) {
                list.add((Shippable) item.product);
            }
        }
        return list;
    }

}
