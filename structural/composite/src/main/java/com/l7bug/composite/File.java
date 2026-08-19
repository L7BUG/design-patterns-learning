package com.l7bug.composite;

/**
 * 文件（Leaf 角色）
 *
 * <p>组合模式中的叶子节点，表示文件系统中的单个文件。
 * 它不包含子组件，直接提供名称和大小信息。</p>
 *
 * <p>GOF 角色：Leaf — 在组合中表示叶子对象，叶子没有子节点。</p>
 */
public class File extends FileSystemComponent {

    private final String name;

    private final int size;

    /**
     * 创建文件
     *
     * @param name 文件名
     * @param size 文件大小（KB）
     */
    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void print(int indent) {
        System.out.println("    ".repeat(indent) + name + " (" + size + " KB)");
    }
}
