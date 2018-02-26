package com.swj.design;

/**
 * 桥接模式（Bridge）是用于把抽象化与实现化解耦，使得二者可以独立变化
 * 属于结构型模式 通过提供抽象化和实现化之间的桥接结构 来实现二者的解耦
 * 涉及到一个作为桥接的接口，使得实体类的功能独立于接口实现类。这两种类型的类可被结构化改变而互不影响。
 * @author swj
 * @date 2018/2/26
 */
public class Bridge {

    public static void main(String[] args) {
        ShapeB redCircle = new CircleB(100, 100, 10, new RedCircle());
        ShapeB greenCircle = new CircleB(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}

interface DrawApi{
    void drawCircle(int radius, int x, int y);
}

/**
 * 创建实现了DrawApi接口的实体桥接实现类
 */
class RedCircle implements DrawApi{
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}

class GreenCircle implements DrawApi {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}

/**
 * 用DrawApi接口创建抽象类ShapeB
 */
abstract class ShapeB {
    protected DrawApi drawApi;
    protected ShapeB(DrawApi drawApi){
        this.drawApi = drawApi;
    }
    public abstract void draw();
}

class CircleB extends ShapeB{
    private int x, y, radius;
    protected CircleB(int x, int y, int radius,DrawApi drawApi) {
        super(drawApi);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawApi.drawCircle(radius,x,y);
    }
}