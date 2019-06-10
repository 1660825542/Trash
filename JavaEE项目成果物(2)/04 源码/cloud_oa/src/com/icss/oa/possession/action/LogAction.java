package com.icss.oa.possession.action;
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
import com.icss.oa.possession.pojo.Log;

import com.icss.oa.possession.service.LogService;


import com.opensymphony.xwork2.ModelDriven;
@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/log")
@Results({ @Result(name = "success", location = "/poss/query.action?pageNum=${pageNum}", type = "redirect") })
public class LogAction  extends BaseAction implements ModelDriven<Log>{
	private Log log = new Log();
	private int pageNum;
	@Autowired
	private LogService service;

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public LogService getService() {
		return service;
	}

	public void setService(LogService service) {
		this.service = service;
	}

	@Override
	public Log getModel() {
		return log;
	}
	@Action("insert")
	public String insert() {
		System.out.println(log);
		service.insert(log);
		   
		return "success";
	}

	@Action("delete")
	public String delete() {
		service.delete(log.getLogId());
		return "success";
	}
	@Action(value = "toUpdate", results = { @Result(name = "success", location = "/pages/UpdatePoss.jsp", type = "dispatcher") })
	public String toUpdate() {
		Log logObj = service.queryById(log.getLogId());
		request.setAttribute("log", logObj);
		return "success";
	}

	@Action("update")
	public String update() {
		service.update(log);
		return "success";
	}
	@Action(value = "query", results = { @Result(name = "success", location = "/pages/AddPoss.jsp", type = "dispatcher") })
	public String query() {
				
		Pager pager = new Pager(service.getCount(), pageNum);
		List<Log> list = service.query(pager);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	@Action(value ="exportExcel")
	public String exportExcel() throws IOException {
		// 通知客户端以附件形式接收数据	
		String filename= URLEncoder.encode("办公用品消耗.xls", "utf-8") ;
		response.setHeader("content-disposition", "attachment;filename="+ filename);
		
		//文件输出流
		OutputStream os = response.getOutputStream();

		service.exportExcel(os);

		return null;
	}


	

}
