package com.l7bug.factorymethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 工厂方法模式单元测试
 */
class FactoryMethodTest {

    @Test
    void emailFactory() {
        NotificationFactory factory = new EmailNotificationFactory();
        Notification notification = factory.createNotification();

        assertNotNull(notification);
        assertInstanceOf(EmailNotification.class, notification);
        assertEquals("EMAIL", factory.getType());
        assertEquals("EMAIL", notification.getType());
    }

    @Test
    void smsFactory() {
        NotificationFactory factory = new SmsNotificationFactory();
        Notification notification = factory.createNotification();

        assertNotNull(notification);
        assertInstanceOf(SmsNotification.class, notification);
        assertEquals("SMS", factory.getType());
        assertEquals("SMS", notification.getType());
    }

    @Test
    void pushFactory() {
        NotificationFactory factory = new PushNotificationFactory();
        Notification notification = factory.createNotification();

        assertNotNull(notification);
        assertInstanceOf(PushNotification.class, notification);
        assertEquals("PUSH", factory.getType());
        assertEquals("PUSH", notification.getType());
    }

    @Test
    void factoryMethodPolymorphism() {
        // 多态：通过工厂接口创建不同类型的对象
        NotificationFactory[] factories = {
            new EmailNotificationFactory(),
            new SmsNotificationFactory(),
            new PushNotificationFactory()
        };

        for (NotificationFactory factory : factories) {
            Notification notification = factory.createNotification();
            assertNotNull(notification);
            assertEquals(factory.getType(), notification.getType());
        }
    }
}
