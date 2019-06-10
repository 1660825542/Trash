package com.icss.oa.work.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.work.dao.AgentDao;
import com.icss.oa.work.pojo.Agent;

@Service
@Transactional(rollbackFor = Exception.class)
public class AgentService {

	@Autowired
	private AgentDao agentDao;
	
	private EmployeeDao empDao;
	
	public void delete(Integer uaId) {
		agentDao.delete(uaId);
	}
	
	public void setAgent(Integer empId, Integer managerId, Integer state) {
		Agent agent = new Agent(empId, managerId, state);
		agentDao.insert(agent);	
	}
	
	public List<Agent> query(Pager pager) {
		List<Agent> list = agentDao.query(pager);
		return list;
	}
	
	public List<Map<String, Object>> queryByCondition(Pager pager, String condition, Integer data) {
		List<Map<String, Object>> list = agentDao.queryByCondition(pager, condition, data);
		return list;
	}

	public List<Agent> simpleQuery() {
		// TODO Auto-generated method stub
		return agentDao.simpleQuery();
	}

	public int getConditionCount(String condition, int data) {
		// TODO Auto-generated method stub
		return agentDao.getConditionCount(condition, data);
	}
	
	public int getAgent(Integer empId, Integer managerId) {
		return agentDao.getAgent(empId, managerId);
	}
	
}
