package com.l7bug.composite;

import lombok.extern.slf4j.Slf4j;

/**
 * 组合模式演示
 *
 * <p>场景：文件系统（树形结构）
 * - FileSystemComponent 是组件接口（Component）
 * - File 是叶子节点（Leaf）
 * - Folder 是组合节点（Composite）
 * <p>客户端可以通过统一接口操作文件和文件夹，
 * 无需区分叶子和组合，实现透明性。</p>
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("=== 组合模式演示 ===\n");

        // 1. 创建叶子节点（文件）
        log.info("1. 创建文件（叶子节点）");
        FileSystemComponent readme = new File("README.md", 2);
        FileSystemComponent mainJava = new File("Main.java", 5);
        FileSystemComponent utilsJava = new File("Utils.java", 3);
        FileSystemComponent testJava = new File("Test.java", 4);
        log.info("   {} 大小: {} KB", readme.getName(), readme.getSize());
        log.info("   {} 大小: {} KB", mainJava.getName(), mainJava.getSize());

        // 2. 创建组合节点（文件夹），组装子树
        log.info("\n2. 创建文件夹（组合节点），组装子树");
        Folder srcFolder = new Folder("src");
        srcFolder.add(mainJava);
        srcFolder.add(utilsJava);
        log.info("   src 文件夹总大小: {} KB", srcFolder.getSize());

        Folder testFolder = new Folder("test");
        testFolder.add(testJava);
        log.info("   test 文件夹总大小: {} KB", testFolder.getSize());

        // 3. 构建完整项目目录树
        log.info("\n3. 构建完整项目目录树");
        Folder projectFolder = new Folder("项目文件夹");
        projectFolder.add(srcFolder);
        projectFolder.add(testFolder);
        projectFolder.add(readme);
        log.info("   项目总大小: {} KB", projectFolder.getSize());

        // 4. 递归打印整个目录树
        log.info("\n4. 递归打印目录树:");
        System.out.println();
        projectFolder.print(0);
        System.out.println();

        // 5. 动态移除子组件
        log.info("5. 移除 test 文件夹后");
        projectFolder.remove(testFolder);
        log.info("   项目总大小: {} KB（减少 {} KB）",
                projectFolder.getSize(), testFolder.getSize());

        // 6. 叶子节点不支持 add/remove
        log.info("\n6. 叶子节点操作验证");
        try {
            readme.add(new File("意外.txt", 1));
        } catch (UnsupportedOperationException e) {
            log.info("   叶子节点 add 抛出异常: {}", e.getMessage());
        }

        log.info("\n=== 组合模式演示完成 ===");
    }
}
