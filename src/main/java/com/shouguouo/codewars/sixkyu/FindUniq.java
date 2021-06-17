package com.shouguouo.codewars.sixkyu;

/**
 * 功能说明:
 *
 * @author shouguouo~
 * @date 2020/3/2 - 10:07
 */
public class FindUniq {
    public static double findUniq(double arr[]) {
        // Do the magic
        boolean flag;
        for(int i = 0; i < arr.length; i++) {
            flag = true;
            for(int j = 0; j < arr.length; j++){
                if(i != j && arr[i] == arr[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return arr[i];
            }
        }
        return arr[0];
    }
}
