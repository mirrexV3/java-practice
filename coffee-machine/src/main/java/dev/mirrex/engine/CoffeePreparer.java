package dev.mirrex.engine;

import dev.mirrex.check.IngredientChecker;

public class CoffeePreparer {
    private final IngredientChecker ingredientChecker;

    public CoffeePreparer(IngredientChecker ingredientChecker) {
        this.ingredientChecker = ingredientChecker;
    }

    public void prepareEspresso(int cups) throws Exception {
        for (int i = 0; i < cups; i++) {
            ingredientChecker.checkIngredients(30, 0, 10);
            ingredientChecker.useIngredients(30, 0, 10);
        }
    }

    public void prepareCappuccino(int cups) throws Exception {
        for (int i = 0; i < cups; i++) {
            ingredientChecker.checkIngredients(30, 100, 10);
            ingredientChecker.useIngredients(30, 100, 10);
        }
    }

    public void prepareCustomDrink(int cups) throws Exception {
        for (int i = 0; i < cups; i++) {
            ingredientChecker.checkIngredients(50, 50, 15);
            ingredientChecker.useIngredients(50, 50, 15);
        }
    }
}
