package com.l7bug.decorator;

/**
 * 具体装饰者 - 奶泡
 */
public class WhipDecorator extends CoffeeDecorator {

    public WhipDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + 奶泡";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 3.0;
    }
}
