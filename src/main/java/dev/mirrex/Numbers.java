package dev.mirrex;

import java.util.Scanner;

public class Numbers {

    static void whatNumberIsGreater() {
        System.out.print("Please, enter the 2 numbers: ");
        try (Scanner scanner = new Scanner(System.in)) {
            if (scanner.hasNextInt()) {
                int number1 = scanner.nextInt();
                int number2 = scanner.nextInt();

                if (number1 == number2) {
                    System.out.print("These numbers are equal");
                } else {
                    String greaterNumber = number1 > number2 ? number1 + " is greater" : number2 + " is greater";
                    System.out.println(greaterNumber);
                }
            }
        }
    }
}
