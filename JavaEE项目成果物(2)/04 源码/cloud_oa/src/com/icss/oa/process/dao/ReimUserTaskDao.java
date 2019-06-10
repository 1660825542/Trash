package com.icss.oa.process.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.process.pojo.ReimUserTask;

@Repository
public class ReimUserTaskDao {

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	public List<ReimUserTask> query(String assignee) {
		SqlSession session = sessionFactory.openSession();
		List<ReimUserTask> list = session.selectList("REIM_USER_TASK.query", assignee);
		return list;
	}
	
}