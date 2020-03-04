package com.swj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author swj
 * @date 2018/3/14
 */
public class OracleUtil {
    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        long start = System.currentTimeMillis();
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:orcl", "drs_user", "drs_user");
            new GenerateSql(connection).build();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                connection.close();
            }
        }
        System.out.println((System.currentTimeMillis() - start) / 1000.0 + "秒");
    }

}
