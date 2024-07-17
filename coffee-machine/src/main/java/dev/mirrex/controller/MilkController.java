package dev.mirrex.controller;

public class MilkController {
    private final int capacity;

    private int milkLevel;

    public MilkController(int capacity) {
        this.capacity = capacity;
        this.milkLevel = 0;
    }

    public int getMilkLevel() {
        return milkLevel;
    }

    public void addMilk(int amount) throws Exception {
        if (milkLevel + amount > capacity) {
            throw new Exception("Milk overflow! Maximum capacity is " + capacity);
        }
        milkLevel += amount;
    }

    public void useMilk(int amount) throws Exception {
        if (milkLevel < amount) {
            throw new Exception("Not enough milk.");
        }
        milkLevel -= amount;
    }
}
