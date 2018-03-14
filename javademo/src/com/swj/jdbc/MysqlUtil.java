package com.swj.jdbc;

import java.sql.*;

/**
 * @author swj
 * @date 2018/3/14
 */
public class MysqlUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "swj19961226");
            String sql = "select * from book where bookid=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "2");
            rs = ps.executeQuery();
            while (rs.next()) {
                String str = rs.getString("bookName");
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
    }
}
