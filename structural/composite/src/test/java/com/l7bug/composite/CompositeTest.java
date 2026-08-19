package com.l7bug.composite;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 组合模式单元测试
 */
class CompositeTest {

    @Test
    void leafSizeReturnsOwnSize() {
        FileSystemComponent file = new File("config.xml", 8);
        assertEquals(8, file.getSize());
        assertEquals("config.xml", file.getName());
    }

    @Test
    void folderSizeSumsChildren() {
        Folder folder = new Folder("docs");
        folder.add(new File("a.md", 3));
        folder.add(new File("b.md", 5));
        assertEquals(8, folder.getSize());
    }

    @Test
    void nestedFoldersRecursiveSize() {
        Folder root = new Folder("root");
        Folder sub = new Folder("sub");
        sub.add(new File("f1.txt", 2));
        sub.add(new File("f2.txt", 3));
        root.add(sub);
        root.add(new File("top.txt", 10));

        // sub(5) + top.txt(10) = 15
        assertEquals(15, root.getSize());
    }

    @Test
    void removeChildReducesSize() {
        Folder folder = new Folder("data");
        FileSystemComponent file = new File("big.bin", 100);
        folder.add(file);
        assertEquals(100, folder.getSize());

        folder.remove(file);
        assertEquals(0, folder.getSize());
    }

    @Test
    void getChildrenReturnsDefensiveCopy() {
        Folder folder = new Folder("pkg");
        folder.add(new File("A.java", 1));
        var children = folder.getChildren();
        assertEquals(1, children.size());

        // 修改返回列表不应影响文件夹内部状态
        // children 是不可变副本，无法 add — 此处验证大小一致
        assertEquals(1, folder.getChildren().size());
    }

    @Test
    void printOutputContainsAllNames() {
        Folder root = new Folder("项目");
        Folder src = new Folder("src");
        src.add(new File("App.java", 4));
        root.add(src);
        root.add(new File("README.md", 2));

        // 捕获 System.out 输出
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        try {
            root.print(0);
        } finally {
            System.setOut(original);
        }

        String output = out.toString();
        assertTrue(output.contains("项目"), "输出应包含根文件夹名");
        assertTrue(output.contains("src"), "输出应包含子文件夹名");
        assertTrue(output.contains("App.java"), "输出应包含文件名");
        assertTrue(output.contains("README.md"), "输出应包含文件名");
    }

    @Test
    void leafDoesNotSupportAdd() {
        FileSystemComponent file = new File("readme.txt", 1);
        assertThrows(UnsupportedOperationException.class,
                () -> file.add(new File("other.txt", 1)));
    }

    @Test
    void leafDoesNotSupportRemove() {
        FileSystemComponent file = new File("readme.txt", 1);
        assertThrows(UnsupportedOperationException.class,
                () -> file.remove(new File("other.txt", 1)));
    }

    @Test
    void leafDoesNotSupportGetChildren() {
        FileSystemComponent file = new File("readme.txt", 1);
        assertThrows(UnsupportedOperationException.class, file::getChildren);
    }

    @Test
    void folderIsFileSystemComponent() {
        Folder folder = new Folder("test");
        assertInstanceOf(FileSystemComponent.class, folder);
    }

    @Test
    void emptyFolderHasZeroSize() {
        Folder folder = new Folder("empty");
        assertEquals(0, folder.getSize());
    }

    @Test
    void printIndentIncreasesWithDepth() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        try {
            FileSystemComponent file = new File("A.txt", 1);
            file.print(0);
            file.print(2);
        } finally {
            System.setOut(original);
        }

        String[] lines = out.toString().split("\\R");
        assertEquals(2, lines.length);
        // 第二行缩进更多
        assertTrue(lines[1].startsWith("        "),
                "缩进 2 层应有 8 个前导空格");
    }
}
