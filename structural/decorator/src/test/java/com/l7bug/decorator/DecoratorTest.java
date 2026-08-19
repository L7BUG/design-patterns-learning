package com.l7bug.decorator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 装饰器模式单元测试
 */
class DecoratorTest {

    @Test
    void espressoCost() {
        Coffee espresso = new Espresso();
        assertEquals(15.0, espresso.getCost());
        assertEquals("浓缩咖啡", espresso.getDescription());
    }

    @Test
    void americanoCost() {
        Coffee americano = new Americano();
        assertEquals(12.0, americano.getCost());
        assertEquals("美式咖啡", americano.getDescription());
    }

    @Test
    void espressoWithMilk() {
        Coffee latte = new MilkDecorator(new Espresso());
        assertEquals(20.0, latte.getCost());  // 15 + 5
        assertEquals("浓缩咖啡 + 牛奶", latte.getDescription());
    }

    @Test
    void espressoWithSugar() {
        Coffee sweet = new SugarDecorator(new Espresso());
        assertEquals(17.0, sweet.getCost());  // 15 + 2
        assertEquals("浓缩咖啡 + 糖", sweet.getDescription());
    }

    @Test
    void espressoWithMultipleDecorators() {
        Coffee fancy = new WhipDecorator(
                new SugarDecorator(
                        new MilkDecorator(new Espresso())));
        assertEquals(25.0, fancy.getCost());  // 15 + 5 + 2 + 3
        assertEquals("浓缩咖啡 + 牛奶 + 糖 + 奶泡", fancy.getDescription());
    }

    @Test
    void dynamicDecoration() {
        Coffee coffee = new Espresso();
        assertEquals("浓缩咖啡", coffee.getDescription());

        coffee = new MilkDecorator(coffee);
        assertEquals("浓缩咖啡 + 牛奶", coffee.getDescription());

        coffee = new SugarDecorator(coffee);
        assertEquals("浓缩咖啡 + 牛奶 + 糖", coffee.getDescription());
    }
}
