package com.l7bug.abstractfactory;

/**
 * 具体工厂 - 浅色主题工厂
 *
 * 创建浅色主题的产品族（白色按钮、白色复选框、白色文本框）
 */
public class LightThemeFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }

    @Override
    public TextField createTextField() {
        return new LightTextField();
    }
}
