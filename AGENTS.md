# AGENTS.md

Java 设计模式学习项目（多模块 Maven），参考 iluwatar/java-design-patterns。

## 语言约定

- 文档、注释、README、commit message 一律使用**简体中文**；代码标识符保留英文。
- 每个模式的 `README.md` 是学习笔记，按 `LEARNING_PLAN.md` 中的模板书写。

## 构建（JDK 25 是硬性要求）

- 环境：JDK 25 + Maven 3.9+（`pom.xml` 中 `maven.compiler.source/target = 25`，需本地有 JDK 25）。
- 编译全部：`mvn compile`
- 单个模块测试（从根目录，已验证）：`mvn -pl creational/prototype test`
- 全部测试：`mvn test`
- 若需在模块目录内单独 `mvn ...`，须先在根目录执行 `mvn install -N` 把父 POM 装进本地仓库，否则解析不到父 POM。

## Java 25 + Lombok 关键坑

- 根 `pom.xml` 的 `maven-compiler-plugin` 已通过 `annotationProcessorPaths` 显式声明 Lombok，并把 ASM 覆盖为 `9.10.1` 以支持 Java 25。
- **新增模块不要重复配置 maven-compiler-plugin**，直接继承父 POM 即可。
- 若 Lombok 报 ASM / "invalid target" 类错误，先检查该模块是否意外覆盖了编译器配置。

## 新增一个模式模块

模块按 `creational|structural|behavioral/<pattern-name>/` 组织，名字用连字符（如 `factory-method`）。参考 `creational/prototype/` 的结构：

- `pom.xml`：parent 指向根 POM，`<artifactId>` 用连字符命名。Lombok/slf4j/logback 已在父 POM 中，无需重复声明；但 **JUnit 5（`junit-jupiter`，scope=test）和 `maven-surefire-plugin` 不会自动继承，必须手动加**（照抄 prototype 的 pom）。
- 新模块需在根 `pom.xml` 的 `<modules>` 中注册。
- 包名去掉连字符：`com.l7bug.factorymethod`（`chain-of-responsibility` → `chainofresponsibility`）。
- 结构：`App.java`（`@Slf4j` 的 main 演示类，用 `log.info` 输出）、模式核心类、`src/test/java/.../XxxTest.java`（JUnit 5）、`README.md`（学习笔记）。
- 提交信息风格：`实现 X 模式：Y 示例`（中文，照 git log 现有格式）。

## 进度现状

- 仅 8 个模块有代码：creational 全部 6 个 + structural 的 `adapter`、`decorator`。
- 其余 17 个模块（proxy/facade/composite/flyweight/bridge 及全部 behavioral）是**空壳，只有 pom.xml**。别假设它们已有实现。
- 实现顺序按 `LEARNING_PLAN.md`；参考实现只读仓库在 `~/java-design-patterns-reference`。

## 代码风格

- `.editorconfig`：缩进用 **tab**（宽度 4）。IDEA 的 `idea_reformat_file` 不会自动遵循，注意 Java 源文件用 tab 缩进。

## IDEA MCP

- `opencode.json` 配置了 IDEA MCP（`http://127.0.0.1:64342/stream`）。调用任何 `idea_*` 工具时必须传 `projectPath=/home/l/Documents/github-projetc/design-patterns-learning`，否则报"Unable to determine the target project"。
