package com.icss.oa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.app.dao.AppTaskDao;
import com.icss.oa.app.pojo.ReturnValue;
import com.icss.oa.common.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskServiceImpl implements TaskService {

	@Autowired
	private AppTaskDao appTaskDao;

	public ReturnValue getTaskList(String pageNum, String empId) {
		ReturnValue rv = null;
		int recordCount =  appTaskDao.getEmpTaskCount(empId);
		Pager pager = new Pager(recordCount, 15, Integer.parseInt(pageNum));
		if (Integer.parseInt(pageNum) > (pager.getPageCount() - 1)) {
			rv = new ReturnValue(2, "已加载全部数据", appTaskDao.getTaskList(pager,
					empId));
		} else {
			rv = new ReturnValue(1, "请求成功",
					appTaskDao.getTaskList(pager, empId));
		}
		return rv;
	}

	@Override
	public ReturnValue deleteTask(String taskId) {
		appTaskDao.delete(Integer.parseInt(taskId));
		return new ReturnValue(1, "删除成功");
	}

	@Override
	public ReturnValue finishTask(String taskId) {
		appTaskDao.finish(Integer.parseInt(taskId));
		return new ReturnValue(1, "请求成功");
	}
}
