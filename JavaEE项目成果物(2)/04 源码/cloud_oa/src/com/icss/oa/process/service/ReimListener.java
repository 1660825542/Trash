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
 * ����������������
 * ��̬�������������
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
		
		//���ô�����
		delegateTask.setAssignee(String.valueOf(money.getEmpId()));
		
		//�ҵ���Ӧ�����̣��������̵�ǰ������״̬Ϊ5�������ˣ�
		String procinsid = delegateTask.getProcessInstanceId();
		Reimbursement reim = new Reimbursement();
		reim.setReimId(Integer.parseInt(logDao.queryRecordidByProId(procinsid)));
		reim.setEndstatus(5);
		reimDao.updateStatus(reim);
		System.out.println("���ó���Ϊ"+money.getEmpName());
		
		Message message = new Message(0, money.getEmpId(), "�յ��µı������룬�뾡����ˣ�<a href='../processreim/queryUserTask.action' style='color:#FF0000' target='_top'>���</a>�鿴");
		messageService.sendMessage(message);
	}

}