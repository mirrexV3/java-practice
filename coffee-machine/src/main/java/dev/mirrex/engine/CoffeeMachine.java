package dev.mirrex.engine;

import dev.mirrex.drink.DrinkType;
import dev.mirrex.exception.OverflowException;
import dev.mirrex.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachine {

    private static final int MAX_WATER_CAPACITY = 1000;

    private static final int MAX_MILK_CAPACITY = 500;

    private static final int MAX_COFFEE_BEANS = 200;

    private static final int MAX_CLEAN_LIMIT = 10;

    private static final int ESPRESSO_WATER = 50;

    private static final int ESPRESSO_COFFEE_BEANS = 10;

    private static final int CAPPUCCINO_WATER = 100;

    private static final int CAPPUCCINO_MILK = 50;

    private static final int CAPPUCCINO_COFFEE_BEANS = 15;


    private static int waterLevel;

    private static int milkLevel;

    private static int coffeeBeans;

    private static int dirtyCount;

    private static boolean isOn;

    private static boolean needsCleaning;

    private static final Map<DrinkType, String> RECIPES = new HashMap<>();

    private static List<String> profiles = new ArrayList<>();

    public static List<String> getProfiles() {
        return profiles;
    }

    public static void setProfile(String profile) {
        profiles.add(profile);
    }

    static {
        RECIPES.put(DrinkType.ESPRESSO, "Espresso Recipe: " + ESPRESSO_WATER + "ml water, " +
                " " + ESPRESSO_COFFEE_BEANS + "g coffee beans.");
        RECIPES.put(DrinkType.CAPPUCCINO, "Cappuccino Recipe: " + CAPPUCCINO_WATER + "ml water, " +
                " " + CAPPUCCINO_MILK + "ml milk, " + CAPPUCCINO_COFFEE_BEANS + "g coffee beans.");
    }

    public static void turnOn() {
        isOn = true;
        Logger.log("Coffee machine turned on.");
    }

    public static void turnOff() {
        isOn = false;
        Logger.log("Coffee machine turned off.");
    }

    public static void addWater(int amount) throws OverflowException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (waterLevel + amount > MAX_WATER_CAPACITY) {
            throw new OverflowException("Water overflow.");
        }
        waterLevel += amount;
        Logger.log("Added " + amount + "ml of water.");
    }

    public static void addMilk(int amount) throws OverflowException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (milkLevel + amount > MAX_MILK_CAPACITY) {
            throw new OverflowException("Milk overflow.");
        }
        milkLevel += amount;
        Logger.log("Added " + amount + "ml of milk.");
    }

    public static void addCoffeeBeans(int amount) throws OverflowException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (coffeeBeans + amount > MAX_COFFEE_BEANS) {
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
            throw new IllegalStateException("Machine does not need cleaning.");
        }
    }

    public static void makeDrink(DrinkType type, int cups) {
        validateMachineState(cups);

        switch (type) {
            case ESPRESSO:
                makeEspresso(cups);
                break;
            case CAPPUCCINO:
                makeCappuccino(cups);
                break;
            default:
                throw new IllegalArgumentException("Unknown drink type.");
        }

        dirtyCount += cups;
        Logger.log("Made " + cups + " cups of " + type + ".");
    }

    private static void validateMachineState(int cups) {
        if (!isOn) {
            throw new IllegalStateException("Machine is off.");
        }
        if (cups <= 0) {
            throw new IllegalArgumentException("Number of cups must be positive.");
        }
        if (dirtyCount >= MAX_CLEAN_LIMIT) {
            needsCleaning = true;
            throw new IllegalStateException("Machine needs cleaning.");
        }
    }

    private static void makeEspresso(int cups) throws IllegalArgumentException {
        if (waterLevel < ESPRESSO_WATER * cups || coffeeBeans < ESPRESSO_COFFEE_BEANS * cups) {
            throw new IllegalArgumentException("Not enough ingredients for Espresso.");
        }
        waterLevel -= ESPRESSO_WATER * cups;
        coffeeBeans -= ESPRESSO_COFFEE_BEANS * cups;
    }

    private static void makeCappuccino(int cups) throws IllegalArgumentException {
        if (waterLevel < CAPPUCCINO_WATER * cups || milkLevel < CAPPUCCINO_MILK *
                cups || coffeeBeans < CAPPUCCINO_COFFEE_BEANS * cups) {
            throw new IllegalArgumentException("Not enough ingredients for Cappuccino.");
        }
        waterLevel -= CAPPUCCINO_WATER * cups;
        milkLevel -= CAPPUCCINO_MILK * cups;
        coffeeBeans -= CAPPUCCINO_COFFEE_BEANS * cups;
    }

    public static String getRecipe(DrinkType type) {
        return RECIPES.get(type);
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

    public static boolean isMachineOn() {
        return isOn;
    }

    public static boolean needsCleaning() {
        return needsCleaning;
    }
}
