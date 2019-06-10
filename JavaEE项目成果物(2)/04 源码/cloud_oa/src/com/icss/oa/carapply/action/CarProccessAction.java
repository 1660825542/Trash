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
 * ���̲���Action
 */
@Controller
@Scope("prototype")
@Namespace("/processcar")
@ParentPackage("struts-default")
public class CarProccessAction extends BaseAction implements ModelDriven<CarUserTask> {
	
	//��װ������
	private CarUserTask carUserTask = new CarUserTask();
		
	@Autowired
	private CarApplyProcessService processService;//����ҵ�����
	
	
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
	@Action(value="queryUserTask",results={@Result(name="success",location="/pages/CarApply/CarTasks.jsp",type="dispatcher")})
	public String queryUserTask() {
		
		//��ǰ��½�û�
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		
		List<CarUserTask> list = processService.queryUserTask(String.valueOf(emp.getEmpId()));
		
		request.setAttribute("list", list);
		request.setAttribute("assigneename", emp.getEmpName());
				
		return "success";
	}
	
	/**
	 * ��ѯ��ǰ���ӹ���Ա�����д�������
	 * @return
	 */
	@Action(value="queryUserAllot",results={@Result(name="success",location="/pages/CarApply/CarAllot.jsp",type="dispatcher")})
	public String queryUserTaskAllot() {
		
		//��ǰ��¼�û�
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		
		List<CarUserTask> list = processService.queryUserTask(String.valueOf(emp.getEmpId()));
		
		request.setAttribute("list", list);
		request.setAttribute("assigneename", emp.getEmpName());
				
		return "success";
	}
	
	/**
	 * �����������
	 * @return
	 */
	@Action(value="handleTask",results={@Result(name="success",location="/processcar/queryUserAllot.action",type="redirect"),@Result(name="approve",location="/processcar/queryUserTask.action",type="redirect")})
	public String handleTask() {
		//��ǰ��¼�û�
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
	 * �������̸���ͼ������
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