# Java 设计模式学习计划

> 基于 [iluwatar/java-design-patterns](https://github.com/iluwatar/java-design-patterns) 项目
> 制定日期：2026-08-17
> 目标：系统学习设计模式，通过阅读源码 + 动手实践，掌握核心设计模式的应用

---

## 一、学习前准备

### 1.1 环境准备
- JDK 17+
- Maven 3.8+
- IntelliJ IDEA（已安装）
- 克隆参考仓库（只读参考，不要直接在上面改代码）

```bash
# 参考仓库克隆到其他目录
git clone https://github.com/iluwatar/java-design-patterns.git ~/java-design-patterns-reference
```

### 1.2 设计原则预习（1天）
在学习具体模式之前，先通读以下设计原则，它们是所有设计模式的基础：

| 原则 | 简要说明 | 参考链接 |
|------|---------|---------|
| **S** - 单一职责原则 | 一个类只负责一件事 | [SRP](https://java-design-patterns.com/principles/#single-responsibility-principle) |
| **O** - 开闭原则 | 对扩展开放，对修改关闭 | [OCP](https://java-design-patterns.com/principles/#open-closed-principle) |
| **L** - 里氏替换原则 | 子类可以替换父类 | [LSP](https://java-design-patterns.com/principles/#liskov-substitution-principle) |
| **I** - 接口隔离原则 | 接口要小而专 | [ISP](https://java-design-patterns.com/principles/#interface-segregation-principle) |
| **D** - 依赖倒置原则 | 依赖抽象而非具体实现 | [DIP](https://java-design-patterns.com/principles/#dependency-inversion-principle) |
| **DRY** | 不要重复自己 | [DRY](https://java-design-patterns.com/principles/#dry) |
| **KISS** | 保持简单 | [KISS](https://java-design-patterns.com/principles/#kiss) |
| **YAGNI** | 你不会用到它 | [YAGNI](https://java-design-patterns.com/principles/#yagni) |
| **迪米特法则** | 最少知识原则 | [LoD](https://java-design-patterns.com/principles/#law-of-demeter) |

### 1.3 学习方法（每个模式）
每个设计模式按以下步骤学习：

```
步骤1：阅读模式简介    → 理解决决什么问题（Why）
步骤2：阅读 UML/类图   → 理解结构关系（What）
步骤3：阅读参考源码    → 理解实现方式（How）
步骤4：在本项目中实现  → 动手写代码（Practice）
步骤5：写学习笔记      → 总结应用场景和心得（Consolidate）
```

### 1.4 项目结构约定
每个模式在本项目中创建独立模块：

```
design-patterns-learning/
├── pom.xml                          # 父 POM
├── creational/
│   ├── singleton/
│   ├── builder/
│   └── ...
├── structural/
│   ├── adapter/
│   ├── decorator/
│   └── ...
├── behavioral/
│   ├── strategy/
│   ├── observer/
│   └── ...
└── LEARNING_PLAN.md                 # 本文件
```

---

## 二、学习路线图（共 6 周）

### 🟢 第一阶段：创建型模式（Creational）— 第 1 周

> 创建型模式关注"对象怎么创建"，将对象的创建和使用分离。

| 序号 | 模式 | 难度 | 预计时间 | 重要度 | 说明 |
|------|------|------|---------|--------|------|
| 1 | **Singleton 单例** | ⭐ | 0.5天 | ⭐⭐⭐⭐⭐ | 最基础，理解线程安全实现 |
| 2 | **Factory Method 工厂方法** | ⭐⭐ | 0.5天 | ⭐⭐⭐⭐⭐ | 最常用的创建模式 |
| 3 | **Abstract Factory 抽象工厂** | ⭐⭐ | 1天 | ⭐⭐⭐⭐ | 工厂方法的升级，创建产品族 |
| 4 | **Builder 建造者** | ⭐⭐ | 1天 | ⭐⭐⭐⭐⭐ | 复杂对象构建，如 StringBuilder |
| 5 | **Prototype 原型** | ⭐⭐ | 0.5天 | ⭐⭐⭐ | 克隆对象，理解浅拷贝与深拷贝 |
| 6 | **Factory Kit** | ⭐⭐⭐ | 0.5天 | ⭐⭐ | 工厂的变体，函数式风格 |

**阶段实践项目**：实现一个「游戏角色创建系统」
- 用 Singleton 管理游戏配置
- 用 Factory Method 创建不同类型的角色（战士/法师/刺客）
- 用 Builder 构建复杂角色装备
- 用 Prototype 克隆已有角色模板

---

### 🟡 第二阶段：结构型模式（Structural）— 第 2 周

> 结构型模式关注"对象如何组合"，用更大的结构来实现新功能。

| 序号 | 模式 | 难度 | 预计时间 | 重要度 | 说明 |
|------|------|------|---------|--------|------|
| 7 | **Adapter 适配器** | ⭐⭐ | 0.5天 | ⭐⭐⭐⭐⭐ | 接口转换，最常用 |
| 8 | **Decorator 装饰器** | ⭐⭐ | 1天 | ⭐⭐⭐⭐⭐ | 动态增强功能，IO 流就是典型 |
| 9 | **Proxy 代理** | ⭐⭐ | 1天 | ⭐⭐⭐⭐⭐ | Spring AOP 核心，非常重要 |
| 10 | **Facade 外观** | ⭐ | 0.5天 | ⭐⭐⭐⭐ | 简化复杂系统调用 |
| 11 | **Composite 组合** | ⭐⭐ | 0.5天 | ⭐⭐⭐⭐ | 树形结构，如文件系统 |
| 12 | **Flyweight 享元** | ⭐⭐⭐ | 0.5天 | ⭐⭐⭐ | 共享细粒度对象，节省内存 |
| 13 | **Bridge 桥接** | ⭐⭐⭐ | 1天 | ⭐⭐⭐ | 抽象与实现分离 |

**阶段实践项目**：实现一个「数据格式转换器」
- 用 Adapter 适配不同格式的数据源（CSV/JSON/XML）
- 用 Decorator 给转换器添加压缩、加密功能
- 用 Facade 提供统一的转换接口
- 用 Proxy 实现日志记录和缓存

---

### 🔴 第三阶段：行为型模式（Behavioral）— 第 3-4 周

> 行为型模式关注"对象之间如何通信"，定义对象间的职责分配和交互方式。

#### 第 3 周：核心行为型模式

| 序号 | 模式 | 难度 | 预计时间 | 重要度 | 说明 |
|------|------|------|---------|--------|------|
| 14 | **Strategy 策略** | ⭐⭐ | 0.5天 | ⭐⭐⭐⭐⭐ | 算法可替换，消除 if-else |
| 15 | **Observer 观察者** | ⭐⭐ | 1天 | ⭐⭐⭐⭐⭐ | 事件驱动，Spring Event 基础 |
| 16 | **Command 命令** | ⭐⭐ | 1天 | ⭐⭐⭐⭐ | 操作封装，支持撤销/重做 |
| 17 | **Template Method 模板方法** | ⭐ | 0.5天 | ⭐⭐⭐⭐ | 定义算法骨架，子类填充细节 |
| 18 | **Chain of Responsibility 责任链** | ⭐⭐ | 1天 | ⭐⭐⭐⭐ | 过滤器/审批链 |

#### 第 4 周：进阶行为型模式

| 序号 | 模式 | 难度 | 预计时间 | 重要度 | 说明 |
|------|------|------|---------|--------|------|
| 19 | **Mediator 中介者** | ⭐⭐ | 0.5天 | ⭐⭐⭐ | 对象间解耦，如聊天室 |
| 20 | **State 状态** | ⭐⭐ | 0.5天 | ⭐⭐⭐⭐ | 状态机，替代复杂条件分支 |
| 21 | **Visitor 访问者** | ⭐⭐⭐ | 1天 | ⭐⭐⭐ | 数据结构与操作分离 |
| 22 | **Iterator 迭代器** | ⭐⭐ | 0.5天 | ⭐⭐⭐⭐ | 统一遍历接口 |
| 23 | **Memento 备忘录** | ⭐⭐ | 0.5天 | ⭐⭐⭐ | 保存/恢复状态 |
| 24 | **Interpreter 解释器** | ⭐⭐⭐ | 0.5天 | ⭐⭐ | DSL 实现 |

**阶段实践项目**：实现一个「电商订单系统」
- 用 Strategy 实现不同支付方式和折扣策略
- 用 Observer 实现订单状态变更通知（邮件/短信/推送）
- 用 Command 实现订单操作（下单/取消/退款）
- 用 Chain of Responsibility 实现订单校验链（库存/余额/风控）
- 用 State 实现订单状态机（待支付→已支付→已发货→已完成）

---

### 🔵 第五阶段：并发/线程模式（Concurrency）— 第 5 周

> 这是 iluwatar 项目的一大特色，超出 GoF 经典范畴。

| 序号 | 模式 | 难度 | 预计时间 | 重要度 | 说明 |
|------|------|------|---------|--------|------|
| 25 | **Double-Checked Locking** | ⭐⭐ | 0.5天 | ⭐⭐⭐⭐ | 线程安全的延迟初始化 |
| 26 | **Thread Pool 线程池** | ⭐⭐ | 0.5天 | ⭐⭐⭐⭐⭐ | 理解 ExecutorService |
| 27 | **Producer-Consumer 生产者消费者** | ⭐⭐ | 0.5天 | ⭐⭐⭐⭐ | 经典并发模型 |
| 28 | **Active Object 主动对象** | ⭐⭐⭐ | 1天 | ⭐⭐⭐ | 异步方法调用 |
| 29 | **Guarded Suspension 保护暂停** | ⭐⭐⭐ | 0.5天 | ⭐⭐⭐ | 等待-通知机制 |
| 30 | **Master-Worker 主从** | ⭐⭐⭐ | 0.5天 | ⭐⭐⭐ | 并行计算模型 |

**阶段实践项目**：实现一个「异步文件处理系统」
- 用 Thread Pool 管理并发下载任务
- 用 Producer-Consumer 实现任务队列
- 用 Double-Checked Locking 实现配置缓存

---

### ⚫ 第六阶段：架构模式 + 项目实战 — 第 6 周

> 从代码级模式上升到架构级模式，综合运用。

| 序号 | 模式 | 难度 | 预计时间 | 重要度 | 说明 |
|------|------|------|---------|--------|------|
| 31 | **MVC** | ⭐⭐ | 0.5天 | ⭐⭐⭐⭐⭐ | 最经典的架构模式 |
| 32 | **Repository 仓库** | ⭐⭐ | 0.5天 | ⭐⭐⭐⭐⭐ | 数据访问抽象 |
| 33 | **Data Transfer Object** | ⭐ | 0.5天 | ⭐⭐⭐⭐⭐ | 数据传输对象 |
| 34 | **Dependency Injection** | ⭐⭐ | 0.5天 | ⭐⭐⭐⭐⭐ | Spring 核心 |
| 35 | **CQRS** | ⭐⭐⭐ | 0.5天 | ⭐⭐⭐ | 读写分离 |
| 36 | **Event Sourcing** | ⭐⭐⭐ | 0.5天 | ⭐⭐⭐ | 事件溯源 |

**阶段综合实战**：使用 Spring Boot 实现一个完整的「博客系统」
- 综合运用前面学到的所有模式
- 分层架构：Controller → Service → Repository
- DTO 传输、策略模式排序、观察者事件通知等

---

## 三、学习节奏与时间安排

### 每日学习时间建议
```
周一至周五：每天 2-3 小时
周末：每天 3-4 小时（实践项目日）
```

### 每日学习流程
```
📖 30min  — 阅读模式原理（书籍/博客/官方文档）
💻 60min  — 阅读参考源码，理解实现
🔨 60min  — 在本项目中动手实现
📝 20min  — 写学习笔记，总结关键点
```

### 六周时间线总览

| 周 | 阶段 | 模式数 | 核心产出 |
|----|------|--------|---------|
| 第 1 周 | 创建型模式 | 6 个 | 游戏角色创建系统 |
| 第 2 周 | 结构型模式 | 7 个 | 数据格式转换器 |
| 第 3 周 | 行为型模式（上） | 5 个 | 电商订单系统（上） |
| 第 4 周 | 行为型模式（下） | 6 个 | 电商订单系统（下） |
| 第 5 周 | 并发模式 | 6 个 | 异步文件处理系统 |
| 第 6 周 | 架构模式 + 实战 | 6 个 | 博客系统综合实战 |

---

## 四、学习资源

### 核心参考
- 📖 **源码参考**：`~/java-design-patterns-reference`（克隆的参考仓库）
- 🌐 **官方文档**：https://java-design-patterns.com/patterns/
- 📚 **书籍**：《Head First 设计模式》（入门） → 《Effective Java》（进阶）

### 各模式快速学习链接
每个模式都可以在以下位置找到详细解释：
1. **官方页面**：`https://java-design-patterns.com/patterns/{pattern-name}/`
2. **源码中的 README.md**：每个模式目录下都有详细说明
3. **Intent / Problem / Solution / Structure / Participants** 标准格式

### 推荐学习顺序（按重要度排序）
```
必学（面试+工作都用）：
  Singleton → Factory Method → Abstract Factory → Builder
  → Strategy → Observer → Decorator → Proxy → Adapter
  → Template Method → Command → Chain of Responsibility

重要（提升代码质量）：
  Facade → Composite → State → Mediator
  → Flyweight → Bridge → Prototype

进阶（架构设计）：
  CQRS → Event Sourcing → Repository → MVC
  → Thread Pool → Active Object → Producer-Consumer
```

---

## 五、笔记模板

每学完一个模式，建议按以下模板记录笔记：

```markdown
# [模式名称] ([中文名])

## 解决什么问题？
> 一句话描述这个模式要解决的问题

## 核心思想
> 用一句话概括模式的本质

## 类图/结构
> 画出关键类的关系图

## 代码实现
> 核心代码片段

## 适用场景
- 场景 1
- 场景 2
- 场景 3

## 与相似模式的区别
> 比如 Factory Method vs Abstract Factory

## 个人理解与心得
> 用自己的话复述，加深记忆
```

---

## 六、进度追踪

| 序号 | 模式 | 状态 | 完成日期 | 笔记链接 | 备注 |
|------|------|------|---------|---------|------|
| 1 | Singleton | ⬜ 未开始 | | | |
| 2 | Factory Method | ⬜ 未开始 | | | |
| 3 | Abstract Factory | ⬜ 未开始 | | | |
| 4 | Builder | ⬜ 未开始 | | | |
| 5 | Prototype | ⬜ 未开始 | | | |
| 6 | Factory Kit | ⬜ 未开始 | | | |
| 7 | Adapter | ⬜ 未开始 | | | |
| 8 | Decorator | ⬜ 未开始 | | | |
| 9 | Proxy | ⬜ 未开始 | | | |
| 10 | Facade | ⬜ 未开始 | | | |
| 11 | Composite | ⬜ 未开始 | | | |
| 12 | Flyweight | ⬜ 未开始 | | | |
| 13 | Bridge | ⬜ 未开始 | | | |
| 14 | Strategy | ⬜ 未开始 | | | |
| 15 | Observer | ⬜ 未开始 | | | |
| 16 | Command | ⬜ 未开始 | | | |
| 17 | Template Method | ⬜ 未开始 | | | |
| 18 | Chain of Responsibility | ⬜ 未开始 | | | |
| 19 | Mediator | ⬜ 未开始 | | | |
| 20 | State | ⬜ 未开始 | | | |
| 21 | Visitor | ⬜ 未开始 | | | |
| 22 | Iterator | ⬜ 未开始 | | | |
| 23 | Memento | ⬜ 未开始 | | | |
| 24 | Interpreter | ⬜ 未开始 | | | |
| 25 | Double-Checked Locking | ⬜ 未开始 | | | |
| 26 | Thread Pool | ⬜ 未开始 | | | |
| 27 | Producer-Consumer | ⬜ 未开始 | | | |
| 28 | Active Object | ⬜ 未开始 | | | |
| 29 | Guarded Suspension | ⬜ 未开始 | | | |
| 30 | Master-Worker | ⬜ 未开始 | | | |
| 31 | MVC | ⬜ 未开始 | | | |
| 32 | Repository | ⬜ 未开始 | | | |
| 33 | Data Transfer Object | ⬜ 未开始 | | | |
| 34 | Dependency Injection | ⬜ 未开始 | | | |
| 35 | CQRS | ⬜ 未开始 | | | |
| 36 | Event Sourcing | ⬜ 未开始 | | | |

---

> 💡 **学习建议**：不要贪多，每天专注 1-2 个模式。理解比记住更重要，实践比阅读更有效。
