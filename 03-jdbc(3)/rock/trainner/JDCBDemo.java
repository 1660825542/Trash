package com.rock.trainner;

import oracle.jdbc.driver.OracleDriver;

import java.sql.*;

/**
 * @author :老张
 * @version :1.0
 * @Description :
 * @Date :2019-03-05 16:05:29
 */
public class JDCBDemo {
    OracleDriver d;
    public static void main(String[] args) {
        try {
            //①、加载Oracle的驱动程序
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //Class.forName(""); 加载MySQL数据库的驱动程序
            //②、连接数据库（获取数据库的连接对象）
            Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
            //③、创建具有发送SQL文能力的对象Statement|PreparedStatement
            Statement stmt=conn.createStatement();
            //④、编写SQL文
            String sql="select * from emp where 1=2";
            //⑤、发送SQL文，并接收数据库返回的结果
            ResultSet rs= stmt.executeQuery(sql);//ResultSet 结果集合对象
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(6));
            }
            System.out.println("读取完毕..................");
            //⑥、关闭相关对象（释放资源）

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
