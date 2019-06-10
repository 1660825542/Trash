package com.icss.oa.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.system.pojo.Employee;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

	@Autowired
	private EmployeeDao dao;
	
	public Employee empObj(String empNum){
		return dao.empNumIsExist(empNum);
	}
}