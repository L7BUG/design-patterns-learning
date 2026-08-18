# Factory Kit 工厂套件模式

## 解决什么问题？
> 创建工厂时，将构建器接口和工厂接口分离，支持运行时动态配置。

## 核心思想
> 通过 Consumer<Builder> 配置工厂，将"注册类型"和"创建对象"分离。

## 适用场景
- 工厂需要创建的对象类型在编译时未知
- 需要在运行时动态注册新类型
- 想要更灵活的工厂配置方式
- 函数式编程风格

## 类图结构

```
                    ┌─────────────────┐
                    │     Client      │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │ MessageFactory  │  ← 工厂接口
                    │ +factory()      │
                    │ +create()       │
                    └────────┬────────┘
                             │
                    ┌────────┴────────┐
                    │ MessageBuilder  │  ← 构建器接口（函数式）
                    │ +add()          │
                    └─────────────────┘
```

## 代码结构

```
Factory Kit/
├── Message.java                 # 产品接口
├── EmailMessage.java            # 具体产品 - 邮件
├── SmsMessage.java              # 具体产品 - 短信
├── PushMessage.java             # 具体产品 - 推送
├── WebhookMessage.java          # 具体产品 - Webhook
├── MessageFactory.java          # 工厂接口
├── MessageBuilder.java          # 构建器接口
├── App.java                     # 演示类
├── FactoryKitTest.java          # 单元测试
└── README.md                    # 本文件
```

## 关键点

### 1. 工厂接口
```java
public interface MessageFactory {
    static MessageFactory factory(Consumer<MessageBuilder> consumer) {
        Map<String, Supplier<Message>> map = new HashMap<>();
        consumer.accept(map::put);
        return name -> map.get(name).get();
    }

    Message create(String name);
}
```

### 2. 构建器接口（函数式）
```java
@FunctionalInterface
public interface MessageBuilder {
    void add(String name, Supplier<Message> supplier);
}
```

### 3. 使用方式
```java
// 通过 Consumer 配置工厂
MessageFactory factory = MessageFactory.factory(builder -> {
    builder.add("EMAIL", EmailMessage::new);
    builder.add("SMS", SmsMessage::new);
    builder.add("PUSH", PushMessage::new);
});

// 使用工厂创建对象
Message email = factory.create("EMAIL");
```

## 与其他工厂模式的区别

| 模式 | 配置方式 | 灵活性 |
|------|---------|--------|
| **Factory Method** | 继承 | 中等 |
| **Abstract Factory** | 继承 | 中等 |
| **Factory Kit** | Consumer 配置 | 高（运行时动态） |

## 实际应用

- 消息队列系统
- 插件系统
- 配置驱动的对象创建
- 函数式编程场景

## 个人理解与心得

> Factory Kit 是函数式编程与工厂模式的完美结合。
> 它通过 Consumer<Builder> 将配置逻辑外部化，
> 使得工厂本身完全不知道要创建什么类型。
> 这种设计非常灵活，可以在运行时动态注册新类型。
