package dev.mirrex;

import dev.mirrex.engine.CoffeeMachine;

public class Application {
    public static void main(String[] args) {
        var coffeeMachine = new CoffeeMachine(1000, 500, 200, 10);
        coffeeMachine.start();
    }
}
