package dev.mirrex.engine;

import dev.mirrex.drink.DrinkType;
import dev.mirrex.exception.OverflowException;
import dev.mirrex.logger.Logger;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static dev.mirrex.drink.DrinkType.CAPPUCCINO;
import static dev.mirrex.drink.DrinkType.ESPRESSO;
import static dev.mirrex.drink.DrinkType.getRecipes;
import static dev.mirrex.utils.Utils.isValidInput;

public class CoffeeMachine {

    private static final int MAX_WATER_CAPACITY = 1000;

    private static final int MAX_MILK_CAPACITY = 500;

    private static final int MAX_COFFEE_BEANS = 200;

    private static final int MAX_CLEAN_LIMIT = 10;

    private static int waterLevel;

    private static int milkLevel;

    private static int coffeeBeans;

    private static int dirtyCount;

    private static boolean isOn;

    private static boolean needsCleaning;

    private static final List<String> profiles = new ArrayList<>();

    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> getProfiles() {
        return profiles;
    }

    public static void setProfile(String profile) {
        profiles.add(profile);
    }

    public static void turnOn() {
        isOn = true;
        Logger.log("Coffee machine turned on.");
    }

    public static void turnOff() {
        isOn = false;
        Logger.log("Coffee machine turned off.");
    }

    public static void addWater() throws OverflowException {
        System.out.print("Введите количество воды (мл): ");

        if (!SCANNER.hasNextInt()) {
            throw new OverflowException("Amount must be number.");
        }

        int amount = SCANNER.nextInt();
        if (amount <= 0) {
            throw new OverflowException("Amount must be positive.");
        }
        if (waterLevel + amount > getMaxWaterCapacity()) {
            throw new OverflowException("Water overflow.");
        }
        waterLevel += amount;
        Logger.log("Added " + amount + "ml of water.");
    }

    public static void addMilk() throws OverflowException {
        System.out.print("Введите количество молока (мл): ");

        if (!SCANNER.hasNextInt()) {
            throw new OverflowException("Amount must be number.");
        }

        int amount = SCANNER.nextInt();
        if (amount <= 0) {
            throw new OverflowException("Amount must be positive.");
        }
        if (milkLevel + amount > getMaxMilkCapacity()) {
            throw new OverflowException("Milk overflow.");
        }
        milkLevel += amount;
        Logger.log("Added " + amount + "ml of milk.");
    }

    public static void addCoffeeBeans() throws OverflowException {
        System.out.print("Введите количество кофе (г): ");

        if (!SCANNER.hasNextInt()) {
            throw new OverflowException("Amount must be number.");
        }

        int amount = SCANNER.nextInt();
        if (amount <= 0) {
            throw new OverflowException("Amount must be positive.");
        }
        if (coffeeBeans + amount > getMaxCoffeeBeans()) {
            throw new OverflowException("Coffee beans overflow.");
        }
        coffeeBeans += amount;
        Logger.log("Added " + amount + " grams of coffee beans.");
    }

    public static void cleanMachine() throws IllegalStateException {
        if (needsCleaning) {
            Logger.log("Machine cleaned.");
            needsCleaning = false;
            dirtyCount = 0;
        } else {
            System.out.println("Machine does not need cleaning.");
        }
    }

    public static void makeDrink() throws OverflowException, InputMismatchException {
        System.out.print("Какой напиток вы хотите приготовить? (1 - Эспрессо, 2 - Капучино): ");
        if (isValidInput(SCANNER)) {
            int drinkChoice = SCANNER.nextInt();
            if (drinkChoice <= 0 || drinkChoice > DrinkType.values().length) {
                System.out.println("Must be positive and in range numbers of drinks");
            }
            System.out.print("Введите количество кружек: ");
            if (isValidInput(SCANNER)) {
                int cups = SCANNER.nextInt();
                DrinkType type = drinkChoice == 1 ? DrinkType.ESPRESSO : DrinkType.CAPPUCCINO;
                validateMachineState(cups);

                switch (type) {
                    case ESPRESSO -> makeEspresso(cups);
                    case CAPPUCCINO -> makeCappuccino(cups);
                    default -> System.out.println("Unknown drink type.");
                }

                dirtyCount += cups;
                Logger.log("Made " + cups + " cups of " + type + ".");
            }
        }
    }

    public static void makeThreeCups() throws OverflowException, InputMismatchException {
        System.out.print("Какой напиток вы хотите приготовить? (1 - Эспрессо, 2 - Капучино): ");
        if (isValidInput(SCANNER)) {
            int drinkChoice = SCANNER.nextInt();
            if (drinkChoice <= 0 || drinkChoice > DrinkType.values().length) {
                System.out.println("Must be positive and in range numbers of drinks");
            }
            int cupsAmount = 3;
            DrinkType type = drinkChoice == 1 ? DrinkType.ESPRESSO : DrinkType.CAPPUCCINO;
            validateMachineState(cupsAmount);

            switch (type) {
                case ESPRESSO -> makeEspresso(3);
                case CAPPUCCINO -> makeCappuccino(3);
                default -> System.out.println("Unknown drink type.");
            }

            dirtyCount += cupsAmount;
            Logger.log("Made " + cupsAmount + " cups of " + type + ".");
        }
    }

    private static void validateMachineState(int cups) {
        if (!isOn) {
            throw new IllegalStateException("Machine is off.");
        }
        if (cups <= 0) {
            throw new IllegalArgumentException("Number of cups must be positive.");
        }
        if (CoffeeMachine.dirtyCount >= getMaxCleanLimit()) {
            needsCleaning = true;
            throw new IllegalStateException("Machine needs cleaning.");
        }
    }

    private static void makeEspresso(int cups) throws IllegalArgumentException, OverflowException {
        if (waterLevel < ESPRESSO.getWater() * cups || coffeeBeans < ESPRESSO.getCoffeeBeans() * cups) {
            throw new OverflowException("Not enough ingredients for Espresso.");
        }
        waterLevel -= ESPRESSO.getWater() * cups;
        coffeeBeans -= ESPRESSO.getCoffeeBeans() * cups;
    }

    private static void makeCappuccino(int cups) throws IllegalArgumentException, OverflowException {
        if (waterLevel < CAPPUCCINO.getWater() * cups || milkLevel < CAPPUCCINO.getMilk() *
                cups || coffeeBeans < CAPPUCCINO.getCoffeeBeans() * cups) {
            throw new OverflowException("Not enough ingredients for Cappuccino.");
        }
        waterLevel -= CAPPUCCINO.getWater() * cups;
        milkLevel -= CAPPUCCINO.getMilk() * cups;
        coffeeBeans -= CAPPUCCINO.getCoffeeBeans() * cups;
    }

    public static String getRecipe(DrinkType type) {
        return getRecipes().get(type);
    }

    public static int getWaterLevel() {
        return waterLevel;
    }

    public static int getMilkLevel() {
        return milkLevel;
    }

    public static int getCoffeeBeans() {
        return coffeeBeans;
    }

    public static boolean needsCleaning() {
        return needsCleaning;
    }

    public static int getMaxCoffeeBeans() {
        return MAX_COFFEE_BEANS;
    }

    public static int getMaxWaterCapacity() {
        return MAX_WATER_CAPACITY;
    }

    public static int getMaxCleanLimit() {
        return MAX_CLEAN_LIMIT;
    }

    public static int getMaxMilkCapacity() {
        return MAX_MILK_CAPACITY;
    }
}
