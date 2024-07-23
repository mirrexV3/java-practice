package dev.mirrex.utils;

import dev.mirrex.exception.OverflowException;

import java.util.Scanner;

public class Utils {
    public static boolean isValidInput(Scanner input) throws OverflowException {
        if (!input.hasNextInt()) {
            throw new OverflowException(
                    """
                    Invalid input. Try again. Only positive numbers are allowed and/or inclusive in range.
                    """);
        }
        return true;
    }
}
