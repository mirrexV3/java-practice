package dev.mirrex.controller;

public class CoffeeBeansController {
    private final int capacity;

    private int coffeeBeans;

    public CoffeeBeansController(int capacity) {
        this.capacity = capacity;
        this.coffeeBeans = 0;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public void addBeans(int amount) throws Exception {
        if (coffeeBeans + amount > capacity) {
            throw new Exception("Coffee beans overflow! Maximum capacity is " + capacity);
        }
        coffeeBeans += amount;
    }

    public void useBeans(int amount) throws Exception {
        if (coffeeBeans < amount) {
            throw new Exception("Not enough coffee beans.");
        }
        coffeeBeans -= amount;
    }
}
