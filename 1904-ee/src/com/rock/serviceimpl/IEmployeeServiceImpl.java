package com.rock.serviceimpl;

import java.util.List;

import com.rock.dao.IEmployeeDao;
import com.rock.daoimpl.IEmployeeDaoImpl;
import com.rock.entities.Employee;
import com.rock.service.IEmployeeService;

/**
 * FileName		:com.rock.serviceimpl 	IEmployeeServiceImpl.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年3月26日:下午5:11:53
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年3月26日 	老张 		1.0 		1.0 Version
 * 
 */
public class IEmployeeServiceImpl implements IEmployeeService {
	private IEmployeeDao iEmployeeDao=null;
	public IEmployeeServiceImpl(){
		iEmployeeDao=new IEmployeeDaoImpl();
	}
	public List<Employee> all() {
		return iEmployeeDao.all();
	}
}
