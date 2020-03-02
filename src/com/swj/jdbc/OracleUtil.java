package com.swj.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "drs_user", "drs_user");
            /*String sql = "select * from book";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                bookEntities.add(new BookEntity(rs.getLong("bsm"), rs.getLong("book_id"),rs.getString("book_name")
                        ,rs.getString("book_author"),rs.getString("book_pub"),rs.getString("book_price")
                        ,rs.getString("book_sort"),rs.getString("book_status"),rs.getDate("book_record")
                        ,rs.getLong("book_times"),rs.getString("book_publication_date")));
            }*/
            System.out.println(getTableInsertSql(connection, "TSYS_MENU", ""));
        } catch (Exception e) {
            e.printStackTrace();
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
    
    public static List<Object> getTableInsertSql(Connection conn, String tableName , String where)  
            throws Exception {  
        ResultSet rs = null;  
        Statement statement = null;  
        List<Object> list=null;  
        try {  
            DatabaseMetaData metadata = conn.getMetaData();  
            rs = metadata.getColumns(null, null, tableName, "%");    //得到表的字段列表  
  
            String sql = "select 'insert into " + tableName + " values ( '";  
            int count=0;  
            int  counts=0;  
            //获得列的总数  
            while (rs.next()) {  
                count++;  
  
            }  
            //重新获得列数据 整理成sql  
                rs = metadata.getColumns(null, null, tableName, "%");    //得到表的字段列表  
                while (rs.next()) {  
                    counts++;  
                    if(counts<=count)  
                    {  
                        Object colName = rs.getObject("column_name");  
                        sql += " ||'''' ||" + colName + "|| ''','";  
                    }  
                }  
                sql=sql.substring(0,sql.length()-2)+"'";  
                sql += " || ' );' from " + tableName+ where;  
                rs.close();  
   
              // System.out.println("DEBUG: SQL="+sql);  
                //执行  
                statement = conn.createStatement();  
                rs = statement.executeQuery(sql);  
                
                //将SQL语句放到List中  
                list=new ArrayList<Object>();  
                while (rs.next())  
                    list.add(rs.getObject(1));  
                rs.close();  
          
                //System.out.println(list.size());  
            } finally {  
                if (rs != null)  
                    rs.close();  
                if (statement != null)  
                    statement.close();  
            }  
            return list;  
    }  
    
}
