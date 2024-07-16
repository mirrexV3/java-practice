package dev.mirrex.services;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        List<Integer> primeNumbers = IntStream
                .rangeClosed(startSequence, endSequence)
                .filter(PrimeNumbers::isPrime)
                .boxed()
                .collect(Collectors.toList());

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
