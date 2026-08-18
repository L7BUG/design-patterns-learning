package com.l7bug.prototype;

/**
 * 原型接口 - 可克隆的对象
 *
 * 定义了克隆方法，所有具体原型都必须实现
 */
public interface Shape extends Cloneable {

    /**
     * 克隆当前对象
     * @return 克隆后的新对象
     */
    Shape clone();

    /**
     * 获取形状类型
     * @return 类型名称
     */
    String getType();

    /**
     * 获取面积
     * @return 面积值
     */
    double getArea();

    /**
     * 显示信息
     */
    void display();
}
