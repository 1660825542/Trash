package com.icss.oa.work.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icss.oa.common.BaseAction;
import com.icss.oa.common.Pager;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.EmployeeService;
import com.icss.oa.work.pojo.Agent;
import com.icss.oa.work.pojo.Task;
import com.icss.oa.work.service.AgentService;
import com.icss.oa.work.service.TaskService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/work")
@Results({ @Result(name = "success", location = "/work/editMy.action", type = "redirect") })
public class TaskAction extends BaseAction implements ModelDriven<Task>{
  
	private Task task = new Task();// 实体对象
	
	private Agent agent = new Agent();
	
	private String emp;
	
	private String manager;
	
	private int empId;
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	private int managerId;

	private int pageNum = 1;// 页码
	
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	private Date date;
	
	@Autowired
	private TaskService service;// 业务逻辑对象
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private EmployeeService empService;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	public String getEmp() {
		return emp;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Action(value = "query")
	public String query() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 分页对象
		Pager pager = new Pager(service.getConditionCount("emp", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()), pageNum);
		// 调用service获得数据集合
		List<Map<String, Object>> list = service.queryByCondition(pager, "emp", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId());
		// 转发到JSP视图
		Map<String, Object> temp;
		List<Map<String, Object>> date = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> item : list) {
			temp = new HashMap<String, Object>();
			temp.put("title", item.get("TASK_NAME"));
			temp.put("start", item.get("START_TIME"));
			temp.put("end", item.get("FINISH_TIME"));
			date.add(temp);		
		}		
		String calendar = gson.toJson(date);
		System.out.println(calendar);
		//request.setAttribute("calendar", calendar);
		out.print(calendar);
		return null;
	}
	
	@Action(value = "queryAll", results = { @Result(name = "success", location = "/pages/Work/tasksearch.jsp", type = "dispatcher") })
	public String queryAll() {
		Pager pager = new Pager(service.getCount(), pageNum);
		List<Map<String, Object>> list = service.query(pager);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "multipleQuery", results = { @Result(name = "success", location = "/pages/Work/tasksearch.jsp", type = "dispatcher") })
	public String multipleQuery() throws Exception {
		Pager pager = new Pager(service.getMultipleCount(date, emp, manager),pageNum);
		List<Map<String, Object>> list = service.multipleQuery(pager, date, emp, manager);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "arrange", results = { @Result(name = "success", location = "/pages/Work/arrange.jsp", type = "dispatcher") })
	public String arrange() {
		Pager pager = new Pager(agentService.getConditionCount("manager", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()), pageNum);
		List<Map<String, Object>> arrList = agentService.queryByCondition(pager, "manager", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		BigDecimal tbd;
		BigDecimal i = new BigDecimal(0);
		for(Map<String, Object> temp : arrList) {
			tbd = new BigDecimal(String.valueOf(temp.get("AGENT")));
			if(tbd.compareTo(i)==1){
				list.add(temp);
			}			
		}
		request.setAttribute("arrList", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "reInsert", results = { @Result(name = "success", location = "/pages/Work/insert.jsp", type = "dispatcher")} )
	public String reInsert() throws IOException {
		Employee employee = empService.queryById(empId);
		Employee tmanager = empService.queryById(((Employee)request.getSession().getAttribute("queryemp")).getEmpId());
		request.setAttribute("emp", employee);
		request.setAttribute("manager", tmanager);
		request.setAttribute("pageNum", pageNum);
		return "success";
	}
	
	@Action(value = "insert", results = { @Result(name = "success", location = "/pages/Work/insert.jsp", type = "dispatcher")} )
	public String insert() throws IOException {
		Employee employee = empService.queryById(empId);
		Employee tmanager = empService.queryById(managerId);
		Task work = new Task(task.getTaskName(), task.getTaskContent(), task.getTaskDate(), task.getState(), task.getStartTime(), task.getFinishTime());
		service.insert(work,employee,tmanager);
		request.setAttribute("emp", employee);
		request.setAttribute("manager", tmanager);
		request.setAttribute("pageNum", pageNum);
		return "success";
	}
	
	@Action(value = "editMy", results = { @Result(name = "success", location = "/pages/Work/editTask.jsp", type = "dispatcher") })
	public String editMy() {
		Pager pager = new Pager(service.getConditionCount("emp", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()), pageNum);		
		System.out.println("empId: "+ ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()+"Count: "+service.getConditionCount("emp", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()));
		List<Map<String, Object>> taskList = service.queryByCondition(pager, "emp", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId());		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> temp : taskList) {
			System.out.println(((BigDecimal) temp.get("MANAGER_ID")).intValue());
			System.out.println(((BigDecimal) temp.get("EMP_ID")).intValue());
			Integer agentState = 0;	
			agentState = agentService.getAgent(((BigDecimal) temp.get("EMP_ID")).intValue(),((BigDecimal) temp.get("MANAGER_ID")).intValue());
			if(agentState == 1){
				if(String.valueOf(temp.get("STATE")).equals("0")){
					temp.put("workState", "未完成");
					
				}else {
					temp.put("workState", "已完成");
				}
				list.add(temp);
			}
		}
		request.setAttribute("taskList", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "editTask", results = { @Result(name = "success", location = "/pages/Work/editTask.jsp", type = "dispatcher") })
	public String editTask() {
		Pager pager = new Pager(service.getConditionCount("manager", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()), pageNum);
		System.out.println("empId: "+ ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()+"Count: "+service.getConditionCount("manager", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()));
		List<Map<String, Object>> taskList = service.queryByCondition(pager, "manager", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> temp : taskList) {
			Integer agentState = 0;
			agentState = agentService.getAgent(((BigDecimal) temp.get("EMP_ID")).intValue(),((BigDecimal) temp.get("MANAGER_ID")).intValue());
			if(agentState == 1&&((BigDecimal) temp.get("EMP_ID")).intValue()!=((Employee)request.getSession().getAttribute("queryemp")).getEmpId()){
				if(String.valueOf(temp.get("STATE")).equals("0")){
					temp.put("workState", "未完成");
					
				}else {
					temp.put("workState", "已完成");
				}
				
				list.add(temp);
			}
		}
		request.setAttribute("taskList", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "reEdit", results = { @Result(name = "success", location = "/pages/Work/edit.jsp", type = "dispatcher")} )
	public String reEdit() throws IOException {
		Map<String, Object> work = service.queryById(task.getTaskId());
		request.setAttribute("task", work);
		request.setAttribute("pageNum", pageNum);
		return "success";
	}
	
	@Action(value = "edit" , results = { @Result(name = "success", location = "/pages/Work/edit.jsp", type = "dispatcher")})
	public String edit() throws IOException {
		Task work = new Task(task.getTaskId(),task.getTaskName(), task.getTaskContent(), task.getTaskDate(), task.getState(), task.getStartTime(), task.getFinishTime());
		service.update(work);
		Map<String, Object> work2 = service.queryById(task.getTaskId());
		request.setAttribute("task", work2);
		request.setAttribute("pageNum", pageNum);
		return "success";
	}
	
	@Action(value = "readMy", results = { @Result(name = "success", location = "/pages/Work/read.jsp", type = "dispatcher") })
	public String read() {
		Pager pager = new Pager(service.getConditionCount("emp", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()), pageNum);	
		System.out.println("empId: "+ ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()+"Count: "+service.getConditionCount("emp", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()));
		List<Map<String, Object>> taskList = service.queryByCondition(pager, "emp", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId());		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> temp : taskList) {
			if(String.valueOf(temp.get("STATE")).equals("0")){
				temp.put("workState", "未完成");
				
			}else {
				temp.put("workState", "已完成");
			}
			list.add(temp);
		}
		request.setAttribute("taskList", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "readTask", results = { @Result(name = "success", location = "/pages/Work/read.jsp", type = "dispatcher") })
	public String readTask() {
		Pager pager = new Pager(service.getConditionCount("manager", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()), pageNum);
		System.out.println("empId: "+ ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()+"Count: "+service.getConditionCount("manager", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()));
		List<Map<String, Object>> taskList = service.queryByCondition(pager, "manager", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> temp : taskList) {
			if(((BigDecimal) temp.get("EMP_ID")).intValue()!=((Employee)request.getSession().getAttribute("queryemp")).getEmpId()){
				if(String.valueOf(temp.get("STATE")).equals("0")){
					temp.put("workState", "未完成");
					
				}else {
					temp.put("workState", "已完成");
				}
				
				list.add(temp);
			}
		}
		request.setAttribute("taskList", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "view" , results = { @Result(name = "success", location = "/pages/Work/taskView.jsp", type = "dispatcher")})
	public String view() throws IOException {
		Map<String, Object> work = service.queryById(task.getTaskId());
		request.setAttribute("task", work);
		request.setAttribute("pageNum", pageNum);
		return "success";
	}
	
	@Action(value = "finish")
	public String finish() throws IOException, NumberFormatException, ParseException {
		Map<String, Object> work = service.queryById(task.getTaskId());
		Task work2 = new Task(((BigDecimal) work.get("TASK_ID")).intValue(),(String)work.get("TASK_NAME"), (String)work.get("TASK_CONTENT"), (Date) work.get("TASK_DATE"), 1, (Date) work.get("START_TIME"),(Date)work.get("FINISH_TIME"));
		service.update(work2);
		request.setAttribute("pageNum", pageNum);
		return "success";
	}
	
	@Action(value = "deleteTask")
	public String deleteTask() throws IOException, NumberFormatException, ParseException {		
		service.delete(task.getTaskId());
		request.setAttribute("pageNum", pageNum);
		return "success";
	}
	
	@Override
	public Task getModel() {
		// TODO Auto-generated method stub
		return task;
	}
	
}
