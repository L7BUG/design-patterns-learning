package com.l7bug.abstractfactory;

/**
 * 具体产品 - 深色主题按钮
 */
public class DarkButton implements Button {

    @Override
    public void render() {
        System.out.println("  [深色按钮] 渲染黑色按钮");
    }

    @Override
    public void onClick(Runnable action) {
        System.out.println("  [深色按钮] 点击事件触发");
        action.run();
    }
}
