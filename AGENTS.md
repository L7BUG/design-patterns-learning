# 项目知识库

**生成时间：** 2026-08-19
**提交：** 03afe8b
**分支：** master

## 概述
Java 设计模式学习项目，基于 iluwatar/java-design-patterns，Maven 多模块结构。使用 Java 25、Lombok、Spring Boot 4.1.0 父 POM（非 Spring Boot 项目）。

## 结构
```
design-patterns-learning/
├── creational/          # 创建型模式（6个模块，全部完成）
├── structural/          # 结构型模式（7个模块，2个完成）
├── behavioral/          # 行为型模式（11个模块，全部空骨架）
├── pom.xml              # 根 POM，声明 24 个模块
├── CLAUDE.md            # Claude 规则入口
├── LEARNING_PLAN.md     # 6 周学习计划
└── README.md            # 项目说明（进度表已过时）
```

## 查找指南
| 任务 | 位置 | 备注 |
|------|------|------|
| 学习计划 | `LEARNING_PLAN.md` | 36 个模式的学习路线 |
| 编码规范 | `.claude/rules/` | common/ + java/ 规则 |
| 构建配置 | `pom.xml` | Java 25、Lombok、ASM 9.10.1 |
| 编辑器配置 | `.editorconfig` | Tab 缩进、UTF-8、LF |
| IDE 检查 | `.idea/inspectionProfiles/` | Alibaba P3C 编码规约 |

## 代码地图

| 符号 | 类型 | 位置 | 引用数 | 角色 |
|------|------|------|--------|------|
| `App` | 类 | 各模块 `src/main/.../App.java` | 0 | 演示入口（main 方法） |
| `*Test` | 类 | 各模块 `src/test/.../*Test.java` | 0 | 单元测试 |
| `*Factory` | 类 | factory-method/abstract-factory | 多 | 工厂类 |
| `*Singleton` | 类 | singleton/ | 5 | 5 种单例实现 |

## 约定
- **模块独立**：每个模式一个 Maven 模块，独立 pom.xml
- **命名**：模块名 `abstract-factory` → 包名 `com.l7bug.abstractfactory`
- **测试**：JUnit 5、package-private 测试类、中文注释、无 Mockito
- **缩进**：Tab（.editorconfig 规定）
- **Java 25**：`maven.compiler.source/target = 25`，需 JDK 25

## 反模式（本项目）
- **空骨架模块**：16/24 模块只有 pom.xml，无 src/（5 structural + 11 behavioral）
- **POM 重复**：8 个完整模块各自声明 junit-jupiter + surefire（违反 DRY）
- **非 Spring Boot**：误用 spring-boot-starter-parent 4.1.0 作父 POM
- **进度表过时**：README/LEARNING_PLAN 进度与实际不符

## 命令
```bash
mvn validate          # 验证项目
mvn compile           # 编译项目
mvn install -N        # 安装父 POM
mvn clean             # 清理构建
mvn compile -DskipTests  # 跳过测试编译
```

## 注意事项
- **Java 25 环境**：需 JDK 25 + ASM 9.10.1 覆盖
- **Lombok**：provided scope，编译时注解处理
- **无 CI**：无 .github/workflows、无 Maven Wrapper
- **无 codegraph**：jdtls LSP 未安装
- **Alibaba P3C**：IntelliJ 已启用约 70 条编码规约检查
