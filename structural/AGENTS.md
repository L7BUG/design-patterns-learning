# 结构型设计模式

## 概述
7 个模块（3 个完成，4 个空骨架），演示对象组合和类关系。

## 结构
```
structural/
├── adapter/     ✅ MediaPlayer/AudioPlayer 与 MediaAdapter
├── bridge/      ✅ RemoteControl 与 Tv/Radio（Device 桥接）
├── composite/   ✗ 空
├── decorator/   ✅ Coffee 与 MilkDecorator/SugarDecorator/WhipDecorator
├── facade/      ✗ 空
├── flyweight/   ✗ 空
└── proxy/       ✗ 空
```

## 查找指南
| 任务 | 位置 | 备注 |
|------|------|------|
| 适配器核心 | `adapter/src/main/java/com/l7bug/adapter/MediaAdapter.java` | 实现 MediaPlayer，委托给 AdvancedMediaPlayer |
| 被适配者接口 | `adapter/src/main/java/com/l7bug/adapter/AdvancedMediaPlayer.java` | 不兼容接口（playVlc/playMp4） |
| 装饰器基础 | `decorator/src/main/java/com/l7bug/decorator/Coffee.java` | 组件接口（getDescription/getCost） |
| 抽象装饰器 | `decorator/src/main/java/com/l7bug/decorator/CoffeeDecorator.java` | 持有 Coffee 引用，默认委托 |
| 具体装饰器 | `decorator/src/main/java/com/l7bug/decorator/*Decorator.java` | Milk、Sugar、Whip 继承 CoffeeDecorator |
| 桥接抽象 | `bridge/src/main/java/com/l7bug/bridge/RemoteControl.java` | 抽象遥控器，持有 Device 引用（桥接点） |
| 桥接实现 | `bridge/src/main/java/com/l7bug/bridge/Device.java` | 实现部分接口（enable/disable/setVolume） |
| 桥接细化 | `bridge/src/main/java/com/l7bug/bridge/AdvancedRemoteControl.java` | 扩展 mute()，展示独立变化 |

## 约定
- **适配器角色命名**：源文件在 Javadoc 注释中使用 GOF 角色名（Adaptee、Adapter、Target）
- **装饰器链**：具体装饰器是叶子类，不再有进一步继承
- **桥接角色命名**：Javadoc 使用 GOF 角色名（Abstraction、RefinedAbstraction、Implementor、ConcreteImplementor）

## 反模式
- **57% 骨架**：4/7 模块只有 pom.xml（composite、facade、flyweight、proxy）
- **无共享适配器示例**：adapter 模块在 MediaAdapter 构造函数中硬编码格式分发，而非使用注册表
