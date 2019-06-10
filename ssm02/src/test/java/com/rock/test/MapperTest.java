package com.rock.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rock.ssm.dao.UserinfoMapper;
import com.rock.ssm.dao.UserlevelMapper;
import com.rock.ssm.entities.Userinfo;
import com.rock.ssm.entities.Userlevel;

/**
 * FileName		:com.rock.test 	MapperTest.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:老张
 * @Date		:2019年4月27日:下午3:14:45
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年4月27日 	老张 			1.0 		1.0 Version
 * 
 */

//@@RunWith:是JUnit提供的一个注解，用于指明JUnit所运用的技术。默认情况下，就是junit本身
//SpringJUnit4ClassRunner：是spring提供的一个单元测试框架
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class MapperTest {
	
	@Autowired
	UserinfoMapper userinfoMapper;
	@Autowired
	UserlevelMapper userlevelMapper;
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testInsert(){
		Userlevel  userlevel=new Userlevel();
		userlevel.setLevelname("黄金等级");
		userlevel.setLeveltxt("拥有黄金等级的会员可以每个月10次品尝本店所有咖啡");
		userlevelMapper.insert(userlevel);
	}
	@Test
	public void testInsertUserinfo(){
		Userinfo userinfo=new Userinfo();
		userinfo.setUsername("刘备");
		userinfo.setSex("M");
		userinfo.setEmail("liubei@163.com");
		userinfo.setLevelid(1);
		userinfoMapper.insert(userinfo);
	}
	@Test
	public void testInsertUserinfoMulti(){
		for(int i=1;i<=2000;i++){
			String name=UUID.randomUUID().toString().substring(0,10);
			Userinfo user=new Userinfo(name,"F",name+"@164.com",1);
			userinfoMapper.insertSelective(user);
		}
	}
}
