package com.l7bug.abstractfactory;

/**
 * 具体工厂 - 深色主题工厂
 *
 * 创建深色主题的产品族（黑色按钮、黑色复选框、黑色文本框）
 */
public class DarkThemeFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }

    @Override
    public TextField createTextField() {
        return new DarkTextField();
    }
}
