package com.stolser.javatraining.designpatterns.creational.prototype;

import com.stolser.javatraining.designpatterns.creational.prototype.entity.Circle;
import com.stolser.javatraining.designpatterns.creational.prototype.entity.Rectangle;
import com.stolser.javatraining.designpatterns.creational.prototype.entity.Shape;
import com.stolser.javatraining.designpatterns.creational.prototype.entity.Triangle;

import java.util.HashMap;
import java.util.Map;

import static com.stolser.javatraining.designpatterns.creational.prototype.ShapeFactory.ShapeType.*;

/**
 * There are three slightly different ways to instantiate prototypes:<br />
 * <ul>
 *     <li>eager initialization during class loading time inside static initialization block.
 *     All prototype objects will be created immediately after the first use of this class;</li>
 *     <li>eager initialization using a static method. All prototype objects will be created
 *     when a user calls a special static method {@code loadRegistry()};</li>
 *     <li>lazy initialization inside {@link ShapeFactory#getInstance(ShapeType)}. A
 *     prototype object for each next type will be created only during the first request
 *     for this specific type.</li>
 * </ul>
 */
public class ShapeFactory {
    private static Map<ShapeType, Shape> registry = new HashMap<>();

    static {
        loadRegistry();
    }

    public enum ShapeType {
        CIRCLE, RECTANGLE, TRIANGLE;
    }

    public static void loadRegistry() {
        registry.put(CIRCLE, new Circle("Cached circle"));
        registry.put(RECTANGLE, new Rectangle("Cached rectangle"));
        registry.put(TRIANGLE, new Triangle("Cached triangle"));
    }

    public static Shape getInstance(ShapeType shapeType) throws CloneNotSupportedException {
        Shape prototype = registry.get(shapeType);

        if (prototype == null) {
            switch (shapeType) {
                case CIRCLE:
                    prototype = new Circle("Cached circle");
                    break;
                case RECTANGLE:
                    prototype = new Rectangle("Cached rectangle");
                    break;
                case TRIANGLE:
                    prototype = new Triangle("Cached triangle");
                    break;
                default:
                    throw new IllegalArgumentException("we should not get here");
            }

            registry.put(shapeType, prototype);
        }

        return (Shape) prototype.clone();
    }


}
