package dev.mirrex.services;

import java.util.Scanner;

public class Password {

    private static String password;

    public static void requestUserPassword() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Please, enter password: ");
            password = scanner.nextLine().trim();

            while (true) {
                System.out.print("Please, repeat the password: ");
                String repeatedPassword = scanner.nextLine().trim();
                if (isPasswordCorrect(repeatedPassword)) {
                    break;
                }
            }
        }

        System.out.println("Passwords match");
    }

    private static boolean isPasswordCorrect(String repeatedPassword) {
        return password.equals(repeatedPassword);
    }
}
