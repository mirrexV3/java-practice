package dev.mirrex.services;

import java.lang.reflect.Array;

public class Values {

    public static void displayMinAndMaxValue() {
        System.out.printf("type:%s min: %s max: %s%n", Character.TYPE,
                (int) Character.MIN_VALUE, (int) Character.MAX_VALUE);
        System.out.printf("type:%s min: %s max: %s%n", Byte.TYPE, Byte.MIN_VALUE, Byte.MAX_VALUE);
        System.out.printf("type:%s min: %s max: %s%n", Short.TYPE, Short.MIN_VALUE, Short.MAX_VALUE);
        System.out.printf("type:%s min: %s max: %s%n", Integer.TYPE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.printf("type:%s min: %s max: %s%n", Long.TYPE, Long.MIN_VALUE, Long.MAX_VALUE);
        System.out.printf("type:%s min: %s max: %s%n", Float.TYPE, Float.MIN_VALUE, Float.MAX_VALUE);
        System.out.printf("type:%s min: %s max: %s%n", Double.TYPE, Double.MIN_VALUE, Double.MAX_VALUE);
        System.out.printf("type:%s default value: %s%n", String.class, null);
        System.out.printf("type:%s default value: %s%n", Array.class, null);
    }
}
