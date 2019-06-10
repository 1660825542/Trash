package com.icss.oa.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.system.pojo.Department;

@Repository
public class DepartmentDao {
	
	@Autowired
	private SqlSessionFactory factory;
	
	public void insert(Department dept){
		SqlSession session = factory.openSession();
		session.insert("DEPARTMENT.insert",dept);	
	}
	
	public void delete(Integer deptId){
		SqlSession session = factory.openSession();
		session.delete("DEPARTMENT.delete",deptId);
	}
	
	public void update(Department dept){
		SqlSession session = factory.openSession();
		session.update("DEPARTMENT.update", dept);
	}
	
	public Department queryById(Integer deptId){
		SqlSession session = factory.openSession();
		Department dept = session.selectOne("DEPARTMENT.queryById", deptId);
		return dept;
	}
	
	public List<Department> query(){
		SqlSession session = factory.openSession();
		List<Department> list = session.selectList("DEPARTMENT.query");
		return list;
	}
	
	public List<Department> queryByPager(Map<String,Integer> map){
		SqlSession session = factory.openSession();
		List<Department> list = session.selectList("DEPARTMENT.queryByPager", map);
		return list;
	}
	
	public int count(){
		SqlSession session = factory.openSession();
		int count = session.selectOne("DEPARTMENT.count");
		return count;
	}
	
	/**
	 * 查询部门是否下面有员工
	 * @param deptId
	 * @return
	 */
	public boolean haveEmp(Integer deptId){
		SqlSession session = factory.openSession();
		List<Department> list = session.selectList("DEPARTMENT.haveEmp",deptId);
		if(!list.isEmpty())
			return true;
		else
			return false;
	}
	
	/**
	 * 获得当前主键，供Action获得主键
	 * @return
	 */
	public int getPrimaryKey() {
		SqlSession session = factory.openSession();
		int primaryKey = session.selectOne("DEPARTMENT.getPrimaryKey");
		return primaryKey;
	}
	
}
