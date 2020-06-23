package com.swj.algorithms;

/**
 * 汉诺塔问题
 * Created by swj on 2018/1/29.
 */
public class HanoiTower {

    /**
     * 普通递归
     * @param n 盘子数量
     * @return 移动次数
     */
    public static int countMovePlate(int n){
        if (n <= 1) return 1;
        return countMovePlate(n - 1) + 1 + countMovePlate(n - 1);
    }

    /**
     * 查看具体过程
     * @param n 盘子总数
     * @param from
     * @param mid
     * @param to
     */
    public static void movePlate(int n, String from, String mid, String to){
        if (n <= 1) {
            System.out.println(from + " -> " + to);
            return;
        }
        movePlate(n - 1, from, to, mid);
        System.out.println(from + " -> " + to);
        movePlate(n - 1, mid, from, to);
    }

    public static void main(String[] args) {
        System.out.println(countMovePlate(3));
        movePlate(3, "A", "B", "C");
        System.out.println("---------------------");
        Factorial.movePlateMemo(3, "A", "B", "C");
    }
}
