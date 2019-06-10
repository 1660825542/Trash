package com.icss.oa.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.app.dao.ContactsDao;
import com.icss.oa.app.pojo.LoginReturnValue;
import com.icss.oa.app.pojo.ReturnValue;
import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.system.pojo.Employee;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private ContactsDao contactsDao;

	@Override
	public LoginReturnValue login(String empNum, String password) {
		Employee emp = employeeDao.empNumIsExist(empNum);
		if (emp == null) {
			return new LoginReturnValue(LoginReturnValue.WRONG_USERNAME,
					"�û���������", null);
		} else if (!password.equals(emp.getPassword())) {
			return new LoginReturnValue(LoginReturnValue.WRONG_PASSWORD,
					"�������", null);
		} else {
			return new LoginReturnValue(LoginReturnValue.LOGIN_SUCESS, "��¼�ɹ�",
					emp);
		}
	}

	@Override
	public ReturnValue getEmployeeByEmpNum(String empNum) {
		Map<String, String> map = contactsDao.queryEmpDetailByNum(empNum);
		if (map == null) {
			return new ReturnValue(0, "�û���������", null);
		} else {
			return new ReturnValue(1, "����ɹ�", map);
		}
	}

	@Override
	public ReturnValue updateInfo(Employee emp) {
		employeeDao.update(emp);
		return new ReturnValue(1, "����ɹ�", null);
	}
}
