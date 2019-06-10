package com.rock.service;

import java.util.List;

import com.rock.entities.Employee;

/**
 * FileName		:com.rock.service 	IEmployeeService.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年3月26日:下午5:11:21
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年3月26日 	老张 		1.0 		1.0 Version
 * 
 */
public interface IEmployeeService {
	public List<Employee> all();
}
