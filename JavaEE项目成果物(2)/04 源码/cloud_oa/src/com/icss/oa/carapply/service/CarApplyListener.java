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
 * �������������
 * ��̬�������������
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
		
		
		//���ô�����Ϊ��������
		Employee carAdmin;
		List<Employee> cars = empDao.queryCarAdmin();
		if(cars.isEmpty()){
			carAdmin = empDao.queryById(0);
		}else {
			carAdmin = cars.get(0);
		}
		delegateTask.setAssignee(String.valueOf(carAdmin.getEmpId()));
		
		//�ҵ���Ӧ�����̣��������̵�ǰ������״̬Ϊ5�����ɳ���
		String procinsid = delegateTask.getProcessInstanceId();
		CarApply carapply = new CarApply();
		carapply.setCarapplyId(Integer.parseInt(logDao.queryRecordidByProId(procinsid)));
		carapply.setEndstatus(5);
		CarApplyDao.updateStatus(carapply);
		System.out.println("���ó�������Ϊ"+carAdmin.getEmpName());
		
		Message message = new Message(0, carAdmin.getEmpId(), "����׼�µ��ó����룬�뾡�������<a href='../processcar/queryUserAllot.action' style='color:#FF0000' target='_top'>���</a>�鿴");
		messageService.sendMessage(message);
	}

}