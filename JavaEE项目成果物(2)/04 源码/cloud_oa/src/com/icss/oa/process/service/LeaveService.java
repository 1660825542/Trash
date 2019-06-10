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
import com.icss.oa.process.common.UtilTools;
import com.icss.oa.process.dao.LeaveDao;
import com.icss.oa.process.dao.LeaveFlowLogDao;
import com.icss.oa.process.pojo.Leave;
import com.icss.oa.process.pojo.LeaveFlowLog;
import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.system.pojo.Employee;

@Service
@Transactional(rollbackFor=Exception.class)
public class LeaveService {
	
	@Autowired
	private LeaveDao leaveDao;
	
	@Autowired
	private LeaveFlowLogDao leaveFlowLogDao;
	
	@Autowired
	private LeaveProcessService processService;

	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private MessageService messageService;
	/**
	 * ��ٿ�ʼִ��
	 * @throws ParseException 
	 */
	public void insert(Leave leave) throws ParseException {
		
		Employee emp = empDao.queryById(leave.getEmpId());
		List<Employee> leaders = empDao.quertDeptLeader(emp.getDepartmentId());
		Employee leader;
		if(leaders.isEmpty()){
			leader = empDao.queryById(0);
		}else {
			leader = leaders.get(0);
		}
		
		//�����������
		leave.setLeaderId(leader.getEmpId());
		leave.setLeaderName(leader.getEmpName());

		leave.setStatus(1);//���õ�ǰ״̬Ϊ���ύ
		leave.setEndstatus(1);//������ֹ״̬Ϊ�����
		
		leaveDao.insert(leave);	
		
		//����������ID
		int recordid = leaveDao.getLastId();
		
		//�������̣������½�û�����Ϊ���̱���
		HashMap<String, Object> proMap = new HashMap<String,Object>();
		proMap.put("loginUser", String.valueOf(leave.getEmpId()));	
		
		if(emp.getEmpId().equals(leader.getEmpId())){
			proMap.put("isDept", 1);
		}else {
			proMap.put("isDept", 0);
			//�������������
			int days = UtilTools.daysBetween(leave.getStartTime(), leave.getEndTime());		
			proMap.put("days", days);
			
			proMap.put("deptManager", String.valueOf(leave.getLeaderId()));
		}
		
		//��ʼһ���µ�����ʵ��
		ProcessInstance processInstance = processService.startInstance("leaveProcess", proMap);
		
		//��ѯ����һ���û�����Task����ɴ�����
		Task task = processService.queryByExecutionIdSingle(processInstance.getProcessInstanceId());
		
		String taskId = task.getId();//�������id
		String defineid = processInstance.getProcessDefinitionId();//������̶���id
		String procinsid = processInstance.getProcessInstanceId();//�������ʵ��id
		
		//����ύ���������
		processService.completeUserTask(task.getId(),proMap);
		
		//��¼������־
		LeaveFlowLog flowLog = new LeaveFlowLog(null, leave.getEmpId(), 1, null, null, taskId, defineid, procinsid, String.valueOf(recordid), leave.getEmpName());
		leaveFlowLogDao.insert(flowLog);
		
		Message message = new Message(0, leader.getEmpId(), "���յ��µ�������룬��<a href='../processleave/queryUserTask.action' style='color:#FF0000' target='_top'>���</a>�鿴");
		messageService.sendMessage(message);
	}
	
	/**
	 * ��������б�
	 * @return
	 */
	public List<Leave> query(Integer leavePerson,Pager pager) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getPageNum()*pager.getPageSize());
		map.put("empId", leavePerson);
		return leaveDao.query(map);
	}
	
	/**
	 * ͨ��id��ѯ�����������
	 * @param id
	 * @return
	 */
	public Leave queryById(Integer id) {
		Leave empLeave = leaveDao.queryById(id);
		return empLeave;
	}
	
	/**
	 * ͨ����ټ�¼id��ѯ������־
	 * @param id
	 * @return
	 */
	public List<LeaveFlowLog> queryFlowLog(Integer id) {
		return leaveFlowLogDao.queryByLeaveId(id);
	}
	
	public int count(Integer empId){
		return leaveDao.count(empId);
	}
}