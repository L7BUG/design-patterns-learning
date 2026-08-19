package com.l7bug.composite;

import java.util.List;

/**
 * 文件系统组件（Component 角色）
 *
 * <p>组合模式的核心抽象，定义文件和文件夹的统一接口。
 * 叶子节点（File）和组合节点（Folder）都继承此类，
 * 客户端可以一致地操作它们。</p>
 *
 * <p>GOF 角色：Component — 为组合中的对象声明统一接口。</p>
 */
public abstract class FileSystemComponent {

    /**
     * 获取组件名称
     *
     * @return 组件名称
     */
    public abstract String getName();

    /**
     * 获取组件大小（KB）
     *
     * @return 文件大小；文件夹则返回所有子组件大小之和
     */
    public abstract int getSize();

    /**
     * 以缩进格式打印组件结构
     *
     * @param indent 缩进层级（每层 4 个空格）
     */
    public abstract void print(int indent);

    /**
     * 添加子组件（仅组合节点有意义）
     *
     * @param component 要添加的子组件
     * @throws UnsupportedOperationException 叶子节点不支持此操作
     */
    public void add(FileSystemComponent component) {
        throw new UnsupportedOperationException("叶子节点不支持添加子组件");
    }

    /**
     * 移除子组件（仅组合节点有意义）
     *
     * @param component 要移除的子组件
     * @throws UnsupportedOperationException 叶子节点不支持此操作
     */
    public void remove(FileSystemComponent component) {
        throw new UnsupportedOperationException("叶子节点不支持移除子组件");
    }

    /**
     * 获取子组件列表（仅组合节点有意义）
     *
     * @return 不可变子组件列表
     * @throws UnsupportedOperationException 叶子节点不支持此操作
     */
    public List<FileSystemComponent> getChildren() {
        throw new UnsupportedOperationException("叶子节点没有子组件");
    }
}
