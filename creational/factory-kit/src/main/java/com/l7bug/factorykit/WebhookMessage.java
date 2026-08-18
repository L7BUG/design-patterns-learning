package com.l7bug.factorykit;

/**
 * 具体产品 - Webhook 消息
 */
public class WebhookMessage implements Message {

    @Override
    public void send(String content) {
        System.out.println("[Webhook] 发送: " + content);
    }

    @Override
    public String getType() {
        return "WEBHOOK";
    }
}
