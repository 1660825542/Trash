package com.icss.oa.process.service;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.icss.oa.message.pojo.Message;
import com.icss.oa.message.service.MessageService;
import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.system.pojo.Employee;

/**
 * 总经理审批任务监听器
 * 动态设置任务代理人
 * @author Administrator
 *
 */
@Component
public class SuperManagerReimListener implements TaskListener {

	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private MessageService messageService;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		Employee manager;
		List<Employee> mangers = empDao.queryManager();
		if(mangers.isEmpty()){
			manager = empDao.queryById(0);
		}else {
			manager = mangers.get(0);
		}
		delegateTask.setAssignee(String.valueOf(manager.getEmpId()));
				
		System.out.println("设置总经理为"+manager.getEmpName());
		Message message = new Message(0, manager.getEmpId(), "您收到新的报销申请，请<a href='../processreim/queryUserTask.action' style='color:#FF0000' target='_top'>点击</a>查看");
		messageService.sendMessage(message);
	}

}