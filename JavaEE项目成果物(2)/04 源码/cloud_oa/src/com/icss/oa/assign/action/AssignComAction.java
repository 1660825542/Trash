package com.icss.oa.assign.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.icss.oa.common.BaseAction;
import com.icss.oa.common.Pager;
import com.icss.oa.assign.pojo.AssignCom;
import com.icss.oa.assign.service.AssignComService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/asscom")
@Results({ @Result(name = "success", location = "/asscom/query.action?pageNum=${pageNum}", type = "redirect") ,
	@Result(name = "success", location = "/asscom/query.action?pageNum=${pageNum}", type = "redirect") })

public class AssignComAction extends BaseAction implements ModelDriven<AssignCom> {

	private AssignCom asscom = new AssignCom();	

	public AssignCom getAssignCom() {
		return asscom;
	}

	public void setAssignCom(AssignCom asscom) {
		this.asscom = asscom;
	}

	private int pageNum;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Autowired
	private AssignComService service;

	@Action(value = "insert")
	public String insert() throws IOException {
		service.insert(asscom);
		return "success";
	}

	@Action(value = "delete")
	public String delete() throws IOException{
		service.delete(asscom.getAssComId());
		return null;
	}
	
	@Action(value = "queryExist")
	public String queryExist() throws IOException{
		int count = service.queryExist(asscom.getAssComId());
		System.out.println(count);
		
		PrintWriter out = response.getWriter();
		out.print(count);
		
		return null;
	}

	@Action(value = "update")
	public String update() throws IOException {
		service.update(asscom);
		return "success";
	}

	@Action(value = "query", results = { @Result(name = "success", location = "/pages/Assign/AssComList.jsp", type = "dispatcher") })
	public String query() {
		Pager pager = new Pager(service.getCount(), pageNum);
		List<AssignCom> list = service.queryByPager(pager);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "queryByCondition", results = { @Result(name = "success", location = "/pages/Assign/ComRequByIdList.jsp", type = "dispatcher") })
	public String queryByCondition() {
		Integer assComId = asscom.getAssComId();		
		List<AssignCom> list = service.querByCondition(assComId);
		request.setAttribute("list", list);
		return "success";
	}

	@Action(value = "toUpdate", results = { @Result(name = "success", location = "/pages/Assign/UpdateAssCom.jsp", type = "dispatcher") })
	public String toUpdate() {
		AssignCom ase = service.queryById(asscom.getAssComId());
		request.setAttribute("asscom", ase);
		return "success";
	}
	
	@Action(value = "selectCom", results = { @Result(name = "success", location = "/pages/Assign/ComOption.jsp", type = "dispatcher") })
	public String selectCom() {				
		// 分页对象
		Pager pager = new Pager(service.getCount(), pageNum);
		// 调用service获得数据集合
		List<AssignCom> list = service.queryByPager(pager);
		// 转发到JSP视图
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}

	@Override
	public AssignCom getModel() {
		// TODO Auto-generated method stub
		return asscom;
	}
}