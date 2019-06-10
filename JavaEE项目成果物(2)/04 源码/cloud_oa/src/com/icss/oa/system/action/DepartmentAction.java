package com.icss.oa.system.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.service.DepartmentService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/dept")
public class DepartmentAction extends BaseAction implements ModelDriven<Department> {

	private Department dept = new Department();
	
	private int pageNum=1;// 页码

	@Autowired
	private DepartmentService service;

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public Department getModel() {
		return dept;
	}

	@Action(value = "query", results = { @Result(name = "success", location = "/pages/system/depts.jsp", type = "dispatcher") })
	public String query() {
		// 分页对象
		Pager pager = new Pager(service.count(), pageNum);
		// 调用service获得数据集合
		List<Department> list = service.queryDepts(pager);
		// 转发到JSP视图
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "insert")
	public String insert() throws IOException{
		PrintWriter out = response.getWriter();
		int id = service.addDept(dept);
		out.print(id);
		return null;
	}
	
	@Action(value = "update")
	public String update(){
		service.editDept(dept);
		return null;
	}
	
	@Action(value = "delete")
	public String delete(){
		if(dept.getDeptId()!=0){
			service.deleteDept(dept.getDeptId());
		}
		return null;
	}
	
	@Action(value = "queryAjaxHaveChild")
	public String queryAjaxHaveChild() throws IOException{
		PrintWriter out = response.getWriter();
		if(service.isHaveEmp(dept.getDeptId()))
			out.print("1");
		else
			out.print("0");
		
		return null;
	}
	
	/**
	 * 使用小窗口查询部门信息选择返回给员工
	 * @return
	 */
	@Action(value = "querySelectDept", results = { @Result(name = "success", location = "/pages/system/selectdepts.jsp", type = "dispatcher") })
	public String querySelectDept(){
		// 分页对象
		Pager pager = new Pager(service.count(), pageNum);
		// 调用service获得数据集合
		List<Department> list = service.queryDepts(pager);
		
		//不显示系统管理员
		Department dept = null;
		for (Department department : list) {
			if(department.getDeptId()==0){
				dept = department;
				break;
			}
		}
		list.remove(dept);
		
		// 转发到JSP视图
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
}
