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
import com.icss.oa.process.pojo.LeaveUserTask;
import com.icss.oa.process.service.LeaveProcessService;
import com.icss.oa.system.pojo.Employee;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ���̲���Action
 */
@Controller
@Scope("prototype")
@Namespace("/processleave")
@ParentPackage("struts-default")
public class LeaveProccessAction extends BaseAction implements ModelDriven<LeaveUserTask> {
	
	//��װ������
	private LeaveUserTask leaveUserTask = new LeaveUserTask();
		
	@Autowired
	private LeaveProcessService processService;//����ҵ�����
	
	
	/**
	 * ����һ�����̶��壬����Xxx.bpmn20.xml
	 * @return
	 * @throws IOException 
	 */
	@Action("deployProcess")
	public String deployProcess() throws IOException {
		
		processService.deployProcess();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("���̶����ѷ���");
		
		return null;
	}
	
	/**
	 * ��ѯ��ǰ�û������д�������
	 * @return
	 */
	@Action(value="queryUserTask",results={@Result(name="success",location="/pages/leave/leavetasks.jsp",type="dispatcher")})
	public String queryUserTask() {
		
		//��ǰ��¼�û�
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		
		List<LeaveUserTask> list = processService.queryUserTask(String.valueOf(emp.getEmpId()));
		
		request.setAttribute("list", list);
		request.setAttribute("assigneename", emp.getEmpName());
				
		return "success";
	}
	
	/**
	 * �����������
	 * @return
	 */
	@Action(value="handleTask",results={@Result(name="success",location="/processleave/queryUserTask.action",type="redirect")})
	public String handleTask() {
		//��ǰ��¼�û�
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		
		leaveUserTask.setUsername(emp.getEmpName());
		leaveUserTask.setUserid(emp.getEmpId());
		processService.handleTask(leaveUserTask);
		return "success";
	}
	
	/**
	 * �������̸���ͼ������
	 * @throws IOException 
	 */
	@Action("getProcessImage")
	public String getProcessImage() throws IOException {
		
		InputStream in = processService.getProcessImage(leaveUserTask.getProcinsid());
		
		OutputStream out = response.getOutputStream();
		
		FileCopyUtils.copy(in, out);
		
		return null;
	}

	@Override
	public LeaveUserTask getModel() {
		
		return leaveUserTask;
	}

}