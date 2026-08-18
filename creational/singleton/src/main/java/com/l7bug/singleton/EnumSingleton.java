package com.l7bug.singleton;

/**
 * 枚举单例（最安全的实现方式）
 *
 * 特点：
 * - 写法最简洁
 * - 天然线程安全
 * - 天然防止反射攻击
 * - 天然防止反序列化重新创建实例
 *
 * 优点：
 * - Joshua Bloch 在《Effective Java》中推荐的方式
 * - 序列化和反射都无法破坏单例
 *
 * 缺点：
 * - 不支持延迟加载
 * - 无法继承其他类（枚举隐式继承 java.lang.Enum）
 *
 * 适用场景：
 * - 不需要延迟加载的单例
 * - 需要绝对安全的单例
 */
public enum EnumSingleton {

    INSTANCE;

    // 可以添加实例变量和方法
    private int counter = 0;

    public void doSomething() {
        counter++;
        System.out.println("[枚举单例] 执行操作次数: " + counter);
    }

    public int getCounter() {
        return counter;
    }
}
