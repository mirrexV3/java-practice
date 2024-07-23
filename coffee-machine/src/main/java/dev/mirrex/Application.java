package dev.mirrex;

import dev.mirrex.drink.DrinkType;
import dev.mirrex.engine.CoffeeMachine;
import dev.mirrex.exception.OverflowException;
import dev.mirrex.logger.Logger;
import dev.mirrex.utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static dev.mirrex.engine.CoffeeMachine.*;
import static dev.mirrex.engine.CoffeeMachine.addCoffeeBeans;
import static dev.mirrex.engine.CoffeeMachine.addMilk;
import static dev.mirrex.engine.CoffeeMachine.addWater;
import static dev.mirrex.engine.CoffeeMachine.cleanMachine;
import static dev.mirrex.engine.CoffeeMachine.getCoffeeBeans;
import static dev.mirrex.engine.CoffeeMachine.getMilkLevel;
import static dev.mirrex.engine.CoffeeMachine.getProfiles;
import static dev.mirrex.engine.CoffeeMachine.getRecipe;
import static dev.mirrex.engine.CoffeeMachine.getWaterLevel;
import static dev.mirrex.engine.CoffeeMachine.needsCleaning;
import static dev.mirrex.engine.CoffeeMachine.setProfile;
import static dev.mirrex.engine.CoffeeMachine.turnOff;
import static dev.mirrex.engine.CoffeeMachine.turnOn;
import static dev.mirrex.utils.Utils.isValidInput;

public class Application {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            while (true) {
                showMenu();
                int choice = getChoice();
                handleChoice(choice);
            }

        } catch (OverflowException | IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    static void showMenu() {
        System.out.println(
                """ 
                        1. Включить кофемашину
                        2. Выключить кофемашину
                        3. Добавить воду
                        4. Добавить кофе
                        5. Добавить молоко
                        6. Проверить уровень молока
                        7. Проверить уровень воды
                        8. Проверить количество кофе
                        9. Проверить статус очистки
                        10. Очистить кофемашину
                        11. Сделать напиток
                        12. Сделать три кружки напитка
                        13. Сделать кастомное количество кружек напитка
                        14. Печать логов
                        15. Добавить профиль
                        16. Печать рецепта напитка
                        17. Выход
                        """);
    }

    private static int getChoice() {
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.print("Введите ваш выбор: ");
        while (!sc.hasNextInt()) {
            System.out.println("That's not a number!");
            System.out.print("Пожалуйста, введите ваш выбор повторно: ");
            sc.next();
        }
        choice = sc.nextInt();
        return choice;
    }

    private static void handleChoice(int choice) throws OverflowException {
        if (choice >= 1 && choice <= 5) {
            handleBasicChoices(choice);
        } else if (choice >= 6 && choice <= 10) {
            handleStatusChoices(choice);
        } else if (choice >= 11 && choice <= 13) {
            handleDrinkChoices(choice);
        } else if (choice >= 14 && choice <= 16) {
            handleProfileAndLogsChoices(choice);
        } else if (choice == 17) {
            System.exit(0);
        } else {
            System.out.println("Некорректный выбор. Попробуйте еще раз.");
        }
    }

    private static void handleBasicChoices(int choice) throws OverflowException {
        switch (choice) {
            case 1:
                turnOn();
                break;
            case 2:
                turnOff();
                break;
            case 3:
                addWater();
                break;
            case 4:
                addCoffeeBeans();
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

    private static void handleDrinkChoices(int choice) throws OverflowException {
        switch (choice) {
            case 11, 13 -> makeDrink();
            case 12 -> makeThreeCups();

            default -> System.out.println("Некорректный выбор. Попробуйте еще раз.");
        }
    }

    private static void handleProfileAndLogsChoices(int choice) throws OverflowException {
        switch (choice) {
            case 14:
                printLogs();
                break;
            case 15:
                addProfile();
                break;
            case 16:
                printRecipe();
                break;
            default:
                System.out.println("Некорректный выбор. Попробуйте еще раз.");
        }
    }

    private static void checkMilkLevel() {
        System.out.println("Уровень молока: " + getMilkLevel() + " мл");
    }

    private static void checkWaterLevel() {
        System.out.println("Уровень воды: " + getWaterLevel() + " мл");
    }

    private static void checkCoffeeBeans() {
        System.out.println("Кофе в машине: " + getCoffeeBeans() + " г");
    }

    private static void checkCleaningStatus() {
        if (needsCleaning()) {
            System.out.println("Кофемашине требуется очистка.");
        } else {
            System.out.println("Кофемашина чиста.");
        }
    }

    private static void printLogs() {
        Logger.printLogs();
    }

    private static void addProfile() throws OverflowException {
        System.out.print("Введите имя профиля: ");
        String profileName = SCANNER.next();
        int drinksAmount = DrinkType.values().length;

        if (getProfiles().contains(profileName)) {
            System.out.println("Профиль уже существует");
        } else {
            Map<DrinkType, Integer> drinks = new HashMap<>();
            System.out.println("Введите напитки для профиля (1 - Эспрессо, 2 - Капучино):");
            for (int i = 0; i < drinksAmount; i++) {
                System.out.print("Напиток " + (i + 1) + ": ");

                if (!SCANNER.hasNextInt()) {
                    throw new OverflowException("The selection must correspond to the drink number");
                }

                int drinkChoice = SCANNER.nextInt();
                if (drinkChoice <= 0 || drinkChoice > drinksAmount) {
                    throw new OverflowException("Should be non negative and not be greater than drinks amount");
                }

                int cupAmount = SCANNER.nextInt();
                System.out.print("Количество кружек: ");
                if (cupAmount > 99 || cupAmount <= 0 || !isValidInput(SCANNER)) {
                    throw new OverflowException("Should be non negative and not be greater than 99");
                }
                DrinkType type = (drinkChoice == 1) ? DrinkType.ESPRESSO : DrinkType.CAPPUCCINO;
                drinks.put(type, cupAmount);
            }
            setProfile(profileName);
            Logger.log("Profile added: " + profileName + " with drinks " + drinks);
            System.out.println("Профиль добавлен.");
        }
    }

    private static void printRecipe() throws OverflowException {
        System.out.print("Введите название напитка (1 - Эспрессо, 2 - Капучино): ");
        if (isValidInput(SCANNER)) {
            int drinkChoice = SCANNER.nextInt();
            DrinkType type = (drinkChoice == 1) ? DrinkType.ESPRESSO : DrinkType.CAPPUCCINO;
            String recipe = getRecipe(type);
            System.out.println("Рецепт: " + recipe);
        }
    }
}
