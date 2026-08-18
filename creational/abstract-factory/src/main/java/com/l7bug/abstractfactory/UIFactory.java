package com.l7bug.abstractfactory;

/**
 * 抽象工厂 - UI 工厂
 *
 * 定义了创建一族相关产品（按钮、复选框、文本框）的接口
 * 具体工厂决定创建哪个主题的产品族
 */
public interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
    TextField createTextField();
}
