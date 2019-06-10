package com.icss.oa.assign.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import org.springframework.util.FileCopyUtils;

import com.icss.oa.common.BaseAction;
import com.icss.oa.common.Pager;
import com.icss.oa.assign.pojo.AssignEmp;
import com.icss.oa.assign.pojo.ResultBean;
import com.icss.oa.assign.service.AssignEmpService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/assemp")
@Results({ @Result(name = "success", location = "/assemp/query.action?pageNum=${pageNum}", type = "redirect")
		,@Result(name = "error", location = "/pages/inedx.jsp", type = "redirect")})

public class AssignEmpAction extends BaseAction implements ModelDriven<AssignEmp> {

	private AssignEmp assemp = new AssignEmp();	//员工信息的实体类
	
	private File data;// 上传的文件数据
	
	private String dataFileName;// 文件名称
	
	private String dataContentType;// 文件MIME类型

	public AssignEmp getAssemp() {
		return assemp;
	}

	public void setAssemp(AssignEmp assemp) {
		this.assemp = assemp;
	}

	private int pageNum;	//传入页码

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public File getData() {
		return data;
	}

	public void setData(File data) {
		this.data = data;
	}

	public String getDataFileName() {
		return dataFileName;
	}

	public void setDataFileName(String dataFileName) {
		this.dataFileName = dataFileName;
	}

	public String getDataContentType() {
		return dataContentType;
	}

	public void setDataContentType(String dataContentType) {
		this.dataContentType = dataContentType;
	}

	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Autowired
	private AssignEmpService service;

	@Action(value = "insert")
	public String insert() throws IOException {
		
		if(data!=null){
			//转换File为byte[]
			byte[] docBytes = FileCopyUtils.copyToByteArray(data);
			assemp.setResume(docBytes);
		}

		service.insert(assemp);
		return "success";
	}

	@Action(value = "delete")
	public String delete() throws IOException {
		service.delete(assemp.getAssId());
		return null;
	}

	@Action(value = "update")
	public String update() throws IOException {
		
		if(data!=null){
			//转换File为byte[]
			byte[] docBytes = FileCopyUtils.copyToByteArray(data);
			assemp.setResume(docBytes);
		}
		
		service.update(assemp);
		return "success";
	}
	
	@Action(value = "query", results = { @Result(name = "success", location = "/pages/Assign/AssEmpList.jsp", type = "dispatcher") })
	public String query() {
		Pager pager = new Pager(service.getCount(), pageNum);
		List<AssignEmp> list = service.queryByPager(pager);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}

	@Action(value = "toUpdate", results = { @Result(name = "success", location = "/pages/Assign/UpdateAssEmp.jsp", type = "dispatcher") })
	public String toUpdate() {
		AssignEmp ase = service.queryById(assemp.getAssId());
		request.setAttribute("assemp", ase);
		return "success";
	}
	
	@Action(value = "queryByIndex", results = { @Result(name = "success", location = "/pages/Assign/QueryIndexAssEmp.jsp", type = "dispatcher") })
	public String queryByIndex() throws Exception {

		Pager pager = new Pager(pageNum);

		ResultBean resultBean = service.queryByIndex(keyword, pager);

		request.setAttribute("queryResult", resultBean);
		request.setAttribute("pager", pager);

		return "success";
	}
	
	//异步判断是否有简历
	@Action(value = "queryResume")
	public String queryById() throws IOException{
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		AssignEmp ase = service.queryById(assemp.getAssId());
		byte[] resume = ase.getResume();
		if(resume==null){
			out.write("notExists");
		}
		if(resume!=null){
			out.write("exists");
		}
		
		return null;
	}
	
	/**
	 * 模态层修改
	 * @return
	 */
	@Action(value = "selectEmp", results = { @Result(name = "success", location = "/pages/Assign/EmpOption.jsp", type = "dispatcher") })
	public String selectEmp() {				
		// 分页对象
		Pager pager = new Pager(service.getCount(), pageNum);
		// 调用service获得数据集合
		List<AssignEmp> list = service.queryByPager(pager);
		// 转发到JSP视图
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	/**
	 * 下载BLOB类型word文档
	 */
	@Action("download")
	public String download(){
		
		try {
		//获取该用户的简历
		AssignEmp emp = service.queryById(assemp.getAssId());		
		
		OutputStream out;
		out= response.getOutputStream();
		
		//设置客户端以附件形式接收
		String filename= URLEncoder.encode(emp.getAssName()+"-简历.doc", "utf-8") ;
		response.setHeader("content-disposition", "attachment;filename=" + filename);
		
		FileCopyUtils.copy(emp.getResume(), out);
				
		return null;
		} 
		catch (IOException e) {
			return "error";
		}
	}
	

	@Override
	public AssignEmp getModel() {
		return assemp;
	}
}