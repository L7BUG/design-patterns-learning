package com.l7bug.abstractfactory;

/**
 * 具体产品 - 浅色主题复选框
 */
public class LightCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println("  [浅色复选框] 渲染白色复选框");
    }

    @Override
    public void toggle() {
        System.out.println("  [浅色复选框] 切换状态");
    }
}
