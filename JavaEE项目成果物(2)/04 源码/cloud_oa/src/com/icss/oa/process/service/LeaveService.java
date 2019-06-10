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
	 * 请假开始执行
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
		
		//插入请假数据
		leave.setLeaderId(leader.getEmpId());
		leave.setLeaderName(leader.getEmpName());

		leave.setStatus(1);//设置当前状态为已提交
		leave.setEndstatus(1);//设置终止状态为审核中
		
		leaveDao.insert(leave);	
		
		//获得新增请假ID
		int recordid = leaveDao.getLastId();
		
		//启动流程，传入登陆用户名作为流程变量
		HashMap<String, Object> proMap = new HashMap<String,Object>();
		proMap.put("loginUser", String.valueOf(leave.getEmpId()));	
		
		if(emp.getEmpId().equals(leader.getEmpId())){
			proMap.put("isDept", 1);
		}else {
			proMap.put("isDept", 0);
			//返回请求的天数
			int days = UtilTools.daysBetween(leave.getStartTime(), leave.getEndTime());		
			proMap.put("days", days);
			
			proMap.put("deptManager", String.valueOf(leave.getLeaderId()));
		}
		
		//开始一个新的流程实例
		ProcessInstance processInstance = processService.startInstance("leaveProcess", proMap);
		
		//查询出第一个用户任务Task，完成此任务
		Task task = processService.queryByExecutionIdSingle(processInstance.getProcessInstanceId());
		
		String taskId = task.getId();//获得任务id
		String defineid = processInstance.getProcessDefinitionId();//获得流程定义id
		String procinsid = processInstance.getProcessInstanceId();//获得流程实例id
		
		//完成提交申请的任务
		processService.completeUserTask(task.getId(),proMap);
		
		//记录流程日志
		LeaveFlowLog flowLog = new LeaveFlowLog(null, leave.getEmpId(), 1, null, null, taskId, defineid, procinsid, String.valueOf(recordid), leave.getEmpName());
		leaveFlowLogDao.insert(flowLog);
		
		Message message = new Message(0, leader.getEmpId(), "您收到新的请假申请，请<a href='../processleave/queryUserTask.action' style='color:#FF0000' target='_top'>点击</a>查看");
		messageService.sendMessage(message);
	}
	
	/**
	 * 返回请假列表
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
	 * 通过id查询单条请假数据
	 * @param id
	 * @return
	 */
	public Leave queryById(Integer id) {
		Leave empLeave = leaveDao.queryById(id);
		return empLeave;
	}
	
	/**
	 * 通过请假记录id查询流程日志
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