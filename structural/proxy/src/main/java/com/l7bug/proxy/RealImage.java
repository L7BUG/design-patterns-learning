package com.l7bug.proxy;

/**
 * 真实图片（RealSubject — GOF 代理模式中的真实主题角色）
 *
 * <p>构造时执行昂贵的磁盘加载操作，模拟真实场景中
 * 加载大尺寸图片的耗时行为。</p>
 */
public class RealImage implements Image {

    /** 全局加载次数计数器，用于测试验证代理确实延迟了加载 */
    private static int loadCount = 0;

    private final String fileName;

    /**
     * 构造时立即从磁盘加载图片（模拟耗时操作）
     *
     * @param fileName 图片文件名
     */
    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    /**
     * 模拟从磁盘加载图片的昂贵操作
     */
    private void loadFromDisk() {
        System.out.println("    [RealImage] 从磁盘加载图片: " + fileName);
        loadCount++;
    }

    @Override
    public void display() {
        System.out.println("显示图片: " + fileName);
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    /**
     * 检查图片是否已从磁盘加载
     *
     * @return 始终为 true（构造时已完成加载）
     */
    public boolean isLoaded() {
        return true;
    }

    /**
     * 获取全局加载次数（每次创建 RealImage 实例时 +1）
     *
     * @return 累计加载次数
     */
    public static int getLoadCount() {
        return loadCount;
    }

    /**
     * 重置加载计数器（仅用于测试隔离）
     */
    public static void resetLoadCount() {
        loadCount = 0;
    }
}
