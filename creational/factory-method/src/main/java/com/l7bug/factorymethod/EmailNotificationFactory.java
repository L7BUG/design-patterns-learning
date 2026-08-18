package com.l7bug.factorymethod;

/**
 * 具体工厂 - 邮件通知工厂
 */
public class EmailNotificationFactory implements NotificationFactory {

    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }

    @Override
    public String getType() {
        return "EMAIL";
    }
}
