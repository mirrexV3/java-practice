package dev.mirrex.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {

    private static final List<String> products = new ArrayList<>();

    private static final String RULES = """
            Hi, there. This is your shopping cart.

            Enter "list" to see a list of added products
            or "add" to add products to the cart.
            Enter "exit" to exit the program.
            Enter "done" to stop adding products and return to the main menu.

            """;

    public static void displayCart() {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print(RULES);

            while (true) {
                System.out.print("Choose your side: ");
                String choice = scanner.nextLine().trim();

                if ("list".equalsIgnoreCase(choice)) {
                    showList();
                } else if ("add".equalsIgnoreCase(choice)) {
                    addToCart();
                } else if ("exit".equalsIgnoreCase(choice)) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please, enter 'list', 'add', or 'exit'.");
                }
            }

            System.out.println("Final list of products in your cart:");
            if (isCartEmpty()) {
                System.out.println("Your cart is empty.");
            } else {
                products.forEach(System.out::println);
            }
        }
    }

    private static void addToCart() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter product name (or type 'done' to stop adding): ");
            String productName = scanner.nextLine().trim();
            if ("done".equalsIgnoreCase(productName)) {
                break;
            } else {
                products.add(productName);
                System.out.println(productName + " added to the cart.");
            }
        }
    }

    private static void showList() {
        if (isCartEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Products in your cart:");
            products.forEach(System.out::println);
        }
    }

    private static boolean isCartEmpty() {
        return products.isEmpty();
    }
}
