package com.l7bug.factorykit;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory Kit 模式演示
 *
 * 场景：消息工厂
 * - 通过 Consumer 配置工厂，注册消息类型
 * - 运行时动态创建消息
 * - 工厂接口和构建器接口分离
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("=== Factory Kit 模式演示 ===\n");

        // 1. 创建工厂并注册消息类型
        log.info("1. 创建工厂并注册消息类型");
        MessageFactory factory = MessageFactory.factory(builder -> {
            builder.add("EMAIL", EmailMessage::new);
            builder.add("SMS", SmsMessage::new);
            builder.add("PUSH", PushMessage::new);
        });

        // 2. 使用工厂创建消息
        log.info("\n2. 使用工厂创建消息");
        List<Message> messages = new ArrayList<>();
        messages.add(factory.create("EMAIL"));
        messages.add(factory.create("SMS"));
        messages.add(factory.create("PUSH"));

        for (Message message : messages) {
            message.send("这是一条 " + message.getType() + " 消息");
        }

        // 3. 动态注册新类型
        log.info("\n3. 动态注册新类型（Webhook）");
        MessageFactory extendedFactory = MessageFactory.factory(builder -> {
            builder.add("EMAIL", EmailMessage::new);
            builder.add("SMS", SmsMessage::new);
            builder.add("PUSH", PushMessage::new);
            builder.add("WEBHOOK", WebhookMessage::new);  // 新增类型
        });

        Message webhook = extendedFactory.create("WEBHOOK");
        webhook.send("Webhook 回调通知");

        // 4. 未注册类型测试
        log.info("\n4. 未注册类型测试");
        try {
            extendedFactory.create("UNKNOWN");
        } catch (IllegalArgumentException e) {
            log.info("预期异常: {}", e.getMessage());
        }

        log.info("\n=== Factory Kit 模式演示完成 ===");
    }
}
