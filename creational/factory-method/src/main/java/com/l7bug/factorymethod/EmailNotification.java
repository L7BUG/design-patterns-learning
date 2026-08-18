package com.l7bug.factorymethod;

/**
 * 具体产品 - 邮件通知
 */
public class EmailNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("[邮件] 发送邮件通知: " + message);
    }

    @Override
    public String getType() {
        return "EMAIL";
    }
}
