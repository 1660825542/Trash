package com.icss.oa.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.icss.oa.common.Pager;

@Repository
@Scope("prototype")
public class AppTaskDao {
	@Autowired
	private SqlSessionFactory factory;

	public List<Map<String, String>> getTaskList(Pager pager, String empId) {
		SqlSession session = factory.openSession();
		Map<String, String> map = new HashMap<String, String>(3);
		map.put("empId", empId);
		map.put("start", Integer.toString(pager.getStart()));
		map.put("end",
				Integer.toString(pager.getStart() + pager.getPageSize() - 1));
		List<Map<String, String>> list = session.selectList("TASK.queryForapp",
				map);
		session.close();
		return list;
	}

	public int getEmpTaskCount(String empIds) {
		int empId = Integer.parseInt(empIds);
		SqlSession session = factory.openSession();
		return session.selectOne("TASK.queryEmpTaskCount", empId);
	}

	public void delete(Integer taskId) {
		SqlSession session = factory.openSession();
		session.delete("TASK.delete", taskId);
	}

	public void finish(Integer taskId) {
		SqlSession session = factory.openSession();
		session.delete("TASK.finishTask", taskId);
	}
}
