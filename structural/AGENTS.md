# 结构型设计模式

## 概述
7 个模块（2 个完成，5 个空骨架），演示对象组合和类关系。

## 结构
```
structural/
├── adapter/     ✅ MediaPlayer/AudioPlayer 与 MediaAdapter
├── bridge/      ✗ 空
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

## 约定
- **适配器角色命名**：源文件在 Javadoc 注释中使用 GOF 角色名（Adaptee、Adapter、Target）
- **装饰器链**：具体装饰器是叶子类，不再有进一步继承

## 反模式
- **71% 骨架**：5/7 模块只有 pom.xml（bridge、composite、facade、flyweight、proxy）
- **无共享适配器示例**：adapter 模块在 MediaAdapter 构造函数中硬编码格式分发，而非使用注册表
