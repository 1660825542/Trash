package com.rock.dao;

import com.rock.pojo.Employee;

public interface EmployeeDao {
	/**
	 * TODO:����Ա����ż���Ա������Ϣ������ֵ��Ӧ��pojo�����
	 * @param empno
	 * @return
	 */
	public Employee queryByID(String empno);
	
	
	/**
	 * TODO:����Ա������ı��ֵ����Ա����Ϣ
	 * @param emp
	 * @return
	 */
	public Employee queryByIDWithEmployee(Employee emp);
}
