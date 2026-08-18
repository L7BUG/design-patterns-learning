package com.l7bug.factorymethod;

/**
 * 具体工厂 - 推送通知工厂
 */
public class PushNotificationFactory implements NotificationFactory {

    @Override
    public Notification createNotification() {
        return new PushNotification();
    }

    @Override
    public String getType() {
        return "PUSH";
    }
}
