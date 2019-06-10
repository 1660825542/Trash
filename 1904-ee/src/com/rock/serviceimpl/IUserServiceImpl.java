package com.rock.serviceimpl;

import com.rock.dao.IUserDao;
import com.rock.daoimpl.IUserDaoImpl;
import com.rock.entities.User;
import com.rock.service.IUserService;

/**
 * FileName		:com.rock.serviceimpl 	IUserServiceImpl.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年3月26日:下午4:36:35
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年3月26日 	老张 		1.0 		1.0 Version
 * 
 */
public class IUserServiceImpl implements IUserService{
	private IUserDao iUserDao=null;
	public IUserServiceImpl(){
		iUserDao=new IUserDaoImpl();
	}
	public User login(String userid, String password) {
		return iUserDao.login(userid, password);
	}
}
