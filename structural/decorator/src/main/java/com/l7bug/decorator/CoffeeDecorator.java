package com.l7bug.decorator;

/**
 * 抽象装饰者 - 咖啡配料
 *
 * 持有一个 Coffee 引用，用于包装被装饰对象
 */
public abstract class CoffeeDecorator implements Coffee {

    protected final Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}
