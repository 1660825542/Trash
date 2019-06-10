package com.icss.oa.assign.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.assign.pojo.ComReq;

@Repository
public class ComReqDao {
	
	@Autowired
	private SqlSessionFactory factory ;
	
	public void insert(ComReq crq) {		
		SqlSession session = factory.openSession();
		session.insert("COM_REQ.insert",crq);	
	}
	
	public void update(ComReq crq) {		
		SqlSession session = factory.openSession();
		session.update("COM_REQ.update",crq);
	}

	public void delete(Integer comReqId) {
		SqlSession session = factory.openSession();
		session.delete("COM_REQ.delete",comReqId);
	}

	public ComReq queryById(Integer comReqId) {
		SqlSession session = factory.openSession();
		ComReq emp = session.selectOne("COM_REQ.queryById",comReqId);
		return emp;
	}
	
	public int getPrimaryKey() {
		SqlSession session = factory.openSession();
		int primaryKey = session.selectOne("COM_REQ.getPrimaryKey");
		return primaryKey;
	}

	public List<ComReq> query() {
		SqlSession session = factory.openSession();
		List<ComReq> list  = session.selectList("COM_REQ.query");
		return list;
	}

	public int getCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("COM_REQ.getCount");
		return count;
	}

	public List<ComReq> queryByPager(Map map) {
		SqlSession session = factory.openSession();
		List<ComReq> list  = session.selectList("COM_REQ.queryByPager",map);
		return list;
	}
	
}