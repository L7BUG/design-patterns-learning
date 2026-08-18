package com.l7bug.singleton;

/**
 * 饿汉式单例
 *
 * 特点：
 * - 类加载时就创建实例（JVM 保证线程安全）
 * - 实现简单，没有同步开销
 * - 缺点：无论是否使用都会创建实例，可能浪费内存
 *
 * 适用场景：
 * - 实例占用资源少
 * - 确定会被使用的情况
 */
public class EagerSingleton {

    // 类加载时就创建实例
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    // 私有构造函数，防止外部实例化
    private EagerSingleton() {
        // 防御反射攻击
        if (INSTANCE != null) {
            throw new IllegalStateException("Singleton instance already exists!");
        }
    }

    // 全局访问点
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public void doSomething() {
        System.out.println("[饿汉式] 正在执行操作...");
    }
}
