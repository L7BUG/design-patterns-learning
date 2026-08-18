package com.l7bug.abstractfactory;

/**
 * 抽象产品 - 按钮
 */
public interface Button {
    void render();
    void onClick(Runnable action);
}
