package com.icss.oa.process.action;

import java.text.ParseException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.icss.oa.common.BaseAction;
import com.icss.oa.common.Pager;
import com.icss.oa.process.pojo.Leave;
import com.icss.oa.process.pojo.LeaveFlowLog;
import com.icss.oa.process.service.LeaveService;
import com.icss.oa.system.pojo.Employee;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 请假Action
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/leave")
@ParentPackage("struts-default")
public class LeaveAction extends BaseAction implements ModelDriven<Leave> {

	private Leave leave = new Leave();
	
	private int pageNum=1;// 分页
	
	@Autowired
	private LeaveService leaveService;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public Leave getModel() {		
		return leave;
	}

	/**
	 * 新增请假
	 * @return
	 * @throws ParseException 
	 */
	@Action(value="insert",results={@Result(name="success",location="/leave/query.action",type="redirect")})
	public String insert() throws ParseException {
		
		//从session中取出登陆用户
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		
		
		leave.setEmpId(emp.getEmpId());
		leave.setEmpName(emp.getEmpName());
		
		leaveService.insert(leave);
		
		return "success";
	}
	
	/**
	 * 查询请假列表
	 * @return
	 */
	@Action(value="query",results={@Result(name="success",location="/pages/leave/leaves.jsp",type="dispatcher")})
	public String query() {
		
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		// 分页对象
		Pager pager = new Pager(leaveService.count(emp.getEmpId()), pageNum);
		
		List<Leave> list = leaveService.query(emp.getEmpId(), pager);
		request.setAttribute("list",list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	/**
	 * 查询显示请假详细信息 
	 * @return
	 */
	@Action(value="queryById",results={@Result(name="success",location="/pages/leave/leavedetail.jsp",type="dispatcher")})
	public String queryById() {
		
		leave = leaveService.queryById(leave.getLeaveId());
		request.setAttribute("leave", leave);
		
		List<LeaveFlowLog> logList = leaveService.queryFlowLog(leave.getLeaveId());
		request.setAttribute("logList", logList);
		
		return "success";
	}
	
}