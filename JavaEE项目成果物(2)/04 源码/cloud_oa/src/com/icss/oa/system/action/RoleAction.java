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
import com.icss.oa.system.pojo.Role;
import com.icss.oa.system.service.RoleService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/role")
public class RoleAction extends BaseAction implements ModelDriven<Role> {

	private Role role = new Role();
	
	private int pageNum = 1;

	private int[] permId;
	
	@Autowired
	private RoleService service;

	private int empId;
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int[] getPermId() {
		return permId;
	}

	public void setPermId(int[] permId) {
		this.permId = permId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@Override
	public Role getModel() {
		return role;
	}

	@Action(value = "query", results = { @Result(name = "success", location = "/pages/system/roles.jsp", type = "dispatcher") })
	public String selectAll(){
		// 分页对象
		Pager pager = new Pager(service.count(), 12, pageNum);
		// 调用service获得数据集合
		List<Role> list = service.query(pager);
		// 转发到JSP视图
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "add")
	public String add() throws IOException{
		PrintWriter out = response.getWriter();
		out.print(service.addRole(role));
		return null;
	}
	
	@Action(value = "edit")
	public String edit(){
		service.editRole(role);
		return null;
	}
	
	@Action(value = "delete")
	public String delete(){
		service.deleteRole(role.getRoleId());
		return null;
	}

	@Action(value = "queryIsToEmp")
	public String queryAjaxHaveChild() throws IOException{
		PrintWriter out = response.getWriter();
		if(service.isToEmp(role.getRoleId()))
			out.print("1");
		else
			out.print("0");
		
		return null;
	}

	@Action(value = "addPermToRole", results = { @Result(name = "success", location = "/pages/system/editsuccess.jsp", type = "redirect"), @Result(name = "error", location = "/pages/system/editfail.jsp", type = "redirect") })
	public String addPermToRole(){
		if(permId!=null){
			for (int i = 0; i < permId.length; i++) {
				service.addPermissionToRole(role.getRoleId(), permId[i]);
			}
			return "success";
		}else{
			return "error";
		}
	}
	
	@Action(value = "querySelectRole", results = { @Result(name = "success", location = "/pages/system/selectroles.jsp", type = "dispatcher") })
	public String selectChoose(){
		// 调用service获得数据集合
		Map<String,Object> listreturn= service.selectNotToEmp(empId);
		List<Role> list = (List<Role>) listreturn.get("notHaveList");
		List<Role> listdelete = (List<Role>) listreturn.get("haveList");
		// 转发到JSP视图
		request.setAttribute("list", list);
		request.setAttribute("listdelete", listdelete);
		request.setAttribute("empId", empId);
		return "success";
	}
}
