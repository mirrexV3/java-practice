package dev.mirrex.engine;

import dev.mirrex.check.IngredientChecker;
import dev.mirrex.logger.Logger;
import dev.mirrex.operation.Operation;
import dev.mirrex.profile.Profile;
import dev.mirrex.profile.ProfileManager;

import java.util.Scanner;

public class CoffeeMachine {
    private boolean isOn;

    private final IngredientChecker ingredientChecker;

    private final CoffeePreparer coffeePreparer;

    private final Logger logger;

    private final ProfileManager profileManager;

    public CoffeeMachine(int waterCapacity, int milkCapacity, int beansCapacity, int cupsBeforeCleaning) {
        this.isOn = false;
        this.ingredientChecker = new IngredientChecker(waterCapacity, milkCapacity, beansCapacity, cupsBeforeCleaning);
        this.coffeePreparer = new CoffeePreparer(ingredientChecker);
        this.logger = new Logger();
        this.profileManager = new ProfileManager();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMenu();
            String choice = scanner.nextLine();
            try {
                handleChoice(choice, scanner);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showMenu() {
        System.out.println("""
                                Coffee Machine Menu:
                                1. Turn On
                                2. Turn Off
                                3. Add Water
                                4. Add Milk
                                5. Add Coffee Beans
                                6. Check Water Level
                                7. Check Milk Level
                                8. Check Coffee Beans Level
                                9. Check if Cleaning Needed
                                10. Clean Machine
                                11. Prepare Espresso
                                12. Prepare Cappuccino
                                13. Prepare Custom Drink
                                14. Create Drink Profile
                                15. Use Drink Profile
                                16. Show Log
                                17. Show Recipe
                                18. Exit
                                Choose an option:""");
    }

    private void handleChoice(String choice, Scanner scanner) throws Exception {
        switch (choice) {
            case "1":
                turnOn();
                break;
            case "2":
                turnOff();
                break;
            case "3":
                System.out.print("Enter amount of water to add: ");
                int waterAmount = Integer.parseInt(scanner.nextLine());
                ingredientChecker.getWaterController().addWater(waterAmount);
                logger.addLog(Operation.ADD_WATER.getDescription());
                break;
            case "4":
                System.out.print("Enter amount of milk to add: ");
                int milkAmount = Integer.parseInt(scanner.nextLine());
                ingredientChecker.getMilkController().addMilk(milkAmount);
                logger.addLog(Operation.ADD_MILK.getDescription());
                break;
            case "5":
                System.out.print("Enter amount of coffee beans to add: ");
                int beansAmount = Integer.parseInt(scanner.nextLine());
                ingredientChecker.getCoffeeBeansController().addBeans(beansAmount);
                logger.addLog(Operation.ADD_COFFEE_BEANS.getDescription());
                break;
            case "6":
                System.out.println("Water level: " + ingredientChecker.getWaterController().getWaterLevel());
                break;
            case "7":
                System.out.println("Milk level: " + ingredientChecker.getMilkController().getMilkLevel());
                break;
            case "8":
                System.out.println("Coffee beans: " + ingredientChecker.getCoffeeBeansController().getCoffeeBeans());
                break;
            case "9":
                System.out.println("Cleaning needed: " + ingredientChecker.needsCleaning());
                break;
            case "10":
                ingredientChecker.cleanMachine();
                logger.addLog(Operation.CLEAN_MACHINE.getDescription());
                break;
            case "11":
                System.out.print("Enter number of cups: ");
                int espressoCups = Integer.parseInt(scanner.nextLine());
                coffeePreparer.prepareEspresso(espressoCups);
                logger.addLog(Operation.PREPARE_ESPRESSO.getDescription() + " (" + espressoCups + " cups)");
                break;
            case "12":
                System.out.print("Enter number of cups: ");
                int cappuccinoCups = Integer.parseInt(scanner.nextLine());
                coffeePreparer.prepareCappuccino(cappuccinoCups);
                logger.addLog(Operation.PREPARE_CAPPUCCINO.getDescription() + " (" + cappuccinoCups + " cups)");
                break;
            case "13":
                System.out.print("Enter number of cups: ");
                int customCups = Integer.parseInt(scanner.nextLine());
                coffeePreparer.prepareCustomDrink(customCups);
                logger.addLog(Operation.PREPARE_CUSTOM.getDescription() + " (" + customCups + " cups)");
                break;
            case "14":
                System.out.print("Enter profile name: ");
                String profileName = scanner.nextLine();
                System.out.print("Enter water amount: ");
                int profileWater = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter milk amount: ");
                int profileMilk = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter beans amount: ");
                int profileBeans = Integer.parseInt(scanner.nextLine());
                Profile profile = new Profile(profileName, profileWater, profileMilk, profileBeans);
                profileManager.addProfile(profileName, profile);
                break;
            case "15":
                System.out.print("Enter profile name: ");
                String profileToUse = scanner.nextLine();
                Profile selectedProfile = profileManager.getProfile(profileToUse);
                if (selectedProfile != null) {
                    coffeePreparer.prepareCustomDrink(1); // Example of using the profile, should customize further
                    logger.addLog(Operation.PREPARE_CUSTOM.getDescription() + " using profile " + profileToUse);
                } else {
                    System.out.println("Profile not found.");
                }
                break;
            case "16":
                System.out.println("Log:");
                for (String logEntry : logger.getLog()) {
                    System.out.println(logEntry);
                }
                break;
            case "17":
                System.out.print("Enter drink name (Espresso/Cappuccino/Custom): ");
                String drinkName = scanner.nextLine();
                showRecipe(drinkName);
                break;
            case "18":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private void turnOn() {
        if (!isOn) {
            isOn = true;
            System.out.println("Coffee machine is now ON.");
            logger.addLog(Operation.TURN_ON.getDescription());
        } else {
            System.out.println("Coffee machine is already ON.");
        }
    }

    private void turnOff() {
        if (isOn) {
            isOn = false;
            System.out.println("Coffee machine is now OFF.");
            logger.addLog(Operation.TURN_OFF.getDescription());
        } else {
            System.out.println("Coffee machine is already OFF.");
        }
    }

    private void showRecipe(String drinkName) {
        switch (drinkName.toLowerCase()) {
            case "espresso":
                System.out.println("Espresso Recipe: Water 30ml, Milk 0ml, Coffee Beans 10g");
                break;
            case "cappuccino":
                System.out.println("Cappuccino Recipe: Water 30ml, Milk 100ml, Coffee Beans 10g");
                break;
            case "custom":
                System.out.println("Custom Drink Recipe: Customize your drink with water, milk, and coffee beans.");
                break;
            default:
                System.out.println("Unknown drink name.");
                break;
        }
    }
}
