# 结构型设计模式

## 概述
7 个模块（全部完成），演示对象组合和类关系。

## 结构
```
structural/
├── adapter/     ✅ MediaPlayer/AudioPlayer 与 MediaAdapter
├── bridge/      ✅ RemoteControl 与 Tv/Radio（Device 桥接）
├── composite/   ✅ FileSystemComponent 与 File/Folder（树形结构）
├── decorator/   ✅ Coffee 与 MilkDecorator/SugarDecorator/WhipDecorator
├── facade/      ✅ ComputerFacade 封装 Cpu/Memory/HardDrive
├── flyweight/   ✅ CharacterStyle 工厂共享（TextCharacter 上下文）
└── proxy/       ✅ ImageProxy 延迟加载 RealImage
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
| 组合组件 | `composite/src/main/java/com/l7bug/composite/FileSystemComponent.java` | 统一接口，add/remove 默认抛异常（透明性） |
| 组合叶子 | `composite/src/main/java/com/l7bug/composite/File.java` | 叶子节点，无子组件 |
| 组合容器 | `composite/src/main/java/com/l7bug/composite/Folder.java` | 递归汇总大小、递归打印 |
| 外观门面 | `facade/src/main/java/com/l7bug/facade/ComputerFacade.java` | start() 一键启动，封装 3 个子系统 |
| 外观子系统 | `facade/src/main/java/com/l7bug/facade/{Cpu,Memory,HardDrive}.java` | 各自独立，可测试状态 |
| 享元对象 | `flyweight/src/main/java/com/l7bug/flyweight/CharacterStyle.java` | 不可变内部状态（字体/字号/颜色） |
| 享元工厂 | `flyweight/src/main/java/com/l7bug/flyweight/CharacterStyleFactory.java` | ConcurrentHashMap 缓存池 |
| 享元上下文 | `flyweight/src/main/java/com/l7bug/flyweight/TextCharacter.java` | 外部状态（字符值+坐标）+ 享元引用 |
| 代理主题 | `proxy/src/main/java/com/l7bug/proxy/Image.java` | 抽象主题（display/getFileName） |
| 代理真实对象 | `proxy/src/main/java/com/l7bug/proxy/RealImage.java` | 构造时执行昂贵加载 |
| 代理占位 | `proxy/src/main/java/com/l7bug/proxy/ImageProxy.java` | 延迟创建 RealImage，首次 display() 才加载 |

## 约定
- **适配器角色命名**：源文件在 Javadoc 注释中使用 GOF 角色名（Adaptee、Adapter、Target）
- **装饰器链**：具体装饰器是叶子类，不再有进一步继承
- **桥接角色命名**：Javadoc 使用 GOF 角色名（Abstraction、RefinedAbstraction、Implementor、ConcreteImplementor）
- **组合透明性**：add/remove 定义在 Component 抽象类，叶子节点抛 UnsupportedOperationException
- **外观可测试**：子系统暴露最小可验证状态（如 Cpu.isExecuted()、Memory.getData()），外观构造注入子系统
- **享元不可变**：内部状态（CharacterStyle）final 字段无 setter；外部状态放 TextCharacter
- **代理惰性**：Proxy 持有 nullable 的真实对象引用，首次调用才创建

## 反模式
- **无共享适配器示例**：adapter 模块在 MediaAdapter 构造函数中硬编码格式分发，而非使用注册表
- **无动态代理示例**：proxy 模块仅演示静态代理（延迟加载），未覆盖 java.lang.reflect.Proxy