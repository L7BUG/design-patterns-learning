package com.l7bug.singleton;

/**
 * 静态内部类单例（Bill Pugh 实现）
 *
 * 特点：
 * - 延迟加载：内部类在第一次被使用时才加载
 * - 线程安全：JVM 保证类加载过程的线程安全性
 * - 无锁：比双重检查锁更简洁高效
 *
 * 原理：
 * - 当 SingletonHolder 被加载时，INSTANCE 字段被初始化
 * - 由于 JVM 类加载机制，这个过程是线程安全的
 * - 既实现了延迟加载，又保证了线程安全
 *
 * 适用场景：
 * - 推荐使用的单例实现方式（Joshua Bloch 推荐）
 */
public class BillPughSingleton {

    private BillPughSingleton() {
    }

    /**
     * 静态内部类
     * 只有在第一次引用 SingletonHolder.INSTANCE 时才会加载
     */
    private static class SingletonHolder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void doSomething() {
        System.out.println("[Bill Pugh] 正在执行操作...");
    }
}
