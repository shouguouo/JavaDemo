package com.shouguouo.base.time;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author shouguouo~
 * @date 2020/6/3 - 9:39
 */
public class DateTest {
    public static void main(String[] args) {
        // 不能转换时区 时间加减较麻烦
        Date date = new Date();
        System.out.println(date.getYear() + 1900); // 必须加上1900
        System.out.println(date.getMonth() + 1); // 0~11，必须加上1
        System.out.println(date.getDate()); // 1~31

        System.out.println(date.toString());
        System.out.println(date.toGMTString());
        System.out.println(date.toLocaleString());

        /*
        yyyy：年
        MM：月
        dd: 日
        HH: 小时
        mm: 分钟
        ss: 秒
        M：输出9
        MM：输出09
        MMM：输出Sep
        MMMM：输出September
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));

        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = 1 + c.get(Calendar.MONTH); // 月份仍然要+1
        int d = c.get(Calendar.DAY_OF_MONTH);
        int w = c.get(Calendar.DAY_OF_WEEK);
        int hh = c.get(Calendar.HOUR_OF_DAY);
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);
        int ms = c.get(Calendar.MILLISECOND);
        System.out.println(y + "-" + m + "-" + d + " " + w + " " + hh + ":" + mm + ":" + ss + "." + ms);


        TimeZone tzDefault = TimeZone.getDefault(); // 当前时区
        TimeZone tzGMT9 = TimeZone.getTimeZone("GMT+09:00"); // GMT+9:00时区
        TimeZone tzNY = TimeZone.getTimeZone("America/New_York"); // 纽约时区
        System.out.println(tzDefault.getID()); // GMT+08:00
        System.out.println(tzGMT9.getID()); // GMT+09:00
        System.out.println(tzNY.getID()); // America/New_York
        System.out.println(Arrays.toString(TimeZone.getAvailableIDs()));

        // 转为新API
        // Date -> Instant:
        Instant ins1 = new Date().toInstant();

        // Calendar -> Instant -> ZonedDateTime:
        Calendar calendar = Calendar.getInstance();
        Instant ins2 = Calendar.getInstance().toInstant();
        ZonedDateTime zdt = ins2.atZone(calendar.getTimeZone().toZoneId());

    }

    /**
     * 数据库	对应Java类（旧）	对应Java类（新）
     * DATETIME	java.util.Date	LocalDateTime
     * DATE	java.sql.Date	LocalDate
     * TIME	java.sql.Time	LocalTime
     * TIMESTAMP	java.sql.Timestamp	LocalDateTime
     */
}
