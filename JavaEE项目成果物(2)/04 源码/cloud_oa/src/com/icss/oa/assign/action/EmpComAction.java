package com.icss.oa.assign.action;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import com.icss.oa.assign.pojo.EmpCom;
import com.icss.oa.assign.pojo.AssignEmp;
import com.icss.oa.assign.service.EmpComService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/empcom")
@Results({ @Result(name = "success", location = "/empcom/query.action", type = "redirect") })
public class EmpComAction extends BaseAction implements ModelDriven<EmpCom> {

	private EmpCom empcom = new EmpCom();// 员工公司对应实体对象
	private AssignEmp ase = new AssignEmp();//外派员工实体对象

	private int pageNum;// 页码

	@Autowired
	private EmpComService service;// 业务逻辑对象

	public EmpCom getEmpcom() {
		return empcom;
	}

	public void setEmpcom(EmpCom empcom) {
		this.empcom = empcom;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Action(value = "insertByEmpCom")
	public String insertByEmpCom() throws IOException {

		
		int assId = empcom.getAssId();
		String available = empcom.getAvailable();
		
		ase.setAssId(assId);
		ase.setAvailable(available);
		
		service.insertByEmpCom(empcom, ase);
		return "success";
	}

	@Action(value = "insert")
	public String insert() throws IOException {
		service.insert(empcom);
		return "success";
	}

	@Action(value = "delete")
	public String delete() throws IOException {
		service.delete(empcom.getEmpComId());
		return null;
	}

	@Action(value = "update")
	public String update() throws IOException {
		service.update(empcom);
		return "success";
	}
		
	@Action(value = "exportExcel")
	public String exportExcel() throws IOException {
		// 通知客户端以附件形式接收数据	
		String filename= URLEncoder.encode("外派关系.xls", "utf-8") ;
		response.setHeader("content-disposition", "attachment;filename="+ filename);
		
		//文件输出流
		OutputStream os = response.getOutputStream();

		service.exportExcel(os);

		return null;
	}

	@Action(value = "toUpdate", results = { @Result(name = "success", location = "/pages/Assign/UpdateEmpCom.jsp", type = "dispatcher") })
	public String toUpdate() {
		EmpCom ecm = service.queryById(empcom.getEmpComId());
		System.out.println(empcom.getAssId());
		request.setAttribute("empcom", ecm);
		return "success";
	}
	
	@Action(value = "updateByEmpCom")
	public String updateByEmpCom() throws IOException {

		
		int assId = empcom.getAssId();
		String available = empcom.getAvailable();
		
		ase.setAssId(assId);
		ase.setAvailable(available);
		
		service.update(empcom);
		service.updateByEmpCom(empcom, ase);
		return "success";
	}

	@Action(value = "query", results = { @Result(name = "success", location = "/pages/Assign/QueryEmpCom.jsp", type = "dispatcher") })
	public String query() {
		// 分页对象
		Pager pager = new Pager(service.getCount(), pageNum);
		// 调用service获得数据集合
		List<EmpCom> list = service.queryByPager(pager);
		// 转发到JSP视图
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);

		return "success";
	}

	@Override
	public EmpCom getModel() {

		return empcom;
	}
}