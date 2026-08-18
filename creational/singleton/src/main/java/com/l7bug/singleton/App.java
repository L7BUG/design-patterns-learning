package com.l7bug.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式演示
 *
 * 本示例展示了 5 种常见的单例实现方式：
 * 1. 饿汉式（Eager） - 类加载时创建
 * 2. 懒汉式（Lazy） - 第一次使用时创建（synchronized）
 * 3. 双重检查锁（Double Check Locking） - 延迟加载 + 线程安全
 * 4. Bill Pugh - 静态内部类，推荐方式
 * 5. 枚举（Enum） - 最安全的方式
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("=== 单例模式演示 ===\n");

        // 1. 饿汉式
        log.info("1. 饿汉式单例");
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        log.info("eager1 == eager2: {}", eager1 == eager2);  // true
        eager1.doSomething();

        // 2. 懒汉式
        log.info("\n2. 懒汉式单例");
        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        log.info("lazy1 == lazy2: {}", lazy1 == lazy2);      // true
        lazy1.doSomething();

        // 3. 双重检查锁
        log.info("\n3. 双重检查锁单例");
        DoubleCheckLockingSingleton dcl1 = DoubleCheckLockingSingleton.getInstance();
        DoubleCheckLockingSingleton dcl2 = DoubleCheckLockingSingleton.getInstance();
        log.info("dcl1 == dcl2: {}", dcl1 == dcl2);          // true
        dcl1.doSomething();

        // 4. Bill Pugh
        log.info("\n4. Bill Pugh 单例");
        BillPughSingleton bill1 = BillPughSingleton.getInstance();
        BillPughSingleton bill2 = BillPughSingleton.getInstance();
        log.info("bill1 == bill2: {}", bill1 == bill2);      // true
        bill1.doSomething();

        // 5. 枚举单例
        log.info("\n5. 枚举单例");
        EnumSingleton enum1 = EnumSingleton.INSTANCE;
        EnumSingleton enum2 = EnumSingleton.INSTANCE;
        log.info("enum1 == enum2: {}", enum1 == enum2);      // true
        enum1.doSomething();
        enum1.doSomething();
        log.info("执行次数: {}", enum1.getCounter());         // 2

        log.info("\n=== 所有单例都是同一个实例 ===");
    }
}
