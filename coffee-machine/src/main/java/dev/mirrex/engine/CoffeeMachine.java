package dev.mirrex.engine;

import dev.mirrex.drink.DrinkType;
import dev.mirrex.exception.OverflowException;
import dev.mirrex.logger.Logger;
import java.util.ArrayList;
import java.util.List;

import static dev.mirrex.drink.DrinkType.getCappuccinoCoffeeBeans;
import static dev.mirrex.drink.DrinkType.getCappuccinoMilk;
import static dev.mirrex.drink.DrinkType.getCappuccinoWater;
import static dev.mirrex.drink.DrinkType.getEspressoCoffeeBeans;
import static dev.mirrex.drink.DrinkType.getEspressoWater;
import static dev.mirrex.drink.DrinkType.getRecipes;

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

    public static void addWater(int amount) throws OverflowException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (waterLevel + amount > getMaxWaterCapacity()) {
            throw new OverflowException("Water overflow.");
        }
        waterLevel += amount;
        Logger.log("Added " + amount + "ml of water.");
    }

    public static void addMilk(int amount) throws OverflowException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (milkLevel + amount > getMaxMilkCapacity()) {
            throw new OverflowException("Milk overflow.");
        }
        milkLevel += amount;
        Logger.log("Added " + amount + "ml of milk.");
    }

    public static void addCoffeeBeans(int amount) throws OverflowException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
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
        if (dirtyCount >= getMaxCleanLimit()) {
            needsCleaning = true;
            throw new IllegalStateException("Machine needs cleaning.");
        }
    }

    private static void makeEspresso(int cups) throws IllegalArgumentException {
        if (waterLevel < getEspressoWater() * cups || coffeeBeans < getEspressoCoffeeBeans() * cups) {
            throw new IllegalArgumentException("Not enough ingredients for Espresso.");
        }
        waterLevel -= getEspressoWater() * cups;
        coffeeBeans -= getEspressoCoffeeBeans() * cups;
    }

    private static void makeCappuccino(int cups) throws IllegalArgumentException {
        if (waterLevel < getCappuccinoWater() * cups || milkLevel < getCappuccinoMilk() *
                cups || coffeeBeans < getCappuccinoCoffeeBeans() * cups) {
            throw new IllegalArgumentException("Not enough ingredients for Cappuccino.");
        }
        waterLevel -= getCappuccinoWater() * cups;
        milkLevel -= getCappuccinoMilk() * cups;
        coffeeBeans -= getCappuccinoCoffeeBeans() * cups;
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

    public static boolean isMachineOn() {
        return isOn;
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

    public static int getDirtyCount() {
        return dirtyCount;
    }

}
