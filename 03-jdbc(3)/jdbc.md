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
        demo.testDeleteByPreparedStatement();
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
    private void testDeleteByPreparedStatement() throws SQLException {
        Connection conn=getConnnection();
        conn.setAutoCommit(false);//设置事务不自动提交
        conn.commit();//手工提交事务
        String sql="delete from emp where empno=? and deptno>?";
        PreparedStatement stmt=conn.prepareStatement(sql);

        Scanner in=new Scanner(System.in);
        System.out.println("请输入要删除的员工的编号");
        int empno=in.nextInt();
        System.out.println("请输入对应的部门编号");
        int deptno=in.nextInt();

        stmt.setInt(1,empno);
        stmt.setInt(2,deptno);

        int a=stmt.executeUpdate();

        System.out.println("delete语句影响了【"+a+"】行数据");
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
        DQLDemo demo=new DQLDemo();
        demo.testSelectByMultiTable();
        //demo.testSelectToBean();
    }
    public void testSelect() throws SQLException {
        Connection conn=getConnection();
        if(conn==null){
            System.out.println("数据库连接失败");
            conn=getConnection();
        }else{
            String  sql="select * from emp";
            PreparedStatement stmt=conn.prepareStatement(sql);
            //stmt.executeUpdate()//操作DML（insert，update，delete）
            ResultSet rs=stmt.executeQuery();//操作DQL（select）
            while(rs.next()){
                System.out.println(rs.getInt("empno")+"\t"+rs.getString(2)+"\t"+rs.getDouble("sal")+"\t"+rs.getString("loc"));
            }
            release(rs,stmt,conn);
        }
    }


    public void testSelectByMultiTable() throws SQLException {
        Connection conn=getConnection();

        if(conn==null){
            System.out.println("数据库连接失败");
            conn=getConnection();
        }else{
            String  sql="select a.*,b.* from emp a inner join dept b on a.deptno=b.deptno";
            PreparedStatement stmt=conn.prepareStatement(sql);
            //stmt.executeUpdate()//操作DML（insert，update，delete）
            ResultSet rs=stmt.executeQuery();//操作DQL（select）
            while(rs.next()){
                System.out.println(rs.getInt("empno")+"\t"+rs.getString(2)+"\t"+rs.getDouble("sal")+"\t"+rs.getString("loc"));
            }
            release(rs,stmt,conn);
        }
    }


    public void testSelectToBean(){

    }
    public Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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




package com.rock.dml;

/**
 * @author :老张
 * @version :1.0
 * @Description :
 * @Date :2019-03-06 14:42:44
 */
public class Employee {
    private String empno;
    private String ename;
    private String job;
    private String mgr;
    private String hiredate;
    private String sal;
    private String commm;
    private String deptno;

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMgr() {
        return mgr;
    }

    public void setMgr(String mgr) {
        this.mgr = mgr;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
    }

    public String getCommm() {
        return commm;
    }

    public void setCommm(String commm) {
        this.commm = commm;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empno='" + empno + '\'' +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr='" + mgr + '\'' +
                ", hiredate='" + hiredate + '\'' +
                ", sal='" + sal + '\'' +
                ", commm='" + commm + '\'' +
                ", deptno='" + deptno + '\'' +
                '}';
    }
}



package com.rock.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author :老张
 * @version :1.0
 * @Description :
 * @Date :2019-03-06 14:38:10
 */
public class EmployeeOperator {
    List<Employee> all=null;
    Map<String,Employee> maps=null;
    public EmployeeOperator(){
        all=new ArrayList<Employee>();
        maps=new HashMap<>();
    }

    public static void main(String[] args) {
        EmployeeOperator dao=new EmployeeOperator();
        dao.getDataFromDB();
        //dao.printEmployeesByList();
        dao.printEmployeesByMap();
    }
    /**
     * 从数据库中将数据检索出来
     */
    public void getDataFromDB(){
        Connection connection=JDBCUtil.getConnection();
        String sql="select * from emp where job=?";
        try {
            PreparedStatement stmt=connection.prepareStatement(sql);
            String s="CLERK";
            stmt.setString(1,s);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Employee emp=new Employee();
                emp.setCommm(rs.getString("comm"));
                emp.setDeptno(rs.getString("deptno"));
                emp.setEmpno(rs.getString("empno"));
                emp.setEname(rs.getString("ename"));
                emp.setHiredate(rs.getString("hiredate"));
                emp.setJob(rs.getString("job"));
                emp.setMgr(rs.getString("mgr"));
                emp.setSal(rs.getString("sal"));
                //System.out.println(emp);
                all.add(emp);
                maps.put(emp.getEmpno(),emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void printEmployeesByList(){
        for(Employee e:all)
            System.out.println(e);
    }
    public void printEmployeesByMap(){
        Set<String> keys=maps.keySet();
        for(String key:keys)
            System.out.println(key+"--->"+maps.get(key));
    }
}



package com.rock.dml;

import java.sql.*;

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
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库相关资源对象
     */
    public static void release(ResultSet rs, Statement stmt , Connection conn){

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



package com.rock.dml;

import java.sql.*;

/**
 * @author :老张
 * @version :1.0
 * @Description :
 * @Date :2019-03-06 16:04:00
 */
public class ResultTypeDemo {
    public static void main(String[] args) {
        Connection conn=JDBCUtil.getConnection();
        try {
            String sql="select empno,ename,sal,deptno from emp where deptno=?";
            PreparedStatement stmt=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

            stmt.setString(1,"20");

            ResultSet rs=stmt.executeQuery();
            rs.last();
            //rs.absolute(2);
            while(rs.next())
                System.out.println(rs.getString(1)+"\t"+rs.getString(2));
            //rs.absolute(1);
            //rs.previous();
            //System.out.println("------------------------");
            //while(rs.next())
            //    System.out.println(rs.getString(1)+"\t"+rs.getString(2));
            testMetaData(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 结果集元数据（非真实数据的数据（列名、列的个数、类型等等都属于结果集的元数据））
     * @param rs
     * @throws SQLException
     */
    public static void testMetaData(ResultSet rs) throws SQLException {
        ResultSetMetaData meta=rs.getMetaData();
        //返回结果集中的列的个数
        int count=meta.getColumnCount();
        System.out.println(count);
        //System.out.println(meta.getColumnName(1));
        for(int i=1;i<=count;i++)
            System.out.println(meta.getColumnName(i));

    }
}



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

~~~

