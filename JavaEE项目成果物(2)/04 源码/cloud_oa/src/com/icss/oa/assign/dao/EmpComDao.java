package com.icss.oa.assign.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.assign.pojo.EmpCom;

@Repository
public class EmpComDao {
	
	@Autowired
	private SqlSessionFactory factory ;
	
	public void insert(EmpCom ase) {		
		SqlSession session = factory.openSession();
		session.insert("EMP_COM.insert",ase);	
	}
	
	public void update(EmpCom ase) {		
		SqlSession session = factory.openSession();
		session.update("EMP_COM.update",ase);
	}

	public void delete(Integer empComId) {
		SqlSession session = factory.openSession();
		session.delete("EMP_COM.delete",empComId);
	}

	public EmpCom queryById(Integer empComId) {
		SqlSession session = factory.openSession();
		EmpCom emp = session.selectOne("EMP_COM.queryById",empComId);
		return emp;
	}
	
	public int getPrimaryKey() {
		SqlSession session = factory.openSession();
		int primaryKey = session.selectOne("EMP_COM.getPrimaryKey");
		return primaryKey;
	}

	public List<EmpCom> query() {
		SqlSession session = factory.openSession();
		List<EmpCom> list  = session.selectList("EMP_COM.query");
		return list;
	}

	public int getCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("EMP_COM.getCount");
		return count;
	}

	public List<EmpCom> queryByPager(Map map) {
		SqlSession session = factory.openSession();
		List<EmpCom> list  = session.selectList("EMP_COM.queryByPager",map);
		return list;
	}
	
	//导出EXCEL报表
	public List<EmpCom> exportExcel(Map map) {
		SqlSession session = factory.openSession();
		List<EmpCom> list  = session.selectList("EMP_COM.queryAll",map);
		return list;
	}
	
}