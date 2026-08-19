package com.l7bug.flyweight;

import java.util.Objects;

/**
 * 字符样式 — 享元对象（Flyweight）
 *
 * <p>GOF 角色：Flyweight（享元）</p>
 *
 * <p>存储内部状态（intrinsic state）：字体名称、字号、颜色。
 * 这些属性在同一类字符间是共享的，不随具体字符变化。
 * 不可变对象，创建后不能修改，确保多处引用的安全性。</p>
 *
 * <p>示例：在渲染一段文字时，"宋体-12-黑色" 可能被上百个字符共用，
 * 而不是每个字符各创建一份样式副本。</p>
 */
public final class CharacterStyle {

    /** 字体名称（内部状态） */
    private final String fontName;

    /** 字号大小（内部状态） */
    private final int fontSize;

    /** 字体颜色（内部状态） */
    private final String color;

    /**
     * 构造不可变的字符样式
     *
     * @param fontName 字体名称，如 "宋体"、"黑体"
     * @param fontSize 字号大小，如 12、14、16
     * @param color    颜色名称，如 "黑色"、"红色"
     */
    public CharacterStyle(String fontName, int fontSize, String color) {
        this.fontName = fontName;
        this.fontSize = fontSize;
        this.color = color;
    }

    public String getFontName() {
        return fontName;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getColor() {
        return color;
    }

    /**
     * 生成复合缓存键，用于工厂的 Map 查找
     *
     * @return 格式为 "字体-字号-颜色" 的唯一键
     */
    public String toCacheKey() {
        return fontName + "-" + fontSize + "-" + color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CharacterStyle that = (CharacterStyle) o;
        return fontSize == that.fontSize
                && Objects.equals(fontName, that.fontName)
                && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fontName, fontSize, color);
    }

    @Override
    public String toString() {
        return "CharacterStyle{font='" + fontName + "', size=" + fontSize + ", color='" + color + "'}";
    }
}
