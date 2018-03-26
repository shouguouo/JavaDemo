package com.swj.jdbc;

import java.sql.*;
import java.util.ArrayList;

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
        ArrayList<BookEntity> bookEntities = new ArrayList<>();
        Long start = System.nanoTime();
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "swj", "19961226");
            String sql = "select * from book";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                bookEntities.add(new BookEntity(rs.getLong("bsm"), rs.getLong("book_id"),rs.getString("book_name")
                        ,rs.getString("book_author"),rs.getString("book_pub"),rs.getString("book_price")
                        ,rs.getString("book_sort"),rs.getString("book_status"),rs.getDate("book_record")
                        ,rs.getLong("book_times"),rs.getString("book_publication_date")));
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
        System.out.println(System.nanoTime() - start);
    }
}
