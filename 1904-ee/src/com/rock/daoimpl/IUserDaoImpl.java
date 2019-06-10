package com.rock.daoimpl;

import com.rock.dao.IUserDao;
import com.rock.entities.User;
import com.rock.util.JDBCUtil;

/**
 * FileName		:com.rock.daoimpl 	IUserDaoImpl.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年3月26日:下午4:47:59
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年3月26日 	老张 		1.0 		1.0 Version
 * 
 */
public class IUserDaoImpl implements  IUserDao{
	private JDBCUtil util=null;
	public IUserDaoImpl(){
		util=new JDBCUtil();//Ctrl+Shift+O
	}
	/**
	 * @TODO	 :验证登录信息的合法性
	 * @Date	 :2019年3月26日 下午4:50:28
	 * @Author	 :老张
	 * @param userid
	 * @param password
	 * @return   :
	 * @see com.rock.dao.IUserDao#login(java.lang.String, java.lang.String)
	 */
	public User login(String userid, String password) {
		String sql="select * from userinfo where userid=? and password=?";
		return util.queryById(User.class, sql, userid,password);
	}

}
