package com.icss.oa.carapply.service;

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
import com.icss.oa.carapply.dao.CarApplyDao;
import com.icss.oa.carapply.dao.CarFlowLogDao;
import com.icss.oa.carapply.pojo.CarApply;
import com.icss.oa.carapply.pojo.CarFlowLog;
import com.icss.oa.message.pojo.Message;
import com.icss.oa.message.service.MessageService;
import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.system.pojo.Employee;


@Service
@Transactional(rollbackFor=Exception.class)
public class CarApplyService {
	
	@Autowired
	private CarApplyDao carApplyDao;
	
	@Autowired
	private CarFlowLogDao CarFlowLogDao;
	
	@Autowired
	private CarApplyProcessService processService;

	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * ���ų�����ʼִ��
	 * @throws ParseException 
	 */
	public void insert(CarApply carapply) throws ParseException {
		
		//����������������
		Employee adminstration;
		List<Employee> adms = empDao.queryAdminstration();
		if(adms.isEmpty()){
			adminstration = empDao.queryById(0);
		}else {
			adminstration = adms.get(0);
		}
		
		//���복������
		carapply.setLeaderId(adminstration.getEmpId());
		carapply.setLeaderName(adminstration.getEmpName());

		carapply.setStatus(1);//���õ�ǰ״̬Ϊ���ύ
		carapply.setEndstatus(1);//������ֹ״̬Ϊ�����
		
		carApplyDao.insert(carapply);	
		
		//�������ID
		int recordid = carApplyDao.getLastId();
		
		//�������̣������½�û�����Ϊ���̱���
		HashMap<String, Object> proMap = new HashMap<String,Object>();
		proMap.put("loginUser", String.valueOf(carapply.getEmpId()));	

		//��ʼһ���µ�����ʵ��
		ProcessInstance processInstance = processService.startInstance("carApplyProcess", proMap);
		
		//��ѯ����һ���û�����Task����ɴ�����
		Task task = processService.queryByExecutionIdSingle(processInstance.getProcessInstanceId());
		
		String taskId = task.getId();//�������id
		String defineid = processInstance.getProcessDefinitionId();//������̶���id
		String procinsid = processInstance.getProcessInstanceId();//�������ʵ��id
		
		
		proMap.put("administrativeManager", String.valueOf(carapply.getLeaderId()));
		
		//����ύ���������
		processService.completeUserTask(task.getId(),proMap);
		
		//��¼������־
		CarFlowLog flowLog = new CarFlowLog(null, String.valueOf(carapply.getEmpId()), 1, null, null, taskId, defineid, procinsid, String.valueOf(recordid), carapply.getEmpName());
		CarFlowLogDao.insert(flowLog);
		
		Message message = new Message(0, adminstration.getEmpId(), "���յ��µ��ó����룬��<a href='../processcar/queryUserTask.action' style='color:#FF0000' target='_top'>���</a>�鿴");
		messageService.sendMessage(message);
	}
	
	/**
	 * ���س����б�
	 * @return
	 */
	public List<CarApply> query(Integer reimPerson,Pager pager) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getPageNum()*pager.getPageSize());
		map.put("empId", reimPerson);
		return carApplyDao.query(map);
	}
	
	/**
	 * ͨ��id��ѯ������������
	 * @param id
	 * @return
	 */
	public CarApply queryById(Integer id) {
		CarApply reim = carApplyDao.queryById(id);
		return reim;
	}
	
	/**
	 * ͨ���������ż�¼id��ѯ������־
	 * @param id
	 * @return
	 */
	public List<CarFlowLog> queryFlowLog(Integer id) {
		return CarFlowLogDao.queryByCarApplyId(id);
	}
	
	public int count(Integer empId){
		return carApplyDao.count(empId);
	}
}