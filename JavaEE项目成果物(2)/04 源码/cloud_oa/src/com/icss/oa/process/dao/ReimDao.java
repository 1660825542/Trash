package com.icss.oa.process.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.process.pojo.Reimbursement;


@Repository
public class ReimDao {

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	/**
	 * �����±�������
	 * @param reim
	 */
	public void insert(Reimbursement reim) {
		SqlSession session = sessionFactory.openSession();
		session.insert("REIMBURSEMENT.insert", reim);
	}
	
	/**
	 * ��øղ����idֵ
	 * @return
	 */
	public int getLastId() {
		SqlSession session = sessionFactory.openSession();
		int id = session.selectOne("REIMBURSEMENT.getLastId");
		return id;
	}
	
	/**
	 * ���ر����б�
	 * @param leavePerson 
	 * @return
	 */
	public List<Reimbursement> query(Map<String,Integer> map) {
		SqlSession session = sessionFactory.openSession();
		List<Reimbursement> list = session.selectList("REIMBURSEMENT.query",map);
		return list;
	}
	
	/**
	 * ���±���״̬
	 * @param empLeave
	 */
	public void updateStatus(Reimbursement reim) {
		SqlSession sqlSession = sessionFactory.openSession();
		sqlSession.update("REIMBURSEMENT.updateStatus",reim);
	}
	
	/**
	 * ��ѯ������������
	 */
	public Reimbursement queryById(Integer reimId) {
		SqlSession sqlSession = sessionFactory.openSession();
		Reimbursement empLeave = sqlSession.selectOne("REIMBURSEMENT.queryById",reimId);
		return empLeave;
	}
	
	/**
	 * ��ѯ��������
	 * @param empId
	 * @return
	 */
	public int count(Integer empId){
		SqlSession sqlSession = sessionFactory.openSession();
		int count = sqlSession.selectOne("REIMBURSEMENT.count", empId);
		return count;
	}
}