package com.icss.oa.assign.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.assign.pojo.AssignEmp;

@Repository
public class AssignEmpDao {
	
	@Autowired
	private SqlSessionFactory factory ;
	
	public void insert(AssignEmp ase) {		
		SqlSession session = factory.openSession();
		session.insert("ASSIGN_EMP.insert",ase);	
	}
	
	public void update(AssignEmp ase) {		
		SqlSession session = factory.openSession();
		session.update("ASSIGN_EMP.update",ase);
	}
	
	public void updateByEmpCom(AssignEmp ase) {
		SqlSession session = factory.openSession();
		session.update("ASSIGN_EMP.updateByEmpCom",ase);
	}

	public void delete(Integer assId) {
		SqlSession session = factory.openSession();
		session.delete("ASSIGN_EMP.delete",assId);
	}

	public AssignEmp queryById(Integer assId) {
		SqlSession session = factory.openSession();
		AssignEmp emp = session.selectOne("ASSIGN_EMP.queryById",assId);
		return emp;
	}
	
	public int getPrimaryKey() {
		SqlSession session = factory.openSession();
		int primaryKey = session.selectOne("ASSIGN_EMP.getPrimaryKey");
		return primaryKey;
	}

	public List<AssignEmp> query() {
		SqlSession session = factory.openSession();
		List<AssignEmp> list  = session.selectList("ASSIGN_EMP.query");
		return list;
	}

	public int getCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("ASSIGN_EMP.getCount");
		return count;
	}

	public List<AssignEmp> queryByPager(Map map) {
		SqlSession session = factory.openSession();
		List<AssignEmp> list  = session.selectList("ASSIGN_EMP.queryByPager",map);
		return list;
	}
	
}