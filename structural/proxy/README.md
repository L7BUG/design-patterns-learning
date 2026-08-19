# Proxy 代理模式

## 解决什么问题？
> 当需要控制对某个对象的访问时，直接暴露真实对象会导致不必要的开销
> （如昂贵的初始化、远程调用、权限检查等），代理模式提供一个占位符来控制访问。

## 核心思想
> 代理（Proxy）— 为其他对象提供一种代理以控制对这个对象的访问。
> 代理与真实对象实现相同接口，在不改变客户端代码的前提下，
> 在访问真实对象前后加入额外逻辑。

## 适用场景
- **延迟加载**：大图片、大文件等昂贵对象的延迟创建（虚拟代理）
- **访问控制**：在访问真实对象前进行权限校验（保护代理）
- **远程代理**：为远程对象提供本地代表（RMI、gRPC Stub）
- **日志代理**：在方法调用前后记录日志、统计耗时（日志代理）
- 需要在不修改客户端代码的前提下增强对象行为

## 类图结构

```
              ┌─────────────────────┐
              │    <<interface>>     │
              │       Image         │  ← 抽象主题（Subject）
              │  + display()        │
              │  + getFileName()    │
              └──────────┬──────────┘
                    ╱          ╲
                   ╱            ╲
    ┌─────────────┴──┐    ┌─────┴──────────────┐
    │   RealImage    │    │     ImageProxy      │  ← 代理（Proxy）
    │  - fileName    │    │  - fileName         │
    │  - loadFromDisk│    │  - realImage        │  持有真实对象引用（延迟创建）
    │  + display()   │    │  + display()        │  首次调用时才创建 RealImage
    │  + isLoaded()  │    │  + isLoaded()       │
    └────────────────┘    └─────────────────────┘
       真实主题（RealSubject）
       构造时执行昂贵加载
```

## 代码结构

```
Proxy/
├── Image.java                  # 图片接口（Subject）
├── RealImage.java              # 真实图片（RealSubject）
├── ImageProxy.java             # 图片代理（Proxy）
├── App.java                    # 演示类
├── ProxyTest.java              # 单元测试
└── README.md                   # 本文件
```

## 关键点

### 1. 抽象主题接口（Image）
```java
public interface Image {
    void display();
    String getFileName();
}
```

### 2. 真实主题（RealSubject）— 构造时执行昂贵加载
```java
public class RealImage implements Image {
    private static int loadCount = 0;
    private final String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();  // 构造时立即加载
    }

    private void loadFromDisk() {
        System.out.println("    [RealImage] 从磁盘加载图片: " + fileName);
        loadCount++;
    }
}
```

### 3. 代理（Proxy）— 延迟创建真实对象
```java
public class ImageProxy implements Image {
    private final String fileName;
    private RealImage realImage;  // 惰性引用，初始为 null

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);  // 首次调用时才创建
        }
        realImage.display();  // 委托给真实对象
    }
}
```

### 4. 代理与真实对象可互换使用
```java
// 客户端无感知 — 代理和真实对象实现同一接口
Image proxy = new ImageProxy("photo.jpg");
Image real = new RealImage("photo.jpg");
proxy.display();   // 第一次触发加载
proxy.display();   // 不再加载（已缓存）
real.display();    // 始终直接显示
```

## 代理模式变体

| 变体 | 说明 | 示例 |
|------|------|------|
| **虚拟代理** | 延迟创建开销大的对象 | 本例的 ImageProxy（延迟加载图片） |
| **保护代理** | 控制访问权限 | 权限校验后才委托调用 |
| **远程代理** | 为远程对象提供本地代表 | RMI Stub、gRPC Client |
| **日志代理** | 记录方法调用日志 | AOP 中的日志切面 |

## 实际应用

- Java RMI：远程方法调用通过 Stub（代理）转发到远程对象
- Spring AOP：动态代理实现事务、日志、权限切面
- MyBatis Mapper：Mapper 接口的代理实现，将方法调用映射为 SQL
- 图片懒加载：前端/桌面应用中大图延迟加载
- Hibernate：延迟加载关联对象（`LazyInitializationException` 的根源）

## 个人理解与心得

> 代理模式的核心是"控制访问"，而不仅仅是"延迟加载"。
> 它的威力在于：在不修改客户端代码的前提下，
> 在调用链中插入额外逻辑（延迟创建、权限检查、日志记录等）。
>
> 延迟加载是最常见的应用场景：
> - 代理创建几乎零成本（只存一个文件名）
> - 真实对象的昂贵初始化推迟到真正需要时
> - 之后的调用直接委托，无额外开销
>
> 和装饰器模式的区别：
> - **代理**：控制对对象的访问（通常有固定逻辑，如懒加载）
> - **装饰器**：为对象添加新功能（可以层层嵌套）
>
> 看代码结构很像，但意图不同：
> 代理关注"控制"，装饰器关注"增强"。
