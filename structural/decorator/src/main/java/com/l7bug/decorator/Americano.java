package com.l7bug.decorator;

/**
 * 具体构件 - 美式咖啡
 */
public class Americano implements Coffee {

    @Override
    public String getDescription() {
        return "美式咖啡";
    }

    @Override
    public double getCost() {
        return 12.0;
    }
}
