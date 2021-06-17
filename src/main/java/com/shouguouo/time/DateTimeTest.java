package com.shouguouo.time;

/**
 * @author shouguouo~
 * @date 2020/6/3 - 9:49
 */

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * 本地日期和时间：LocalDateTime，LocalDate，LocalTime；
 * 带时区的日期和时间：ZonedDateTime；
 * 时刻：Instant；
 * 时区：ZoneId，ZoneOffset；
 * 时间间隔：Duration。
 * 格式化：DateTimeFormatter 不可变 线程安全
 */
public class DateTimeTest {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.now(); // 当前日期和时间
        LocalDate d = dt.toLocalDate(); // 当前日期
        LocalTime t = dt.toLocalTime(); // 当前时间
        System.out.println(d); // 严格按照ISO 8601格式打印
        System.out.println(t); // 严格按照ISO 8601格式打印
        System.out.println(dt); // 严格按照ISO 8601格式打印

        LocalDate d2 = LocalDate.of(2020,6,3);
        LocalTime t2 = LocalTime.of(9,53,20, 111);
        LocalDateTime dt2 = LocalDateTime.of(d2, t2);
        System.out.println(d2); // 严格按照ISO 8601格式打印
        System.out.println(t2); // 严格按照ISO 8601格式打印
        System.out.println(dt2); // 严格按照ISO 8601格式打印

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSSSSSSSS");
        System.out.println(dtf.format(dt2));
        LocalDateTime dt3 = LocalDateTime.parse("2020/07/03 09:53:20.000000111", dtf);
        System.out.println(dtf.format(dt3));

        // 日期加减
        LocalDateTime dt4 = dt3.plusDays(5).minusHours(2).minusMonths(1).minusYears(2);
        System.out.println(dtf.format(dt4));
        // 日期调整
        LocalDateTime dt5 = dt3.withHour(23).withYear(2001).withSecond(5);
        System.out.println(dtf.format(dt5));

        LocalDate firstWeekday = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
        System.out.println(firstWeekday);

        Duration dr = Duration.between(dt5, dt4);
        System.out.println(dr); //时间间隔
        Period pr = Period.between(d2, d);
        System.out.println(pr); //日期间隔


        // ZonedDateTime理解成LocalDateTime加ZoneId
        ZonedDateTime zbj = ZonedDateTime.now();
        System.out.println(zbj);
        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(zny);
        LocalDateTime ldt = LocalDateTime.of(2019, 9, 15, 15, 16, 17);
        ZonedDateTime zbj2 = ldt.atZone(ZoneId.systemDefault());
        ZonedDateTime zny2 = ldt.atZone(ZoneId.of("America/New_York"));
        System.out.println(zbj2);
        System.out.println(zny2);
        // 涉及到时区时，千万不要自己计算时差，否则难以正确处理夏令时。
        ZonedDateTime zbj3 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        // 转换为纽约时间:
        ZonedDateTime zny3 = zbj.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(zbj3);
        System.out.println(zny3);

        DateTimeFormatter zhFormatter = DateTimeFormatter.ofPattern("yyyy MMM dd EE HH:mm", Locale.CHINA);
        DateTimeFormatter usFormatter = DateTimeFormatter.ofPattern("E, MMMM/dd/yyyy HH:mm", Locale.US);
        System.out.println(zhFormatter.format(dt));
        System.out.println(usFormatter.format(dt));

        // Instant表示高精度时间戳，它可以和ZonedDateTime以及long互相转换
        Instant now = Instant.now();
        System.out.println(now.getEpochSecond()); // 秒
        System.out.println(now.toEpochMilli()); // 毫秒

    }
    /**
     * 注意ISO 8601规定的日期和时间分隔符是T。标准格式如下：
     *
     * 日期：yyyy-MM-dd
     * 时间：HH:mm:ss
     * 带毫秒的时间：HH:mm:ss.SSS
     * 日期和时间：yyyy-MM-dd'T'HH:mm:ss
     * 带毫秒的日期和时间：yyyy-MM-dd'T'HH:mm:ss.SSS
     */

    /**
     * LocalDateTime + ZoneId --> ZoneDateTime
     * Instant + Long --> ZoneDateTime
     */
}
