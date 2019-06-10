package com.rock.test;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.rock.dao.PersonDao;
import com.rock.pojo.Person;

public class PersonTest {
	private PersonDao personDao=null;
	@Before
	public void init(){
		try {
			SqlSession sqlSession=new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml")).openSession(true);
			personDao=sqlSession.getMapper(PersonDao.class);
//			personDao=sqlSession.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testSavePerson(){
		Person person=new Person("p001","Αυ±Έ1ΊΕ",30,"c001","Audi");
		personDao.saveSerson(person);
	}
	
	
	@Test
	public void testQueryByid(){
		String personid="p001";
		Person p=personDao.queryByid(personid);
		System.out.println(p);
	}
	
	@Test
	public void testqueryByidUseResultMap(){
		String personid="p001";
		Person p=personDao.queryByidUseResultMap(personid);
		System.out.println(p);
	}
	
	
	@Test
	public void testqueryByIdWithCar(){
		String personid="p001";
		Person p=personDao.queryByIdWithCar(personid);
		System.out.println(p);
	}
}
