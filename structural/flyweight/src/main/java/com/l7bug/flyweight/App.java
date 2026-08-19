package com.l7bug.flyweight;

import lombok.extern.slf4j.Slf4j;

/**
 * 享元模式演示
 *
 * <p>场景：文本编辑器字符渲染
 * - CharacterStyle 是享元对象（Flyweight），存储共享的字体样式
 * - CharacterStyleFactory 是享元工厂（FlyweightFactory），管理样式缓存池
 * - Character 是上下文对象（Context），持有外部状态（字符值+位置）和享元引用</p>
 *
 * <p>演示效果：渲染一段中文文本，100 个字符仅创建 3 个样式对象，
 * 体现享元模式"用共享替代重复"的核心价值。</p>
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        // 清空缓存，确保演示环境干净
        CharacterStyleFactory.clearCache();

        log.info("=== 享元模式演示 ===\n");

        // 1. 从工厂获取样式（享元对象），观察缓存增长
        log.info("1. 从工厂获取样式对象");
        CharacterStyle boldStyle = CharacterStyleFactory.getStyle("黑体", 16, "红色");
        CharacterStyle normalStyle = CharacterStyleFactory.getStyle("宋体", 12, "黑色");
        CharacterStyle italicStyle = CharacterStyleFactory.getStyle("楷体", 14, "蓝色");
        log.info("   创建 3 种样式，缓存大小: {}", CharacterStyleFactory.getCacheSize());

        // 2. 重复获取相同样式，验证享元复用（同一实例）
        log.info("\n2. 验证享元复用（assertSame）");
        CharacterStyle sameBold = CharacterStyleFactory.getStyle("黑体", 16, "红色");
        log.info("   同参数获取 → 同一实例: {}", boldStyle == sameBold);

        // 3. 构建一段中文文本，多个字符共享少量样式
        log.info("\n3. 渲染一段文本（100 个字符）");
        String text = "享元模式通过共享来高效支持大量细粒度的对象。"
                + "在文本编辑器中，每个字符有自己的位置，但同类型的字符共享字体样式。"
                + "这大大减少了内存占用，是对象池技术的经典应用。";

        // 将每个字符展开，按规则分配样式
        int charCount = 0;
        int x = 0;
        for (char c : text.toCharArray()) {
            // 简单规则：标点用斜体，数字/英文用粗体，其余用普通样式
            CharacterStyle style;
            if (java.lang.Character.isDigit(c) || java.lang.Character.isLetter(c)) {
                style = boldStyle;
            } else if (c == '，' || c == '。' || c == '、' || c == '；'
                    || c == '：' || c == '！' || c == '？') {
                style = italicStyle;
            } else {
                style = normalStyle;
            }

            TextCharacter ch = new TextCharacter(c, x, 0, style);
            x += style.getFontSize(); // 模拟字符宽度

            // 只打印前 10 个字符的渲染结果作为示例
            if (charCount < 10) {
                log.info("   {}", ch.render());
            }
            charCount++;
        }

        // 4. 展示享元效果
        log.info("\n4. 享元效果统计");
        log.info("   总字符数: {}", charCount);
        log.info("   样式对象数: {}", CharacterStyleFactory.getCacheSize());
        log.info("   {} 个字符仅创建 {} 个样式对象",
                charCount, CharacterStyleFactory.getCacheSize());
        log.info("   如果每个字符都独立创建样式，则需要 {} 个对象",
                charCount);

        // 5. 内存节省估算
        int saved = charCount - CharacterStyleFactory.getCacheSize();
        double savedPercent = (saved * 100.0) / charCount;
        log.info("\n5. 节省效果");
        log.info("   减少 {} 个对象创建，节省 {}% 的样式对象内存",
                saved, String.format("%.1f", savedPercent));

        log.info("\n=== 享元模式演示完成 ===");
    }
}
