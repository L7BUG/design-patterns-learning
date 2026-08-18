package com.l7bug.prototype;

/**
 * 具体原型 - 矩形
 */
public class Rectangle implements Shape {

    private String color;
    private int width;
    private int height;

    public Rectangle(String color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    /**
     * 拷贝构造函数 - 用于深拷贝
     * @param source 源对象
     */
    public Rectangle(Rectangle source) {
        this.color = source.color;
        this.width = source.width;
        this.height = source.height;
    }

    @Override
    public Shape clone() {
        return new Rectangle(this);
    }

    @Override
    public String getType() {
        return "RECTANGLE";
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public void display() {
        System.out.printf("  矩形: 颜色=%s, 宽=%d, 高=%d, 面积=%.2f%n",
                color, width, height, getArea());
    }

    // Getters and Setters
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
}
