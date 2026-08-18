package com.l7bug.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * 原型缓存 - 存储预定义的原型对象
 *
 * 客户端通过缓存获取原型，然后克隆出新对象
 * 不需要知道具体类的构造函数
 */
public class ShapeCache {

    private static final Map<String, Shape> cache = new HashMap<>();

    /**
     * 加载预定义的原型到缓存
     */
    public static void loadCache() {
        Circle redCircle = new Circle("红色", 10);
        Circle blueCircle = new Circle("蓝色", 15);
        Rectangle greenRect = new Rectangle("绿色", 20, 10);
        Rectangle yellowRect = new Rectangle("黄色", 25, 15);

        cache.put("RED_CIRCLE", redCircle);
        cache.put("BLUE_CIRCLE", blueCircle);
        cache.put("GREEN_RECT", greenRect);
        cache.put("YELLOW_RECT", yellowRect);
    }

    /**
     * 从缓存获取原型并克隆
     * @param key 原型键
     * @return 克隆后的新对象
     */
    public static Shape getShape(String key) {
        Shape cachedShape = cache.get(key);
        if (cachedShape == null) {
            throw new IllegalArgumentException("未找到原型: " + key);
        }
        return cachedShape.clone();
    }

    /**
     * 获取所有原型的键
     * @return 所有可用原型的键
     */
    public static java.util.Set<String> getAvailableKeys() {
        return cache.keySet();
    }
}
