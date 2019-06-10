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
import com.icss.oa.process.pojo.ReimFlowLog;
import com.icss.oa.process.pojo.Reimbursement;
import com.icss.oa.process.service.ReimService;
import com.icss.oa.system.pojo.Employee;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 请假Action
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/reim")
@ParentPackage("struts-default")
public class ReimAction extends BaseAction implements ModelDriven<Reimbursement> {

	private Reimbursement reim = new Reimbursement();
	
	private int pageNum=1;// 分页
	
	@Autowired
	private ReimService reimService;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public Reimbursement getModel() {		
		return reim;
	}

	/**
	 * 新增报销
	 * @return
	 * @throws ParseException 
	 */
	@Action(value="insert",results={@Result(name="success",location="/reim/query.action",type="redirect")})
	public String insert() throws ParseException {
		
		//从session中取出登陆用户
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		
		
		reim.setEmpId(emp.getEmpId());
		reim.setEmpName(emp.getEmpName());
		
		reimService.insert(reim);
		
		return "success";
	}
	
	/**
	 * 查询报销列表
	 * @return
	 */
	@Action(value="query",results={@Result(name="success",location="/pages/reim/reims.jsp",type="dispatcher")})
	public String query() {
		
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		// 分页对象
		Pager pager = new Pager(reimService.count(emp.getEmpId()), pageNum);
		
		List<Reimbursement> list = reimService.query(emp.getEmpId(), pager);
		request.setAttribute("list",list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	/**
	 * 查询显示报销详细信息 
	 * @return
	 */
	@Action(value="queryById",results={@Result(name="success",location="/pages/reim/reimdetail.jsp",type="dispatcher")})
	public String queryById() {
		
		reim = reimService.queryById(reim.getReimId());
		request.setAttribute("reim", reim);
		
		List<ReimFlowLog> logList = reimService.queryFlowLog(reim.getReimId());
		request.setAttribute("logList", logList);
		
		return "success";
	}
	
}