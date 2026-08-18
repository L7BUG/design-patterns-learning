# Adapter 适配器模式

## 解决什么问题？
> 将一个类的接口转换成客户端期望的另一个接口，使不兼容的类可以协同工作。

## 核心思想
> 包装（Wrapper）— 在不修改原有类的前提下，通过适配器转换接口。

## 适用场景
- 使用已有的类，但接口与需求不匹配
- 需要整合第三方库
- 想要创建一个可复用的类，与无关类协同工作

## 类图结构

```
                    ┌─────────────────┐
                    │     Client      │
                    └────────┬────────┘
                             │ 期望 MediaPlayer 接口
                             ▼
                    ┌─────────────────┐
                    │   MediaPlayer   │  ← 目标接口
                    │  +play()        │
                    └────────┬────────┘
                             │ 实现
                             ▼
                    ┌─────────────────┐
                    │  MediaAdapter   │  ← 适配器
                    │  -vlcPlayer     │  持有适配者
                    │  -mp4Player     │
                    └────────┬────────┘
                             │ 委托调用
                    ┌────────┴────────┐
              ┌─────┴─────┐    ┌─────┴─────┐
              │ VlcPlayer │    │ Mp4Player │  ← 适配者
              │ +playVlc()│    │ +playMp4()│
              └───────────┘    └───────────┘
```

## 代码结构

```
Adapter/
├── MediaPlayer.java              # 目标接口
├── AudioPlayer.java              # 基础播放器（支持 mp3）
├── AdvancedMediaPlayer.java      # 适配者接口
├── VlcPlayer.java                # 适配者 - VLC
├── Mp4Player.java                # 适配者 - MP4
├── MediaAdapter.java             # 适配器
├── AudioPlayerAdapter.java       # 增强版播放器
├── App.java                      # 演示类
├── AdapterTest.java              # 单元测试
└── README.md                     # 本文件
```

## 关键点

### 1. 目标接口
```java
public interface MediaPlayer {
    void play(String audioType, String fileName);
}
```

### 2. 适配者（不兼容的类）
```java
public class VlcPlayer {
    public void playVlc(String fileName) { ... }  // 接口不兼容
}
```

### 3. 适配器（桥梁）
```java
public class MediaAdapter implements MediaPlayer {
    private final AdvancedMediaPlayer advancedPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer = new VlcPlayer();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        advancedPlayer.playVlc(fileName);  // 转换调用
    }
}
```

## 类适配器 vs 对象适配器

| 类型 | 实现方式 | 优点 | 缺点 |
|------|---------|------|------|
| **类适配器** | 继承适配者 | 可以重写适配者方法 | 无法适配子类 |
| **对象适配器** | 组合适配者 | 可以适配子类 | 无法重写适配者方法 |

本示例使用**对象适配器**。

## 实际应用

- `java.io.InputStreamReader`（字节流 → 字符流）
- `java.util.Arrays#asList()`
- Spring MVC 的 `HandlerAdapter`
- 日志框架适配

## 个人理解与心得

> 适配器模式的核心是"包装而不修改"。
> 当你无法修改第三方库的代码，但又需要它的功能时，
> 适配器是最优雅的解决方案。
> Java IO 流就是适配器模式的经典应用。
