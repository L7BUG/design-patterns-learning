# Facade 外观模式

## 解决什么问题？
> 为子系统中的一组接口提供一个统一的简化入口。
> 客户端不需要了解子系统的内部交互细节，只需调用外观类的一个方法。

## 核心思想
> 外观（Facade）— 定义一个高层接口，让子系统更容易使用。
> 不是替代子系统的功能，而是简化客户端的调用路径。

## 适用场景
- 为复杂子系统提供简单的默认实现
- 将子系统分层，定义各层的入口点
- 客户端依赖很多子系统类，耦合度过高
- 想要对子系统进行分层抽象

## 类图结构

```
              ┌──────────────────────┐
              │   ComputerFacade     │  ← 外观类（Facade）
              │  - cpu: Cpu          │  持有子系统引用
              │  - memory: Memory    │
              │  - hardDrive: HardDrive│
              │  + start()           │  统一启动入口
              │  + getCpu()          │
              │  + getMemory()       │
              └──────────┬───────────┘
                         │ 组合
         ┌───────────────┼───────────────┐
         │               │               │
         ▼               ▼               ▼
┌─────────────┐  ┌─────────────┐  ┌──────────────┐
│     Cpu     │  │   Memory    │  │  HardDrive   │  ← 子系统（Subsystem）
│  + freeze() │  │  + load()   │  │  + read()    │  各自独立工作
│  + jump()   │  │  + getData()│  │  + getBoot() │
│  + execute()│  │             │  │              │
└─────────────┘  └─────────────┘  └──────────────┘
```

## 代码结构

```
Facade/
├── Cpu.java              # CPU 子系统（Subsystem）— 冻结、跳转、执行
├── Memory.java           # 内存子系统（Subsystem）— 加载数据
├── HardDrive.java        # 硬盘子系统（Subsystem）— 读取数据
├── ComputerFacade.java   # 计算机外观（Facade）— 统一启动入口
├── App.java              # 演示类
├── FacadeTest.java       # 单元测试
└── README.md             # 本文件
```

## 关键点

### 1. 子系统各自独立（Subsystem）
```java
public class Cpu {
    private boolean executed;

    public void freeze() { /* 冻结处理器 */ }
    public void jump(long position) { /* 跳转到指定地址 */ }
    public void execute() { executed = true; }
    public boolean isExecuted() { return executed; }
}
```

### 2. 外观类封装调用流程（Facade）
```java
public class ComputerFacade {
    private final Cpu cpu;
    private final Memory memory;
    private final HardDrive hardDrive;

    // 构造函数注入子系统
    public ComputerFacade(Cpu cpu, Memory memory, HardDrive hardDrive) {
        this.cpu = cpu;
        this.memory = memory;
        this.hardDrive = hardDrive;
    }

    // 客户端只需调用 start()
    public void start() {
        cpu.freeze();
        byte[] bootData = hardDrive.read(0L, 4);
        memory.load(0x0001L, bootData);
        cpu.jump(0x0001L);
        cpu.execute();
    }
}
```

### 3. 客户端代码极简
```java
// 无需了解子系统的调用顺序
ComputerFacade computer = new ComputerFacade(new Cpu(), new Memory(), new HardDrive());
computer.start();  // 一键启动
```

## 外观模式 vs 直接调用

| 方式 | 问题 | 外观模式优势 |
|------|------|-------------|
| **直接调用** | 客户端需了解 3+ 子系统的调用顺序 | 只需调用一个 start() |
| **耦合度** | 客户端与所有子系统直接耦合 | 只与外观类耦合 |
| **可维护性** | 子系统接口变更需修改所有客户端 | 修改外观类即可 |
| **灵活性** | 外观简化但不阻止直接访问 | 可继续直接操作子系统 |

## 实际应用

- **JDBC**：`DriverManager.getConnection()` 是 `Driver`、`Connection`、`Statement` 的外观
- **SLF4J**：`LoggerFactory.getLogger()` 是底层日志框架的外观
- **Spring MVC**：`DispatcherServlet` 是 Servlet 容器与 Spring 组件的外观
- **计算机 BIOS**：开机自检流程就是硬件子系统的外观

## 个人理解与心得

> 外观模式的核心是"用一个入口替代一堆入口"。
> 它不要求你改变子系统的内部实现，
> 只是在客户端和子系统之间加了一层"翻译"。
>
> 最典型的例子是计算机启动：
> - 不用外观：用户要依次操作 CPU、内存、硬盘，必须知道正确的调用顺序
> - 用外观：用户只需按一下电源键（start），所有操作由 BIOS 自动完成
>
> 外观模式的价值在于降低认知负荷：
> 新人不需要理解 3 个子系统的交互关系，
> 只需要知道一个 `start()` 就能启动计算机。
