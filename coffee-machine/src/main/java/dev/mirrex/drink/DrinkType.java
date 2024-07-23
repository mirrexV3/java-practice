package dev.mirrex.drink;

import java.util.HashMap;
import java.util.Map;

public enum DrinkType {
    ESPRESSO(50, 10, 0), CAPPUCCINO(100, 50, 15);

    private static final Map<DrinkType, String> RECIPES = new HashMap<>();

    private final int milk;

    private final int coffeeBeans;

    private final int water;

    DrinkType(int milk, int coffeeBeans, int water) {
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.water = water;
    }

    public static Map<DrinkType, String> getRecipes() {
        return RECIPES;
    }

    static {
        RECIPES.put(DrinkType.ESPRESSO, "Espresso Recipe: " + ESPRESSO.water + "ml water, " +
                " " + ESPRESSO.coffeeBeans + "g coffee beans, " + ESPRESSO.milk + "ml milk.");
        RECIPES.put(DrinkType.CAPPUCCINO, "Cappuccino Recipe: " + CAPPUCCINO.water + "ml water, " +
                " " + CAPPUCCINO.milk + "ml milk, " + CAPPUCCINO.coffeeBeans + "g coffee beans.");
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public int getMilk() {
        return milk;
    }

    public int getWater() {
        return water;
    }
}
