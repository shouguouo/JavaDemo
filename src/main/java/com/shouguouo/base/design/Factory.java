package com.shouguouo.base.design;

/**
 * 工厂模式(Factory Pattern) 属于创建型模式 提供了一种创建对象的最佳方式
 * 抽象工程模式(Abstract Factory Pattern)围绕一个超级工厂(其他工厂的工厂)创建其他工厂。
 * @author swj
 * @date 2018/2/25
 */

interface Shape{
    /**
     * 画
     */
    void draw();
}
interface Color{
    /**
     * 填充
     */
    void fill();
}
class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

class Red implements Color{
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}

class Green implements Color{
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}

class Blue implements Color{
    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}

abstract class AbstractFactory {
    /**
     * 颜色
     * @param color
     * @return
     */
    abstract Color getColor(String color);

    /**
     * 形状
     * @param shape
     * @return
     */
    abstract Shape getShape(String shape);
}

class ShapeFactory extends AbstractFactory{
    @Override
    Color getColor(String color) {
        return null;
    }

    @Override
    Shape getShape(String shapeType) {
        if (shapeType == null){
            return null;
        }
        if ("CIRCLE".equalsIgnoreCase(shapeType)){
            return new Circle();
        }else if ("SQUARE".equalsIgnoreCase(shapeType)){
            return new Square();
        }else if ("RECTANGLE".equalsIgnoreCase(shapeType)){
            return new Rectangle();
        }
        return null;
    }
}

class ColorFactory extends AbstractFactory{
    @Override
    Color getColor(String colorType) {
        if (colorType == null){
            return null;
        }
        if ("RED".equalsIgnoreCase(colorType)){
            return new Red();
        }else if ("GREEN".equalsIgnoreCase(colorType)){
            return new Green();
        }else if ("BLUE".equalsIgnoreCase(colorType)){
            return new Blue();
        }
        return null;
    }

    @Override
    Shape getShape(String shape) {
        return null;
    }
}

class FactoryProducer{
    public static AbstractFactory getFactory(String choice){
        if ("SHAPE".equalsIgnoreCase(choice)){
            return new ShapeFactory();
        } else if ("COLOR".equalsIgnoreCase(choice)){
            return new ColorFactory();
        }
        return null;
    }
}
public class Factory {

    public static void main(String[] args) {
        AbstractFactory shapeFactory = FactoryProducer.getFactory("shape");
        Shape rectangle = shapeFactory.getShape("rectangle");
        rectangle.draw();

        AbstractFactory colorFactory = FactoryProducer.getFactory("color");
        Color red = colorFactory.getColor("red");
        red.fill();
    }
}
