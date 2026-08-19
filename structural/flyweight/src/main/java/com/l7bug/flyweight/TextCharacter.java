package com.l7bug.flyweight;

/**
 * 文本字符 — 上下文对象（Context）
 *
 * <p>GOF 角色：Flyweight 的使用者，持有外部状态（extrinsic state）</p>
 *
 * <p>每个字符对象存储自己的外部状态：
 * - 字符值（char）：要显示的字符本身
 * - 位置坐标（x, y）：字符在屏幕上的渲染位置</p>
 *
 * <p>样式（CharacterStyle）作为共享的内部状态，通过引用指向工厂提供的享元对象，
 * 而不是每个字符各自拥有一份样式副本。这就是享元模式节省内存的核心。</p>
 */
public class TextCharacter {

    /** 字符值（外部状态） */
    private final char value;

    /** 横坐标（外部状态） */
    private final int x;

    /** 纵坐标（外部状态） */
    private final int y;

    /** 共享的样式引用（指向享元对象） */
    private final CharacterStyle style;

    /**
     * 构造字符上下文对象
     *
     * @param value 字符值
     * @param x     横坐标
     * @param y     纵坐标
     * @param style 共享的字符样式（享元对象引用）
     */
    public TextCharacter(char value, int x, int y, CharacterStyle style) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.style = style;
    }

    /**
     * 渲染字符，返回显示字符串
     *
     * <p>模拟文本编辑器中字符的渲染输出，包含字符值及其样式信息。</p>
     *
     * @return 格式化的渲染字符串，如 "['你' @(10,20) 宋体-12-黑色]"
     */
    public String render() {
        return "['" + value + "' @(" + x + "," + y + ") "
                + style.getFontName() + "-" + style.getFontSize() + "-" + style.getColor() + "]";
    }

    public CharacterStyle getStyle() {
        return style;
    }
}
