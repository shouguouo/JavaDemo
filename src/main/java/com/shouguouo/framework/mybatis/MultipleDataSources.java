package com.shouguouo.framework.mybatis;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * 功能说明:
 *
 * @author shouguouo~
 */
public class MultipleDataSources {
  public static void main(String[] args) {
    try {
      Class.forName("oracle.jdbc.OracleDriver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    /*
    String hostname = "127.0.0.1";
    String port = "1521";
    String databaseName = "orcl";
    String url = "jdbc:oracle:thin:@" + hostname + ":" + port + "/" + databaseName;
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(url, "drs", "drs");
      String sql = "select schema from tds_interfaces";
      PreparedStatement statement = connection.prepareStatement(sql);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getString("schema"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }*/

    Enumeration<Driver> drivers = DriverManager.getDrivers();
    while (drivers.hasMoreElements()) {
      Driver d = drivers.nextElement();
      System.out.println(d.getClass().getName());
    }
    ArrayList<String> list= new ArrayList<>();
  }
}
