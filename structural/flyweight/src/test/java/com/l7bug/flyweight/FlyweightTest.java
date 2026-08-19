package com.l7bug.flyweight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 享元模式单元测试
 */
class FlyweightTest {

    @BeforeEach
    void setUp() {
        // 每个测试前清空缓存，确保测试隔离
        CharacterStyleFactory.clearCache();
    }

    @Test
    void factoryReturnsSameInstanceForSameStyle() {
        // 相同参数获取样式，应该返回同一个实例（享元复用）
        CharacterStyle style1 = CharacterStyleFactory.getStyle("宋体", 12, "黑色");
        CharacterStyle style2 = CharacterStyleFactory.getStyle("宋体", 12, "黑色");
        assertSame(style1, style2, "相同参数应返回同一享元实例");
    }

    @Test
    void factoryReturnsDifferentInstanceForDifferentStyle() {
        // 不同参数获取样式，应该返回不同实例
        CharacterStyle style1 = CharacterStyleFactory.getStyle("宋体", 12, "黑色");
        CharacterStyle style2 = CharacterStyleFactory.getStyle("黑体", 16, "红色");
        assertNotSame(style1, style2, "不同参数应返回不同实例");
        assertNotEquals(style1, style2, "不同参数的样式内容应不相等");
    }

    @Test
    void cacheSizeGrowsOnlyWithDistinctStyles() {
        // 缓存大小只随不同样式增长
        CharacterStyleFactory.getStyle("宋体", 12, "黑色");
        CharacterStyleFactory.getStyle("宋体", 12, "黑色"); // 重复，不增长
        CharacterStyleFactory.getStyle("宋体", 14, "黑色"); // 不同字号，增长
        CharacterStyleFactory.getStyle("黑体", 12, "红色"); // 不同字体和颜色，增长

        assertEquals(3, CharacterStyleFactory.getCacheSize(),
                "缓存中应有 3 个不同的样式对象");
    }

    @Test
    void charactersWithSameStyleShareTheFlyweight() {
        // 多个字符使用相同样式时，它们持有的样式引用应是同一个享元对象
        CharacterStyle sharedStyle = CharacterStyleFactory.getStyle("宋体", 12, "黑色");
        TextCharacter char1 = new TextCharacter('你', 0, 0, sharedStyle);
        TextCharacter char2 = new TextCharacter('好', 12, 0, sharedStyle);
        TextCharacter char3 = new TextCharacter('世', 24, 0, sharedStyle);

        assertSame(char1.getStyle(), char2.getStyle(),
                "同一样式参数的字符应共享享元对象");
        assertSame(char2.getStyle(), char3.getStyle(),
                "同一样式参数的字符应共享享元对象");
    }

    @Test
    void renderOutputContainsCharValue() {
        // 渲染输出应包含字符值
        CharacterStyle style = CharacterStyleFactory.getStyle("宋体", 12, "黑色");
        TextCharacter ch = new TextCharacter('你', 10, 20, style);

        String rendered = ch.render();
        assertTrue(rendered.contains("你"), "渲染结果应包含字符值 '你'");
        assertTrue(rendered.contains("宋体"), "渲染结果应包含字体名");
        assertTrue(rendered.contains("12"), "渲染结果应包含字号");
        assertTrue(rendered.contains("黑色"), "渲染结果应包含颜色");
    }

    @Test
    void styleFieldsAreImmutable() {
        // CharacterStyle 是不可变的，所有字段通过构造函数设置
        CharacterStyle style = CharacterStyleFactory.getStyle("宋体", 12, "黑色");
        assertEquals("宋体", style.getFontName());
        assertEquals(12, style.getFontSize());
        assertEquals("黑色", style.getColor());
    }

    @Test
    void toCacheKeyMatchesCompositeKey() {
        // toCacheKey 生成的键与工厂内部使用的复合键一致
        CharacterStyle style = CharacterStyleFactory.getStyle("宋体", 12, "黑色");
        String key = style.toCacheKey();
        assertEquals("宋体-12-黑色", key, "复合键应为 '字体-字号-颜色' 格式");
    }
}
