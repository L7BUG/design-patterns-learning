# Factory Method 工厂方法模式

## 解决什么问题？
> 定义一个创建对象的接口，但让子类决定实例化哪个类。工厂方法将对象的创建延迟到子类。

## 核心思想
> 父类定义创建对象的接口（工厂方法），子类决定具体创建哪个对象。

## 适用场景
- 不知道具体需要创建哪种对象
- 想让子类决定创建什么对象
- 想要将创建逻辑与使用逻辑分离

## 类图结构

```
                    ┌─────────────────┐
                    │    Client       │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │    Factory      │  ← 工厂接口
                    │  +create()      │
                    └────────┬────────┘
                             │
              ┌──────────────┼──────────────┐
              ▼              ▼              ▼
     ┌────────────────┐ ┌────────────────┐ ┌────────────────┐
     │ EmailFactory   │ │ SmsFactory     │ │ PushFactory    │  ← 具体工厂
     │ +create()      │ │ +create()      │ │ +create()      │
     └────────┬───────┘ └────────┬───────┘ └────────┬───────┘
              │                  │                  │
              ▼                  ▼                  ▼
     ┌────────────────┐ ┌────────────────┐ ┌────────────────┐
     │ EmailNotif.    │ │ SmsNotif.      │ │ PushNotif.     │  ← 具体产品
     │ +send()        │ │ +send()        │ │ +send()        │
     └────────────────┘ └────────────────┘ └────────────────┘
```

## 代码结构

```
Factory Method/
├── Notification.java                    # 产品接口
├── EmailNotification.java               # 具体产品 - 邮件
├── SmsNotification.java                 # 具体产品 - 短信
├── PushNotification.java                # 具体产品 - 推送
├── NotificationFactory.java             # 工厂接口
├── EmailNotificationFactory.java        # 具体工厂 - 邮件
├── SmsNotificationFactory.java          # 具体工厂 - 短信
├── PushNotificationFactory.java         # 具体工厂 - 推送
├── App.java                             # 演示类
├── FactoryMethodTest.java               # 单元测试
└── README.md                            # 本文件
```

## 关键点

### 1. 工厂接口
```java
public interface NotificationFactory {
    Notification createNotification();  // 工厂方法
}
```

### 2. 具体工厂
```java
public class EmailNotificationFactory implements NotificationFactory {
    @Override
    public Notification createNotification() {
        return new EmailNotification();  // 子类决定创建什么
    }
}
```

### 3. 客户端使用
```java
// 客户端只依赖工厂接口
NotificationFactory factory = new EmailNotificationFactory();
Notification notification = factory.createNotification();
notification.send("消息");
```

## 与相似模式的区别

| 模式 | 区别 |
|------|------|
| **Singleton** | 单例保证只有一个实例，工厂方法可以创建多个实例 |
| **Abstract Factory** | 抽象工厂创建产品族，工厂方法创建单个产品 |
| **Prototype** | 原型通过克隆创建，工厂方法通过 new 创建 |

## 实际应用

- `java.util.Calendar.getInstance()`
- `java.text.NumberFormat.getInstance()`
- `java.nio.charset.Charset.forName()`
- `java.net.URLStreamHandlerFactory`
- Spring BeanFactory

## 个人理解与心得

> 工厂方法模式的核心是"延迟到子类"。
> 客户端不需要知道具体创建什么，只需要通过工厂接口创建。
> 当需要新增产品类型时，只需要新增一个工厂类，不需要修改现有代码（符合开闭原则）。
