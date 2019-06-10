package com.rock.dml;

import java.sql.*;

/**
 * @author :老张
 * @version :1.0
 * @Description :
 * @Date :2019-03-06 13:11:39
 */
public class DQLDemo {
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        DQLDemo demo = new DQLDemo();
        demo.testSelectByMultiTable();
        //demo.testSelectToBean();
    }

    public void testSelect() throws SQLException {
        Connection conn = getConnection();
        if (conn == null) {
            System.out.println("数据库连接失败");
            conn = getConnection();
        } else {
            String sql = "select * from emp";
            PreparedStatement stmt = conn.prepareStatement(sql);
            //stmt.executeUpdate()//操作DML（insert，update，delete）
            ResultSet rs = stmt.executeQuery();//操作DQL（select）
            while (rs.next()) {
                System.out.println(rs.getInt("empno") + "\t" + rs.getString(2) + "\t" + rs.getDouble("sal") + "\t" + rs.getString("loc"));
            }
            release(rs, stmt, conn);
        }
    }


    public void testSelectByMultiTable() throws SQLException {
        Connection conn = getConnection();

        if (conn == null) {
            System.out.println("数据库连接失败");
            conn = getConnection();
        } else {
            String sql = "select a.*,b.* from emp a inner join dept b on a.deptno=b.deptno";
            PreparedStatement stmt = conn.prepareStatement(sql);
            //stmt.executeUpdate()//操作DML（insert，update，delete）
            ResultSet rs = stmt.executeQuery();//操作DQL（select）
            while (rs.next()) {
                System.out.println(rs.getInt("empno") + "\t" + rs.getString(2) + "\t" + rs.getDouble("sal") + "\t" + rs.getString("loc"));
            }
            release(rs, stmt, conn);
        }
    }


    public void testSelectToBean() {

    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库相关资源对象
     */
    private void release(ResultSet rs, Statement stmt, Connection conn) {

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
