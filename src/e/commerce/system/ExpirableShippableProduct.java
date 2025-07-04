/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.commerce.system;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class ExpirableShippableProduct extends ExpirableProduct implements Shippable {

    private double weight;

    public ExpirableShippableProduct(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity, expiryDate);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
    @Override
    public String getName() {
        return name;
    }

}
