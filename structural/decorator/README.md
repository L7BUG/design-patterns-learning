# Decorator 装饰器模式

## 解决什么问题？
> 动态地给对象添加新的功能，而不修改原有代码。

## 核心思想
> 包装（Wrapper）— 将对象放入装饰器中，通过层层包装添加功能。

## 适用场景
- 需要动态添加对象功能
- 避免子类爆炸（继承导致大量子类）
- 一个类的定义被隐藏或无法继承

## 类图结构

```
                    ┌─────────────────┐
                    │    Coffee       │  ← 抽象构件
                    │  +getCost()     │
                    └────────┬────────┘
                             │
              ┌──────────────┼──────────────┐
              ▼              ▼              ▼
     ┌────────────────┐ ┌────────────────┐  ┌────────────────┐
     │   Espresso     │ │  Americano     │  │CoffeeDecorator │  ← 抽象装饰者
     │  15元          │ │  12元          │  │ ~coffee        │
     └────────────────┘ └────────────────┘  └───────┬────────┘
                                            实现/继承 │
                        ┌───────────────────────────┼───────────┐
                        ▼              ▼            ▼
                ┌─────────────┐ ┌─────────────┐ ┌─────────────┐
                │ MilkDeco    │ │ SugarDeco   │ │ WhipDeco    │  ← 具体装饰者
                │ +5元        │ │ +2元        │ │ +3元        │
                └─────────────┘ └─────────────┘ └─────────────┘
```

## 代码结构

```
Decorator/
├── Coffee.java                  # 抽象构件接口
├── Espresso.java                # 具体构件 - 浓缩咖啡
├── Americano.java               # 具体构件 - 美式咖啡
├── CoffeeDecorator.java         # 抽象装饰者
├── MilkDecorator.java           # 具体装饰者 - 牛奶
├── SugarDecorator.java          # 具体装饰者 - 糖
├── WhipDecorator.java           # 具体装饰者 - 奶泡
├── App.java                     # 演示类
├── DecoratorTest.java           # 单元测试
└── README.md                    # 本文件
```

## 关键点

### 1. 抽象构件
```java
public interface Coffee {
    String getDescription();
    double getCost();
}
```

### 2. 抽象装饰者
```java
public abstract class CoffeeDecorator implements Coffee {
    protected final Coffee coffee;  // 持有被装饰对象

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}
```

### 3. 具体装饰者
```java
public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + 牛奶";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 5.0;  // 添加价格
    }
}
```

### 4. 链式装饰
```java
// 美式 + 牛奶 + 糖 + 奶泡 = 12 + 5 + 2 + 3 = 22 元
Coffee fancy = new WhipDecorator(
        new SugarDecorator(
                new MilkDecorator(new Americano())));
```

## 装饰器 vs 继承

| 方式 | 优点 | 缺点 |
|------|------|------|
| **继承** | 简单 | 子类爆炸（MilkEspresso, SugarEspresso...） |
| **装饰器** | 灵活组合 | 类数量多，但可以复用 |

## 实际应用

- `java.io.BufferedReader`（包装 Reader 添加缓冲）
- `java.io.BufferedInputStream`
- `java.util.Collections.synchronizedList()`
- Java 8 的 `Function.andThen()`
- Spring 的 TransactionTemplate

## 个人理解与心得

> 装饰器模式完美体现了"开闭原则"。
> 对扩展开放（添加装饰器），对修改关闭（不修改原类）。
> Java IO 流是最经典的例子：`new BufferedReader(new FileReader(...))`
> 通过层层包装添加功能，不用修改原来的类。
