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
import com.icss.oa.system.pojo.Permission;
import com.icss.oa.system.service.PermissionService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/perm")
public class PermissionAction extends BaseAction implements
		ModelDriven<Permission> {

	private Permission perm = new Permission();
	
	private int pageNum = 1;
	
	private int roleId;
	
	@Autowired
	private PermissionService service;
	
	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	@Override
	public Permission getModel() {
		return perm;
	}
	
	@Action(value = "query", results = { @Result(name = "success", location = "/pages/system/perms.jsp", type = "dispatcher") })
	public String selectAll(){
		// 分页对象
		Pager pager = new Pager(service.count(), pageNum);
		// 调用service获得数据集合
		List<Permission> list = service.selectPerm(pager);
		// 转发到JSP视图
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "querySelectPerm", results = { @Result(name = "success", location = "/pages/system/selectperms.jsp", type = "dispatcher") })
	public String selectChoose(){
		// 调用service获得数据集合
		Map<String,Object> listAndPage= service.selectNotToRole(roleId, pageNum);
		List<Permission> list = (List<Permission>) listAndPage.get("notHaveList");
		List<Permission> haveList = (List<Permission>) listAndPage.get("haveList");
		Pager pager = (Pager) listAndPage.get("Pager");
		// 转发到JSP视图
		request.setAttribute("list", list);
		request.setAttribute("haveList", haveList);
		request.setAttribute("pager", pager);
		request.setAttribute("roleId", roleId);
		return "success";
	}
	
	@Action(value = "add")
	public String add() throws IOException{
		PrintWriter out = response.getWriter();
		out.print(service.addPerm(perm));
		return null;
	}
	
	@Action(value = "edit")
	public String edit(){
		service.editPerm(perm);
		return null;
	}
	
	@Action(value = "queryIsToRole")
	public String queryAjaxHaveChild() throws IOException{
		PrintWriter out = response.getWriter();
		if(service.isToRole(perm.getPermId()))
			out.print("1");
		else
			out.print("0");
		
		return null;
	}
}
