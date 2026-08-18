package com.l7bug.factorykit;

/**
 * 产品接口 - 消息
 */
public interface Message {

    /**
     * 发送消息
     * @param content 消息内容
     */
    void send(String content);

    /**
     * 获取消息类型
     * @return 类型名称
     */
    String getType();
}
