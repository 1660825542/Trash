package com.rock.dml;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author :老张
 * @version :1.0
 * @Description :
 * @Date :2019-03-06 14:38:45
 */
public class JDBCUtil {

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
    }
    }

    public static Connection getConnection() {
        try {
            Properties p=new Properties();
            try {
                p.load(JDBCUtil.class.getResourceAsStream("/db.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return DriverManager.getConnection(p.getProperty("url"), "scott", "tiger");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库相关资源对象
     */
    public static void release(ResultSet rs, Statement stmt, Connection conn) {

        try {
            if (rs != null)
                rs.close();
        } catch (Exception ex) {

        }
        try {
            if (stmt != null)
                stmt.close();
        } catch (Exception ex) {

        }
        try {
            if (conn != null)
                conn.close();
        } catch (Exception ex) {

        }


    }

    private Connection getConnnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
