package com.icss.oa.work.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.common.Pager;
import com.icss.oa.work.pojo.Agent;

@Repository
public class AgentDao {
	@Autowired
	private SqlSessionFactory factory;
	
	public void insert(Agent agent) {
		SqlSession session = factory.openSession();
		session.insert("AGENT.insert", agent);
	}

	public void delete(Integer agentId) {
		SqlSession session = factory.openSession();
		session.delete("AGENT.delete", agentId);
	}
	
	public List<Agent> query(Pager pager) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		List<Agent> list = session.selectList("AGENT.query",map);
		return list;
	}
	
	public List<Map<String, Object>> queryByCondition(Pager pager, String condition, Integer data) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("data", data);
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		List<Map<String, Object>> list = session.selectList("AGENT.queryByCondition",map);
		return list;
	}
	
	public int getAgent(Integer empId, Integer managerId) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("empId", empId);
		map.put("managerId", managerId);
		int agent = session.selectOne("AGENT.getAgent",map);
		return agent;
	}
	
	public int getCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("AGENT.getCount");
		return count;
	}
	
	public int getConditionCount(String condition, Integer data) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("data",data);
		int count = session.selectOne("AGENT.getConditionCount",map);
		return count;
	}
	
	public int getPrimaryKey() {
		SqlSession session = factory.openSession();
		int primaryKey = session.selectOne("AGENT.getPrimaryKey");
		return primaryKey;
	}

	public List<Agent> simpleQuery() {
		SqlSession session = factory.openSession();
		List<Agent> agent = session.selectList("AGENT.simpleQuery");
		return agent;
	}
}
