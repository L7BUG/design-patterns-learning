package com.l7bug.abstractfactory;

/**
 * 具体产品 - 浅色主题文本框
 */
public class LightTextField implements TextField {

    @Override
    public void render() {
        System.out.println("  [浅色文本框] 渲染白色文本框");
    }

    @Override
    public void type(String text) {
        System.out.println("  [浅色文本框] 输入: " + text);
    }
}
