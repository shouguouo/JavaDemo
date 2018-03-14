package com.swj.jdbc;

import java.sql.*;

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
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.5.200:1521:kfhzbdc", "bdcdj", "bdcdj");
            String sql = "select * from h where bdcdyh = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "330102001001GB00009F00040001");
            rs = ps.executeQuery();
            while (rs.next()){
                String str = rs.getString("bsm");
                System.out.println(str);
            }
        } finally {
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }
}
