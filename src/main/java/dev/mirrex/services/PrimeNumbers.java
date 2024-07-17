package dev.mirrex.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeNumbers {

    public static void primeNumbersSequence() {
        int startSequence;
        int endSequence;
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Please, enter min number range: ");
            startSequence = scanner.nextInt();
            System.out.print("Please, enter max number range: ");
            endSequence = scanner.nextInt();
        }

        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = startSequence; i <= endSequence; i++) {
            if (isPrime(i)) {
                Integer integer = i;
                primeNumbers.add(integer);
            }
        }

        System.out.println(primeNumbers);
    }

    private static boolean isPrime(int number) {
        double root = Math.sqrt(number);

        if (number <= 1) {
            return false;
        }

        for (int initialValue = 2; initialValue <= (int) Math.floor(root); initialValue++) {
            if (number % initialValue == 0) {
                return false;
            }
        }

        return true;
    }
}
