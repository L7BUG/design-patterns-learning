package com.l7bug.factorykit;

/**
 * 具体产品 - 推送消息
 */
public class PushMessage implements Message {

    @Override
    public void send(String content) {
        System.out.println("[推送] 发送: " + content);
    }

    @Override
    public String getType() {
        return "PUSH";
    }
}
