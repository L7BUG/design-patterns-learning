package com.l7bug.proxy;

/**
 * 图片代理（Proxy — GOF 代理模式中的代理角色）
 *
 * <p>持有文件名和延迟创建的 RealImage 引用。
 * 在第一次调用 {@link #display()} 时才真正创建 RealImage，
 * 实现延迟加载（Lazy Loading）。</p>
 *
 * <p>代理与真实图片实现相同接口 {@link Image}，
 * 客户端无感知地使用代理替代真实对象。</p>
 */
public class ImageProxy implements Image {

    private final String fileName;

    /** 延迟创建的真实图片引用，首次 display() 时才赋值 */
    private RealImage realImage;

    public ImageProxy(String fileName) {
        this.fileName = fileName;
        this.realImage = null;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    /**
     * 检查真实图片是否已被创建（即是否已加载）
     *
     * @return true 表示已加载，false 表示尚未加载
     */
    public boolean isLoaded() {
        return realImage != null;
    }
}
