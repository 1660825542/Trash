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
