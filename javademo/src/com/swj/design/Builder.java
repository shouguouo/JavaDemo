package com.swj.design;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式（Builder Pattern）：使用多个简单的对象一步一步构建成一个复杂的对象
 * 属于创建型模式 提供了一种创建对象的最佳方式
 * @author swj
 * @date 2018/2/26
 */
public class Builder {

    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total cost: " + vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("Non-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " +nonVegMeal.getCost());
    }

}

/**
 * 创建一个表示食物条目和食物包装的接口
 */
interface Item{
    public String name();
    public Packing packing();
    public float price();
}

interface  Packing{
    public String pack();
}

class Wrapper implements Packing{
    @Override
    public String pack() {
        return "Wrapper";
    }
}

class Bottle implements Packing{
    @Override
    public String pack() {
        return "Bottle";
    }
}

abstract class Burger implements Item{
    @Override
    public Packing packing() {
        return new Wrapper();
    }
}

abstract class ColdDrink implements Item{
    @Override
    public Packing packing() {
        return new Bottle();
    }
}

class VegBurger extends Burger{
    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}

class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 50.5f;
    }
}

class Coke extends ColdDrink{
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}

class Pepsi extends ColdDrink{
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 35.0f;
    }
}

class Meal{
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public float getCost(){
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void showItems(){
        for (Item item : items) {
            System.out.print("Item : "+item.name());
            System.out.print(", Packing : "+item.packing().pack());
            System.out.println(", Price : "+item.price());
        }
    }
}


class MealBuilder{

    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}