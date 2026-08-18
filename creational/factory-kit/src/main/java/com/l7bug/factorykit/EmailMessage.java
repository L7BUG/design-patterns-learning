package com.l7bug.factorykit;

/**
 * 具体产品 - 邮件消息
 */
public class EmailMessage implements Message {

    @Override
    public void send(String content) {
        System.out.println("[邮件] 发送: " + content);
    }

    @Override
    public String getType() {
        return "EMAIL";
    }
}
