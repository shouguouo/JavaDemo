package com.shouguouo.algorithms;

/**
 * 基数排序
 * @author swj
 * @date 2018/2/10
 */
public class RadixSort {

    /**
     * 直接排序分解成几次排序 分趟排序
     * 有n个数 取值范围:0到n^2 排序 要求时间复杂度和空间复杂度为O(n)
     * 这里的思路是这样的：
     * 假如 n = 10，那么对0~99之间的数字进行排序。例如 11, 21, 23, 31, 63, 32, 12 吧。
     * 我们先通过getRadix取出这些数字的个位上的数，然后根据个位数进行计数排序，一趟计数排序以后，
     * 数组就变成了11, 21, 31, 32, 12, 23, 63，现在的数组已经根据个位有序了。
     * 再根据十位数做一趟排序，就变成了11, 12, 21, 23, 31, 32, 63。
     * @param a
     */
    public void sort(int[] a, int n){
        int[] c = new int[n];
        int[] remainder = new int[a.length];
        int[] b = new int[a.length];

        for (int i = 0; i < 2; i ++){
            for (int j = 0; j < a.length; j ++){
                int temp = getRadix(a[j], i, n);
                remainder[j] = temp;
                c[temp] ++;
            }

            for (int k = 1; k < n; k ++){
                c[k] += c[k - 1];
            }

            for (int j = a.length - 1; j >= 0; j ++){
                b[--c[remainder[j]]] = a[j];
            }

            for (int j = 0; j < n; j ++){
                c[j] = 0;
            }

            for (int j = 0; j < a.length; j ++){
                a[j] = b[j];
            }
        }
    }

    /**
     *  取一个数的第i + 1位
     * @param a
     * @param i
     * @param radix
     * @return
     */
    private int getRadix(int a, int i, int radix){
        return ((int)(a / Math.pow(radix, i))) % radix;
    }

    public static void main(String[] args) {
        System.out.println(new RadixSort().getRadix(25,1,2));
    }
}
