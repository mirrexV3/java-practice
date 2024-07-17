package dev.mirrex.profile;

public class Profile {
    private final String name;

    private final int waterAmount;

    private final int milkAmount;

    private final int beansAmount;

    public Profile(String name, int waterAmount, int milkAmount, int beansAmount) {
        this.name = name;
        this.waterAmount = waterAmount;
        this.milkAmount = milkAmount;
        this.beansAmount = beansAmount;
    }

    public String getName() {
        return name;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public int getMilkAmount() {
        return milkAmount;
    }

    public int getBeansAmount() {
        return beansAmount;
    }
}
