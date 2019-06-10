# JDBC

## 一、JDBC

​	是一套java提供的专门用于完成是数据库操作的API组成（说白了就是一些类库），学会了JDBC即可以在java中完成对数据库中的数据进行CRUD操作（增删改查操作）。

### 1、开发步骤

​	前提：导入相关驱动的jar包。企业中可以通过 maven等管理工具实现jar包的管理  

	> mysql-connector-java-5.1.22-bin.jar   mysql数据库的jar包	
	>
	> ojdbc6.jar						  oracle数据库的jar包

①、加载数据库的驱动程序，利用了java中refection（反射）驱动的加载



~~~ JAVA

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
            String sql="select * from emp";
            //⑤、发送SQL文，并接收数据库返回的结果
            ResultSet rs= stmt.executeQuery(sql);//ResultSet 结果集合对象
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2));
            }


            //⑥、关闭相关对象（释放资源）

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

~~~

### 2、Statement   CURD

~~~ java
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
        DMLDemo demo=new DMLDemo();
        demo.testUpdateByPreparedStatement();
    }
    private void testDelete() throws SQLException {
        //so
        Connection conn=this.getConnnection();
        Statement stmt=conn.createStatement();
        String sql="delete from emp where empno=8888";
        //a==delete语句影响到的行数。。。executeUpdate（dml语句）
        int a=stmt.executeUpdate(sql);

        System.out.println("delete语句删除了【"+a+"】行数据");
        release(null,stmt,conn);

    }
    private void testUpdate() throws SQLException {
        Connection conn=this.getConnnection();
        Statement stmt=conn.createStatement();

        Scanner in=new Scanner(System.in);
        System.out.println("请输入要修改工资 的员工姓名");
        String ename=in.next();
        System.out.println("请输入要新的工资是多少？");
        double salary=in.nextDouble();


        String sql="update emp set sal="+salary+" where ename='"+ename+"'";
        //返回了update语句影响的行数
        System.out.println(sql);
        int a=stmt.executeUpdate(sql);
        System.out.println("update语句修改了【"+a+"】行数据");

        release(null,stmt,conn);

    }
    private void testInsert() throws SQLException {
        Connection connection=getConnnection();
        Statement stmt=connection.createStatement();

        Scanner in=new Scanner(System.in);

        System.out.println("请输入要录入的员工工号");
        int empno=in.nextInt();

        System.out.println("请输入要录入的员工姓名");
        String ename=in.next();

        System.out.println("请输入要录入的员工岗位名称");
        String job=in.next();

        System.out.println("请输入要录入的员工工资");
        double sal=in.nextDouble();

        String sql="insert into emp(empno,ename,job,sal) values("+empno+",'"+ename+"','"+job+"',"+sal+")";
        int a=stmt.executeUpdate(sql);//executeUpdate方法发送所有的DML（insert|update|delete）文
        System.out.println("update语句修改了【"+a+"】行数据");
        release(null,stmt,connection);

    }
    private void testInsertByPreparedStatement() throws SQLException {
        Connection connection=getConnnection();
        String sql="insert into emp(empno,ename,job,sal) values(?,?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);

        Scanner in=new Scanner(System.in);

        System.out.println("请输入要录入的员工工号");
        int empno=in.nextInt();

        System.out.println("请输入要录入的员工姓名");
        String ename=in.next();

        System.out.println("请输入要录入的员工岗位名称");
        String job=in.next();

        System.out.println("请输入要录入的员工工资");
        double sal=in.nextDouble();
        //String sql="insert into emp(empno,ename,job,sal) values(?,?,?,?)";
        stmt.setInt(1,empno);
        stmt.setString(2,ename);
        stmt.setString(3,job);
        stmt.setDouble(4,sal);

        int a=stmt.executeUpdate();//int a=stmt.executeUpdate(sql);
        System.out.println("update语句修改了【"+a+"】行数据");
        release(null,stmt,connection);

    }
    private void testUpdateByPreparedStatement() throws SQLException {
        Connection conn=getConnnection();

        String sql="update emp set sal=? where ename=?";
        PreparedStatement stmt=conn.prepareStatement(sql);

        Scanner in=new Scanner(System.in);
        System.out.println("请输入要修改工资 的员工姓名");
        String ename=in.next();
        System.out.println("请输入要新的工资是多少？");
        double salary=in.nextDouble();

        stmt.setDouble(1,salary);
        stmt.setString(2,ename);

        int a=stmt.executeUpdate();
        System.out.println("update语句修改了【"+a+"】行数据");
        release(null,stmt,conn);

    }
    /**
     * 关闭数据库相关资源对象
     */
    private void release(ResultSet rs, Statement stmt , Connection conn){

            try{
                if(rs!=null)
                    rs.close();
            }catch(Exception ex){

            }
        try{
            if(stmt!=null)
                stmt.close();
        }catch(Exception ex){

        }
        try{
            if(conn!=null)
                conn.close();
        }catch(Exception ex){

        }



    }
    private Connection getConnnection(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

~~~

