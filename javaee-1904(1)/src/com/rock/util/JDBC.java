package com.rock.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author :老张
 * @version :1.0
 * @Description :
 * @Date :2019-03-20 14:43:50
 */
public class JDBC {
    public static Connection getConnection(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Properties init=new Properties();
            try {
                init.load(JDBC.class.getResourceAsStream("/init.properties"));
                try {
                    return DriverManager.getConnection(init.getProperty("oracle.url"),init.getProperty("oracle.user"),init.getProperty("oracle.password"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
