package com.l7bug.decorator;

/**
 * 抽象构件 - 咖啡
 *
 * 定义了咖啡的基本行为
 */
public interface Coffee {

    /**
     * 获取咖啡描述
     * @return 描述
     */
    String getDescription();

    /**
     * 获取咖啡价格
     * @return 价格
     */
    double getCost();
}
