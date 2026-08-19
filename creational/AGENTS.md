# 创建型设计模式

## 概述
6 个已完成模块，演示对象创建机制的独特模式实现。

## 结构
```
creational/
├── singleton/          # 5 种单线程和多线程实现
├── factory-method/     # 通知层次结构与工厂
├── abstract-factory/   # UI 主题族（Windows/Mac）
├── builder/            # 电脑分步构建
├── prototype/          # ShapeCache 基于克隆的创建
└── factory-kit/        # MessageFactory 基于类型的实例化
```

## 查找指南
| 任务 | 位置 | 备注 |
|------|------|------|
| 单例变体 | `singleton/src/main/java/com/l7bug/singleton/` | 5 种实现：饿汉、懒汉、双重检查、静态内部类、枚举 |
| 工厂方法模式 | `factory-method/src/main/java/com/l7bug/factorymethod/` | Notification + ConcreteNotification 层次结构 |
| 抽象工厂 | `abstract-factory/src/main/java/com/l7bug/abstractfactory/` | GUIFactory 创建 Button/Checkbox 族 |
| 建造者模式 | `builder/src/main/java/com/l7bug/builder/` | Computer 类与 Builder 内部类 |
| 原型模式 | `prototype/src/main/java/com/l7bug/prototype/` | ShapeCache 存储可克隆图形 |
| 工厂套件 | `factory-kit/src/main/java/com/l7bug/factorykit/` | MessageFactory 与 MessageType 枚举 |

## 约定
- **无 Mockito**：依赖简单，测试使用直接实例化
- **场景化测试名称**：方法名描述行为（`shouldCreateSingletonWhen...`）
- **中文注释**：测试文件使用中文场景描述
- **5 种单例变体**：饿汉、懒汉、双重检查锁、静态内部类、枚举

## 反模式
- **自包含模块**：每个模式独立，无跨模块依赖
- **完整实现**：与其他目录不同，所有模块都有完整的 src/ 结构
- **多单例对比**：同一模式用 5 种不同方式实现，便于对比
