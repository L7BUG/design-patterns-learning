# Builder 建造者模式

## 解决什么问题？
> 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。

## 核心思想
> 使用链式调用（流式 API）逐步构建复杂对象，必填参数通过构造函数，可选参数通过方法。

## 适用场景
- 对象有很多可选参数
- 构造函数参数过多（伸缩构造函数反模式）
- 想要创建不可变对象
- 需要不同的构建过程创建不同的表示

## 类图结构

```
                    ┌─────────────────┐
                    │     Client      │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │ Computer        │  ← 产品
                    │ -cpu, -ram, ... │
                    └────────┬────────┘
                             │
                    ┌────────┴────────┐
                    │ Computer.Builder │  ← 静态内部类
                    │ +cpu()          │
                    │ +ram()          │
                    │ +storage()      │
                    │ +build()        │
                    └─────────────────┘
```

## 代码结构

```
Builder/
├── Computer.java              # 产品（含静态内部类 Builder）
├── App.java                   # 演示类
├── BuilderTest.java           # 单元测试
└── README.md                  # 本文件
```

## 关键点

### 1. 产品类（私有构造函数）
```java
public class Computer {
    private final String cpu;      // 必填
    private final String ram;      // 必填
    private final String storage;  // 可选

    private Computer(Builder builder) {  // 私有！
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
    }
}
```

### 2. 静态内部类 Builder
```java
public static class Builder {
    private final String cpu;   // 必填
    private final String ram;   // 必填
    private String storage = "256GB SSD";  // 可选，有默认值

    public Builder(String cpu, String ram) {  // 必填参数
        this.cpu = cpu;
        this.ram = ram;
    }

    public Builder storage(String storage) {  // 可选参数，链式调用
        this.storage = storage;
        return this;  // 返回 this 实现链式
    }

    public Computer build() {
        return new Computer(this);
    }
}
```

### 3. 链式调用
```java
Computer computer = new Computer.Builder("Intel i5", "16GB")
        .storage("512GB SSD")      // 可选
        .gpu("NVIDIA RTX 3060")    // 可选
        .hasWifi(true)             // 可选
        .build();                  // 构建
```

## 与其他模式的区别

| 模式 | 区别 |
|------|------|
| **Factory Method** | 工厂方法创建单个对象，Builder 构建复杂对象 |
| **Abstract Factory** | 抽象工厂创建一族对象，Builder 构建单个复杂对象 |
| **Prototype** | 原型通过克隆创建，Builder 通过步骤构建 |

## 实际应用

- `StringBuilder` / `StringBuffer`
- `ByteBuffer`
- `javax.swing.GroupLayout`
- Apache Camel 的 Builder API
- Lombok 的 `@Builder`

## 个人理解与心得

> Builder 模式解决了"伸缩构造函数"问题。
> 当对象有 5 个以上参数时，特别是很多可选参数，Builder 是最佳选择。
> 链式调用让代码可读性极高，每个方法名都表达了意图。
> Java 的 `StringBuilder` 就是 Builder 模式的典型应用。
