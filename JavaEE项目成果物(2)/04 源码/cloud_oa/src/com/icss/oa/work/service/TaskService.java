package com.icss.oa.work.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.work.dao.TaskDao;
import com.icss.oa.work.dao.TaskListDao;
import com.icss.oa.work.pojo.Task;
import com.icss.oa.work.pojo.TaskList;

@Service("myTaskService")
@Transactional(rollbackFor = Exception.class)
public class TaskService {

	@Autowired
	private TaskDao dao;
	
	@Autowired
	private TaskListDao tlDao;
	
	//private TaskIndexDao indexDao;
	
	public void insert(Task task, Employee emp, Employee manager) throws IOException {
		dao.insert(task);
		int taskId = dao.getPrimaryKey();
		TaskList tl = new TaskList(emp.getEmpId(),manager.getEmpId(),taskId);
		tlDao.insert(tl);
	}
	
	public void update(Task task) throws IOException{
		dao.update(task);
	}
	
	public void delete(Integer taskId) throws IOException{
		tlDao.delete(taskId);	
		dao.delete(taskId);
	}
	
	public List<Map<String, Object>> query(Pager pager)
	{
		List<Map<String, Object>> list = dao.query(pager);
		return list;
	}
	
	public Map<String, Object> queryById(Integer taskId) {
		Map<String, Object> task = dao.queryById(taskId);
		return task;
	}
	
	public List<Map<String, Object>> queryByCondition(Pager pager, String condition, Integer data) {
		
		List<Map<String, Object>> list = dao.queryByCondition(pager, condition , data);
		return list;
	}
	
	public List<Map<String, Object>> multipleQuery(Pager pager, Date date, String empName, String managerName) {
		
		List<Map<String, Object>> list = dao.multipleQuery(pager, date, empName, managerName);
		return list;
	}
	
	public int getCount() {

		return dao.getCount();
	}

	public int getConditionCount(String condition, Object data) {

		return dao.getConditionCount(condition, data);
	}
	
	public int getMultipleCount(Date date, String empName, String managerName) {

		return dao.getMultipleCount(date, empName, managerName);
	}
	
	
}
	

