package dev.mirrex.drink;

import java.util.HashMap;
import java.util.Map;

public enum DrinkType {
    ESPRESSO, CAPPUCCINO;

    private static final int ESPRESSO_WATER = 50;

    private static final int ESPRESSO_COFFEE_BEANS = 10;

    private static final int CAPPUCCINO_WATER = 100;

    private static final int CAPPUCCINO_MILK = 50;

    private static final int CAPPUCCINO_COFFEE_BEANS = 15;

    private static final Map<DrinkType, String> RECIPES = new HashMap<>();

    public static Map<DrinkType, String> getRECIPES() {
        return RECIPES;
    }

    static {
        RECIPES.put(DrinkType.ESPRESSO, "Espresso Recipe: " + getEspressoWater() + "ml water, " +
                " " + getEspressoCoffeeBeans() + "g coffee beans.");
        RECIPES.put(DrinkType.CAPPUCCINO, "Cappuccino Recipe: " + getCappuccinoWater() + "ml water, " +
                " " + getCappuccinoMilk() + "ml milk, " + getCappuccinoCoffeeBeans() + "g coffee beans.");
    }

    public static int getCappuccinoCoffeeBeans() {
        return CAPPUCCINO_COFFEE_BEANS;
    }

    public static int getCappuccinoMilk() {
        return CAPPUCCINO_MILK;
    }

    public static int getCappuccinoWater() {
        return CAPPUCCINO_WATER;
    }

    public static int getEspressoCoffeeBeans() {
        return ESPRESSO_COFFEE_BEANS;
    }

    public static int getEspressoWater() {
        return ESPRESSO_WATER;
    }
}
