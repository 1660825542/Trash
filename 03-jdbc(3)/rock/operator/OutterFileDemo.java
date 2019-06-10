package com.rock.operator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author :老张
 * @version :1.0
 * @Description :
 * @Date :2019-03-06 15:53:32
 */
public class OutterFileDemo {
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //从配置文件中获取对应的参数
        Properties init=new Properties();
        init.load(OutterFileDemo.class.getResourceAsStream("/db.properties"));

        Connection conn=DriverManager.getConnection(init.getProperty("url"),init.getProperty("userid"),init.getProperty("password"));


        ResultSet rs=conn.createStatement().executeQuery("select * from emp");
        while(rs.next())
            System.out.println(rs.getString(1));

    }
}
