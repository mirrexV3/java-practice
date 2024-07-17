package dev.mirrex.check;

import dev.mirrex.controller.CoffeeBeansController;
import dev.mirrex.controller.MilkController;
import dev.mirrex.controller.WaterController;

public class IngredientChecker {
    private final WaterController waterController;

    private final MilkController milkController;

    private final CoffeeBeansController coffeeBeansController;

    private final int cupsBeforeCleaning;

    private int cupsMade;

    private boolean needsCleaning;

    public IngredientChecker(int waterCapacity, int milkCapacity, int beansCapacity, int cupsBeforeCleaning) {
        this.waterController = new WaterController(waterCapacity);
        this.milkController = new MilkController(milkCapacity);
        this.coffeeBeansController = new CoffeeBeansController(beansCapacity);
        this.cupsBeforeCleaning = cupsBeforeCleaning;
        this.cupsMade = 0;
        this.needsCleaning = false;
    }

    public WaterController getWaterController() {
        return waterController;
    }

    public MilkController getMilkController() {
        return milkController;
    }

    public CoffeeBeansController getCoffeeBeansController() {
        return coffeeBeansController;
    }

    public boolean needsCleaning() {
        return needsCleaning;
    }

    public void cleanMachine() throws Exception {
        if (!needsCleaning) {
            throw new Exception("Machine does not need cleaning.");
        }
        needsCleaning = false;
        cupsMade = 0;
    }

    public void checkIngredients(int water, int milk, int beans) throws Exception {
        waterController.useWater(water);
        milkController.useMilk(milk);
        coffeeBeansController.useBeans(beans);
    }

    public void useIngredients(int water, int milk, int beans) throws Exception {
        waterController.useWater(water);
        milkController.useMilk(milk);
        coffeeBeansController.useBeans(beans);
        cupsMade++;
        if (cupsMade >= cupsBeforeCleaning) {
            needsCleaning = true;
        }
    }
}
