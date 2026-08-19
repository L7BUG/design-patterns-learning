package com.l7bug.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 代理模式演示
 *
 * <p>场景：延迟加载图片
 * - Image 是抽象主题（Subject）
 * - RealImage 是真实主题（RealSubject），构造时执行昂贵加载
 * - ImageProxy 是代理（Proxy），持有 RealImage 引用，首次 display() 时才创建
 * <p>客户端通过 Image 接口与代理交互，无需知道背后是否有真实对象。</p>
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("=== 代理模式演示 ===\n");

        // 1. 创建代理（此时不会加载真实图片）
        log.info("1. 创建图片代理");
        Image image = new ImageProxy("large-photo.jpg");
        log.info("   代理已创建，图片未加载");
        log.info("   文件名: {}", image.getFileName());

        // 2. 第一次调用 display()（触发真实加载）
        log.info("\n2. 第一次调用 display()（触发真实加载）");
        image.display();

        // 3. 第二次调用 display()（不再重新加载）
        log.info("\n3. 第二次调用 display()（不再重新加载）");
        image.display();

        // 4. 展示多个代理各自独立工作
        log.info("\n4. 多个代理独立工作");
        Image proxy1 = new ImageProxy("sunset.png");
        Image proxy2 = new ImageProxy("mountain.jpg");
        log.info("   代理1: {}", proxy1.getFileName());
        log.info("   代理2: {}", proxy2.getFileName());
        proxy1.display();
        proxy2.display();

        log.info("\n=== 代理模式演示完成 ===");
    }
}
