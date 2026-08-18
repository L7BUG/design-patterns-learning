package com.l7bug.factorymethod;

/**
 * 产品接口 - 通知
 *
 * 定义了所有通知类型必须实现的方法
 */
public interface Notification {

    /**
     * 发送通知
     * @param message 消息内容
     */
    void send(String message);

    /**
     * 获取通知类型
     * @return 类型名称
     */
    String getType();
}
