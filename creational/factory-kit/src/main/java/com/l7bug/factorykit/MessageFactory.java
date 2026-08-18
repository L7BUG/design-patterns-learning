package com.l7bug.factorykit;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 消息工厂 - Factory Kit 模式
 *
 * 核心特点：
 * 1. 工厂接口和构建器接口分离
 * 2. 通过 Consumer<Builder> 配置工厂
 * 3. 运行时动态注册消息类型
 * 4. 使用函数式接口实现，代码简洁
 */
public interface MessageFactory {

    /**
     * 创建消息工厂
     *
     * @param consumer 配置函数，用于注册消息类型
     * @return 消息工厂实例
     */
    static MessageFactory factory(Consumer<MessageBuilder> consumer) {
        Map<String, Supplier<Message>> map = new HashMap<>();
        consumer.accept(map::put);
        return name -> {
            Supplier<Message> supplier = map.get(name);
            if (supplier == null) {
                throw new IllegalArgumentException("未注册的消息类型: " + name);
            }
            return supplier.get();
        };
    }

    /**
     * 创建消息
     *
     * @param name 消息类型名称
     * @return 消息实例
     */
    Message create(String name);
}
