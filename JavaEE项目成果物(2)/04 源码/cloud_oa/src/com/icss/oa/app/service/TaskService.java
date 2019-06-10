package com.icss.oa.app.service;

import com.icss.oa.app.pojo.ReturnValue;

public interface TaskService {
	ReturnValue getTaskList(String pageNum, String empId);

	ReturnValue deleteTask(String taskId);

	ReturnValue finishTask(String taskId);
}
