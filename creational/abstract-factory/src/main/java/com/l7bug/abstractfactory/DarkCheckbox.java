package com.l7bug.abstractfactory;

/**
 * 具体产品 - 深色主题复选框
 */
public class DarkCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println("  [深色复选框] 渲染黑色复选框");
    }

    @Override
    public void toggle() {
        System.out.println("  [深色复选框] 切换状态");
    }
}
