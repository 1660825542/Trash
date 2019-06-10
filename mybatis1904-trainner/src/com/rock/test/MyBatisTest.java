package com.rock.test;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.rock.dao.EmployeeDao;
import com.rock.pojo.Employee;

public class MyBatisTest {
	SqlSession sqlSession = null;
	EmployeeDao employeeDao=null;
	@Before
	public void run() {
		System.out.println("MyBatisTest.before()");
		try {
			sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml")).openSession(true);
			employeeDao= sqlSession.getMapper(EmployeeDao.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void run1() {
		Employee emp = employeeDao.queryByID("7788");
		System.out.println(emp);
	}
	
	@Test
	public void testQueryByIDWithEmployee(){
		Employee e=employeeDao.queryByIDWithEmployee(new Employee("7788"));
		System.out.println(e);
	}

	/*
	 * @After public void after(){ System.out.println("MyBatisTest.after()"); }
	 */

	/*
	 * public static void main(String[] args) { MyBatisTest test=new Test();
	 * test.run(); }
	 */
}
