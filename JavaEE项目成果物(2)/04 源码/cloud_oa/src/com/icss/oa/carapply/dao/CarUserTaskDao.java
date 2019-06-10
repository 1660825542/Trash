package com.icss.oa.carapply.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.carapply.pojo.CarUserTask;

@Repository
public class CarUserTaskDao {

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	public List<CarUserTask> query(String assignee) {
		SqlSession session = sessionFactory.openSession();
		List<CarUserTask> list = session.selectList("CAR_USER_TASK.query", assignee);
		return list;
	}
	
}