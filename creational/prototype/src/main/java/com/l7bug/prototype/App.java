package com.l7bug.prototype;

import lombok.extern.slf4j.Slf4j;

/**
 * 原型模式演示
 *
 * 场景：图形克隆系统
 * - 预定义一些图形原型
 * - 通过克隆创建新对象，而不是 new
 * - 克隆后可以修改属性，不影响原型
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("=== 原型模式演示 ===\n");

        // 1. 加载原型缓存
        ShapeCache.loadCache();
        log.info("1. 已加载原型缓存");
        log.info("可用原型: {}", ShapeCache.getAvailableKeys());

        // 2. 从缓存克隆对象
        log.info("\n2. 从缓存克隆对象");
        Shape shape1 = ShapeCache.getShape("RED_CIRCLE");
        shape1.display();

        Shape shape2 = ShapeCache.getShape("BLUE_CIRCLE");
        shape2.display();

        Shape shape3 = ShapeCache.getShape("GREEN_RECT");
        shape3.display();

        // 3. 克隆后修改属性
        log.info("\n3. 克隆后修改属性");
        Shape originalCircle = ShapeCache.getShape("RED_CIRCLE");
        log.info("原始圆形:");
        originalCircle.display();

        Shape clonedCircle = originalCircle.clone();
        log.info("克隆圆形（修改前）:");
        clonedCircle.display();

        // 修改克隆对象的属性
        if (clonedCircle instanceof Circle circle) {
            circle.setRadius(20);  // 修改半径
            circle.setColor("绿色");  // 修改颜色
        }
        log.info("克隆圆形（修改后）:");
        clonedCircle.display();

        log.info("原始圆形（未受影响）:");
        originalCircle.display();

        // 4. 克隆 vs new 的对比
        log.info("\n4. 克隆 vs new");
        long startClone = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            ShapeCache.getShape("RED_CIRCLE").clone();
        }
        long cloneTime = System.nanoTime() - startClone;

        long startNew = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            new Circle("红色", 10);
        }
        long newTime = System.nanoTime() - startNew;

        log.info("克隆 100000 次: {} ms", cloneTime / 1_000_000);
        log.info("new 100000 次: {} ms", newTime / 1_000_000);
        log.info("克隆比 new 快 {} 倍", (double) newTime / cloneTime);

        log.info("\n=== 原型模式演示完成 ===");
    }
}
