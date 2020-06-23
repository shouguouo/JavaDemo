package com.swj.mybatis;

import com.google.common.collect.Lists;
import com.swj.utils.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author wink~
 * @date 2020/5/19 - 16:16
 */
public class GenerateResults {
    public static void main(String[] args) {
        generate(DirSettingVO.class).forEach(System.out::println);
    }

    private static final String TEMPLATE = "<result column=\"%s\" property=\"%s\" jdbcType=\"%s\" />";
    public static List<String> generate(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> lists = Lists.newArrayList();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            String underName = StringUtils.humpToLine2(name).toUpperCase();
            if (Number.class.isAssignableFrom(field.getType())) {
                lists.add(String.format(TEMPLATE, underName, name, "DECIMAL"));
            } else {
                lists.add(String.format(TEMPLATE, underName, name, "VARCHAR"));
            }
        }
        return lists;
    }
}
