package com.icss.oa.process.service;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.icss.oa.message.pojo.Message;
import com.icss.oa.message.service.MessageService;
import com.icss.oa.process.dao.ReimDao;
import com.icss.oa.process.dao.ReimFlowLogDao;
import com.icss.oa.process.pojo.Reimbursement;
import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.system.pojo.Employee;

/**
 * 财务给款任务监听器
 * 动态设置任务代理人
 * @author Administrator
 *
 */
@Component
public class ReimListener implements TaskListener {

	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private ReimDao reimDao;
	
	@Autowired
	private ReimFlowLogDao logDao;
	
	@Autowired
	private MessageService messageService;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		Employee money;
		List<Employee> moneys = empDao.queryMoney();
		if(moneys.isEmpty()){
			money = empDao.queryById(0);
		}else {
			money = moneys.get(0);
		}
		
		//设置代理人
		delegateTask.setAssignee(String.valueOf(money.getEmpId()));
		
		//找到对应的流程，设置流程当前的最终状态为5（待出账）
		String procinsid = delegateTask.getProcessInstanceId();
		Reimbursement reim = new Reimbursement();
		reim.setReimId(Integer.parseInt(logDao.queryRecordidByProId(procinsid)));
		reim.setEndstatus(5);
		reimDao.updateStatus(reim);
		System.out.println("设置出纳为"+money.getEmpName());
		
		Message message = new Message(0, money.getEmpId(), "收到新的报销申请，请尽快出账！<a href='../processreim/queryUserTask.action' style='color:#FF0000' target='_top'>点击</a>查看");
		messageService.sendMessage(message);
	}

}