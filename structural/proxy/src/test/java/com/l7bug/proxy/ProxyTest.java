package com.l7bug.proxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** 代理模式单元测试 */
class ProxyTest {

    @BeforeEach
    void setUp() {
        RealImage.resetLoadCount();
    }

    @Test
    void proxyDoesNotLoadBeforeDisplay() {
        ImageProxy proxy = new ImageProxy("test.jpg");

        assertFalse(proxy.isLoaded(), "代理创建后不应立即加载真实图片");
        assertEquals(0, RealImage.getLoadCount(), "不应有任何 RealImage 被创建");
    }

    @Test
    void displayLoadsOnFirstCall() {
        ImageProxy proxy = new ImageProxy("test.jpg");

        proxy.display();

        assertTrue(proxy.isLoaded(), "首次 display() 后应已加载真实图片");
        assertEquals(1, RealImage.getLoadCount(), "应创建一个 RealImage 实例");
    }

    @Test
    void secondDisplayDoesNotReload() {
        ImageProxy proxy = new ImageProxy("test.jpg");

        proxy.display();
        proxy.display();

        assertEquals(1, RealImage.getLoadCount(), "第二次 display() 不应重新加载");
    }

    @Test
    void proxyImplementsImageInterface() {
        Image proxy = new ImageProxy("test.jpg");

        assertInstanceOf(Image.class, proxy, "代理应实现 Image 接口");
    }

    @Test
    void proxyDelegatesFileNameCorrectly() {
        ImageProxy proxy = new ImageProxy("large-photo.jpg");

        assertEquals("large-photo.jpg", proxy.getFileName(), "代理应正确返回文件名");
    }
}
