# Design Patterns Learning

Java 设计模式学习项目，基于 iluwatar/java-design-patterns。

## 构建命令

- **验证项目**: `mvn validate`
- **编译项目**: `mvn compile`
- **安装父 POM**: `mvn install -N`
- **清理构建**: `mvn clean`
- **跳过测试编译**: `mvn compile -DskipTests`

## 项目结构

- `creational/` - 创建型模式（6个模块）
- `structural/` - 结构型模式（7个模块）
- `behavioral/` - 行为型模式（11个模块）

## 开发规范

- 每个模式独立模块，包含自己的 pom.xml
- 使用 Lombok 简化代码
- 遵循 SOLID 设计原则
- 参考 iluwatar/java-design-patterns 的实现

## 参考资源

- 参考仓库: `~/java-design-patterns-reference`
- 学习计划: `LEARNING_PLAN.md`
