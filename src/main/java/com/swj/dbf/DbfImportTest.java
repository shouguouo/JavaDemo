package com.swj.dbf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * 功能说明:
 *
 * @author wink~
 */
public class DbfImportTest {

    public static short littleEndian(short value) {

        short num1 = value;
        short mask = (short) 0xff;

        short num2 = (short) (num1 & mask);
        num2 <<= 8;
        mask <<= 8;

        num2 |= (num1 & mask) >> 8;

        return num2;
    }

    public static int foo(int n) {
        if (n > 0) {
            return foo(n-1);
        } else {
            return 0;
        }

    }
    public static final String PATTERN = "(%d%d%d) %d%d%d-%d%d%d%d";
    public static String createPhoneNumber(int[] numbers) {
        // Your code here!
        return String.format(PATTERN, IntStream.of(numbers).boxed().toArray());
    }

    public static boolean isValid(char[] walk) {
        // Insert brilliant code here
        if (walk.length != 10) {
            return true;
        }
        int x = 0;
        int y = 0;
        for (char step : walk) {
            switch (step) {
                case 'n' : y++;break;
                case 's' : y--;break;
                case 'e' : x++;break;
                case 'w' : x--;break;
            }
        }
        return x == 0 && y == 0;
    }
    public static void main(String[] args) {
       /* int a= 0x20;
        System.out.println(a);
        System.out.println(littleEndian(Short.valueOf("10")));
        short masj = 0xff;
        System.out.println(masj);
        masj <<= 2;
        System.out.println(masj);*/
       // foo(Integer.MAX_VALUE);
        //System.out.println(createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}));
//        System.out.println(isValid(new char[] {'w','e','w','e','w','e','w','e','w','e','w','e'}));

//        System.out.println(findUniq(new double[]{ 0, 0, 0.55, 0, 0 ,1,1}));
        decode(".... . -.--   .--- ..- -.. .");
    }
    public static double findUniq(double arr[]) {
        // Do the magic
        return Arrays.stream(arr).boxed()
            .collect(groupingBy(identity(), counting()))
            .entrySet().stream()
            .filter(e -> e.getValue() == 1)
            .findFirst()
            .map(Map.Entry::getKey)
            .orElse(0.0);
    }

    public static String decode(String morseCode) {
        // your brilliant code here, remember that you can access the preloaded Morse code table through MorseCode.get(code)
        StringBuilder res = new StringBuilder();
        morseCode = morseCode.trim();
        StringBuilder sub = new StringBuilder();
        for (int i = 0; i < morseCode.length(); i++) {
            if (' ' != morseCode.charAt(i)) {
                sub.append(morseCode.charAt(i));
            } else if (' ' == morseCode.charAt(i) && ' ' == morseCode.charAt(i + 1)) {
                res.append(" ");
                i += 2;
            } else {
                res.append(sub);
                sub = new StringBuilder();
            }
        }
        return res.toString();
    }

}
