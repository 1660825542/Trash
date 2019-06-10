package com.icss.oa.carapply.service;

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

import com.icss.oa.carapply.dao.CarApplyDao;
import com.icss.oa.carapply.dao.CarFlowLogDao;
import com.icss.oa.carapply.dao.CarUserTaskDao;
import com.icss.oa.carapply.pojo.CarFlowLog;
import com.icss.oa.carapply.pojo.CarUserTask;
import com.icss.oa.carapply.pojo.CarApply;

/**
 * ����ҵ��ʵ��
 * 
 * @author Administrator
 * 
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CarApplyProcessService {

	/**************** ��־���� ****************/
	private static final Logger logger = LoggerFactory
			.getLogger(CarApplyProcessService.class);

	@Resource(name = "processEngine")
	private ProcessEngine processEngine;

	@Resource(name = "repositoryService")
	private RepositoryService repositoryService;// �������̶���

	@Resource(name = "runtimeService")
	private RuntimeService runtimeService;// ��������ʵ��

	@Resource(name = "taskService")
	private TaskService taskService;// ������������

	@Resource(name = "historyService")
	private HistoryService historyService;// ������ʷ��¼

	@Autowired
	private CarUserTaskDao carUserTaskDao;// UserTaskBean���ݷ���

	@Autowired
	private CarFlowLogDao carFlowLogDao;// ������־���ݷ���

	@Autowired
	private CarApplyDao carApplyDao;// �����������ݷ���

	/**
	 * �������̶���
	 */
	public void deployProcess() {
		Deployment deployment = repositoryService.createDeployment()
				.addClasspathResource("carApply.bpmn20.xml").deploy();
		
		System.out.println("���̶����ѷ���:id=" + deployment.getId() + ",name="
				+ deployment.getName());
	}

	/**
	 * ��������ʵ��
	 * 
	 * @param processInstanceByKey
	 *            ���̲���KEY
	 * @param proMap
	 *            ���̱���
	 */
	public ProcessInstance startInstance(String processInstanceByKey,
			Map<String, Object> proMap) {

		ProcessInstance processInstance = null;

		// ���û�����̱�����ֱ�����������������̱���
		if (proMap != null) {
			processInstance = runtimeService.startProcessInstanceByKey(
					processInstanceByKey, proMap);
		} else {
			processInstance = runtimeService
					.startProcessInstanceByKey(processInstanceByKey);
		}

		System.out.println("����ʵ����������id=" + processInstance.getId());

		return processInstance;
	}

	/**
	 * ���UserTask
	 * 
	 * @param taskId
	 *            ����ID
	 * @param proMap
	 *            ���̱���
	 */
	public void completeUserTask(String taskId, Map<String, Object> proMap) {
		if (proMap != null) {
			taskService.complete(taskId, proMap);
		} else {
			taskService.complete(taskId);
		}
	}

	/**
	 * ����executionId��ѯtask
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
	 * ��������ID�������ʵ��
	 * 
	 * @param taskId
	 *            ����ID
	 * @return
	 * @throws Exception
	 */
	private TaskEntity findTaskById(String taskId) {

		TaskEntity task = (TaskEntity) taskService.createTaskQuery()
				.taskId(taskId).singleResult();
		if (task == null) {
			throw new RuntimeException("����ʵ��δ�ҵ�!");
		}
		return task;
	}

	/**
	 * ��������ID��ȡ��Ӧ������ʵ��
	 * 
	 * @param taskId
	 *            ����ID
	 * @return
	 * @throws Exception
	 */
	public ProcessInstance findProcessInstanceByTaskId(String taskId) {
		// �ҵ�����ʵ��
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()
				.processInstanceId(findTaskById(taskId).getProcessInstanceId())
				.singleResult();
		if (processInstance == null) {
			throw new RuntimeException("����ʵ��δ�ҵ�!");
		}
		return processInstance;
	}

	/**
	 * ��������ʵ��ID�鿴�����Ƿ����
	 * 
	 * @param processInstanceId
	 *            ����ID
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
	 * ��������ʵ��id������̸���ͼ
	 * @param processInstanceId
	 * @return
	 */
	public InputStream getProcessImage(String processInstanceId) {
		// �ҵ�����ʵ��
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();

		// ͨ�����̶���ID�õ�BpmnModel
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance
				.getProcessDefinitionId());
		// �õ�����ִ�еĻ���ID
		List<String> activeIds = runtimeService
				.getActiveActivityIds(processInstanceId);
		// ��ֹ����ͼ��������
		Context.setProcessEngineConfiguration(((ProcessEngineImpl) processEngine)
				.getProcessEngineConfiguration());
		// ��ӡ����ͼ
		InputStream is = ProcessDiagramGenerator.generateDiagram(bpmnModel,
				"png", activeIds);
		return is;
	}

	/**
	 * ��ѯ��ǰ�û��������б�
	 * 
	 * @param assignee
	 * @return
	 */
	public List<CarUserTask> queryUserTask(String assignee) {
		List<CarUserTask> list = carUserTaskDao.query(assignee);
		return list;
	}

	/**
	 * ������ɴ����������̽ڵ�
	 * 
	 * @param userTask
	 */
	public void handleTask(CarUserTask userTask) {

		// ���definitionId���̶���id
		String definitionId = this.findProcessInstanceByTaskId(
				userTask.getTaskid()).getProcessDefinitionId();

		/* ��ɵ�ǰ���̽ڵ� */
		// �������̱���action 1.�ύ���� 2�����ɹ���3����ʧ��
		HashMap<String, Object> proMap = new HashMap<String, Object>();
		proMap.put("action", userTask.getAction());
		
		System.out.println(proMap.toString());
		// ���Task
		this.completeUserTask(userTask.getTaskid(), proMap);

		/* ��¼������־ */
		CarFlowLog flowLog = new CarFlowLog(
				null,
				String.valueOf(userTask.getUserid()),
				userTask.getAction(),
				null, 
				userTask.getOpinion(),
				userTask.getTaskid(), 
				definitionId, 
				userTask.getProcinsid(),
				String.valueOf(userTask.getRecordid()),
				userTask.getUsername()
				);
		
		carFlowLogDao.insert(flowLog);

		/* ���³���״̬ */
		CarApply carapply = new CarApply();
		carapply.setCarapplyId(userTask.getRecordid());// ��������id
		carapply.setStatus(userTask.getAction());// ��ǰ״̬

		// �ж������Ƿ��Ѿ�����
		boolean isEnd = this.queryProcessIsEnd(userTask.getProcinsid());
		if (isEnd) {
			carapply.setEndstatus(userTask.getAction());// ����״̬
		}

		// ��������
		carApplyDao.updateStatus(carapply);
	}

}