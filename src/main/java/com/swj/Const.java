package com.swj;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Created by wink~ on 2018/9/3.
 */
public class Const {
    public static final String LINUXIP = "127.0.0.1";
    public static final String MIDDLE_LINE = "-";


    public static void main(String[] args) throws Exception {
/*        System.out.println(new BigDecimal("1234567891421423456").setScale(2, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal("1.4").multiply(new BigDecimal("100")).divide(new BigDecimal("3"), 2, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal("1").divide(new BigDecimal(2), 0, RoundingMode.HALF_DOWN));
        System.out.println((int) 'a');
        System.out.println((int) 'A');

        Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher("one cat two cats in the yard");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "dog");
        }
        m.appendTail(sb);
        System.out.println(sb.toString());

        int[] arr = new int[] {-1,-2,-3,0,5,6,-10};
        List<Test> list = new ArrayList<>();
        list.add(new Test(-1,"213"));
        list.add(new Test(-2,"213"));
        list.add(new Test(-3,"213"));
        list.add(new Test(-0,"213"));
        list.add(new Test(5,"213"));
        list.add(new Test(6,"213"));
        list.add(new Test(-10,"213"));
        System.out.println(Stream.concat(list.stream().filter(x -> x.getA() > 0).sorted(Comparator.comparingInt(Test::getA)), list.stream().filter(x -> x.getA() <= 0).sorted(Comparator.comparingInt(Test::getA))).collect(Collectors.toList()));
   *//*
        System.out.println(Integer.valueOf("0001"));
        System.out.println(String.format("%04d", 1));
        List<Test> list = null;
       *//* list.add(new Test(-1,"213"));
        list.add(new Test(-2,"213"));
        list.add(new Test(-3,"213"));
        list.add(new Test(-0,"213"));
        list.add(new Test(5,"213"));
        list.add(new Test(6,"213"));
        list.add(new Test(-10,"213"))*//*;
        Integer s = list.stream().map(x -> x.getA()).max(Integer::compareTo).orElse(0);
        System.out.println(s);*/
        /*Object value = new Double("123412312431323.232312");
        String str = "3";
        if (value instanceof Number) {
            DecimalFormat decimalFormat = new DecimalFormat();
           decimalFormat.setGroupingUsed(false);


                // 根据需要保留小数位数 不足位数时 补0
                decimalFormat.setMaximumFractionDigits(Integer.parseInt(str));
                decimalFormat.setMinimumFractionDigits(Integer.parseInt(str));
                decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            System.out.println(decimalFormat.format(value));
        }*/
/*
        List<LeadAbstract<Integer>> tests = Lists.newArrayList(
            new NextTest(1, 0, "1"),
            new NextTest(2, 0, "2"),
            new NextTest(3, 0, "3"),
            new NextTest(4, 0, "4"),
            new NextTest(5, 0, "5"),
            new NextTest(6, 0, "6"),
            new NextTest(7, 0, "7")
            );
        *//*AtomicInteger forward = new AtomicInteger();
        tests.stream().sorted(Comparator.comparingInt(NextTest::getCurrent).reversed()).forEachOrdered(x -> {
            if (forward.get() != 0) {
                x.setNext(forward.get());
            }
            forward.set(x.getCurrent());
        });*//*
        lead(tests, (current, next) -> next.setNext(current.getCurrent()));
        tests.forEach(System.out::println);
        int[] arr = new int[]{1,2,3,4};
        System.out.println(arr.getClass().isArray());
        // System.out.println(Double.NaN);
        double s = 1.23456;
        NumberFormat decimalFormat = NumberFormat.getInstance();
        decimalFormat.setGroupingUsed(false);
        System.out.println(decimalFormat.format(s));*/
/*        List<Short> result = Lists.newArrayList();
        result.add((short) 1);
        result.add((short) 2);
        result.add((short) 3);
        result.add((short) 10);
        result.add((short) 11);
        result.add((short) 12);
        result.add((short) 13);
        result.add((short) 14);
        result.add((short) 43);
        String sb = result.stream().map(Object::toString).collect(Collectors.joining(","));
        System.out.println(sb);*/
        /* BigDecimal bigDecimal = BigDecimal.ZERO;
//        bigDecimal = bigDecimal.subtract(new BigDecimal("1.2"));
//        bigDecimal = bigDecimal.subtract(new BigDecimal("1.12"));
        bigDecimal = bigDecimal.subtract(new BigDecimal("1.123456"));
        System.out.println(bigDecimal);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        System.out.println(nf.format(20000000));*/
     /*  String maxSegment = "1";
       String now = String.valueOf((char)((int) maxSegment.charAt(0) + 1));
        System.out.println(now);*/


/*        String licenseStr = "<License>\n" +
            "  <Data>\n" +
            "    <Products>\n" +
            "      <Product>Aspose.Total for Java</Product>\n" +
            "      <Product>Aspose.Words for Java</Product>\n" +
            "    </Products>\n" +
            "    <EditionType>Enterprise</EditionType>\n" +
            "    <SubscriptionExpiry>20991231</SubscriptionExpiry>\n" +
            "    <LicenseExpiry>20991231</LicenseExpiry>\n" +
            "    <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n" +
            "  </Data>\n" +
            "  <Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature>\n" +
            "</License>";
        InputStream license = new ByteArrayInputStream(licenseStr.getBytes("UTF-8"));
        License asposeLic = new License();
        asposeLic.setLicense(license);
        Document document = new Document("D:\\CustomToc.docx");
        document.updateFields();
        document.save("E:\\CustomToc.docx");*/

       /* for (int year = 2020; year <= 2100; year++) {
            LocalDate time = LocalDate.of(year, Month.JUNE, 30);
            if (testWeekend().test(time)) {
                System.out.println(time.toString());
            }
            time = LocalDate.of(year, Month.DECEMBER, 31);
            if (testWeekend().test(time)) {
                System.out.println(time.toString());
            }

        }*/
//        System.out.println(StringUtils.blankToLowerCaseMiddleLine("Replace Parameter With Method"));

/*        System.out.println(containEndOfHalfYear(20201230, 20201230,20201231));
        System.out.println(containEndOfHalfYear(20201230, 20201210,20210101));
        System.out.println(containEndOfHalfYear(20211230, 20211210,20201211));
        System.out.println(containEndOfHalfYear(20201231, 20201230,20201231));*/
    /*    System.out.println(notQuarterStart(20200101));
        System.out.println(notQuarterStart(20201101));
        System.out.println(notQuarterStart(20200401));
        System.out.println(notQuarterStart(20200701));
        System.out.println(notQuarterStart(20201001));
        System.out.println(notQuarterStart(20201201));*/
        /*Optional<String> sb = Optional.ofNullable(null);
        System.out.println(sb.map(String::isEmpty).orElse(true));
        System.out.println(sb.map(String::length).orElse(1));

        System.out.println(new BigDecimal("0").equals(BigDecimal.ZERO));
        System.out.println(new BigDecimal("1").divide(new BigDecimal("2"), 10,RoundingMode.HALF_UP));*/
       /* templateMap.putIfAbsent("1", "23");
        templateMap.put("1", "34");
        templateMap.put("1", "45");
        System.out.println(templateMap.get("1"));*/

        System.out.println(new BigDecimal("0").signum());
        System.out.println(new BigDecimal("1").signum());
        System.out.println(new BigDecimal("-1").signum());
        System.out.println(new BigDecimal("0.000001").signum());
        System.out.println(new BigDecimal("-152341.21").signum());

        System.out.println("123456789".substring(0, 6));
        System.out.println(endOfMonth(20200401));
        System.out.println(endOfMonth(20200301));
        System.out.println(endOfMonth(20200201));
        System.out.println(endOfMonth(20200525));
        List<BigDecimal> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            sb = sb.add(new BigDecimal(i));
            list.add(sb);
        }

        System.out.println(list);

        System.out.println(BigDecimal.ZERO.divide(new BigDecimal("0.0001"), 1));
        List<Integer> listslist = Lists.newArrayList();
        listslist.add(2);
        listslist.add(1);
        listslist.add(0);
        listslist.add(-1);

        System.out.println(listslist.stream().sorted().collect(Collectors.toList()));

        BigDecimal hi = new BigDecimal("1.123456");
        System.out.println(hi.divide(new BigDecimal(10000), hi.scale() + 4, RoundingMode.HALF_UP));
        System.out.println(hi.round(new MathContext(5, RoundingMode.HALF_UP)));
        System.out.println(hi.setScale(5, RoundingMode.HALF_UP));

        Range<Integer> range = Range.closedOpen(20160512,20160631);
        Range<Integer> range2 = Range.closed(20160101,20170101);
        System.out.println(range.intersection(range2).lowerEndpoint());
        System.out.println(range.intersection(range2).upperEndpoint());


        if ("jdbc:mysql://192.168.223.20:3306/shouwj?user=root&password=swjxhy666&useUnicode=true&characterEncoding=UTF8&serverTimezone=Asia/Shanghai&useSSL=false".equals("jdbc:mysql://192.168.223.20:3306/shouwj?user=root&password=xhyswj666&useUnicode=true&characterEncoding=UTF8&serverTimezone=Asia/Shanghai&useSSL=false")) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

        Range<Integer> range11 = Range.openClosed(1,10);
       System.out.println(range11.lowerEndpoint());
        System.out.println(range11.upperEndpoint());
        System.out.println("---------");
        for(int i =12; i<=50; i++) {
            if (i%10 == 0) {
                continue;
            }
            if (i%(i/10) == 0 && i%(i%10) ==0) {
            }
        }
        System.out.println(tableSizeFor(100));
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
        System.out.println(~10); // 取反
/*
        System.out.println(Integer.valueOf("11111111111111111111111111110101", 2));
*/

        System.out.println(toTen("11110101"));

        Stream.of(1,2,3,4,5,6,7,8).filter( x -> {
            System.out.println("是");
            return true;
        }).forEach(System.out::println);

        List<String> lst = Arrays.asList("aaa", "bbb", "ccc", "awdas");
        lst.stream().flatMap(Const::toCharacterStream).forEach(System.out::println);
        byte[] arr = "四大sad".getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(arr));
        String arrStr = new String(arr, StandardCharsets.UTF_8);
        System.out.println(arrStr);
        byte[] afterConvert = arrStr.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(afterConvert));


        List<Integer> newInt = Lists.newArrayList(3,2,1);
        List<Integer> ordered = newInt.stream().sorted().collect(Collectors.toList());
        ordered.forEach(System.out::println);
        List<BigDecimal> newBigDecimal = Lists.newArrayList(null, BigDecimal.ONE, null);
        BigDecimal a = newBigDecimal.stream().reduce(BigDecimal.ZERO, (x, y) -> x.add(defaultCalResultWithoutScale(y)));
        System.out.println(a);

        System.out.println("------------------");
        String privateFundType = "1,3,5,9";
        List<String> privateFundTypeList = new ArrayList<>();
        if (StringUtils.isNotBlank(privateFundType)) {
            privateFundTypeList = Lists.newArrayList(Splitter.on(",").split(privateFundType));
        }
        privateFundTypeList.forEach(System.out::println);

        BigDecimal one = BigDecimal.valueOf(1000);
        BigDecimal three = BigDecimal.valueOf(1);
        System.out.println(one.divide(three, 9, RoundingMode.HALF_UP).stripTrailingZeros());

    }


    private static BigDecimal defaultCalResultWithoutScale(BigDecimal cal) {
        return Optional.ofNullable(cal).orElse(BigDecimal.ZERO);
    }
    public static Stream<Character> toCharacterStream(String str) {
        List<Character> list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    public static int toTen(String str){
        char[] chars = str.toCharArray();
        boolean negative = false;
        if (chars[0] == '1') {
            negative = true;
        }
        int res = 0;
        int flag = 1;
        for (int i = chars.length - 1; i > 0; i--) {
            if (chars[i] == '1') {
                res += flag;
            }
            flag *= 2;
        }
        return negative ? -res : res;
    }

    public static final int MAXIMUM_CAPACITY = 1 << 30;
    static  int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
    public static BigDecimal sb = BigDecimal.ZERO;


    public static int endOfMonth(int date) {
        return Integer.parseInt(
            LocalDate.parse(String.valueOf(date), DateTimeFormatter.BASIC_ISO_DATE)
                .with(TemporalAdjusters.lastDayOfMonth())
                .format(DateTimeFormatter.BASIC_ISO_DATE));
    }
    protected static ConcurrentMap<String, String> templateMap = new ConcurrentHashMap<>();


    private static boolean notQuarterStart(int date) {
        switch (("" + date).substring(4)){
            case "0101":
            case "0401":
            case "0701":
            case "1001": return false;
            default: return true;
        }
    }

    /**
     * 判断日期是否年末半年末
     *
     * @param date 日期
     * @return 年末半年末为true 否则为false
     */
    private static boolean isEndOfHalfYear(int date) {
        return date % 10000 == 630 || date % 10000 == 1231;
    }

    /**
     * 判断节假日是否处于包含年末半年末的节假日
     * @param date
     * @param first
     * @param last
     * @return
     */
    private static boolean containEndOfHalfYear(int date, int first, int last) {
        for (int cur = first; cur <= last; cur++) {
            if (isEndOfHalfYear(cur) && date >= first && date <= last) {
                return true;
            }
        }
        return false;
    }
    public static Predicate<LocalDate> testWeekend() {
        return (time) -> time.getDayOfWeek().equals(DayOfWeek.SATURDAY) || time.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    @FunctionalInterface
    public interface Consumer<T> {
        void accept(LeadAbstract<T> t1, LeadAbstract<T> t2);
    }
    public static <T> void lead(Collection<LeadAbstract<T>> collection, Consumer<T> consumer) {
        Iterator<LeadAbstract<T>> iterator = collection.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        LeadAbstract<T> current = iterator.next();

        while (iterator.hasNext()) {
            LeadAbstract<T> next = iterator.next();
            consumer.accept(current, next);
            current = next;
        }
    }

    public static class Test {
        private int a;
        private String name;

        public Test(int a, String name) {
            this.a = a;
            this.name = name;
        }


        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Test{" +
                "a=" + a +
                ", name='" + name + '\'' +
                '}';
        }
    }
    public static class NextTest extends LeadAbstract<Integer> {

        private String name;

        public NextTest() {
        }

        @Override
        public String toString() {
            return "NextTest{" +
                "current=" + current +
                ", next=" + next +
                ", name='" + name + '\'' +
                '}';
        }

        public NextTest(int current, int next, String name) {
            this.current = current;
            this.next = next;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

abstract class LeadAbstract<T> {
    T current;

    T next;

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public T getNext() {
        return next;
    }

    public void setNext(T next) {
        this.next = next;
    }
}
