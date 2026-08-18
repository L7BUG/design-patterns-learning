package com.l7bug.singleton;

/**
 * 双重检查锁单例（线程安全 + 延迟加载）
 *
 * 特点：
 * - 第一次使用时才创建实例（延迟加载）
 * - 线程安全
 * - 需要 volatile 关键字防止指令重排序
 *
 * 为什么需要 volatile？
 * new Object() 实际上分三步：
 * 1. 分配内存
 * 2. 初始化对象
 * 3. 将引用指向内存地址
 * 如果没有 volatile，步骤 2 和 3 可能重排序，导致其他线程获取到未初始化的对象
 *
 * 适用场景：
 * - 实例占用资源大
 * - 需要延迟加载
 */
public class DoubleCheckLockingSingleton {

    // volatile 关键字防止指令重排序
    private static volatile DoubleCheckLockingSingleton instance;

    private DoubleCheckLockingSingleton() {
    }

    /**
     * 双重检查锁
     * 第一次检查：避免不必要的同步（实例已创建时直接返回）
     * 第二次检查：确保只有第一次调用时创建实例
     */
    public static DoubleCheckLockingSingleton getInstance() {
        if (instance == null) {                    // 第一次检查（无锁）
            synchronized (DoubleCheckLockingSingleton.class) {
                if (instance == null) {            // 第二次检查（有锁）
                    instance = new DoubleCheckLockingSingleton();
                }
            }
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("[双重检查锁] 正在执行操作...");
    }
}
