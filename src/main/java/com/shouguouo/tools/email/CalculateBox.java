package com.shouguouo.tools.email;

/**
 * 功能说明:
 *
 * @author shouguouo~
 */
public class CalculateBox {

    private static final Box container;

    static {
        container = new Box(12.032, 2.352, 2.385);
        //container = new Box(1.08, 1.12, 2.385);
    }
    private static int maxNumberOfBox(Box box, Box salver) {
        if (box == null || box.getHeight() > container.getHeight() ||
            salver.getHeight() > container.getHeight() ||
            (box.getLength() > container.getLength() && box.getLength() > container.getWidth()) ||
            (box.getWidth() > container.getLength() && box.getWidth() > container.getWidth()) ||
            (salver.getLength() > container.getLength() && salver.getLength() > container.getWidth()) ||
            (salver.getWidth() > container.getLength() && salver.getWidth() > container.getWidth())) {
            return 0;
        }
        int salverCount = count(container, salver);
        int multiple = (int) ((container.getHeight() - salver.getHeight()) / box.getHeight());
        int boxCount = count(salver, box);
        return salverCount * multiple * boxCount;
    }
    private static int count(Box container, Box box) {
        int type1 = (int)(container.getLength() / box.getLength()) * (int) (container.getWidth() / box.getWidth());
        int type2 = (int)(container.getLength() / box.getWidth()) * (int) (container.getWidth() / box.getLength());
        return type1 > type2 ? type1 : type2;
    }
    public static void main(String[] args) {
        //System.out.println(maxNumberOfBox(new Box(1.08, 1.12, 2.385)));
        System.out.println(maxNumberOfBox(new Box(0.52, 0.36, 0.49),
            new Box(1.08, 1.12, 0.1)));
    }

}

class Box {
    private double length;

    private double width;

    private double height;

    public Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public Box() {
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}