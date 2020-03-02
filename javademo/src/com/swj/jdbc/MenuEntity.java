package com.swj.jdbc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 功能说明:
 *
 * @author wink~
 */
public class MenuEntity {

    public static final String TPF = "[YYYYMMDD]";
    public static void main(String[] args) {
        String now = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("/home/[YYYYMMDD]/[yyyymmdd]"
            .replace(TPF, now)
            .replace(TPF.toLowerCase(), now));
    }
}
