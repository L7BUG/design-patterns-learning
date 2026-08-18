package com.l7bug.factorykit;

/**
 * 具体产品 - 短信消息
 */
public class SmsMessage implements Message {

    @Override
    public void send(String content) {
        System.out.println("[短信] 发送: " + content);
    }

    @Override
    public String getType() {
        return "SMS";
    }
}
