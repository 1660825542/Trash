package com.rock.daoimpl;

import java.util.List;

import com.rock.dao.IEmployeeDao;
import com.rock.entities.Employee;
import com.rock.util.JDBCUtil;

/**
 * FileName		:com.rock.daoimpl 	IEmployeeDaoImpl.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:Rock
 * @Date		:2019年3月26日:下午5:13:03
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年3月26日 	老张 		1.0 		1.0 Version
 * 
 */
public class IEmployeeDaoImpl  implements IEmployeeDao{
	private JDBCUtil util=null;
	public IEmployeeDaoImpl(){
		util=new JDBCUtil();
	}
	public List<Employee> all() {
		String sql="select e.*,d.dname from emp e left join dept d on e.deptno=d.deptno";
		return util.queryAll(Employee.class, sql);
	}

}
