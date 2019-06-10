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
	 * ��ҳ��ѯԱ����Ϣ����ҳ��Ϣ��Service�㴫��
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> query(Map<String, Integer> map) {
		SqlSession session = factory.openSession();
		List<Map<String, Object>> list = session.selectList("EMPLOYEE.query", map);
		return list;
	}

	/**
	 * ��ѯ����
	 * @return
	 */
	public int getCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("EMPLOYEE.getCount");
		return count;
	}

	/**
	 * ��ͨ����ͨ����������Ա��
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
	 * ��ͨ������������
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
	 * ��õ�ǰ��������Service��������ʹ��
	 * @return
	 */
	public int getPrimaryKey() {
		SqlSession session = factory.openSession();
		int primaryKey = session.selectOne("EMPLOYEE.getPrimaryKey");
		return primaryKey;
	}
	
	/**
	 * ��ѯ��¼���Ƿ����
	 * @param empNum
	 * @return
	 */
	public Employee empNumIsExist(String empNum){
		SqlSession session = factory.openSession();
		Employee emp = session.selectOne("EMPLOYEE.queryByEmpNum",empNum);
		return emp;
	}
	
	/**
	 * ��ѯ�������յ�Ա��
	 * @param date
	 * @return
	 */
	public List<Employee> queryTodayBirthday(){
		SqlSession session = factory.openSession();
		List<Employee> list = session.selectList("EMPLOYEE.queryBirthday");
		return list;
	}
	
	/**
	 * ΪԱ�����ӽ�ɫ
	 * @param map
	 */
	public void addEmpRole(Map<String,Integer> map){
		SqlSession session = factory.openSession();
		session.insert("EMPLOYEE.addEmpRole", map);
	}
	
	/**
	 * ɾ��Ա���Ľ�ɫ
	 * @param map
	 */
	public void deleteEmpRole(Map<String,Integer> map){
		SqlSession session = factory.openSession();
		session.delete("EMPLOYEE.deleteEmpRole", map);
	}
	
	/**
	 * ͨ��Ա��ID��ѯ��������
	 * @param empId
	 * @return ��������
	 */
	public String getDeptNameByEmp(Integer empId){
		SqlSession session = factory.openSession();
		String name = session.selectOne("EMPLOYEE.queryDeptByEmp",empId);
		return name;
	}
	
	/**
	 * ͨ��Ա��ID��ѯְ������
	 * @param empId
	 * @return
	 */
	public String getPosNameByEmp(Integer empId){
		SqlSession session = factory.openSession();
		String name = session.selectOne("EMPLOYEE.queryPosByEmp",empId);
		return name;
	}
	
	/**
	 * ��ѯĳ������������Ա��
	 * @param deptId
	 * @return
	 */
	public List<Employee> queryEmpByDept(Integer deptId){
		SqlSession session = factory.openSession();
		List<Employee> list = session.selectList("EMPLOYEE.queryEmpByDept2", deptId);
		return list;
	}
	
	/**
	 * ��ѯĳ������������Ա�� ��ҳ
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
	 * ��ѯĳ����ɫ��Ӧ ������Ա��
	 * @param roleId
	 * @return
	 */
	public List<Employee> queryByRole(Integer roleId){
		SqlSession session = factory.openSession();
		List<Employee> emp = session.selectList("EMPLOYEE.queryByRole", roleId);
		return emp;
	}
	
	/**
	 * ��ѯĳ���ŵĲ����쵼
	 * @param deptId
	 * @return
	 */
	public List<Employee> quertDeptLeader(Integer deptId){
		SqlSession session = factory.openSession();
		List<Employee> emp = session.selectList("EMPLOYEE.queryDeptLeader", deptId);
		return emp;
	}
	
	/**
	 * ��ѯ�ܾ���
	 * @return
	 */
	public List<Employee> queryManager(){
		SqlSession session = factory.openSession();
		List<Employee> emp = session.selectList("EMPLOYEE.queryManager");
		return emp;
	}
	
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<Employee> queryAdminstration(){
		SqlSession session = factory.openSession();
		List<Employee> emp = session.selectList("EMPLOYEE.queryAdminstration");
		return emp;
	}
	
	/**
	 * ��ѯ���ӹ���
	 * @return
	 */
	public List<Employee> queryCarAdmin(){
		SqlSession session = factory.openSession();
		List<Employee> emp = session.selectList("EMPLOYEE.queryCarAdmin");
		return emp;
	}
	
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<Employee> queryMoney(){
		SqlSession session = factory.openSession();
		List<Employee> emp = session.selectList("EMPLOYEE.queryMoney");
		return emp;
	}
	
	/**
	 * ��ѯ��ҳ������Ա��
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
