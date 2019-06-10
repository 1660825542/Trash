package com.icss.oa.process.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.process.pojo.LeaveUserTask;

@Repository
public class LeaveUserTaskDao {

	@Autowired
	private SqlSessionFactory factory;
	
	public List<LeaveUserTask> query(String assignee) {
		SqlSession session = factory.openSession();
		List<LeaveUserTask> list = session.selectList("LEAVE_USER_TASK.query", assignee);
		return list;
	}
	
}