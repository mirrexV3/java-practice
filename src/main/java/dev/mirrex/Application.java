package dev.mirrex;

import dev.mirrex.services.Numbers;
import dev.mirrex.services.PrimeNumbers;
import dev.mirrex.services.ShoppingCart;
import dev.mirrex.services.Values;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Please enter the program number and press Enter.");
            System.out.println("""
                    1 - Min and Max value of primitives
                    2 - What number is greater
                    3 - Prime numbers sequence
                    4 - Shopping cart
                    """);

            System.out.print("Your choice: ");
            String userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1":
                    Values.displayMinAndMaxValue();
                    break;
                case "2":
                    Numbers.whatNumberIsGreater();
                    break;
                case "3":
                    PrimeNumbers.primeNumbersSequence();
                    break;
                case "4":
                    ShoppingCart.displayCart();
                    break;
                default:
                    System.out.println("Nonexistent selection. Please, try again.");
                    break;
            }
        }
    }
}
