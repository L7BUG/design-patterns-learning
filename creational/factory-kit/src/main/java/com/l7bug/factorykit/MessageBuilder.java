package com.l7bug.factorykit;

import java.util.function.Supplier;

/**
 * 消息构建器接口
 *
 * 用于注册消息类型到工厂
 * 使用函数式接口，支持 lambda 表达式
 */
@FunctionalInterface
public interface MessageBuilder {

    /**
     * 注册消息类型
     *
     * @param name     消息类型名称
     * @param supplier 消息创建函数
     */
    void add(String name, Supplier<Message> supplier);
}
