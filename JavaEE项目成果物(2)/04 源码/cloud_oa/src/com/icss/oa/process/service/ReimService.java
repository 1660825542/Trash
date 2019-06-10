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
	 * 报销开始执行
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
		
		//插入报销数据
		reim.setLeaderId(leader.getEmpId());
		reim.setLeaderName(leader.getEmpName());

		reim.setStatus(1);//设置当前状态为已提交
		reim.setEndstatus(1);//设置终止状态为审核中
		
		reimDao.insert(reim);	
		
		//获得新增报销ID
		int recordid = reimDao.getLastId();
		
		//启动流程，传入登陆用户名作为流程变量
		HashMap<String, Object> proMap = new HashMap<String,Object>();
		proMap.put("loginUser", String.valueOf(reim.getEmpId()));	
		

		if(emp.getEmpId().equals(leader.getEmpId())){
			proMap.put("isDept", 1);
		}else {
			proMap.put("isDept", 0);
			//传入报销金额
			proMap.put("sum", reim.getReimSum());
			
			proMap.put("deptManager", String.valueOf(reim.getLeaderId()));
		}
		
		
		
		//开始一个新的流程实例
		ProcessInstance processInstance = processService.startInstance("reimbursementProcess", proMap);
		
		//查询出第一个用户任务Task，完成此任务
		Task task = processService.queryByExecutionIdSingle(processInstance.getProcessInstanceId());
		
		String taskId = task.getId();//获得任务id
		String defineid = processInstance.getProcessDefinitionId();//获得流程定义id
		String procinsid = processInstance.getProcessInstanceId();//获得流程实例id
		
		
		proMap.put("deptManager", String.valueOf(reim.getLeaderId()));
		
		//完成提交申请的任务
		processService.completeUserTask(task.getId(),proMap);
		
		//记录流程日志
		ReimFlowLog flowLog = new ReimFlowLog(null, String.valueOf(reim.getEmpId()), 1, null, null, taskId, defineid, procinsid, String.valueOf(recordid), reim.getEmpName());
		reimFlowLogDao.insert(flowLog);
		
		Message message = new Message(0, leader.getEmpId(), "您收到新的报销申请，请<a href='../processreim/queryUserTask.action' style='color:#FF0000' target='_top'>点击</a>查看");
		messageService.sendMessage(message);
	}
	
	/**
	 * 返回报销列表
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
	 * 通过id查询单条报销数据
	 * @param id
	 * @return
	 */
	public Reimbursement queryById(Integer id) {
		Reimbursement reim = reimDao.queryById(id);
		return reim;
	}
	
	/**
	 * 通过报销记录id查询流程日志
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