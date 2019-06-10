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
    List<Employee> all = null;
    Map<String, Employee> maps = null;

    public EmployeeOperator() {
        all = new ArrayList<Employee>();
        maps = new HashMap<>();
    }

    public static void main(String[] args) {
        EmployeeOperator dao = new EmployeeOperator();
        dao.getDataFromDB();
        //dao.printEmployeesByList();
        dao.printEmployeesByMap();
    }

    /**
     * 从数据库中将数据检索出来
     */
    public void getDataFromDB() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select * from emp where job=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            String s = "CLERK";
            stmt.setString(1, s);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
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
                maps.put(emp.getEmpno(), emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void printEmployeesByList() {
        for (Employee e : all)
            System.out.println(e);
    }

    public void printEmployeesByMap() {
        Set<String> keys = maps.keySet();
        for (String key : keys)
            System.out.println(key + "--->" + maps.get(key));
    }
}
