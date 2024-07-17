package dev.mirrex.operation;

public enum Operation {
    TURN_ON("Turned On"),
    TURN_OFF("Turned Off"),
    ADD_WATER("Added Water"),
    ADD_MILK("Added Milk"),
    ADD_COFFEE_BEANS("Added Coffee Beans"),
    CLEAN_MACHINE("Cleaned Machine"),
    PREPARE_ESPRESSO("Prepared Espresso"),
    PREPARE_CAPPUCCINO("Prepared Cappuccino"),
    PREPARE_CUSTOM("Prepared Custom Drink");

    private final String description;

    Operation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
