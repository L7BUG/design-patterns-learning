package com.l7bug.singleton;

/**
 * 懒汉式单例（线程安全，使用 synchronized）
 *
 * 特点：
 * - 第一次使用时才创建实例（延迟加载）
 * - 使用 synchronized 保证线程安全
 * - 缺点：每次调用 getInstance() 都需要同步，性能较差
 *
 * 适用场景：
 * - 不推荐在生产环境使用
 * - 仅用于理解单例模式的演进过程
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
    }

    /**
     * 线程安全的懒汉式
     * 缺点：每次调用都需要同步，性能开销大
     */
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("[懒汉式] 正在执行操作...");
    }
}
