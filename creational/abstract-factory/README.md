# Abstract Factory 抽象工厂模式

## 解决什么问题？
> 提供一个接口，用于创建一族相关或相互依赖的对象，而不需要指定它们的具体类。

## 核心思想
> 工厂的工厂 — 将多个相关的工厂方法组合在一起，创建一个完整的产品族。

## 适用场景
- 系统需要独立于产品的创建方式
- 需要确保多个相关产品一起使用（产品族一致性）
- 想要暴露接口而不是实现
- 需要在运行时选择产品族

## 类图结构

```
                    ┌─────────────────┐
                    │     Client      │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   UIFactory     │  ← 抽象工厂
                    │  +createButton()│
                    │  +createCheckbox()│
                    │  +createTextField()│
                    └────────┬────────┘
                             │
              ┌──────────────┼──────────────┐
              ▼              ▼              ▼
     ┌────────────────┐ ┌────────────────┐
     │ LightFactory   │ │ DarkFactory    │  ← 具体工厂
     └───────┬────────┘ └───────┬────────┘
             │                  │
     ┌───────┼───────┐  ┌──────┼───────┐
     ▼       ▼       ▼  ▼      ▼       ▼
   Button  Checkbox  TextField ...（同一主题的所有产品）
```

## 代码结构

```
Abstract Factory/
├── Button.java                  # 抽象产品 - 按钮
├── Checkbox.java                # 抽象产品 - 复选框
├── TextField.java               # 抽象产品 - 文本框
├── LightButton.java             # 具体产品 - 浅色按钮
├── LightCheckbox.java           # 具体产品 - 浅色复选框
├── LightTextField.java          # 具体产品 - 浅色文本框
├── DarkButton.java              # 具体产品 - 深色按钮
├── DarkCheckbox.java            # 具体产品 - 深色复选框
├── DarkTextField.java           # 具体产品 - 深色文本框
├── UIFactory.java               # 抽象工厂
├── LightThemeFactory.java       # 具体工厂 - 浅色主题
├── DarkThemeFactory.java        # 具体工厂 - 深色主题
├── App.java                     # 演示类
├── AbstractFactoryTest.java     # 单元测试
└── README.md                    # 本文件
```

## 关键点

### 1. 抽象工厂接口
```java
public interface UIFactory {
    Button createButton();       // 创建按钮
    Checkbox createCheckbox();   // 创建复选框
    TextField createTextField(); // 创建文本框
}
```

### 2. 具体工厂
```java
public class LightThemeFactory implements UIFactory {
    public Button createButton() { return new LightButton(); }
    public Checkbox createCheckbox() { return new LightCheckbox(); }
    public TextField createTextField() { return new LightTextField(); }
}
```

### 3. 客户端使用
```java
// 客户端只依赖 UIFactory 接口
UIFactory factory = new LightThemeFactory();
Button button = factory.createButton();
button.render();
```

## 与工厂方法的区别

| 模式 | 产品数量 | 关注点 |
|------|---------|--------|
| **Factory Method** | 单个产品 | 一个工厂方法创建一个产品 |
| **Abstract Factory** | 产品族 | 一组相关产品由同一个工厂创建 |

## 实际应用

- Java Swing 的 `LookAndFeel` 类
- `DocumentBuilderFactory`
- `TransformerFactory`
- `XPathFactory`
- 游戏中的种族系统（精灵/兽人各自的一套单位）

## 个人理解与心得

> 抽象工厂是"工厂方法的升级版"。
> 工厂方法关注"创建一个产品"，抽象工厂关注"创建一族产品"。
> 当你需要确保多个对象属于同一个"系列"时（如浅色主题的所有组件），
> 抽象工厂能保证一致性，避免混用不同主题的组件。
