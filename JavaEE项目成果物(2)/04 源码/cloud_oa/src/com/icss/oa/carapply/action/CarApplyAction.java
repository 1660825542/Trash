package com.icss.oa.carapply.action;

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
import com.icss.oa.carapply.pojo.CarFlowLog;
import com.icss.oa.carapply.pojo.CarApply;
import com.icss.oa.carapply.service.CarApplyService;
import com.icss.oa.system.pojo.Employee;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 申请用车Action
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/carapply")
@ParentPackage("struts-default")
public class CarApplyAction extends BaseAction implements ModelDriven<CarApply> {

	private CarApply carapply = new CarApply();
	
	public CarApply getCarapply() {
		return carapply;
	}

	public void setCarapply(CarApply carapply) {
		this.carapply = carapply;
	}

	private int pageNum=1;// 页码
	
	@Autowired
	private CarApplyService carapplyService;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public CarApply getModel() {		
		return carapply;
	}

	/**
	 * 新增申请
	 * @return
	 * @throws ParseException 
	 */
	@Action(value="insert",results={@Result(name="success",location="/carapply/query.action",type="redirect")})
	public String insert() throws ParseException {
		
		//从session中取出登陆用户
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		
		
		carapply.setEmpId(emp.getEmpId());
		carapply.setEmpName(emp.getEmpName());
		
		carapplyService.insert(carapply);
		
		return "success";
	}
	
	/**
	 * 查询申请用车列表
	 * @return
	 */
	@Action(value="query",results={@Result(name="success",location="/pages/CarApply/CarApply.jsp",type="dispatcher")})
	public String query() {
		
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		// 分页对象
		Pager pager = new Pager(carapplyService.count(emp.getEmpId()), pageNum);
		
		List<CarApply> list = carapplyService.query(emp.getEmpId(), pager);
		request.setAttribute("list",list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	/**
	 * 
	 * @return
	 */
	@Action(value="queryById",results={@Result(name="success",location="/pages/CarApply/CarApplyDetail.jsp",type="dispatcher")})
	public String queryById() {
		
		carapply = carapplyService.queryById(carapply.getCarapplyId());
		request.setAttribute("carapply", carapply);
		
		List<CarFlowLog> logList = carapplyService.queryFlowLog(carapply.getCarapplyId());
		request.setAttribute("logList", logList);
		
		return "success";
	}
	
	@Action(value="queryUserRecord",results={@Result(name="success",location="/pages/CarApply/CarChosen.jsp",type="dispatcher")})
	public String queryUserRecord() {
		
		carapply = carapplyService.queryById(carapply.getCarapplyId());
		request.setAttribute("carapply", carapply);
		
		List<CarFlowLog> logList = carapplyService.queryFlowLog(carapply.getCarapplyId());
		request.setAttribute("logList", logList);
		
		return "success";
	}
	
}