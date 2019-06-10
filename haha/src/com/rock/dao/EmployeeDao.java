package com.rock.dao;

import java.util.List;

import com.rock.pojo.Employee;


public interface EmployeeDao {
	/**
	 * TODO:根据员工编号检索员工的消息，返回值对应的pojo类对象
	 * @param empno
	 * @return
	 */
	public Employee queryByID(String empno);
	
	
	/**
	 * TODO:根据员工对象的编号值检索员工信息
	 * @param emp
	 * @return
	 */
	public Employee queryByIDWithEmployee(Employee emp);
	
	/**
	 * TODO:根據員工編號修改員工信息
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
