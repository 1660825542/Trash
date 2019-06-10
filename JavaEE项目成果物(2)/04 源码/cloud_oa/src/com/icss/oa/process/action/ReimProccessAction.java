package com.icss.oa.process.action;


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
import com.icss.oa.process.pojo.ReimUserTask;
import com.icss.oa.process.service.ReimProcessService;
import com.icss.oa.system.pojo.Employee;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 流程操作Action
 */
@Controller
@Scope("prototype")
@Namespace("/processreim")
@ParentPackage("struts-default")
public class ReimProccessAction extends BaseAction implements ModelDriven<ReimUserTask> {
	
	//封装表单数据
	private ReimUserTask reimUserTask = new ReimUserTask();
		
	@Autowired
	private ReimProcessService processService;//流程业务对象
	
	
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
	@Action(value="queryUserTask",results={@Result(name="success",location="/pages/reim/reimtasks.jsp",type="dispatcher")})
	public String queryUserTask() {
		
		//当前登录用户
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		
		List<ReimUserTask> list = processService.queryUserTask(String.valueOf(emp.getEmpId()));
		
		request.setAttribute("list", list);
		request.setAttribute("assigneename", emp.getEmpName());
				
		return "success";
	}
	
	/**
	 * 处理代办任务
	 * @return
	 */
	@Action(value="handleTask",results={@Result(name="success",location="/processreim/queryUserTask.action",type="redirect")})
	public String handleTask() {
		//当前登录用户
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		
		reimUserTask.setUsername(emp.getEmpName());
		reimUserTask.setUserid(emp.getEmpId());
		processService.handleTask(reimUserTask);
		return "success";
	}
	
	/**
	 * 返回流程跟踪图数据流
	 * @throws IOException 
	 */
	@Action("getProcessImage")
	public String getProcessImage() throws IOException {
		
		InputStream in = processService.getProcessImage(reimUserTask.getProcinsid());
		
		OutputStream out = response.getOutputStream();
		
		FileCopyUtils.copy(in, out);
		
		return null;
	}

	@Override
	public ReimUserTask getModel() {
		
		return reimUserTask;
	}

}