package com.l7bug.factorymethod;

/**
 * 工厂接口 - 通知工厂
 *
 * 定义了创建通知对象的工厂方法
 * 具体创建哪个产品由子类决定
 */
public interface NotificationFactory {

    /**
     * 工厂方法：创建通知对象
     * @return Notification 实例
     */
    Notification createNotification();

    /**
     * 获取工厂支持的通知类型
     * @return 类型名称
     */
    String getType();
}
