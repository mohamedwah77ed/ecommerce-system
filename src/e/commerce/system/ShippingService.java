/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.commerce.system;

import java.util.List;

/**
 *
 * @author User
 */
public class ShippingService {
     public void shipItems(List<Shippable> items) {
        System.out.println("Shipping the following items:");
        for (Shippable item : items) {
            System.out.println("- " + item.getName() + " | weight: " + item.getWeight());
        }
    }
    
}
