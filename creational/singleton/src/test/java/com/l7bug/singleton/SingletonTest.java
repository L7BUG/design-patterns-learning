package com.l7bug.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 单例模式单元测试
 * 验证所有实现方式都返回同一个实例
 */
class SingletonTest {

    @Test
    void eagerSingleton() {
        EagerSingleton instance1 = EagerSingleton.getInstance();
        EagerSingleton instance2 = EagerSingleton.getInstance();
        assertSame(instance1, instance2, "饿汉式单例应返回同一个实例");
    }

    @Test
    void lazySingleton() {
        LazySingleton instance1 = LazySingleton.getInstance();
        LazySingleton instance2 = LazySingleton.getInstance();
        assertSame(instance1, instance2, "懒汉式单例应返回同一个实例");
    }

    @Test
    void doubleCheckLockingSingleton() {
        DoubleCheckLockingSingleton instance1 = DoubleCheckLockingSingleton.getInstance();
        DoubleCheckLockingSingleton instance2 = DoubleCheckLockingSingleton.getInstance();
        assertSame(instance1, instance2, "双重检查锁单例应返回同一个实例");
    }

    @Test
    void billPughSingleton() {
        BillPughSingleton instance1 = BillPughSingleton.getInstance();
        BillPughSingleton instance2 = BillPughSingleton.getInstance();
        assertSame(instance1, instance2, "Bill Pugh 单例应返回同一个实例");
    }

    @Test
    void enumSingleton() {
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        assertSame(instance1, instance2, "枚举单例应返回同一个实例");
    }

    @Test
    void enumSingletonCounter() {
        EnumSingleton instance = EnumSingleton.INSTANCE;
        instance.doSomething();
        instance.doSomething();
        assertEquals(2, instance.getCounter(), "枚举单例计数器应为 2");
    }
}
