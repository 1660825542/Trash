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
	 * 安排车辆开始执行
	 * @throws ParseException 
	 */
	public void insert(CarApply carapply) throws ParseException {
		
		//设置行政部门主管
		Employee adminstration;
		List<Employee> adms = empDao.queryAdminstration();
		if(adms.isEmpty()){
			adminstration = empDao.queryById(0);
		}else {
			adminstration = adms.get(0);
		}
		
		//插入车辆数据
		carapply.setLeaderId(adminstration.getEmpId());
		carapply.setLeaderName(adminstration.getEmpName());

		carapply.setStatus(1);//设置当前状态为已提交
		carapply.setEndstatus(1);//设置终止状态为审核中
		
		carApplyDao.insert(carapply);	
		
		//获得新增ID
		int recordid = carApplyDao.getLastId();
		
		//启动流程，传入登陆用户名作为流程变量
		HashMap<String, Object> proMap = new HashMap<String,Object>();
		proMap.put("loginUser", String.valueOf(carapply.getEmpId()));	

		//开始一个新的流程实例
		ProcessInstance processInstance = processService.startInstance("carApplyProcess", proMap);
		
		//查询出第一个用户任务Task，完成此任务
		Task task = processService.queryByExecutionIdSingle(processInstance.getProcessInstanceId());
		
		String taskId = task.getId();//获得任务id
		String defineid = processInstance.getProcessDefinitionId();//获得流程定义id
		String procinsid = processInstance.getProcessInstanceId();//获得流程实例id
		
		
		proMap.put("administrativeManager", String.valueOf(carapply.getLeaderId()));
		
		//完成提交申请的任务
		processService.completeUserTask(task.getId(),proMap);
		
		//记录流程日志
		CarFlowLog flowLog = new CarFlowLog(null, String.valueOf(carapply.getEmpId()), 1, null, null, taskId, defineid, procinsid, String.valueOf(recordid), carapply.getEmpName());
		CarFlowLogDao.insert(flowLog);
		
		Message message = new Message(0, adminstration.getEmpId(), "您收到新的用车申请，请<a href='../processcar/queryUserTask.action' style='color:#FF0000' target='_top'>点击</a>查看");
		messageService.sendMessage(message);
	}
	
	/**
	 * 返回车辆列表
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
	 * 通过id查询单条车辆数据
	 * @param id
	 * @return
	 */
	public CarApply queryById(Integer id) {
		CarApply reim = carApplyDao.queryById(id);
		return reim;
	}
	
	/**
	 * 通过车辆安排记录id查询流程日志
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