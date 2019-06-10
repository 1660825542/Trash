package com.icss.oa.process.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.process.pojo.Leave;


@Repository
public class LeaveDao {

	@Autowired
	private SqlSessionFactory factory;
	
	/**
	 * �������������
	 * @param leave
	 */
	public void insert(Leave leave) {
		SqlSession session = factory.openSession();
		session.insert("LEAVE.insert", leave);
	}
	
	/**
	 * ��øղ����idֵ
	 * @return
	 */
	public int getLastId() {
		SqlSession session = factory.openSession();
		int id = session.selectOne("LEAVE.getLastId");
		return id;
	}
	
	/**
	 * ��������б�
	 * @param leavePerson 
	 * @return
	 */
	public List<Leave> query(Map<String,Integer> map) {
		SqlSession session = factory.openSession();
		List<Leave> list = session.selectList("LEAVE.query",map);
		return list;
	}
	
	/**
	 * �������״̬
	 * @param empLeave
	 */
	public void updateStatus(Leave leave) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.update("LEAVE.updateStatus",leave);
	}
	
	/**
	 * ��ѯ�����������
	 */
	public Leave queryById(Integer LeaveId) {
		SqlSession sqlSession = factory.openSession();
		Leave empLeave = sqlSession.selectOne("LEAVE.queryById",LeaveId);
		return empLeave;
	}
	
	/**
	 * ��ѯ�������
	 * @param empId
	 * @return
	 */
	public int count(Integer empId){
		SqlSession sqlSession = factory.openSession();
		int count = sqlSession.selectOne("LEAVE.count", empId);
		return count;
	}
}