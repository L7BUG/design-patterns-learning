package com.l7bug.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 字符样式工厂 — 享元工厂（Flyweight Factory）
 *
 * <p>GOF 角色：FlyweightFactory（享元工厂）</p>
 *
 * <p>管理享元对象的缓存池，确保相同的内部状态只创建一个实例。
 * 当请求的样式已存在时，返回缓存中的实例；否则创建新实例并缓存。</p>
 *
 * <p>使用 ConcurrentHashMap 保证线程安全（教学演示场景下
 * 也兼顾了多线程环境的正确性）。</p>
 */
public final class CharacterStyleFactory {

    /**
     * 样式缓存池 — 键为复合键 "字体-字号-颜色"，值为享元实例
     */
    private static final Map<String, CharacterStyle> CACHE = new ConcurrentHashMap<>();

    // 工具类，禁止实例化
    private CharacterStyleFactory() {
    }

    /**
     * 获取或创建字符样式
     *
     * <p>根据字体名称、字号、颜色查找缓存，命中则返回已有实例（享元复用），
     * 未命中则创建新实例并放入缓存。</p>
     *
     * @param fontName 字体名称
     * @param fontSize 字号大小
     * @param color    颜色名称
     * @return 缓存中的享元实例（相同参数始终返回同一对象）
     */
    public static CharacterStyle getStyle(String fontName, int fontSize, String color) {
        // 利用 ConcurrentHashMap 的 computeIfAbsent 实现原子性的"查缓存或创建"
        String key = fontName + "-" + fontSize + "-" + color;
        return CACHE.computeIfAbsent(key, k -> new CharacterStyle(fontName, fontSize, color));
    }

    /**
     * 获取当前缓存中的样式数量
     *
     * <p>用于测试和演示，观察享元复用效果：大量字符只产生少量样式对象。</p>
     *
     * @return 缓存中的不同样式数量
     */
    public static int getCacheSize() {
        return CACHE.size();
    }

    /**
     * 清空缓存（仅用于测试隔离）
     */
    public static void clearCache() {
        CACHE.clear();
    }
}
