package com.l7bug.abstractfactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 抽象工厂模式单元测试
 */
class AbstractFactoryTest {

    @Test
    void lightThemeFactory() {
        UIFactory factory = new LightThemeFactory();

        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();
        TextField textField = factory.createTextField();

        assertNotNull(button);
        assertNotNull(checkbox);
        assertNotNull(textField);

        assertInstanceOf(LightButton.class, button);
        assertInstanceOf(LightCheckbox.class, checkbox);
        assertInstanceOf(LightTextField.class, textField);
    }

    @Test
    void darkThemeFactory() {
        UIFactory factory = new DarkThemeFactory();

        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();
        TextField textField = factory.createTextField();

        assertNotNull(button);
        assertNotNull(checkbox);
        assertNotNull(textField);

        assertInstanceOf(DarkButton.class, button);
        assertInstanceOf(DarkCheckbox.class, checkbox);
        assertInstanceOf(DarkTextField.class, textField);
    }

    @Test
    void factoryConsistency() {
        // 同一个工厂创建的产品应该属于同一个主题族
        UIFactory lightFactory = new LightThemeFactory();
        UIFactory darkFactory = new DarkThemeFactory();

        // 浅色工厂应该创建浅色产品
        assertInstanceOf(LightButton.class, lightFactory.createButton());
        assertInstanceOf(LightCheckbox.class, lightFactory.createCheckbox());
        assertInstanceOf(LightTextField.class, lightFactory.createTextField());

        // 深色工厂应该创建深色产品
        assertInstanceOf(DarkButton.class, darkFactory.createButton());
        assertInstanceOf(DarkCheckbox.class, darkFactory.createCheckbox());
        assertInstanceOf(DarkTextField.class, darkFactory.createTextField());
    }

    @Test
    void clientCodeWorksBothThemes() {
        // 客户端代码通过接口工作，不关心具体主题
        UIFactory[] factories = {new LightThemeFactory(), new DarkThemeFactory()};

        for (UIFactory factory : factories) {
            Button button = factory.createButton();
            Checkbox checkbox = factory.createCheckbox();
            TextField textField = factory.createTextField();

            assertNotNull(button);
            assertNotNull(checkbox);
            assertNotNull(textField);
        }
    }
}
