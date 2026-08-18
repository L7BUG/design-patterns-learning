package com.l7bug.prototype;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 原型模式单元测试
 */
class PrototypeTest {

    @BeforeAll
    static void setUp() {
        ShapeCache.loadCache();
    }

    @Test
    void cloneCircle() {
        Shape original = ShapeCache.getShape("RED_CIRCLE");
        Shape cloned = original.clone();

        assertNotNull(cloned);
        assertNotSame(original, cloned);
        assertInstanceOf(Circle.class, cloned);
        assertEquals(original.getType(), cloned.getType());
        assertEquals(original.getArea(), cloned.getArea());
    }

    @Test
    void cloneRectangle() {
        Shape original = ShapeCache.getShape("GREEN_RECT");
        Shape cloned = original.clone();

        assertNotNull(cloned);
        assertNotSame(original, cloned);
        assertInstanceOf(Rectangle.class, cloned);
        assertEquals(original.getType(), cloned.getType());
        assertEquals(original.getArea(), cloned.getArea());
    }

    @Test
    void clonedObjectIsIndependent() {
        Shape original = ShapeCache.getShape("RED_CIRCLE");
        Shape cloned = original.clone();

        // 修改克隆对象
        if (cloned instanceof Circle clonedCircle) {
            clonedCircle.setRadius(100);
        }

        // 验证原始对象未受影响
        assertNotEquals(original.getArea(), cloned.getArea());
    }

    @Test
    void multipleClonesAreIndependent() {
        Shape shape1 = ShapeCache.getShape("BLUE_CIRCLE");
        Shape shape2 = shape1.clone();
        Shape shape3 = shape1.clone();

        // 修改 shape2
        if (shape2 instanceof Circle circle) {
            circle.setRadius(50);
        }

        // 验证 shape1 和 shape3 未受影响
        assertNotEquals(shape1.getArea(), shape2.getArea());
        assertEquals(shape1.getArea(), shape3.getArea());
    }

    @Test
    void invalidKeyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ShapeCache.getShape("NONEXISTENT");
        });
    }
}
