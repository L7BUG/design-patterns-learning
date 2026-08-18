package com.l7bug.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 抽象工厂模式演示
 *
 * 场景：跨平台 UI 主题系统
 * - 浅色主题工厂创建白色按钮、白色复选框、白色文本框
 * - 深色主题工厂创建黑色按钮、黑色复选框、黑色文本框
 *
 * 客户端只需要通过工厂接口创建对象，
 * 不需要知道具体是哪个主题的实现
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("=== 抽象工厂模式演示 ===\n");

        // 1. 浅色主题
        log.info("1. 浅色主题");
        createUI(new LightThemeFactory());

        // 2. 深色主题
        log.info("\n2. 深色主题");
        createUI(new DarkThemeFactory());

        // 3. 动态切换主题
        log.info("\n3. 动态切换主题演示");
        UIFactory factory = new LightThemeFactory();
        createUI(factory);

        factory = new DarkThemeFactory();
        createUI(factory);

        log.info("\n=== 抽象工厂模式演示完成 ===");
    }

    /**
     * 客户端代码：只依赖 UIFactory 接口
     * 不关心具体是浅色还是深色主题
     */
    private static void createUI(UIFactory factory) {
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();
        TextField textField = factory.createTextField();

        button.render();
        button.onClick(() -> System.out.println("    → 按钮点击回调执行"));
        checkbox.render();
        checkbox.toggle();
        textField.render();
        textField.type("Hello Abstract Factory!");
    }
}
