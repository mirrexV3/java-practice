package dev.mirrex;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumbers {

    public static void primeNumbersSequence() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please, enter min number range: ");
        int startSequence = scanner.nextInt();
        System.out.print("Please, enter max number range: ");
        int endSequence = scanner.nextInt();

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
