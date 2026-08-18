package com.l7bug.abstractfactory;

/**
 * 具体产品 - 浅色主题按钮
 */
public class LightButton implements Button {

    @Override
    public void render() {
        System.out.println("  [浅色按钮] 渲染白色按钮");
    }

    @Override
    public void onClick(Runnable action) {
        System.out.println("  [浅色按钮] 点击事件触发");
        action.run();
    }
}
