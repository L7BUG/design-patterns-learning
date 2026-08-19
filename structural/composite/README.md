# Composite 组合模式

## 解决什么问题？
> 将对象组合成树形结构以表示"部分-整体"的层次关系。
> 使得客户端可以一致地处理单个对象和组合对象，无需区分。

## 核心思想
> 组合（Composite）— 通过统一接口，让叶子节点和组合节点
> 具有相同的操作方式，客户端无需关心当前处理的是单个对象还是容器。

## 适用场景
- 需要表示对象的树形结构（文件系统、组织架构、菜单）
- 希望客户端统一操作叶子和组合节点，不区分类型
- 树形结构的层次可能在运行时动态变化
- 需要递归遍历整个树并执行统一操作

## 类图结构

```
              ┌─────────────────────────┐
              │   FileSystemComponent   │  ← 组件（Component）
              │  + getName(): String    │  统一接口
              │  + getSize(): int       │
              │  + print(indent: int)   │
              │  + add(component)       │  默认抛异常
              │  + remove(component)    │  默认抛异常
              │  + getChildren()        │
              └──────────┬──────────────┘
                    ┌────┴────┐
                    │         │
         继承 ┌─────┴──┐  ┌──┴──────┐
              │  File  │  │ Folder  │  ← 叶子（Leaf）/ 组合（Composite）
              │-name   │  │-name    │  File 无子组件
              │-size   │  │-children│  Folder 递归管理子组件
              └────────┘  └─────────┘
```

## 代码结构

```
Composite/
├── FileSystemComponent.java   # 组件抽象类（Component）
├── File.java                  # 叶子节点（Leaf）
├── Folder.java                # 组合节点（Composite）
├── App.java                   # 演示类
├── CompositeTest.java         # 单元测试
└── README.md                  # 本文件
```

## 关键点

### 1. 组件抽象类（Component）
```java
public abstract class FileSystemComponent {
    public abstract String getName();
    public abstract int getSize();
    public abstract void print(int indent);

    // 叶子节点默认不支持的操作，抛出异常
    public void add(FileSystemComponent component) {
        throw new UnsupportedOperationException("叶子节点不支持添加子组件");
    }
    public void remove(FileSystemComponent component) {
        throw new UnsupportedOperationException("叶子节点不支持移除子组件");
    }
}
```

### 2. 叶子节点（Leaf）
```java
public class File extends FileSystemComponent {
    private final String name;
    private final int size;

    @Override
    public int getSize() {
        return size;  // 直接返回自身大小
    }

    @Override
    public void print(int indent) {
        System.out.println("    ".repeat(indent) + name + " (" + size + " KB)");
    }
}
```

### 3. 组合节点（Composite）递归操作
```java
public class Folder extends FileSystemComponent {
    private final List<FileSystemComponent> children = new ArrayList<>();

    @Override
    public int getSize() {
        int total = 0;
        for (FileSystemComponent child : children) {
            total += child.getSize();  // 递归汇总
        }
        return total;
    }

    @Override
    public void print(int indent) {
        System.out.println("    ".repeat(indent) + name + " (" + getSize() + " KB)");
        for (FileSystemComponent child : children) {
            child.print(indent + 1);  // 递归打印
        }
    }

    @Override
    public List<FileSystemComponent> getChildren() {
        return List.copyOf(children);  // 防御拷贝
    }
}
```

### 4. 透明性 — 客户端统一操作
```java
// 客户端无需区分文件和文件夹
FileSystemComponent file = new File("readme.md", 2);
FileSystemComponent folder = new Folder("src");
folder.add(file);

// 统一调用
file.getSize();    // 2
folder.getSize();  // 2（递归汇总）
file.print(0);     // readme.md (2 KB)
folder.print(0);   // src (2 KB)
```

## 组合模式 vs 直接使用集合

| 方式 | 问题 | 组合模式优势 |
|------|------|-------------|
| **分散管理** | 文件和文件夹各自维护逻辑，无法统一遍历 | 统一接口，透明操作 |
| **类型判断** | 到处 `instanceof` 检查类型 | 无需区分叶子和组合 |
| **递归困难** | 手动递归逻辑分散在各处 | 组合节点自动递归子树 |
| **扩展性差** | 新增节点类型需修改所有遍历代码 | 开闭原则，新增节点无需修改 |

## 实际应用

- 文件系统：`File`（叶子）与 `Directory`（组合），`ls -R` 递归遍历
- GUI 组件树：`Widget`（叶子）与 `Container`（组合），布局递归渲染
- 组织架构：`Employee`（叶子）与 `Department`（组合），统计部门人数
- 菜单系统：`MenuItem`（叶子）与 `Menu`（组合），嵌套菜单树
- XML/HTML DOM：`TextNode`（叶子）与 `Element`（组合），递归解析

## 个人理解与心得

> 组合模式的精髓是"部分与整体的一致性"。
> 它让树形结构中的叶子和容器拥有相同的接口，
> 客户端代码可以递归地处理整棵树，而不需要知道当前节点的具体类型。
>
> 最经典的例子是文件系统：
> - 你用 `du -sh` 统计目录大小时，它递归地把所有文件大小加起来
> - 你用 `ls` 列出目录内容时，文件和子目录都显示在同一个列表中
> - 你甚至不需要知道某个条目是文件还是目录，操作方式完全一致
>
> 组合模式的关键设计决策是"透明性 vs 安全性"：
> - 透明性：在 Component 中声明 add/remove，叶子节点抛异常（本示例采用）
> - 安全性：只在 Composite 中声明 add/remove，客户端需类型判断
>
> 透明性的好处是客户端完全不需要区分类型，代价是叶子节点有"不该有"的方法。
