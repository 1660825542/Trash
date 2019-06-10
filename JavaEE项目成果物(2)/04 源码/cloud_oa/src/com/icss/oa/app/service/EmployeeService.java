package com.icss.oa.app.service;

import com.icss.oa.app.pojo.LoginReturnValue;
import com.icss.oa.app.pojo.ReturnValue;
import com.icss.oa.system.pojo.Employee;

public interface EmployeeService {
	LoginReturnValue login(String empId, String password);

	ReturnValue getEmployeeByEmpNum(String empNum);

	ReturnValue updateInfo(Employee emp);
}
