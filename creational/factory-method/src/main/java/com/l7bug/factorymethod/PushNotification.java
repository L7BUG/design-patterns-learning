package com.l7bug.factorymethod;

/**
 * 具体产品 - 推送通知
 */
public class PushNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("[推送] 发送推送通知: " + message);
    }

    @Override
    public String getType() {
        return "PUSH";
    }
}
