package dev.mirrex;

import dev.mirrex.drink.DrinkType;
import dev.mirrex.engine.CoffeeMachine;
import dev.mirrex.exception.OverflowException;
import dev.mirrex.logger.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = getChoice();
            handleChoice(choice);
        }
    }

    static void showMenu() {
        System.out.println("1. Включить кофемашину");
        System.out.println("2. Выключить кофемашину");
        System.out.println("3. Добавить воду");
        System.out.println("4. Добавить кофе");
        System.out.println("5. Добавить молоко");
        System.out.println("6. Проверить уровень молока");
        System.out.println("7. Проверить уровень воды");
        System.out.println("8. Проверить количество кофе");
        System.out.println("9. Проверить статус очистки");
        System.out.println("10. Очистить кофемашину");
        System.out.println("11. Сделать эспрессо");
        System.out.println("12. Сделать капучино");
        System.out.println("13. Сделать три кружки напитка");
        System.out.println("14. Сделать кастомное количество кружек напитка");
        System.out.println("15. Печать логов");
        System.out.println("16. Добавить профиль");
        System.out.println("17. Печать рецепта напитка");
        System.out.println("18. Выход");
    }

    private static int getChoice() {
        System.out.print("Введите ваш выбор: ");
        return SCANNER.nextInt();
    }

    private static void handleChoice(int choice) {
        if (choice >= 1 && choice <= 5) {
            handleBasicChoices(choice);
        } else if (choice >= 6 && choice <= 10) {
            handleStatusChoices(choice);
        } else if (choice >= 11 && choice <= 14) {
            handleDrinkChoices(choice);
        } else if (choice >= 15 && choice <= 17) {
            handleProfileAndLogsChoices(choice);
        } else if (choice == 18) {
            System.exit(0);
        } else {
            System.out.println("Некорректный выбор. Попробуйте еще раз.");
        }
    }

    private static void handleBasicChoices(int choice) {
        switch (choice) {
            case 1:
                CoffeeMachine.turnOn();
                break;
            case 2:
                CoffeeMachine.turnOff();
                break;
            case 3:
                addWater();
                break;
            case 4:
                addCoffee();
                break;
            case 5:
                addMilk();
                break;
            default:
                System.out.println("Некорректный выбор. Попробуйте еще раз.");
        }
    }

    private static void handleStatusChoices(int choice) {
        switch (choice) {
            case 6:
                checkMilkLevel();
                break;
            case 7:
                checkWaterLevel();
                break;
            case 8:
                checkCoffeeBeans();
                break;
            case 9:
                checkCleaningStatus();
                break;
            case 10:
                cleanMachine();
                break;
            default:
                System.out.println("Некорректный выбор. Попробуйте еще раз.");
        }
    }

    private static void handleDrinkChoices(int choice) {
        switch (choice) {
            case 11:
                makeEspresso();
                break;
            case 12:
                makeCappuccino();
                break;
            case 13:
                makeThreeCups();
                break;
            case 14:
                makeCustomCups();
                break;
            default:
                System.out.println("Некорректный выбор. Попробуйте еще раз.");
        }
    }

    private static void handleProfileAndLogsChoices(int choice) {
        switch (choice) {
            case 15:
                printLogs();
                break;
            case 16:
                addProfile();
                break;
            case 17:
                printRecipe();
                break;
            default:
                System.out.println("Некорректный выбор. Попробуйте еще раз.");
        }
    }

    private static void addWater() {
        System.out.print("Введите количество воды (мл): ");
        int amount = SCANNER.nextInt();
        try {
            CoffeeMachine.addWater(amount);
        } catch (OverflowException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addCoffee() {
        System.out.print("Введите количество кофе (г): ");
        int amount = SCANNER.nextInt();
        try {
            CoffeeMachine.addCoffeeBeans(amount);
        } catch (OverflowException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addMilk() {
        System.out.print("Введите количество молока (мл): ");
        int amount = SCANNER.nextInt();
        try {
            CoffeeMachine.addMilk(amount);
        } catch (OverflowException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkMilkLevel() {
        System.out.println("Уровень молока: " + CoffeeMachine.getMilkLevel() + " мл");
    }

    private static void checkWaterLevel() {
        System.out.println("Уровень воды: " + CoffeeMachine.getWaterLevel() + " мл");
    }

    private static void checkCoffeeBeans() {
        System.out.println("Кофе в машине: " + CoffeeMachine.getCoffeeBeans() + " г");
    }

    private static void checkCleaningStatus() {
        if (CoffeeMachine.needsCleaning()) {
            System.out.println("Кофемашине требуется очистка.");
        } else {
            System.out.println("Кофемашина чиста.");
        }
    }

    private static void cleanMachine() {
        try {
            CoffeeMachine.cleanMachine();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void makeEspresso() {
        try {
            CoffeeMachine.makeDrink(DrinkType.ESPRESSO, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void makeCappuccino() {
        try {
            CoffeeMachine.makeDrink(DrinkType.CAPPUCCINO, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void makeThreeCups() {
        System.out.print("Какой напиток вы хотите приготовить? (1 - Эспрессо, 2 - Капучино): ");
        int drinkChoice = SCANNER.nextInt();
        DrinkType type = (drinkChoice == 1) ? DrinkType.ESPRESSO : DrinkType.CAPPUCCINO;
        try {
            CoffeeMachine.makeDrink(type, 3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void makeCustomCups() {
        System.out.print("Какой напиток вы хотите приготовить? (1 - Эспрессо, 2 - Капучино): ");
        int drinkChoice = SCANNER.nextInt();
        DrinkType type = (drinkChoice == 1) ? DrinkType.ESPRESSO : DrinkType.CAPPUCCINO;
        System.out.print("Введите количество кружек: ");
        int cups = SCANNER.nextInt();
        try {
            CoffeeMachine.makeDrink(type, cups);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printLogs() {
        Logger.printLogs();
    }

    private static void addProfile() {
        System.out.print("Введите имя профиля: ");
        String profileName = SCANNER.next();

        if (CoffeeMachine.getProfiles().contains(profileName)) {
            System.out.println("Профиль уже существует");
        } else {
            Map<DrinkType, Integer> drinks = new HashMap<>();
            System.out.println("Введите напитки для профиля (1 - Эспрессо, 2 - Капучино):");
            for (int i = 0; i < 3; i++) {
                System.out.print("Напиток " + (i + 1) + ": ");
                int drinkChoice = SCANNER.nextInt();
                DrinkType type = (drinkChoice == 1) ? DrinkType.ESPRESSO : DrinkType.CAPPUCCINO;
                System.out.print("Количество кружек: ");
                int cups = SCANNER.nextInt();
                drinks.put(type, cups);
            }

            CoffeeMachine.setProfile(profileName);
            Logger.log("Profile added: " + profileName + " with drinks " + drinks);
            System.out.println("Профиль добавлен.");
        }
    }

    private static void printRecipe() {
        System.out.print("Введите название напитка (1 - Эспрессо, 2 - Капучино): ");
        int drinkChoice = SCANNER.nextInt();
        DrinkType type = (drinkChoice == 1) ? DrinkType.ESPRESSO : DrinkType.CAPPUCCINO;
        String recipe = CoffeeMachine.getRecipe(type);
        System.out.println("Рецепт: " + recipe);
    }
}
