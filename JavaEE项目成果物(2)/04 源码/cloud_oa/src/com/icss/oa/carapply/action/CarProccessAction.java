package com.icss.oa.carapply.action;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;

import com.icss.oa.common.BaseAction;
import com.icss.oa.carapply.pojo.CarUserTask;
import com.icss.oa.carapply.service.CarApplyProcessService;
import com.icss.oa.system.pojo.Employee;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 流程操作Action
 */
@Controller
@Scope("prototype")
@Namespace("/processcar")
@ParentPackage("struts-default")
public class CarProccessAction extends BaseAction implements ModelDriven<CarUserTask> {
	
	//封装表单数据
	private CarUserTask carUserTask = new CarUserTask();
		
	@Autowired
	private CarApplyProcessService processService;//流程业务对象
	
	
	/**
	 * 发布一个流程定义，例如Xxx.bpmn20.xml
	 * @return
	 * @throws IOException 
	 */
	@Action("deployProcess")
	public String deployProcess() throws IOException {
		
		processService.deployProcess();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("流程定义已发布");
		
		return null;
	}
	
	/**
	 * 查询当前用户的所有代办任务
	 * @return
	 */
	@Action(value="queryUserTask",results={@Result(name="success",location="/pages/CarApply/CarTasks.jsp",type="dispatcher")})
	public String queryUserTask() {
		
		//当前登陆用户
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		
		List<CarUserTask> list = processService.queryUserTask(String.valueOf(emp.getEmpId()));
		
		request.setAttribute("list", list);
		request.setAttribute("assigneename", emp.getEmpName());
				
		return "success";
	}
	
	/**
	 * 查询当前车队管理员的所有代办任务
	 * @return
	 */
	@Action(value="queryUserAllot",results={@Result(name="success",location="/pages/CarApply/CarAllot.jsp",type="dispatcher")})
	public String queryUserTaskAllot() {
		
		//当前登录用户
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		
		List<CarUserTask> list = processService.queryUserTask(String.valueOf(emp.getEmpId()));
		
		request.setAttribute("list", list);
		request.setAttribute("assigneename", emp.getEmpName());
				
		return "success";
	}
	
	/**
	 * 处理代办任务
	 * @return
	 */
	@Action(value="handleTask",results={@Result(name="success",location="/processcar/queryUserAllot.action",type="redirect"),@Result(name="approve",location="/processcar/queryUserTask.action",type="redirect")})
	public String handleTask() {
		//当前登录用户
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		
		carUserTask.setUsername(emp.getEmpName());
		carUserTask.setUserid(emp.getEmpId());
		processService.handleTask(carUserTask);
		if(carUserTask.getAction()==4)
			return "success";
		else
			return "approve";
	}
	
	/**
	 * 返回流程跟踪图数据流
	 * @throws IOException 
	 */
	@Action("getProcessImage")
	public String getProcessImage() throws IOException {
		
		InputStream in = processService.getProcessImage(carUserTask.getProcinsid());
		
		OutputStream out = response.getOutputStream();
		
		FileCopyUtils.copy(in, out);
		
		return null;
	}

	@Override
	public CarUserTask getModel() {
		
		return carUserTask;
	}

}