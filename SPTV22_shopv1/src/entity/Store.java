/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class Store {
    private HashMap<Product, Integer> products;

    public Store() {
        products = new HashMap<>();
        // Добавляем некоторые товары в магазин
        products.put(new Product("Молоко", 2.5, 10), 10);
        products.put(new Product("Хлеб", 1.0, 20), 20);
        products.put(new Product("Яйца", 3.0, 15), 15);
    }

    public void displayProducts() {
        System.out.println("Товары в магазине:");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(product.getName() + " - $" + product.getPrice() + " x " + quantity);
        }
    }

    public Product getProduct(int index) {
        int i = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (i == index) {
                return entry.getKey();
            }
            i++;
        }
        return null;
    }

    public void reduceProductQuantity(Product product) {
        if (products.containsKey(product)) {
            int quantity = products.get(product);
            if (quantity > 0) {
                products.put(product, quantity - 1);
            } else {
                System.out.println("Товар закончился на складе!");
            }
        }
    }
}