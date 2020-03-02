package com.swj.jdbc;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import javax.management.MBeanInfo;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Enumeration;

/**
 * @author swj
 * @date 2018/3/14
 */
public class MysqlUtil {
    /* static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
    @CallerSensitive
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/wink~?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=true", "root", "swjxhy666");
            System.out.println(connection.getMetaData().toString());
            String sql = "select sysdate() as currentTime from dual";
            ps = connection.prepareStatement(sql);
            //ps.setString(1, "1");
            rs = ps.executeQuery();
            while (rs.next()) {
                String str = rs.getString("currentTime");
                System.out.println(str);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        Enumeration<Driver> sb = DriverManager.getDrivers();
        while (sb.hasMoreElements()) {
            System.out.println(sb.nextElement().getClass().getName());
        }

        System.out.println(MysqlUtil.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
       // System.out.println(nothing());
    }

    @CallerSensitive
    public static Class<?> nothing() {
        return Reflection.getCallerClass();
    }
}
