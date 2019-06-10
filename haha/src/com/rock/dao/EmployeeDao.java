package com.rock.dao;

import java.util.List;

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
	
	/**
	 * TODO:�����T����̖�޸ĆT����Ϣ
	 * @param empno
	 * @return
	 */
	public int deleteByid(String empno);
	public int updateEmployee(Employee e);
	public int insertEmployee(Employee e);
	
	
	public List<Employee> queryByLike(Employee emp);
	public List<Employee> queryByMulti(Employee emp);
	public List<Employee> queryByWhere4Deptno(Employee emp);
}
