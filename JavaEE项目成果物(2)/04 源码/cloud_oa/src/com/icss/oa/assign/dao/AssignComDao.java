package com.icss.oa.assign.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.assign.pojo.AssignCom;

@Repository
public class AssignComDao {
	
	@Autowired
	private SqlSessionFactory factory ;
	
	public void insert(AssignCom asc) {		
		SqlSession session = factory.openSession();
		session.insert("ASSIGN_COM.insert",asc);	
	}
	
	public void update(AssignCom asc) {		
		SqlSession session = factory.openSession();
		session.update("ASSIGN_COM.update",asc);
	}

	public void delete(Integer assComId) {
		SqlSession session = factory.openSession();
		session.delete("ASSIGN_COM.delete",assComId);
	}

	public AssignCom queryById(Integer assComId) {
		SqlSession session = factory.openSession();
		AssignCom asc = session.selectOne("ASSIGN_COM.queryById",assComId);
		return asc;
	}
	
	public int getPrimaryKey() {
		SqlSession session = factory.openSession();
		int primaryKey = session.selectOne("ASSIGN_COM.getPrimaryKey");
		return primaryKey;
	}

	public int queryExist(Integer assComId) {
		SqlSession session = factory.openSession();
		int existNum = session.selectOne("ASSIGN_COM.queryExist",assComId);
		return existNum;
	}
	
	public List<AssignCom> query() {
		SqlSession session = factory.openSession();
		List<AssignCom> list  = session.selectList("ASSIGN_COM.query");
		return list;
	}

	public int getCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("ASSIGN_COM.getCount");
		return count;
	}

	public List<AssignCom> queryByPager(Map map) {
		SqlSession session = factory.openSession();
		List<AssignCom> list  = session.selectList("ASSIGN_COM.queryByPager",map);
		return list;
	}
	
	/**
	 * 根据检索条件查询
	 * @param pager
	 * @return
	 */
	public List<AssignCom> queryByCondition(Integer assComId) {
		SqlSession session = factory.openSession();
		List<AssignCom> list = session.selectList("ASSIGN_COM.queryByCondition", assComId);
		return list;
	}
	
}