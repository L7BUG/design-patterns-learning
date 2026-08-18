package com.l7bug.abstractfactory;

/**
 * 具体产品 - 深色主题文本框
 */
public class DarkTextField implements TextField {

    @Override
    public void render() {
        System.out.println("  [深色文本框] 渲染黑色文本框");
    }

    @Override
    public void type(String text) {
        System.out.println("  [深色文本框] 输入: " + text);
    }
}
