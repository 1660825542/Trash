package com.icss.oa.carapply.service;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.icss.oa.carapply.dao.CarApplyDao;
import com.icss.oa.carapply.dao.CarFlowLogDao;
import com.icss.oa.carapply.pojo.CarApply;
import com.icss.oa.message.pojo.Message;
import com.icss.oa.message.service.MessageService;
import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.system.pojo.Employee;

/**
 * 车队任务监听器
 * 动态设置任务代理人
 * @author Administrator
 *
 */
@Component
public class CarApplyListener implements TaskListener {

	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private CarApplyDao CarApplyDao;
	
	@Autowired
	private CarFlowLogDao logDao;

	@Autowired
	private MessageService messageService;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		
		
		//设置代理人为车队主管
		Employee carAdmin;
		List<Employee> cars = empDao.queryCarAdmin();
		if(cars.isEmpty()){
			carAdmin = empDao.queryById(0);
		}else {
			carAdmin = cars.get(0);
		}
		delegateTask.setAssignee(String.valueOf(carAdmin.getEmpId()));
		
		//找到对应的流程，设置流程当前的最终状态为5（已派车）
		String procinsid = delegateTask.getProcessInstanceId();
		CarApply carapply = new CarApply();
		carapply.setCarapplyId(Integer.parseInt(logDao.queryRecordidByProId(procinsid)));
		carapply.setEndstatus(5);
		CarApplyDao.updateStatus(carapply);
		System.out.println("设置车队主管为"+carAdmin.getEmpName());
		
		Message message = new Message(0, carAdmin.getEmpId(), "已批准新的用车申请，请尽快出车！<a href='../processcar/queryUserAllot.action' style='color:#FF0000' target='_top'>点击</a>查看");
		messageService.sendMessage(message);
	}

}