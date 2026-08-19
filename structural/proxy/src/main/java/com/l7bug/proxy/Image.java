package com.l7bug.proxy;

/**
 * 图片接口（Subject — GOF 代理模式中的抽象主题角色）
 *
 * <p>定义了 RealImage 和 ImageProxy 共同实现的接口，
 * 使得代理可以替代真实对象被客户端使用。</p>
 */
public interface Image {

    /**
     * 显示图片
     */
    void display();

    /**
     * 获取图片文件名
     *
     * @return 文件名
     */
    String getFileName();
}
