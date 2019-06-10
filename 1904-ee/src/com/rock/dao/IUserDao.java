package com.rock.dao;

import com.rock.entities.User;

/**
 * FileName		:com.rock.dao 	IUserDao.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年3月26日:下午4:38:23
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年3月26日 	老张 		1.0 		1.0 Version
 * 
 */
public interface IUserDao {
	/**
	 * @TODO	 :完成登录合法性验证
	 * @Date	 :2019年3月26日 下午4:38:54
	 * @Author	 :老张
	 * @param userid
	 * @param password
	 * @return   :
	 */
	public User login(String userid, String password);
}
