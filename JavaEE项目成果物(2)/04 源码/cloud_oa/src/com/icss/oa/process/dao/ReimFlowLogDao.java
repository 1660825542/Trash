package com.icss.oa.process.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.process.pojo.ReimFlowLog;


/**
 * 流程日志DAO类
 * @author Administrator
 *
 */
@Repository
public class ReimFlowLogDao {

	@Autowired
	private SqlSessionFactory factory;
	
	public void insert(ReimFlowLog flowLog) {
		SqlSession session = factory.openSession();
		session.insert("REIM_FLOW_LOG.insert",flowLog);
	}
	
	public List<ReimFlowLog> query() {
		SqlSession session = factory.openSession();
		List<ReimFlowLog> list = session.selectList("REIM_FLOW_LOG.query");
		return list;
	}
	
	public List<ReimFlowLog> queryByReimId(Integer reimId) {
		SqlSession session = factory.openSession();
		List<ReimFlowLog> list = session.selectList("REIM_FLOW_LOG.queryByReimId",reimId);
		return list;
	}
	
	/**
	 * 根据流程id得到报销记录id
	 * @param proId
	 * @return
	 */
	public String queryRecordidByProId(String proId){
		SqlSession session = factory.openSession();
		String recordid = session.selectOne("REIM_FLOW_LOG.queryRecordidByProid", proId);
		return recordid;
	}
}