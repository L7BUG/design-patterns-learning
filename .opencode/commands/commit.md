---
description: 按项目规范提交改动（Conventional Commits + IDE 格式化）
---
用 skill 工具加载 `git-commit` 技能，然后严格按其中流程提交当前改动，不要跳步。

如果用户提供了额外参数，只提交与它相关的改动：$ARGUMENTS

当前仓库状态：

!`git status --short; echo "--- 未暂存 ---"; git diff --stat; echo "--- 已暂存 ---"; git diff --cached --stat`
