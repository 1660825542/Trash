package com.icss.oa.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.common.Pager;
import com.icss.oa.system.pojo.Employee;

@Repository
public class EmployeeDao {
	
	@Autowired
	private SqlSessionFactory factory;

	public void insert(Employee emp) {
		SqlSession session = factory.openSession();
		session.insert("EMPLOYEE.insert", emp);
	}

	public void update(Employee emp) {
		SqlSession session = factory.openSession();
		session.update("EMPLOYEE.update", emp);
	}

	public void delete(Integer empId) {
		SqlSession session = factory.openSession();
		session.delete("EMPLOYEE.delete", empId);
	}

	public Employee queryById(Integer empId) {
		SqlSession session = factory.openSession();
		Employee emp = session.selectOne("EMPLOYEE.queryById", empId);
		return emp;
	}
		
	/**
	 * 分页查询员工信息，分页信息在Service层传入
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> query(Map<String, Integer> map) {
		SqlSession session = factory.openSession();
		List<Map<String, Object>> list = session.selectList("EMPLOYEE.query", map);
		return list;
	}

	/**
	 * 查询总数
	 * @return
	 */
	public int getCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("EMPLOYEE.getCount");
		return count;
	}

	/**
	 * 普通条件通过姓名检索员工
	 * @param map
	 * @param nameOrDept
	 * @return
	 */
	public List<Map<String,Object>> queryByCondition(Map<String, Object> map) {
		SqlSession session = factory.openSession();
		List<Map<String, Object>> list = session.selectList("EMPLOYEE.queryByCondition", map);
		return list;
	}
	
	/**
	 * 普通条件检索总数
	 * @param nameOrDept
	 * @return
	 */
	public int getConditionCount(String nameOrDept) {
		SqlSession session = factory.openSession();
		Map<String,String> map = new HashMap<String, String>();
		map.put("nameOrDept", nameOrDept);
		int count = session.selectOne("EMPLOYEE.getConditionCount",map);
		return count;
	}
	
	/**
	 * 获得当前主键，供Service创建索引使用
	 * @return
	 */
	public int getPrimaryKey() {
		SqlSession session = factory.openSession();
		int primaryKey = session.selectOne("EMPLOYEE.getPrimaryKey");
		return primaryKey;
	}
	
	/**
	 * 查询登录名是否存在
	 * @param empNum
	 * @return
	 */
	public Employee empNumIsExist(String empNum){
		SqlSession session = factory.openSession();
		Employee emp = session.selectOne("EMPLOYEE.queryByEmpNum",empNum);
		return emp;
	}
	
	/**
	 * 查询今天生日的员工
	 * @param date
	 * @return
	 */
	public List<Employee> queryTodayBirthday(){
		SqlSession session = factory.openSession();
		List<Employee> list = session.selectList("EMPLOYEE.queryBirthday");
		return list;
	}
	
	/**
	 * 为员工增加角色
	 * @param map
	 */
	public void addEmpRole(Map<String,Integer> map){
		SqlSession session = factory.openSession();
		session.insert("EMPLOYEE.addEmpRole", map);
	}
	
	/**
	 * 删除员工的角色
	 * @param map
	 */
	public void deleteEmpRole(Map<String,Integer> map){
		SqlSession session = factory.openSession();
		session.delete("EMPLOYEE.deleteEmpRole", map);
	}
	
	/**
	 * 通过员工ID查询部门名称
	 * @param empId
	 * @return 部门名称
	 */
	public String getDeptNameByEmp(Integer empId){
		SqlSession session = factory.openSession();
		String name = session.selectOne("EMPLOYEE.queryDeptByEmp",empId);
		return name;
	}
	
	/**
	 * 通过员工ID查询职务名称
	 * @param empId
	 * @return
	 */
	public String getPosNameByEmp(Integer empId){
		SqlSession session = factory.openSession();
		String name = session.selectOne("EMPLOYEE.queryPosByEmp",empId);
		return name;
	}
	
	/**
	 * 查询某个部门下所有员工
	 * @param deptId
	 * @return
	 */
	public List<Employee> queryEmpByDept(Integer deptId){
		SqlSession session = factory.openSession();
		List<Employee> list = session.selectList("EMPLOYEE.queryEmpByDept2", deptId);
		return list;
	}
	
	/**
	 * 查询某个部门下所有员工 分页
	 * @param pager
	 * @param deptId
	 * @return
	 */
	public List<Map<String, Object>> queryEmpByDept(Pager pager,Integer deptId){
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deptId", deptId);
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		List<Map<String, Object>> list = session.selectList("EMPLOYEE.queryEmpByDept", map);
		return list;
	}
	
	/**
	 * 查询某个角色对应 的所有员工
	 * @param roleId
	 * @return
	 */
	public List<Employee> queryByRole(Integer roleId){
		SqlSession session = factory.openSession();
		List<Employee> emp = session.selectList("EMPLOYEE.queryByRole", roleId);
		return emp;
	}
	
	/**
	 * 查询某部门的部门领导
	 * @param deptId
	 * @return
	 */
	public List<Employee> quertDeptLeader(Integer deptId){
		SqlSession session = factory.openSession();
		List<Employee> emp = session.selectList("EMPLOYEE.queryDeptLeader", deptId);
		return emp;
	}
	
	/**
	 * 查询总经理
	 * @return
	 */
	public List<Employee> queryManager(){
		SqlSession session = factory.openSession();
		List<Employee> emp = session.selectList("EMPLOYEE.queryManager");
		return emp;
	}
	
	/**
	 * 查询行政
	 * @return
	 */
	public List<Employee> queryAdminstration(){
		SqlSession session = factory.openSession();
		List<Employee> emp = session.selectList("EMPLOYEE.queryAdminstration");
		return emp;
	}
	
	/**
	 * 查询车队管理
	 * @return
	 */
	public List<Employee> queryCarAdmin(){
		SqlSession session = factory.openSession();
		List<Employee> emp = session.selectList("EMPLOYEE.queryCarAdmin");
		return emp;
	}
	
	/**
	 * 查询出纳
	 * @return
	 */
	public List<Employee> queryMoney(){
		SqlSession session = factory.openSession();
		List<Employee> emp = session.selectList("EMPLOYEE.queryMoney");
		return emp;
	}
	
	/**
	 * 查询分页部门下员工
	 * @param pager
	 * @param deptId
	 * @return
	 */
	public List<Employee> queryByDeptByPager(Pager pager,Integer deptId){
		SqlSession session = factory.openSession();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("deptId",deptId);
		List<Employee> emp =session.selectList("EMPLOYEE.queryEmpByDeptByPager", map);
		return emp;
	}
	
	public int getEmpCountByDept(Integer deptId){
		SqlSession session = factory.openSession();
		int count = session.selectOne("EMPLOYEE.getEmpCountByDept",deptId);
		return count;
	}
	
	public void changePwd(Integer empId,String pwd){
		SqlSession session = factory.openSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empId", empId);
		map.put("password", pwd);
		session.update("EMPLOYEE.changePwd", map);
	}

}
