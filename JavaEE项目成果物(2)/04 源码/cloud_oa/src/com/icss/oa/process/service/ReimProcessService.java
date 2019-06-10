package com.icss.oa.process.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.process.dao.ReimDao;
import com.icss.oa.process.dao.ReimFlowLogDao;
import com.icss.oa.process.dao.ReimUserTaskDao;
import com.icss.oa.process.pojo.ReimFlowLog;
import com.icss.oa.process.pojo.ReimUserTask;
import com.icss.oa.process.pojo.Reimbursement;

/**
 * 流程业务实现
 * 
 * @author Administrator
 * 
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReimProcessService {

	/**************** 日志对象 ****************/
	private static final Logger logger = LoggerFactory
			.getLogger(ReimProcessService.class);

	@Resource(name = "processEngine")
	private ProcessEngine processEngine;

	@Resource(name = "repositoryService")
	private RepositoryService repositoryService;// 操作流程定义

	@Resource(name = "runtimeService")
	private RuntimeService runtimeService;// 操作流程实例

	@Resource(name = "taskService")
	private TaskService taskService;// 操作流程任务

	@Resource(name = "historyService")
	private HistoryService historyService;// 操作历史记录

	@Autowired
	private ReimUserTaskDao reimUserTaskDao;// UserTaskBean数据访问

	@Autowired
	private ReimFlowLogDao reimFlowLogDao;// 流程日志数据访问

	@Autowired
	private ReimDao reimDao;// 报销数据访问

	/**
	 * 发布流程定义
	 */
	public void deployProcess() {
		Deployment deployment = repositoryService.createDeployment()
				.addClasspathResource("reimbursement.bpmn20.xml").deploy();
		
		System.out.println("流程定义已发布:id=" + deployment.getId() + ",name="
				+ deployment.getName());
	}

	/**
	 * 启动流程实例
	 * 
	 * @param processInstanceByKey
	 *            流程部署KEY
	 * @param proMap
	 *            流程变量
	 */
	public ProcessInstance startInstance(String processInstanceByKey,
			Map<String, Object> proMap) {

		ProcessInstance processInstance = null;

		// 如果没有流程变量，直接启动，否则传入流程变量
		if (proMap != null) {
			processInstance = runtimeService.startProcessInstanceByKey(
					processInstanceByKey, proMap);
		} else {
			processInstance = runtimeService
					.startProcessInstanceByKey(processInstanceByKey);
		}

		System.out.println("流程实例已启动：id=" + processInstance.getId());

		return processInstance;
	}

	/**
	 * 完成UserTask
	 * 
	 * @param taskId
	 *            任务ID
	 * @param proMap
	 *            流程变量
	 */
	public void completeUserTask(String taskId, Map<String, Object> proMap) {
		if (proMap != null) {
			taskService.complete(taskId, proMap);
		} else {
			taskService.complete(taskId);
		}
	}

	/**
	 * 根据executionId查询task
	 * 
	 * @param executionId
	 * @return
	 */
	public Task queryByExecutionIdSingle(String executionId) {
		Task task = taskService.createTaskQuery().executionId(executionId)
				.singleResult();
		return task;
	}

	/**
	 * 根据任务ID获得任务实例
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	private TaskEntity findTaskById(String taskId) {

		TaskEntity task = (TaskEntity) taskService.createTaskQuery()
				.taskId(taskId).singleResult();
		if (task == null) {
			throw new RuntimeException("任务实例未找到!");
		}
		return task;
	}

	/**
	 * 根据任务ID获取对应的流程实例
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	public ProcessInstance findProcessInstanceByTaskId(String taskId) {
		// 找到流程实例
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()
				.processInstanceId(findTaskById(taskId).getProcessInstanceId())
				.singleResult();
		if (processInstance == null) {
			throw new RuntimeException("流程实例未找到!");
		}
		return processInstance;
	}

	/**
	 * 根据流程实例ID查看流程是否结束
	 * 
	 * @param processInstanceId
	 *            流程ID
	 */
	public boolean queryProcessIsEnd(String processInstanceId) {
		HistoricProcessInstance historicProcessInstance = historyService
				.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		if (historicProcessInstance != null
				&& historicProcessInstance.getStartTime() != null
				&& historicProcessInstance.getEndTime() != null) {
			return true;
		}
		return false;
	}

	/**
	 * 根据流程实例id获得流程跟踪图
	 * @param processInstanceId
	 * @return
	 */
	public InputStream getProcessImage(String processInstanceId) {
		// 找到流程实例
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();

		// 通过流程定义ID得到BpmnModel
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance
				.getProcessDefinitionId());
		// 得到正在执行的环节ID
		List<String> activeIds = runtimeService
				.getActiveActivityIds(processInstanceId);
		// 防止流程图中文乱码
		Context.setProcessEngineConfiguration(((ProcessEngineImpl) processEngine)
				.getProcessEngineConfiguration());
		// 打印流程图
		InputStream is = ProcessDiagramGenerator.generateDiagram(bpmnModel,
				"png", activeIds);
		return is;
	}

	/**
	 * 查询当前用户的任务列表
	 * 
	 * @param assignee
	 * @return
	 */
	public List<ReimUserTask> queryUserTask(String assignee) {
		List<ReimUserTask> list = reimUserTaskDao.query(assignee);
		return list;
	}

	/**
	 * 处理完成代办任务流程节点
	 * 
	 * @param userTask
	 */
	public void handleTask(ReimUserTask userTask) {

		// 获得definitionId流程定义id
		String definitionId = this.findProcessInstanceByTaskId(
				userTask.getTaskid()).getProcessDefinitionId();

		/* 完成当前流程节点 */
		// 设置流程变量action 2审批成功，3审批失败， 4.完成付款
		HashMap<String, Object> proMap = new HashMap<String, Object>();
		proMap.put("action", userTask.getAction());
		// 完成Task
		this.completeUserTask(userTask.getTaskid(), proMap);

		/* 记录流程日志 */
		ReimFlowLog flowLog = new ReimFlowLog(null,String.valueOf(userTask.getUserid()),
				userTask.getAction(),null, userTask.getOpinion(),
				userTask.getTaskid(), definitionId, userTask.getProcinsid(),
				String.valueOf(userTask.getRecordid()),userTask.getUsername());
		reimFlowLogDao.insert(flowLog);

		/* 更新请假状态 */
		Reimbursement reim = new Reimbursement();
		reim.setReimId(userTask.getRecordid());// 报销id
		reim.setStatus(userTask.getAction());// 当前状态

		// 判断流程是否已经结束
		boolean isEnd = this.queryProcessIsEnd(userTask.getProcinsid());
		if (isEnd) {
			reim.setEndstatus(userTask.getAction());// 结束状态
		}

		// 更新数据
		reimDao.updateStatus(reim);
	}

}