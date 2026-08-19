package com.l7bug.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹（Composite 角色）
 *
 * <p>组合模式中的组合节点，包含子组件列表。
 * 可以递归嵌套，getSize() 递归汇总所有子组件大小，
 * print() 递归缩进打印整个子树。</p>
 *
 * <p>GOF 角色：Composite — 定义有子部件的对象，存储子部件并实现 Component 接口。</p>
 */
public class Folder extends FileSystemComponent {

    private final String name;

    private final List<FileSystemComponent> children = new ArrayList<>();

    /**
     * 创建文件夹
     *
     * @param name 文件夹名
     */
    public Folder(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * 递归计算文件夹总大小
     *
     * @return 所有子组件大小之和
     */
    @Override
    public int getSize() {
        int total = 0;
        for (FileSystemComponent child : children) {
            total += child.getSize();
        }
        return total;
    }

    /**
     * 递归缩进打印文件夹及其子组件
     *
     * @param indent 缩进层级
     */
    @Override
    public void print(int indent) {
        System.out.println("    ".repeat(indent) + name + " (" + getSize() + " KB)");
        for (FileSystemComponent child : children) {
            child.print(indent + 1);
        }
    }

    /**
     * 添加子组件
     *
     * @param component 要添加的子组件
     */
    @Override
    public void add(FileSystemComponent component) {
        children.add(component);
    }

    /**
     * 移除子组件
     *
     * @param component 要移除的子组件
     */
    @Override
    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    /**
     * 获取子组件列表（防御拷贝）
     *
     * @return 不可变的子组件列表副本
     */
    @Override
    public List<FileSystemComponent> getChildren() {
        return List.copyOf(children);
    }
}
