package com.icss.oa.process.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.process.pojo.LeaveFlowLog;


/**
 * 流程日志DAO类
 * @author Administrator
 *
 */
@Repository
public class LeaveFlowLogDao {

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	public void insert(LeaveFlowLog flowLog) {
		SqlSession session = sessionFactory.openSession();
		session.insert("LEAVE_FLOW_LOG.insert",flowLog);
	}
	
	public List<LeaveFlowLog> query() {
		SqlSession session = sessionFactory.openSession();
		List<LeaveFlowLog> list = session.selectList("LEAVE_FLOW_LOG.query");
		return list;
	}
	
	public List<LeaveFlowLog> queryByLeaveId(Integer leaveId) {
		SqlSession session = sessionFactory.openSession();
		List<LeaveFlowLog> list = session.selectList("LEAVE_FLOW_LOG.queryByLeaveId",leaveId);
		return list;
	}
	

		
}