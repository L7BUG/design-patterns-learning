package com.l7bug.decorator;

/**
 * 具体构件 - 浓缩咖啡
 */
public class Espresso implements Coffee {

    @Override
    public String getDescription() {
        return "浓缩咖啡";
    }

    @Override
    public double getCost() {
        return 15.0;
    }
}
