package com.rock.dml;

import java.sql.*;
import java.util.Scanner;

/**
 * @author :老张
 * @version :1.0
 * @Description :
 * @Date :2019-03-06 08:58:53
 */
public class DMLDemo {
    /*
        PrepareStatement:SQL文预处理，特点：
        ①、可以有效的防止SQL注入，即保障数据的安全
        ②、提高SQL的执行效率
        ③、简化SQL的字符串拼接
     */
    public static void main(String[] args) throws SQLException {
        DMLDemo demo = new DMLDemo();
        demo.testDeleteByPreparedStatement();
    }

    private void testDelete() throws SQLException {
        //so
        Connection conn = this.getConnnection();
        Statement stmt = conn.createStatement();
        String sql = "delete from emp where empno=8888";
        //a==delete语句影响到的行数。。。executeUpdate（dml语句）
        int a = stmt.executeUpdate(sql);

        System.out.println("delete语句删除了【" + a + "】行数据");
        release(null, stmt, conn);

    }

    private void testUpdate() throws SQLException {
        Connection conn = this.getConnnection();
        Statement stmt = conn.createStatement();

        Scanner in = new Scanner(System.in);
        System.out.println("请输入要修改工资 的员工姓名");
        String ename = in.next();
        System.out.println("请输入要新的工资是多少？");
        double salary = in.nextDouble();


        String sql = "update emp set sal=" + salary + " where ename='" + ename + "'";
        //返回了update语句影响的行数
        System.out.println(sql);
        int a = stmt.executeUpdate(sql);
        System.out.println("update语句修改了【" + a + "】行数据");

        release(null, stmt, conn);

    }

    private void testInsert() throws SQLException {
        Connection connection = getConnnection();
        Statement stmt = connection.createStatement();

        Scanner in = new Scanner(System.in);

        System.out.println("请输入要录入的员工工号");
        int empno = in.nextInt();

        System.out.println("请输入要录入的员工姓名");
        String ename = in.next();

        System.out.println("请输入要录入的员工岗位名称");
        String job = in.next();

        System.out.println("请输入要录入的员工工资");
        double sal = in.nextDouble();

        String sql = "insert into emp(empno,ename,job,sal) values(" + empno + ",'" + ename + "','" + job + "'," + sal + ")";
        int a = stmt.executeUpdate(sql);//executeUpdate方法发送所有的DML（insert|update|delete）文
        System.out.println("update语句修改了【" + a + "】行数据");
        release(null, stmt, connection);

    }

    private void testInsertByPreparedStatement() throws SQLException {
        Connection connection = getConnnection();
        String sql = "insert into emp(empno,ename,job,sal) values(?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);

        Scanner in = new Scanner(System.in);

        System.out.println("请输入要录入的员工工号");
        int empno = in.nextInt();

        System.out.println("请输入要录入的员工姓名");
        String ename = in.next();

        System.out.println("请输入要录入的员工岗位名称");
        String job = in.next();

        System.out.println("请输入要录入的员工工资");
        double sal = in.nextDouble();
        //String sql="insert into emp(empno,ename,job,sal) values(?,?,?,?)";
        stmt.setInt(1, empno);
        stmt.setString(2, ename);
        stmt.setString(3, job);
        stmt.setDouble(4, sal);

        int a = stmt.executeUpdate();//int a=stmt.executeUpdate(sql);
        System.out.println("update语句修改了【" + a + "】行数据");
        release(null, stmt, connection);

    }

    private void testUpdateByPreparedStatement() throws SQLException {
        Connection conn = getConnnection();

        String sql = "update emp set sal=? where ename=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        Scanner in = new Scanner(System.in);
        System.out.println("请输入要修改工资 的员工姓名");
        String ename = in.next();
        System.out.println("请输入要新的工资是多少？");
        double salary = in.nextDouble();

        stmt.setDouble(1, salary);
        stmt.setString(2, ename);

        int a = stmt.executeUpdate();
        System.out.println("update语句修改了【" + a + "】行数据");
        release(null, stmt, conn);

    }

    private void testDeleteByPreparedStatement() throws SQLException {
        Connection conn = getConnnection();
        conn.setAutoCommit(false);//设置事务不自动提交
        conn.commit();//手工提交事务
        String sql = "delete from emp where empno=? and deptno>?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        Scanner in = new Scanner(System.in);
        System.out.println("请输入要删除的员工的编号");
        int empno = in.nextInt();
        System.out.println("请输入对应的部门编号");
        int deptno = in.nextInt();

        stmt.setInt(1, empno);
        stmt.setInt(2, deptno);

        int a = stmt.executeUpdate();

        System.out.println("delete语句影响了【" + a + "】行数据");
        release(null, stmt, conn);
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
