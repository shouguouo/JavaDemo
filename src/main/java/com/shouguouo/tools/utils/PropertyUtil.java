package com.shouguouo.tools.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author shouguouo~
 * @date 2020/6/23 - 11:29
 */
public class PropertyUtil {

    public static void main(String[] args) throws IOException {
        InputStream stream = PropertyUtil.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        properties.load(stream);
        for (String name:
             properties.stringPropertyNames()) {
            System.out.println(name + "：" + properties.getProperty(name));
        }
    }
}
