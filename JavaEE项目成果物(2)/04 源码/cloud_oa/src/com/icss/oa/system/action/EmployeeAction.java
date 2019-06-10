package com.icss.oa.system.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.icss.oa.system.index.EmpIndexResultBean;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.EmployeeService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/emp")
public class EmployeeAction extends BaseAction implements ModelDriven<Employee> {

	private Employee employee = new Employee();

	private int pageNum=1;// 分页

	private String keyword;// 搜索关键字

	private int[] roleId;
	
	private int[] roleIdDelete;
	
	@Autowired
	private EmployeeService service;
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int[] getRoleId() {
		return roleId;
	}

	public void setRoleId(int[] roleId) {
		this.roleId = roleId;
	}

	public int[] getRoleIdDelete() {
		return roleIdDelete;
	}

	public void setRoleIdDelete(int[] roleIdDelete) {
		this.roleIdDelete = roleIdDelete;
	}

	@Override
	public Employee getModel() {
		return this.employee;
	}
	
	@Action(value = "delete")
	public String delete(){
		try {
			if(employee.getEmpId()!=0){
				service.deleteEmployee(employee.getEmpId());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Action(value = "add", results = { @Result(name = "success", location = "/emp/query.action?pageNum=${pageNum}", type = "redirect") })
	public String add(){
		try {
			service.addEmployee(employee);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	@Action(value = "detail", results = { @Result(name = "success", location = "/pages/system/editEmp.jsp?pageNum=${pageNum}", type = "dispatcher") })
	public String detail(){
		Employee emp = service.queryById(employee.getEmpId());
		request.setAttribute("emp", emp);
		String deptName = service.getDeptNameByEmpId(employee.getEmpId());
		String posName = service.getPosNameByEmpId(employee.getEmpId());
		
		request.setAttribute("deptName", deptName);
		request.setAttribute("posName", posName);
		
		return "success";
	}
	
	@Action(value = "edit", results = { @Result(name = "success", location = "/emp/query.action?pageNum=${pageNum}", type = "redirect") })
	public String edit(){
		try {
			service.editEmployee(employee);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	@Action(value = "query", results = { @Result(name = "success", location = "/pages/system/emps.jsp", type = "dispatcher") })
	public String query(){
		// 分页对象
		Pager pager = new Pager(service.count(), pageNum);
		// 调用service获得数据集合
		List<Map<String,Object>> list = service.queryEmployee(pager);
		// 转发到JSP视图
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "queryByIndex", results = { @Result(name = "success", location = "/pages/system/indexEmps.jsp", type = "dispatcher") })
	public String queryByIndex() throws Exception {

		Pager pager = new Pager(pageNum);

		EmpIndexResultBean resultBean = service.queryByIndex(keyword, pager);

		request.setAttribute("queryResult", resultBean);
		request.setAttribute("pager", pager);

		return "success";
	}
	
	@Action(value = "queryIsExist")
	public String queryIsExist() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String param = request.getParameter("param");
		if(service.numIsExist(param))
			out.print("{\"info\":\"用户名已存在！\",\"status\":\"n\"}");
		else
			out.print("{\"info\":\" \",\"status\":\"y\"}");
		return null;
	}
	
	@Action(value = "addRoleToEmp", results = { @Result(name = "success", location = "/pages/system/editsuccess.jsp", type = "redirect"),@Result(name = "error", location = "/pages/system/editfail.jsp", type = "redirect") })
	public String addRoleToEmp(){
		if(roleId!=null){
			for (int i = 0; i < roleId.length; i++) {
				service.addRoleToEmp(employee.getEmpId(), roleId[i]);
			}
		}
		if(roleIdDelete!=null){
			for (int i = 0; i < roleIdDelete.length; i++){
				service.deleteEmpRole(employee.getEmpId(), roleIdDelete[i]);
			}
		}
		if(roleId==null&& roleIdDelete==null){
			return "error";
		}
		return "success";
		
	}
}
