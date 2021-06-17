package com.shouguouo.generics;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 功能说明:
 *
 * @author shouguouo~
 */
public class Holder<T> {
    private T a;

    public Holder(T a) {
        this.a = a;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Holder<AutoMobile> holder = new Holder<>(new AutoMobile());
        Holder<AnotherMobile> anotherHolder = new Holder<>(new AnotherMobile());
       /* for (int i=20190000; i<=20191301; i++) {
            if (isEndOfHalfYear(i)) {
                System.out.println(i);
            }
        }*/
    /*    LocalDate sb = LocalDate.parse(String.valueOf(20191201), DateTimeFormatter.BASIC_ISO_DATE);

        System.out.println(sb.format(DateTimeFormatter.BASIC_ISO_DATE));*/

        /*int curDate = 20191227;
        int days = -10;
        LocalDate current = LocalDate.parse(String.valueOf(curDate), DateTimeFormatter.BASIC_ISO_DATE);
        int middle = 15 + (int)Math.ceil(1.5 * Math.abs(days));
        current = current.minusDays(middle);
        if (days > 0) {
            System.out.println(middle);
            System.out.println("+");
            System.out.println(current.format(DateTimeFormatter.BASIC_ISO_DATE));
        } else {
            System.out.println(middle);
            System.out.println("-");
            System.out.println(current.format(DateTimeFormatter.BASIC_ISO_DATE));
        }

        byte[] sb = new byte[]{
            33, 1, 3, 0, 0, 0, 0, 4, 0, 0, 0, 1, 0, 0, 0, -69, 0, 0, 0, 0, 112, 97, 114, 97, 109, 115, 0, 82, 127, -1, -1, -2, 0, 111, 112, 101, 114, 97, 116, 111, 114, 95, 99, 111, 100, 101, 0, 83, 0, 0, 0, 6, 0, 105, 112, 0, 83, 0, 0, 0, 16, 0, 117, 115, 101, 114, 95, 116, 111, 107, 101, 110, 0, 83, 0, 0, 0, 38, 0, 0, 0, 0, 63, 33, 1, 3, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 43, 0, 0, 0, 0, 112, 97, 114, 97, 109, 67, 111, 100, 101, 0, 83, 0, 0, 0, 28, 0, 102, 117, 110, 100, 73, 100, 0, 73, 0, 0, 0, 20, 0, 75, 72, 68, 77, 0, 48, 0, 75, 72, 68, 77, 0, 50, 0, 0, 97, 100, 109, 105, 110, 0, 49, 57, 50, 46, 49, 54, 56, 46, 49, 55, 55, 46, 49, 53, 57, 0, 101, 55, 57, 100, 52, 53, 57, 55, 45, 101, 102, 50, 49, 45, 52, 53, 48, 101, 45, 57, 99, 48, 56, 45, 54, 51, 100, 55, 51, 50, 51, 48, 98, 53, 49, 49, 51, 0};

        System.out.println(new String(sb, 20,6,StandardCharsets.UTF_8));

        System.out.println("FB102".split("_")[0]);
        ArrayList<String> list = Lists.newArrayList("1", "2");
        System.out.println(list);
        list = list.stream().filter(x -> x.equals("1")).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list);*/

        System.out.println(new BigDecimal("1").divide(new BigDecimal("3"),10, RoundingMode.HALF_UP).toPlainString());

    }

    private static  boolean isShortEquals(Short a, short b) {
        return a != null && a == b;
    }

    private static boolean isEndOfHalfYear(int date) {
        return date % 10000 == 630 || date % 10000 ==  1231;
    }
}
class AutoMobile {
}

class AnotherMobile {
}