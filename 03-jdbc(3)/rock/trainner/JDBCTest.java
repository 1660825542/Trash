package com.rock.trainner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author :老张
 * @version :1.0
 * @Description :
 * @Date :2019-03-05 17:07:54
 */
public class JDBCTest {
    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        Connection conn = DriverManager.getConnection(url, "scott", "tiger");
        String sql = "select * from dept";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.print(rs.getString(1));
            System.out.print(rs.getString(2));
            System.out.println(rs.getString(3));
        }
        System.out.println("endding.............");
    }
}
