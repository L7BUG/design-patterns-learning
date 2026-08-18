# Singleton 单例模式

## 解决什么问题？
> 确保一个类只有一个实例，并提供一个全局访问点来访问这个实例。

## 核心思想
> 将构造函数私有化，通过静态方法返回唯一的实例。

## 适用场景
- 配置管理器（Configuration Manager）
- 数据库连接池
- 日志记录器
- 线程池
- 缓存

## 实现方式对比

| 实现方式 | 延迟加载 | 线程安全 | 防反射 | 防序列化 | 推荐度 |
|---------|---------|---------|-------|---------|-------|
| 饿汉式 | ❌ | ✅ | ❌ | ❌ | ⭐⭐⭐ |
| 懒汉式（synchronized） | ✅ | ✅ | ❌ | ❌ | ⭐⭐ |
| 双重检查锁 | ✅ | ✅ | ❌ | ❌ | ⭐⭐⭐⭐ |
| Bill Pugh（静态内部类） | ✅ | ✅ | ❌ | ❌ | ⭐⭐⭐⭐⭐ |
| 枚举 | ❌ | ✅ | ✅ | ✅ | ⭐⭐⭐⭐⭐ |

## 代码结构

```
Singleton/
├── EagerSingleton.java              # 饿汉式
├── LazySingleton.java               # 懒汉式（synchronized）
├── DoubleCheckLockingSingleton.java  # 双重检查锁
├── BillPughSingleton.java           # 静态内部类（推荐）
├── EnumSingleton.java               # 枚举（最安全）
├── App.java                         # 演示类
└── SingletonTest.java               # 单元测试
```

## 关键点

### 1. 饿汉式
```java
private static final Singleton INSTANCE = new Singleton();
public static Singleton getInstance() {
    return INSTANCE;
}
```
- 优点：简单，线程安全
- 缺点：无法延迟加载

### 2. 双重检查锁
```java
private static volatile Singleton instance;
public static Singleton getInstance() {
    if (instance == null) {
        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton();
            }
        }
    }
    return instance;
}
```
- 关键：必须使用 `volatile` 关键字
- 原因：防止指令重排序导致获取到未初始化的对象

### 3. Bill Pugh（推荐）
```java
private static class SingletonHolder {
    private static final Singleton INSTANCE = new Singleton();
}
public static Singleton getInstance() {
    return SingletonHolder.INSTANCE;
}
```
- 利用 JVM 类加载机制保证线程安全
- 实现延迟加载

### 4. 枚举（最安全）
```java
public enum Singleton {
    INSTANCE;
}
```
- 天然防止反射和序列化攻击
- Joshua Bloch 推荐方式

## 与相似模式的区别

| 模式 | 区别 |
|------|------|
| **Factory Method** | 工厂方法可以创建多个实例，单例只创建一个 |
| **Abstract Factory** | 抽象工厂通常配合单例使用，确保工厂唯一 |
| **Prototype** | 原型模式通过克隆创建新实例，单例禁止创建新实例 |

## 常见错误

1. **忘记 volatile**（双重检查锁）
   ```java
   // ❌ 错误
   private static Singleton instance;
   // ✅ 正确
   private static volatile Singleton instance;
   ```

2. **同步整个方法**（懒汉式）
   ```java
   // ❌ 性能差
   public static synchronized Singleton getInstance() { ... }
   // ✅ 只同步创建过程
   public static Singleton getInstance() {
       if (instance == null) {
           synchronized (Singleton.class) { ... }
       }
       return instance;
   }
   ```

## 实际应用

- `Runtime.getRuntime()` - Java 运行时
- `Desktop.getDesktop()` - 桌面应用
- Spring Bean 默认作用域（singleton）
- 数据库连接池（HikariCP, Druid）
- 日志框架（Log4j, Logback）

## 个人理解与心得

> 单例模式看似简单，但要实现一个线程安全、防反射、防序列化的单例并不容易。
> 在实际开发中，推荐使用枚举方式或 Bill Pugh 方式。
> Spring 框架中默认的 Bean 作用域就是 singleton，理解这个模式对使用 Spring 很有帮助。
