package dev.mirrex.controller;

public class WaterController {
    private final int capacity;

    private int waterLevel;

    public WaterController(int capacity) {
        this.capacity = capacity;
        this.waterLevel = 0;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void addWater(int amount) throws Exception {
        if (waterLevel + amount > capacity) {
            throw new Exception("Water overflow! Maximum capacity is " + capacity);
        }
        waterLevel += amount;
    }

    public void useWater(int amount) throws Exception {
        if (waterLevel < amount) {
            throw new Exception("Not enough water.");
        }
        waterLevel -= amount;
    }
}
