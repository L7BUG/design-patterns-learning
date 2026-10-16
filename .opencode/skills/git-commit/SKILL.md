---
name: Git 提交规范
description: 按 Conventional Commits + 中文描述创建 git 提交，提交前用 IntelliJ IDEA 的 MCP 工具（reformat_file / lint_files）格式化与检查。当用户要求提交代码、commit、整理提交历史，或需要决定提交信息格式时使用。
---

# 提交流程（按序执行，不要跳步）

## 1. 前置：读完全部改动

- `git status --short`、`git diff`、`git diff --cached` 都要看，**理解**改了什么再动手
- 识别生成产物，不要提交：`target/`、`*.class`、以 `~` 命名的目录（本项目出过 `creational/game-character/~/` 这种误建目录）、IDE 本地文件
- 发现可疑文件先问用户，不要自作主张删除

## 2. 拆分提交

一个提交 = 一个逻辑单元。判断标准：能否用一句话说清这个提交做了什么。

- 跨关注点必须拆：功能实现 / 仓库配置 / 纯格式修正
- 反例：`实现 X 功能 + 删除无关的规则文件` → 拆成 2 个提交
- 提交顺序：被依赖的先提交（配置/基础设施在前，使用它的功能在后）

## 3. IDE 格式化与检查（只对 `*.java`）

先探测 IDE 是否可用：在 `execute` 中调用 `tools.idea.get_repositories()`。
失败（IDE 未运行 / MCP 连不上）→ **跳过本步**，并明确告诉用户「IDE 未运行，已跳过格式化」，不要静默跳过。

IDE 可用时：

1. **格式化**：`tools.idea.reformat_file({ files })`，`files` 只包含本次改动涉及的 `*.java`
2. **检查**：`tools.idea.lint_files({ files, min_severity: "warning" })`（含阿里 P3C 规约）
   - `error` 级别：必须修复后才能提交
   - `warning` 级别：不要静默放过，向用户报告（例如 P3C 的 `'File.mkdirs()' 的结果被忽略`）
3. **格式化改动的归属**：
   - 与本次逻辑改动在**同一文件** → 并入该逻辑提交
   - 独立的纯格式修正 → 单独提交，type 用 `style`

### 严格禁止格式化这些文件

| 文件 | 原因 |
|------|------|
| `pom.xml` | IDEA 的 XML 格式化会把属性续行从 Tab 改成空格，与本仓库 `.editorconfig`（Tab）及其他模块的 POM 风格冲突 |
| `*.properties` | 无需重排，收益为零，只增加 diff 噪声 |
| `*.md` | 会重排中文排版 |

> 依据：`.editorconfig` 规定 `indent_style = tab`；实测对 `pom.xml` 调用 `reformat_file` 会产生 Tab→空格 的非预期改动。

## 4. 验证：测试必须通过

改动了 `src/main/java` 或 `src/test/java` → 提交前必须跑对应模块测试：

```bash
mvn -o -q -pl creational/game-character test
```

- 不通过就修，**禁止**用 `--no-verify`、删测试或改断言绕过
- 未改动 Java 源码（如只改文档/配置）可跳过

## 5. 暂存：显式列路径

```bash
git add .opencode/ AGENTS.md          # ✅ 显式路径
```

**禁止** `git add -A`、`git add .`、`git add --all`——本项目存在误建目录被一并暂存的实际风险。

## 6. 提交信息：Conventional Commits + 中文描述

```
<type>(<scope>): <中文描述>

- 要点一
- 要点二
```

| 部分 | 规则 |
|------|------|
| `type` | `feat` `fix` `docs` `style` `refactor` `perf` `test` `build` `ci` `chore` `revert` 之一 |
| `scope` | 模块名（`game-character`、`proxy`）或 `repo`（跨模块/仓库级配置）；小写、可省略 |
| 描述 | **中文**，动宾结构，不加句号，首行 ≤ 100 字符 |
| 正文 | 可选，`-` 列表，说明**为什么**改，而非复述代码 |

示例：

```
feat(game-character): 实现英雄工厂与属性成长

- HeroFactory 抽象出 createHero()，AgilityHeroFactory 为具体工厂
- AbstractHero 承载等级、属性成长与 6 格装备槽

注：Builder / Prototype 部分尚未完成
```

**不添加** AI 工具署名尾注（如 `Ultraworked with ...`），除非用户明确要求。

用 heredoc 提交，避免转义问题：

```bash
git commit -F - <<'EOF'
feat(game-character): 实现英雄工厂与属性成长

- 要点
EOF
```

## 7. 收尾

- `git log -1 --stat` 确认提交内容正确
- `git status --short` 应干净，或只剩已知的生成产物
- **不要 push**，除非用户明确要求

## 硬约束

`.githooks/commit-msg` 会拒绝不合规的提交信息，`.githooks/pre-commit` 会拒绝暂存 `~` 目录或 `target/` 产物。
被拒绝时**修正内容**，不要用 `--no-verify` 绕过。若 hook 本身有 bug，先和用户确认再改 hook。

## 常见坑

| 现象 | 处理 |
|------|------|
| IDE MCP 调用超时/连接失败 | 跳过格式化，告知用户，用 git 正常提交 |
| `reformat_file` 返回 ok 但产生大量无关 diff | `git checkout -- <file>` 回退，只在必要文件上重跑 |
| 提交信息被 hook 拒绝 | 按提示改 type/scope/空格，不要加 `--no-verify` |
| 需要修改刚提交的内容 | 未 push 时用 `git commit --amend`；已 push 则新建提交，不要改写历史 |
| `mvn` 因缺依赖失败 | 先 `mvn -o` 离线跑；仍失败则报告用户，不要擅自改依赖 |
