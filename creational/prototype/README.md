# Prototype 原型模式

## 解决什么问题？
> 用原型实例指定创建对象的种类，并且通过克隆这些原型创建新对象。

## 核心思想
> 通过克隆（clone）现有对象来创建新对象，而不是通过 new。

## 适用场景
- 创建对象成本高（需要网络请求、数据库查询等）
- 需要在运行时确定对象类型
- 对象状态有限，可以预定义原型
- 避免创建大量的工厂类

## 类图结构

```
                    ┌─────────────────┐
                    │  <<interface>>  │
                    │     Shape       │  ← 原型接口
                    │  +clone()       │
                    └────────┬────────┘
                             │
              ┌──────────────┼──────────────┐
              ▼              ▼              ▼
     ┌────────────────┐ ┌────────────────┐
     │     Circle     │ │   Rectangle    │  ← 具体原型
     │  +clone()      │ │  +clone()      │
     └────────────────┘ └────────────────┘

                    ┌─────────────────┐
                    │   ShapeCache    │  ← 原型缓存
                    │  +getShape()    │
                    │  -cache         │
                    └─────────────────┘
```

## 代码结构

```
Prototype/
├── Shape.java                 # 原型接口
├── Circle.java                # 具体原型 - 圆形
├── Rectangle.java             # 具体原型 - 矩形
├── ShapeCache.java            # 原型缓存
├── App.java                   # 演示类
├── PrototypeTest.java         # 单元测试
└── README.md                  # 本文件
```

## 关键点

### 1. 原型接口
```java
public interface Shape extends Cloneable {
    Shape clone();
}
```

### 2. 拷贝构造函数
```java
public class Circle implements Shape {
    public Circle(Circle source) {
        this.color = source.color;
        this.radius = source.radius;
    }

    @Override
    public Shape clone() {
        return new Circle(this);  // 调用拷贝构造函数
    }
}
```

### 3. 原型缓存
```java
public class ShapeCache {
    private static final Map<String, Shape> cache = new HashMap<>();

    public static void loadCache() {
        cache.put("RED_CIRCLE", new Circle("红色", 10));
        cache.put("BLUE_CIRCLE", new Circle("蓝色", 15));
    }

    public static Shape getShape(String key) {
        return cache.get(key).clone();  // 返回克隆体
    }
}
```

## 浅拷贝 vs 深拷贝

| 类型 | 说明 | 适用场景 |
|------|------|---------|
| **浅拷贝** | 复制基本类型，引用类型共享 | 对象没有引用类型 |
| **深拷贝** | 复制所有字段，包括引用类型 | 对象有引用类型 |

本示例使用**深拷贝**（通过拷贝构造函数）。

## 与其他模式的区别

| 模式 | 区别 |
|------|------|
| **Factory Method** | 工厂方法通过 new 创建，原型通过克隆创建 |
| **Builder** | Builder 逐步构建，原型直接克隆整个对象 |
| **Singleton** | 单例只有一个实例，原型可以克隆出多个实例 |

## 实际应用

- `Object.clone()` 方法
- `java.util.Arrays.copyOf()`
- 序列化克隆（深拷贝）
- Spring Bean 的 prototype 作用域

## 个人理解与心得

> 原型模式的核心是"克隆"。
> 当创建对象成本高（如需要网络请求、数据库查询）时，
> 克隆一个已配置好的原型比重新创建要高效得多。
> 注意深拷贝和浅拷贝的区别，处理引用类型时要格外小心。
