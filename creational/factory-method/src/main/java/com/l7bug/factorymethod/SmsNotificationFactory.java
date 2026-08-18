package com.l7bug.factorymethod;

/**
 * 具体工厂 - 短信通知工厂
 */
public class SmsNotificationFactory implements NotificationFactory {

    @Override
    public Notification createNotification() {
        return new SmsNotification();
    }

    @Override
    public String getType() {
        return "SMS";
    }
}
