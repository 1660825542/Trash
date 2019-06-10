package com.icss.oa.work.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.work.pojo.TaskList;

@Repository
public class TaskListDao {
	@Autowired
	private SqlSessionFactory factory;

	public void insert(TaskList tl) {
		SqlSession session = factory.openSession();
		session.insert("TASK_LIST.insert", tl);
	}

	public void delete(Integer taskId) {
		SqlSession session = factory.openSession();
		session.delete("TASK_LIST.delete", taskId);
	}
}
