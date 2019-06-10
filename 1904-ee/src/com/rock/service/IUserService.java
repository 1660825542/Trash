package com.rock.service;

import com.rock.entities.User;

/**
 * FileName		:com.rock.service 	IUserService.java
 * TODO			:用户的相关页面的处理
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年3月26日:下午4:25:19
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年3月26日 	老张 		1.0 		1.0 Version
 * 
 */
public interface IUserService {
	public User login(String userid,String password);
}
