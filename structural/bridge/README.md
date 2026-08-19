# Bridge 桥接模式

## 解决什么问题？
> 将抽象部分与实现部分分离，使它们可以独立变化。
> 当一个类存在两个或多个独立变化的维度时，避免类爆炸。

## 核心思想
> 桥接（Bridge）— 通过组合（而非继承）将抽象与实现连接，
> 让两个维度可以独立扩展。

## 适用场景
- 一个类存在两个以上独立变化的维度
- 需要在运行时切换实现
- 不希望因为多维度组合导致类数量激增
- 想要隐藏实现细节，只暴露抽象接口

## 类图结构

```
              ┌─────────────────────┐
              │   RemoteControl     │  ← 抽象部分（Abstraction）
              │  # device: Device   │  持有实现引用
              │  + togglePower()    │
              │  + volumeUp()       │
              │  + volumeDown()     │
              └──────────┬──────────┘
                         │ 继承
              ┌──────────┴──────────┐
              │ AdvancedRemoteControl│  ← 细化抽象（RefinedAbstraction）
              │  + mute()           │  扩展功能
              └─────────────────────┘

              ┌─────────────────────┐
              │    <<interface>>     │
              │      Device         │  ← 实现部分接口（Implementor）
              │  + enable()         │
              │  + disable()        │
              │  + setVolume()      │
              │  + getVolume()      │
              │  + isEnabled()      │
              │  + getName()        │
              └──────────┬──────────┘
                         │ 实现
              ┌──────────┴──────────┐
        ┌─────┴─────┐        ┌─────┴─────┐
        │    Tv     │        │   Radio   │  ← 具体实现（ConcreteImplementor）
        │  -volume  │        │  -volume  │  各自维护独立状态
        │  -enabled │        │  -enabled │
        └───────────┘        └───────────┘
```

## 代码结构

```
Bridge/
├── Device.java                  # 设备接口（Implementor）
├── Tv.java                      # 具体实现 - 电视
├── Radio.java                   # 具体实现 - 收音机
├── RemoteControl.java           # 抽象遥控器（Abstraction）
├── BasicRemoteControl.java      # 基础遥控器（RefinedAbstraction）
├── AdvancedRemoteControl.java   # 高级遥控器（RefinedAbstraction）
├── App.java                     # 演示类
├── BridgeTest.java              # 单元测试
└── README.md                    # 本文件
```

## 关键点

### 1. 实现部分接口（Device）
```java
public interface Device {
    void enable();
    void disable();
    void setVolume(int volume);
    int getVolume();
    boolean isEnabled();
    String getName();
}
```

### 2. 抽象部分持有实现引用（桥接）
```java
public abstract class RemoteControl {
    protected final Device device;  // 桥接点

    protected RemoteControl(Device device) {
        this.device = device;       // 构造函数注入
    }

    public void togglePower() {
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }
}
```

### 3. 细化抽象扩展功能
```java
public class AdvancedRemoteControl extends RemoteControl {
    public AdvancedRemoteControl(Device device) {
        super(device);
    }

    public void mute() {
        device.setVolume(0);  // 直接使用桥接的 Device
    }
}
```

### 4. 独立变化演示
```java
// 任意遥控器控制任意设备
Device tv = new Tv();
Device radio = new Radio();
RemoteControl remote1 = new BasicRemoteControl(tv);
RemoteControl remote2 = new AdvancedRemoteControl(radio);
// 电视和收音机的状态互不影响
```

## 桥接模式 vs 继承

| 方式 | 问题 | 桥接模式优势 |
|------|------|-------------|
| **多层继承** | M 个抽象 × N 个实现 = M×N 个类 | M + N 个类 |
| **运行时切换** | 继承在编译期确定 | 组合在运行时绑定 |
| **独立扩展** | 修改一个维度影响另一个 | 两个维度互不干扰 |

## 实际应用

- JDBC 驱动：`Driver`（抽象）与各数据库实现（MySQL/PostgreSQL）
- AWT/Swing：`Component`（抽象）与各平台渲染（Implementor）
- 消息发送：`Message`（抽象）与发送方式（Email/SMS/Push）
- 图形渲染：`Shape`（抽象）与渲染引擎（OpenGL/DirectX）

## 个人理解与心得

> 桥接模式的核心是"用组合替代多层继承"。
> 当你发现一个类需要沿着两个维度独立扩展时，
> 就应该考虑桥接模式，而不是让类数量指数级增长。
> 
> 最典型的例子是 JDBC：
> - 抽象维度：Connection、Statement、ResultSet
> - 实现维度：MySQL、PostgreSQL、Oracle 各自的驱动
> 
> 通过桥接，我们只需要为每个数据库写一套驱动，
> 而不需要为每种数据库 × 每种操作写一个类。
