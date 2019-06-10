package com.icss.oa.work.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.icss.oa.common.BaseAction;
import com.icss.oa.common.Pager;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.DepartmentService;
import com.icss.oa.system.service.OrgService;
import com.icss.oa.work.pojo.Agent;
import com.icss.oa.work.service.AgentService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/agent")
public class AgentAction extends BaseAction implements ModelDriven<Employee> {
	
	private Employee emp = new Employee();
			
	private int pageNum = 1;// 页码
	
	private int state;

	private int uaId;
	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private OrgService orgService;
	
	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getUaId() {
		return uaId;
	}

	public void setUaId(int uaId) {
		this.uaId = uaId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	@Action(value = "initial", results = { @Result(name = "success", location = "/pages/Work/organization.jsp", type = "dispatcher") })
	public String initial() {
		List<Department> deptList = deptService.query();
		List<Department> list = new ArrayList<Department>();
 		for(Department temp : deptList){
			if(temp.getDeptId()!=0){
				list.add(temp);
			}
				
		}
		request.setAttribute("deptList", list);
		return "success";
	}
	
	@Action(value = "queryAll", results = { @Result(name = "success", location = "/pages/Work/queryByDept.jsp", type = "dispatcher") })
	public String queryAll() {
		int count = orgService.getCount();
		Pager pagerAll = new Pager(count, count, 1);
		List<Map<String, Object>> empList = orgService.query(pagerAll);
		List<Agent> agentList = agentService.simpleQuery();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int flag = 0;
		BigDecimal tbd;
		BigDecimal abd;
		for(Map<String, Object> temp : empList) {
			for (Agent a : agentList) {
				tbd = new BigDecimal(String.valueOf(temp.get("EMP_ID")));
				abd = new BigDecimal(a.getManagerId());
				if(tbd.compareTo(abd) == 0&&(new BigDecimal(a.getEmpId())).compareTo((new BigDecimal(((Employee)request.getSession().getAttribute("queryemp")).getEmpId())))==0) {
					
					flag = 1;
					
				}
			}
			if(flag!=1) {
				if(Integer.parseInt(String.valueOf(temp.get("EMP_ID")))!=0)
					list.add(temp);
			}
			flag = 0;
		}
		System.out.println(list.size());
		Pager pager = new Pager(list.size(),pageNum);
		int end;
		if((pager.getStart() + pager.getPageSize() - 1)>list.size()){
			end = list.size();
		}else{
			end = pager.getStart() + pager.getPageSize() - 1;
		}
		list = list.subList(pager.getStart()-1, end);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "queryEmpByDept", results = { @Result(name = "success", location = "/pages/Work/queryByDept.jsp", type = "dispatcher") })
	public String queryEmpByDept() {
		int count = orgService.getCountByDept(emp.getDepartmentId());
		Pager pagerAll = new Pager(count, count, 1);
		List<Map<String, Object>> empList = orgService.queryEmpByDept(pagerAll,emp.getDepartmentId());
		List<Agent> agentList = agentService.simpleQuery();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int flag = 0;
		BigDecimal tbd;
		BigDecimal abd;
		for(Map<String, Object> temp : empList) {
			for (Agent a : agentList) {
				tbd = new BigDecimal(String.valueOf(temp.get("EMP_ID")));
				abd = new BigDecimal(a.getManagerId());
				if(tbd.compareTo(abd) == 0&&(new BigDecimal(a.getEmpId())).compareTo((new BigDecimal(((Employee)request.getSession().getAttribute("queryemp")).getEmpId())))==0) {
					
					flag = 1;
					
				}
				
			}
			if(flag!=1) {
				list.add(temp);
			}
			flag = 0;
		}
		Pager pager = new Pager(list.size(),pageNum);
		int end = pager.getStart() + pager.getPageSize() - 1;
		if(end>list.size())
			end = list.size();
		list = list.subList(pager.getStart()-1, end);
		request.setAttribute("list", list );
		request.setAttribute("pager", pager);
		return "success";
	}
	


	@Action(value = "setAgent", results = { @Result(name = "success", location = "/agent/queryAll.action", type = "redirect") })
	public String setAgent() {
		int i = 0;
		List<Agent> agentList = agentService.simpleQuery();
		for(Agent a : agentList) {
			if((a.getEmpId()==((Employee)request.getSession().getAttribute("queryemp")).getEmpId())&&(a.getManagerId()==emp.getEmpId()))
				i = 1;
		}
		if(i==0) {
			agentService.setAgent(((Employee)request.getSession().getAttribute("queryemp")).getEmpId(), emp.getEmpId(), state);
		}
		return "success";
	}
	
	@Action(value = "selectManager", results = { @Result(name = "success", location = "/pages/Work/agentmanage.jsp", type = "dispatcher") })
	public String selectManager() {
		Pager pager = new Pager(agentService.getConditionCount("emp", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()), pageNum);
		List<Map<String, Object>> managerList = agentService.queryByCondition(pager, "emp", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		BigDecimal tbd;
		BigDecimal i = new BigDecimal(0);
		for(Map<String, Object> temp : managerList) {
			tbd = new BigDecimal(String.valueOf(temp.get("AGENT")));
			if(tbd.compareTo(i)==0){
				temp.put("STATE", "读者");
			}else {
				temp.put("STATE", "代办");
			}
			if((new BigDecimal(String.valueOf(temp.get("MANAGER_ID")))).compareTo((new BigDecimal(((Employee)request.getSession().getAttribute("queryemp")).getEmpId())))!=0){
				list.add(temp);
			}
			
		}
		request.setAttribute("managerList", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "selectEmp", results = { @Result(name = "success", location = "/pages/Work/agentview.jsp", type = "dispatcher") })
	public String selectEmp() {
		Pager pager = new Pager(agentService.getConditionCount("manager", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()), pageNum);
		List<Map<String, Object>> empList = agentService.queryByCondition(pager, "manager", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		BigDecimal tbd;
		BigDecimal i = new BigDecimal(0);
		for(Map<String, Object> temp : empList) {
			tbd = new BigDecimal(String.valueOf(temp.get("AGENT")));
			if(tbd.compareTo(i)==0){
				temp.put("STATE", "读者");
			}else {
				temp.put("STATE", "代办");
			}
			list.add(temp);
		}
		request.setAttribute("empList", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "deleteAgent", results = { @Result(name = "success", location = "/pages/Work/agentmanage.jsp", type = "dispatcher") })
	public String deleteAgent() {
		agentService.delete(uaId);
		Pager pager = new Pager(agentService.getConditionCount("emp", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()), pageNum);
		List<Map<String, Object>> managerList = agentService.queryByCondition(pager, "emp", ((Employee)request.getSession().getAttribute("queryemp")).getEmpId());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		BigDecimal tbd;
		BigDecimal i = new BigDecimal(0);
		for(Map<String, Object> temp : managerList) {
			tbd = new BigDecimal(String.valueOf(temp.get("AGENT")));
			if(tbd.compareTo(i)==0){
				temp.put("STATE", "读者");
			}else {
				temp.put("STATE", "代办");
			}
			if((new BigDecimal(String.valueOf(temp.get("MANAGER_ID")))).compareTo((new BigDecimal(((Employee)request.getSession().getAttribute("queryemp")).getEmpId())))!=0){
				list.add(temp);
			}
		}
		request.setAttribute("managerList", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "compareAgent")
	public String compareAgent() throws IOException {
		List<Agent> list = agentService.simpleQuery();
		int flag = 0;
		for(Agent a : list){
			if(a.getEmpId()==emp.getEmpId()&&a.getManagerId()== ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()){
				flag = 1;
			}
		}
		PrintWriter out = response.getWriter();
		out.print(flag);
		System.out.println("是否权限="+flag);
		return null;
	}
	
	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		return emp;
	}

}
