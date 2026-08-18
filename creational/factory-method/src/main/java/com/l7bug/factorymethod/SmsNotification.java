package com.l7bug.factorymethod;

/**
 * 具体产品 - 短信通知
 */
public class SmsNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("[短信] 发送短信通知: " + message);
    }

    @Override
    public String getType() {
        return "SMS";
    }
}
