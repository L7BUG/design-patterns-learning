package com.l7bug.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * 装饰器模式演示
 *
 * 场景：咖啡店
 * - 基础咖啡：浓缩咖啡、美式咖啡
 * - 配料装饰：牛奶、糖、奶泡
 * - 可以动态组合，运行时添加功能
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("=== 装饰器模式演示 ===\n");

        // 1. 基础浓缩咖啡
        log.info("1. 基础浓缩咖啡");
        Coffee espresso = new Espresso();
        printCoffee(espresso);

        // 2. 加牛奶
        log.info("\n2. 浓缩咖啡 + 牛奶");
        Coffee latte = new MilkDecorator(new Espresso());
        printCoffee(latte);

        // 3. 加牛奶 + 糖
        log.info("\n3. 浓缩咖啡 + 牛奶 + 糖");
        Coffee sweetLatte = new SugarDecorator(new MilkDecorator(new Espresso()));
        printCoffee(sweetLatte);

        // 4. 美式 + 所有配料
        log.info("\n4. 美式 + 牛奶 + 糖 + 奶泡");
        Coffee fancy = new WhipDecorator(
                new SugarDecorator(
                        new MilkDecorator(new Americano())));
        printCoffee(fancy);

        // 5. 运行时动态添加职责
        log.info("\n5. 动态添加职责");
        Coffee coffee = new Espresso();
        log.info("原始: {}", coffee.getDescription());
        coffee = new MilkDecorator(coffee);  // 运行时添加
        log.info("加牛奶: {}", coffee.getDescription());
        coffee = new SugarDecorator(coffee); // 再添加
        log.info("加糖: {}", coffee.getDescription());

        log.info("\n=== 装饰器模式演示完成 ===");
    }

    private static void printCoffee(Coffee coffee) {
        log.info("{} - 价格: ¥{}", coffee.getDescription(), coffee.getCost());
    }
}
