package com.icss.oa.process.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.message.pojo.Message;
import com.icss.oa.message.service.MessageService;
import com.icss.oa.process.dao.ReimDao;
import com.icss.oa.process.dao.ReimFlowLogDao;
import com.icss.oa.process.pojo.Reimbursement;
import com.icss.oa.process.pojo.ReimFlowLog;
import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.system.pojo.Employee;


@Service
@Transactional(rollbackFor=Exception.class)
public class ReimService {
	
	@Autowired
	private ReimDao reimDao;
	
	@Autowired
	private ReimFlowLogDao reimFlowLogDao;
	
	@Autowired
	private ReimProcessService processService;

	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * ������ʼִ��
	 * @throws ParseException 
	 */
	public void insert(Reimbursement reim) throws ParseException {
		
		Employee emp = empDao.queryById(reim.getEmpId());
		List<Employee> leaders = empDao.quertDeptLeader(emp.getDepartmentId());
		Employee leader;
		if(leaders.isEmpty()){
			leader = empDao.queryById(0);
		}else {
			leader = leaders.get(0);
		}
		
		//���뱨������
		reim.setLeaderId(leader.getEmpId());
		reim.setLeaderName(leader.getEmpName());

		reim.setStatus(1);//���õ�ǰ״̬Ϊ���ύ
		reim.setEndstatus(1);//������ֹ״̬Ϊ�����
		
		reimDao.insert(reim);	
		
		//�����������ID
		int recordid = reimDao.getLastId();
		
		//�������̣������½�û�����Ϊ���̱���
		HashMap<String, Object> proMap = new HashMap<String,Object>();
		proMap.put("loginUser", String.valueOf(reim.getEmpId()));	
		

		if(emp.getEmpId().equals(leader.getEmpId())){
			proMap.put("isDept", 1);
		}else {
			proMap.put("isDept", 0);
			//���뱨�����
			proMap.put("sum", reim.getReimSum());
			
			proMap.put("deptManager", String.valueOf(reim.getLeaderId()));
		}
		
		
		
		//��ʼһ���µ�����ʵ��
		ProcessInstance processInstance = processService.startInstance("reimbursementProcess", proMap);
		
		//��ѯ����һ���û�����Task����ɴ�����
		Task task = processService.queryByExecutionIdSingle(processInstance.getProcessInstanceId());
		
		String taskId = task.getId();//�������id
		String defineid = processInstance.getProcessDefinitionId();//������̶���id
		String procinsid = processInstance.getProcessInstanceId();//�������ʵ��id
		
		
		proMap.put("deptManager", String.valueOf(reim.getLeaderId()));
		
		//����ύ���������
		processService.completeUserTask(task.getId(),proMap);
		
		//��¼������־
		ReimFlowLog flowLog = new ReimFlowLog(null, String.valueOf(reim.getEmpId()), 1, null, null, taskId, defineid, procinsid, String.valueOf(recordid), reim.getEmpName());
		reimFlowLogDao.insert(flowLog);
		
		Message message = new Message(0, leader.getEmpId(), "���յ��µı������룬��<a href='../processreim/queryUserTask.action' style='color:#FF0000' target='_top'>���</a>�鿴");
		messageService.sendMessage(message);
	}
	
	/**
	 * ���ر����б�
	 * @return
	 */
	public List<Reimbursement> query(Integer reimPerson,Pager pager) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getPageNum()*pager.getPageSize());
		map.put("empId", reimPerson);
		return reimDao.query(map);
	}
	
	/**
	 * ͨ��id��ѯ������������
	 * @param id
	 * @return
	 */
	public Reimbursement queryById(Integer id) {
		Reimbursement reim = reimDao.queryById(id);
		return reim;
	}
	
	/**
	 * ͨ��������¼id��ѯ������־
	 * @param id
	 * @return
	 */
	public List<ReimFlowLog> queryFlowLog(Integer id) {
		return reimFlowLogDao.queryByReimId(id);
	}
	
	public int count(Integer empId){
		return reimDao.count(empId);
	}
}