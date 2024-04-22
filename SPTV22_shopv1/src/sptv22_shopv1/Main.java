/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entity.Cart;
import entity.Product;
import entity.Store;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

// Главный класс приложения
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store();
        Cart cart = new Cart();

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Просмотреть товары в магазине");
            System.out.println("2. Добавить товар в корзину");
            System.out.println("3. Посмотреть содержимое корзины");
            System.out.println("4. Завершить покупки");
            System.out.println("5. Сохранить корзину в файл");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    store.displayProducts();
                    break;
                case 2:
                    System.out.println("Введите номер товара, который хотите добавить:");
                    int productIndex = scanner.nextInt();
                    Product selectedProduct = store.getProduct(productIndex - 1); // Индексация с 1
                    if (selectedProduct != null && selectedProduct.getQuantity() > 0) {
                        cart.addProduct(selectedProduct);
                        store.reduceProductQuantity(selectedProduct);
                        System.out.println(selectedProduct.getName() + " добавлен в корзину.");
                    } else {
                        System.out.println("Товара с таким номером нет или он закончился на складе.");
                    }
                    break;
                case 3:
                    cart.displayCart();
                    break;
                case 4:
                    System.out.println("Спасибо за покупки!");
                    isRunning = false;
                    break;
                case 5:
                    saveCartToFile(cart);
                    break;
                default:
                    System.out.println("Неверный ввод, попробуйте еще раз.");
            }
        }

        scanner.close();
    }

    private static void saveCartToFile(Cart cart) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("cart.txt"))) {
            writer.println("Содержимое корзины:");
            for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                writer.println(product.getName() + " - $" + product.getPrice() + " x " + quantity);
            }
            writer.println("Общая сумма: $" + cart.getTotalPrice());
            System.out.println("Корзина сохранена в файл 'cart.txt'.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении корзины: " + e.getMessage());
        }
    }
}