# Game Character Creator 游戏角色创建系统（阶段实践项目）

> **第一阶段（创建型模式）综合实践**
> 本模块是练习项目：**需求已定，代码由你实现**。
> 综合运用 Singleton / Factory Method / Builder / Prototype 四个创建型模式。

---

## 一、项目目标

实现一个「游戏角色创建系统」，完整演示创建型模式如何协作：

> 读取全局配置 → 选择职业 → 构建装备 → 从模板克隆角色

完成后应能回答：**为什么这 4 个模式要这么设计？各自解决了什么问题？**

## 二、必用模式（每个都必须出现）

| 模式 | 必须实现的要求 |
|------|---------------|
| **Singleton 单例** | 管理游戏全局配置（如：游戏名称、版本号、最大角色数、默认难度）。整个系统任何位置获取配置，必须是同一个实例 |
| **Factory Method 工厂方法** | 创建角色类型：**战士（Warrior）/ 法师（Mage）/ 刺客（Assassin）**。每种职业有独立的基础属性（如生命值、攻击力、魔力） |
| **Builder 建造者** | 为角色构建复杂装备：武器 + 防具 + 饰品 + 技能列表。装备配置过程分步进行 |
| **Prototype 原型** | 从「角色模板」克隆新角色。克隆出的角色可独立修改，**修改克隆体不影响原型** |

## 三、功能需求（App 演示流程）

`App.main()` 按以下顺序演示（日志用 `log.info` 中文输出）：

1. **启动游戏** → 获取全局配置，打印游戏名/版本/难度
2. **创建职业** → 通过工厂分别创建战士、法师、刺客，打印各自基础属性
3. **构建装备** → 用 Builder 为战士配齐「武器+防具+饰品+技能」，打印完整配置
4. **克隆角色** → 从战士模板克隆一个新角色，修改克隆体的装备或属性，验证原型不受影响
5. **统计** → 打印全局配置的实例唯一性验证结果（两次获取是否同一实例）

## 四、建议类结构（提示，非强制，可自行设计）

```
com.l7bug.gamecharacter
├── config/
│   └── GameConfig.java            # Singleton：全局配置
├── character/
│   ├── Character.java             # 抽象角色（含基础属性）
│   ├── Warrior.java               # 战士（Factory Method 产品）
│   ├── Mage.java                  # 法师
│   └── Assassin.java              # 刺客
├── factory/
│   ├── CharacterFactory.java      # 抽象工厂（创建角色的工厂方法）
│   ├── WarriorFactory.java        # 具体工厂
│   ├── MageFactory.java
│   └── AssassinFactory.java
├── equipment/
│   ├── Equipment.java             # 装备集合（武器/防具/饰品/技能）
│   ├── CharacterBuilder.java      # Builder：分步构建装备
│   └── CharacterDirector.java     # Director（可选）：编排构建顺序
├── prototype/
│   ├── CharacterTemplate.java     # Prototype：可克隆的角色模板
│   └── TemplateCache.java         # 模板仓库（可选）：按类型取模板
└── App.java                       # 演示入口（@Slf4j）
```

> 💡 包结构只是建议。如果你觉得某类放别处更合理，请按你的设计来——
> 这是你的练习，重点是**用对模式**，不是**照抄结构**。

## 五、验收标准（写完自测，全部满足才算完成）

- [ ] `mvn test` 通过，**至少 8 个测试用例**
- [ ] 每个模式至少有 1 个针对性测试：
  - Singleton：两次 `getInstance()` 返回同一实例（`assertSame`）
  - Factory Method：三种工厂各创建对应职业，且属性符合预期
  - Builder：分步构建后装备完整，缺步骤（如没配武器）有合理处理
  - Prototype：克隆体与原型 `assertNotSame`，修改克隆体后原型字段不变（深拷贝验证）
- [ ] `App.main()` 完整跑通 5 步演示流程
- [ ] 所有注释、日志为中文
- [ ] 测试类 package-private、无 Mockito、无 AssertJ（只用 JUnit 5 断言）

## 六、学习自问（检验理解，README 或笔记中回答）

1. **Singleton**：你用了哪种实现（饿汉/懒汉/DCL/静态内部类/枚举）？为什么选它？线程安全吗？
2. **Factory Method**：和直接 `new Warrior()` 的区别是什么？新增一个职业（如弓箭手 Archer）需要改哪些代码？
3. **Builder**：为什么不用构造器 + setter？装备的哪些特性让 Builder 比构造器更合适？
4. **Prototype**：浅拷贝和深拷贝的区别？你的 clone() 是深拷贝吗？为什么 Character 里的 List 字段要特殊处理？
5. **综合**：这 4 个模式在你这个系统里分别扮演什么角色？如果删掉其中任何一个，代码会变成什么样？

## 七、加分项（可选，做完基础再做）

- [ ] 工厂方法支持参数化：`factory.create("warrior")` 按字符串创建
- [ ] 用 **Factory Kit** 注册表风格重构工厂（参考 factory-kit 模块）
- [ ] Builder 支持链式调用（`.weapon(...).armor(...).build()`）
- [ ] 装备类用不可变对象（final 字段，无 setter）
- [ ] 角色模板缓存：按职业预存模板，克隆时直接取

## 八、风格约束（与其他模块保持一致）

- 包名 `com.l7bug.gamecharacter`，4 空格缩进
- Javadoc 标注 GOF 角色（Singleton / Product / Creator / ConcreteCreator / Builder / Prototype）
- 字段 `final` 优先，构造函数注入
- 禁止：Lombok（`@Slf4j` 仅限 App）、Mockito、匿名子类（`new X() {}`）

---

> 🚀 提示：先参照 `creational/` 下已完成的 6 个单模式模块复习，再动手。
> 写完对照第六节自问，把答案写在模块内 `NOTES.md` 或 README 末尾。