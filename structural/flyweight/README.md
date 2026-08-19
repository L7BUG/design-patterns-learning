# Flyweight 享元模式

## 解决什么问题？
> 当系统中存在大量细粒度的对象，且这些对象有大量重复状态时，
> 如何避免创建过多对象导致内存爆炸？

## 核心思想
> 享元（Flyweight）— 将对象的状态分为内部状态（intrinsic）和外部状态（extrinsic），
> 内部状态是可共享的、不变的，存储在享元对象中；
> 外部状态是不可共享的、随上下文变化的，由客户端在使用时传入。
> 通过共享内部状态，大幅减少对象数量。

## 适用场景
- 应用中使用了大量对象，造成内存开销过大
- 对象的大部分状态可以外部化（可以将状态传入而非存储在对象中）
- 去除外部状态后，可以用较少的共享对象替代多组类似对象
- 文本编辑器中大量字符共享字体样式
- 游戏中大量粒子/子弹共享纹理和颜色

## 内部状态 vs 外部状态

| 状态类型 | 说明 | 本例 |
|---------|------|------|
| **内部状态（intrinsic）** | 可共享、不变，存储在享元中 | 字体名、字号、颜色 |
| **外部状态（extrinsic）** | 不可共享、随上下文变化 | 字符值、位置坐标(x,y) |

## 类图结构

```
    ┌──────────────────────────┐
    │ CharacterStyleFactory    │  ← 享元工厂（FlyweightFactory）
    │  - CACHE: Map            │  管理享元缓存池
    │  + getStyle(): CharacterStyle  │  获取或创建享元
    │  + getCacheSize(): int   │  查询缓存大小
    └────────────┬─────────────┘
                 │ 创建/管理
    ┌────────────┴─────────────┐
    │     CharacterStyle       │  ← 享元对象（Flyweight）
    │  - fontName: String      │  内部状态（共享）
    │  - fontSize: int         │
    │  - color: String         │
    │  + toCacheKey(): String  │
    └──────────────────────────┘
                 │ 被引用
    ┌──────────────────────────┐
    │    TextCharacter       │  ← 上下文对象（Context）
    │  - value: char           │  外部状态（不共享）
    │  - x: int                │
    │  - y: int                │
    │  - style: CharacterStyle │  持有享元引用
    │  + render(): String      │
    └──────────────────────────┘
```

## 代码结构

```
Flyweight/
├── CharacterStyle.java         # 享元对象 — 共享内部状态（Flyweight）
├── CharacterStyleFactory.java  # 享元工厂 — 管理缓存池（FlyweightFactory）
├── TextCharacter.java           # 上下文对象 — 外部状态（Context）
├── App.java                    # 演示类（Client）
├── FlyweightTest.java          # 单元测试
└── README.md                   # 本文件
```

## 关键点

### 1. 享元对象 — 不可变的内部状态（CharacterStyle）
```java
public final class CharacterStyle {
    private final String fontName;  // 内部状态：不可变
    private final int fontSize;
    private final String color;

    public CharacterStyle(String fontName, int fontSize, String color) {
        this.fontName = fontName;
        this.fontSize = fontSize;
        this.color = color;
    }
    // 没有 setter — 创建后不能修改，确保共享安全
}
```

### 2. 享元工厂 — 缓存池管理（CharacterStyleFactory）
```java
public static CharacterStyle getStyle(String fontName, int fontSize, String color) {
    String key = fontName + "-" + fontSize + "-" + color;
    // computeIfAbsent：线程安全的"查缓存或创建"操作
    return CACHE.computeIfAbsent(key, k -> new CharacterStyle(fontName, fontSize, color));
}
```

### 3. 上下文对象 — 外部状态 + 享元引用（TextCharacter）
```java
public class TextCharacter {
    private final char value;            // 外部状态：每个字符不同
    private final int x, y;             // 外部状态：位置各异
    private final CharacterStyle style; // 共享引用：指向享元对象

    public String render() {
        return "['" + value + "' @(" + x + "," + y + ") "
                + style.getFontName() + "-" + style.getFontSize() + "-" + style.getColor() + "]";
    }
}
```

### 4. 演示效果
```
100 个字符仅创建 3 个样式对象
如果每个字符都独立创建样式，则需要 100 个对象
减少 97 个对象创建，节省 97.0% 的样式对象内存
```

## 现实中的享元模式

| 场景 | 内部状态（共享） | 外部状态（不共享） |
|------|----------------|-------------------|
| **String 池** | 字符串内容 | 引用位置 |
| **Integer 缓存** | -128~127 的值 | 使用处的变量名 |
| **线程池** | 线程的创建和管理 | 各任务的 Runnable |
| **数据库连接池** | 连接的创建和管理 | 各次查询的 SQL |
| **棋类游戏** | 棋子的类型和图片 | 棋子在棋盘的位置 |

## 享元模式 vs 其他模式

| 模式 | 区别 |
|------|------|
| **单例** | 单例确保全局唯一实例；享元确保同一"键"返回同一实例 |
| **工厂方法** | 工厂方法每次创建新对象；享元工厂缓存复用已创建的对象 |
| **对象池** | 对象池管理对象的借出/归还；享元通过共享减少对象数量 |
| **缓存** | 缓存是通用概念；享元是缓存模式在设计模式中的具体应用 |

## 个人理解与心得

> 享元模式的本质是"用共享替代重复"。
> 关键在于识别哪些状态是内部的（可共享）、哪些是外部的（不可共享）。
>
> 内部状态的特征：
> - 不随使用场景变化（字体名在所有字符间相同）
> - 创建后不需要修改（不可变对象）
> - 可以用简单的键来唯一标识（"宋体-12-黑色"）
>
> 外部状态的特征：
> - 每个使用场景都不同（每个字符的坐标不同）
> - 无法或不适合共享（字符值是每个字符独有的）
>
> 在实际开发中，享元模式最常见的应用就是各种"池"：
> - String 池：相同字符串内容只存储一份
> - Integer 缓存：-128 到 127 的整数只创建一次
> - 线程池：线程被复用而非每次任务都创建新线程
>
> 理解了"内部状态 vs 外部状态"的划分，
> 就能在遇到大量相似对象时，自然地想到享元模式。
