/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.commerce.system;

/**
 *
 * @author User
 */
public class Customer {
    private String name;
    private double balance;
    private Cart cart;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public double getBalance() {
        return balance;
    }
     public void deduct(double amount) {
        this.balance -= amount;
    }

    public String getName() {
        return name;
    }
    
}
