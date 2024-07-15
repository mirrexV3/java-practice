package dev.mirrex;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String userChoice;
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Please enter the program number and press Enter.");
            System.out.println("""
                    1 - Min and Max value of primitives
                    2 - What number is greater
                    """);

            System.out.print("Your choice: ");
            userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1":
                    Values.displayMinAndMaxValue();
                    break;
                case "2":
                    Numbers.whatNumberIsGreater();
                    break;
                default:
                    System.out.println("Nonexistent selection. Please, try again.");
                    break;
            }
        }
    }
}
