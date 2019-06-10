package com.rock.test;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.rock.dao.DeptDao;
import com.rock.pojo.Dept;

public class DeptTest {
	private DeptDao deptDao=null;
	@Before
	public void init(){
		try {
			SqlSession sqlSession=new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml")).openSession(true);
			deptDao=sqlSession.getMapper(DeptDao.class);
//			personDao=sqlSession.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testQuerybyId(){
		Dept dept=deptDao.queryById("20");
		System.out.println(dept);
	}
	@Test
	public void testqueryByIdWithEmployees(){
		Dept dept=deptDao.queryByIdWithEmployees("20");
		System.out.println(dept);
	}
}
