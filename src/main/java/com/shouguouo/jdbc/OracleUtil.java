package com.shouguouo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@10.20.27.198:1521:orcl", "drs", "DRS");

            connection = DriverManager.getConnection("jdbc:oracle:thin:@10.20.39.219:1521:orcl", "xbrl", "xbrl#123");
            new GenerateSql(connection).build();
            /*statement = connection.prepareStatement("select round(sum(t.fund_networth)/1000000000000,2) from tb_fundbalance t");
            resultSet  = statement.executeQuery();
            Object value = null;
            while (resultSet.next()) {
                value = resultSet.getObject(1);
            }
            if (value instanceof Number) {
                DecimalFormat decimalFormat = new DecimalFormat();
                decimalFormat.setGroupingUsed(false);


                // 根据需要保留小数位数 不足位数时 补0
                decimalFormat.setMaximumFractionDigits(Integer.parseInt("3"));
                decimalFormat.setMinimumFractionDigits(Integer.parseInt("3"));
                decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                System.out.println(decimalFormat.format(value));
            }*/
            //
/*            connection = DriverManager.getConnection("jdbc:oracle:thin:@10.20.39.219:1521:orcl", "drs", "drskf");
            statement = connection.prepareStatement("select a.fund_id,\n" +
                "       a.order_number,\n" +
                "       a.bdate,\n" +
                "       a.edate,\n" +
                "       b.benchmark_name,\n" +
                "       b.cgr_algorithm,\n" +
                "       b.date_type,\n" +
                "       b.inner_id as benchmark_inner_id\n" +
                "  from tbp_fund_benchmark a, tbn_i_benchmark b\n" +
                " where a.tbenchmark_inner_id = b.inner_id\n" +
                "   and a.fund_id = '1002'\n");
            resultSet = statement.executeQuery();

            System.out.println(TestDataUtil.transferToJson(resultSet));*/


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
