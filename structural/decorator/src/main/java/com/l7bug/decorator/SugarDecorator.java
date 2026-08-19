package com.l7bug.decorator;

/**
 * 具体装饰者 - 糖
 */
public class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + 糖";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 2.0;
    }
}
