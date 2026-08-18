package com.l7bug.prototype;

/**
 * 具体原型 - 圆形
 */
public class Circle implements Shape {

    private String color;
    private int radius;

    public Circle(String color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    /**
     * 拷贝构造函数 - 用于深拷贝
     * @param source 源对象
     */
    public Circle(Circle source) {
        this.color = source.color;
        this.radius = source.radius;
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }

    @Override
    public String getType() {
        return "CIRCLE";
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void display() {
        System.out.printf("  圆形: 颜色=%s, 半径=%d, 面积=%.2f%n",
                color, radius, getArea());
    }

    // Getters and Setters
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getRadius() { return radius; }
    public void setRadius(int radius) { this.radius = radius; }
}
