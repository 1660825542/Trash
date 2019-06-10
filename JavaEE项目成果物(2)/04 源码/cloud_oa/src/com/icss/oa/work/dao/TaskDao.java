package com.icss.oa.work.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.common.Pager;
import com.icss.oa.work.pojo.Task;

@Repository
public class TaskDao {
	@Autowired
	private SqlSessionFactory factory;

	public void insert(Task task) {
		SqlSession session = factory.openSession();
		session.insert("TASK.insert", task);
	}

	
	public void update(Task task) {
		SqlSession session = factory.openSession();
		session.update("TASK.update", task);
	}

	public void delete(Integer taskId) {
		SqlSession session = factory.openSession();
		session.delete("TASK.delete", taskId);
	}

	public List<Map<String, Object>> query(Pager pager) {
		SqlSession session = factory.openSession();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		List<Map<String, Object>> list = session.selectList("TASK.query", map);
		return list;
	}
	
	public Map<String, Object> queryById(Integer taskId) {
		SqlSession session = factory.openSession();
		Map<String, Object> task = session.selectOne("TASK.queryById", taskId);
		return task;
	}
	
	public List<Map<String, Object>> queryByCondition(Pager pager, String condition, Integer data) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("data", data);
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		List<Map<String, Object>> list = session.selectList("TASK.queryByCondition", map);
		return list;
	}
	
	public List<Map<String, Object>> multipleQuery(Pager pager, Date date, String empName, String managerName) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("empName", empName);
		map.put("managerName", managerName);
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		List<Map<String, Object>> list = session.selectList("TASK.multipleQuery", map);
		return list;
	}
	
	public int getCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("TASK.getCount");
		return count;
	}
	
	/**
	 * 返回满足条件的总记录数
	 * @return
	 */
	public int getConditionCount(String condition, Object data) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("data",data);
		int count = session.selectOne("TASK.getConditionCount",map);
		return count;
	}
	
	public int getMultipleCount(Date date, String empName, String managerName) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("empName", empName);
		map.put("managerName", managerName);
		int count = session.selectOne("TASK.getMultipleCount",map);
		return count;
	}
	
	public int getPrimaryKey() {
		SqlSession session = factory.openSession();
		int primaryKey = session.selectOne("TASK.getPrimaryKey");
		return primaryKey;
	}
}
