package com.l7bug.factorymethod;

import lombok.extern.slf4j.Slf4j;

/**
 * 工厂方法模式演示
 *
 * 场景：通知系统
 * - 邮件工厂创建邮件通知
 * - 短信工厂创建短信通知
 * - 推送工厂创建推送通知
 *
 * 客户端只需要通过工厂接口创建对象，
 * 不需要知道具体是哪个实现类
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("=== 工厂方法模式演示 ===\n");

        // 1. 邮件通知
        log.info("1. 邮件通知工厂");
        NotificationFactory emailFactory = new EmailNotificationFactory();
        Notification emailNotification = emailFactory.createNotification();
        emailNotification.send("您的订单已发货");
        log.info("工厂类型: {}", emailFactory.getType());
        log.info("通知类型: {}", emailNotification.getType());

        // 2. 短信通知
        log.info("\n2. 短信通知工厂");
        NotificationFactory smsFactory = new SmsNotificationFactory();
        Notification smsNotification = smsFactory.createNotification();
        smsNotification.send("您的验证码是 123456");
        log.info("工厂类型: {}", smsFactory.getType());
        log.info("通知类型: {}", smsNotification.getType());

        // 3. 推送通知
        log.info("\n3. 推送通知工厂");
        NotificationFactory pushFactory = new PushNotificationFactory();
        Notification pushNotification = pushFactory.createNotification();
        pushNotification.send("您有新的消息");
        log.info("工厂类型: {}", pushFactory.getType());
        log.info("通知类型: {}", pushNotification.getType());

        // 4. 多态演示：客户端不需要知道具体类型
        log.info("\n4. 多态演示");
        NotificationFactory[] factories = {
            new EmailNotificationFactory(),
            new SmsNotificationFactory(),
            new PushNotificationFactory()
        };

        for (NotificationFactory factory : factories) {
            Notification notification = factory.createNotification();
            notification.send("这是通过 " + factory.getType() + " 发送的消息");
        }

        log.info("\n=== 工厂方法模式演示完成 ===");
    }
}
