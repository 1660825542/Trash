package com.icss.oa.assign.action;

import java.io.IOException;
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
import com.icss.oa.assign.pojo.ComReq;
import com.icss.oa.assign.service.ComReqService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/comreq")
@Results({ @Result(name = "success", location = "/comreq/query.action?pageNum=${pageNum}", type = "redirect") })

public class ComReqAction extends BaseAction implements ModelDriven<ComReq> {

	private ComReq comreq = new ComReq();	//��˾�����ʵ����

	public ComReq getComreq() {
		return comreq;
	}

	public void setComreq(ComReq comreq) {
		this.comreq = comreq;
	}

	private int pageNum;	//ҳ��

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	private String keyword;// �����ؼ���

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Autowired
	private ComReqService service;// ҵ���߼�����

	@Action(value = "insert")
	public String insert() throws IOException {
		service.insert(comreq);
		return "success";
	}

	@Action(value = "delete")
	public String delete() throws IOException {
		service.delete(comreq.getComReqId());
		return null;
	}

	@Action(value = "update")
	public String update() throws IOException {
		service.update(comreq);
		return null;
	}
	
	@Action(value = "query", results = { @Result(name = "success", location = "/pages/Assign/QueryComReq.jsp", type = "dispatcher") })
	public String query() {
		// ��ҳ����
		Pager pager = new Pager(service.getCount(), pageNum);
		// ����service������ݼ���
		List<ComReq> list = service.queryByPager(pager);
		// ת����JSP��ͼ
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}

	@Action(value = "toUpdate", results = { @Result(name = "success", location = "/pages/Assign/UpdateAssEmp.jsp", type = "dispatcher") })
	public String toUpdate() {
		ComReq cmq = service.queryById(comreq.getComReqId());
		request.setAttribute("comreq", cmq);
		return "success";
	}
	
	/**
	 * ������Ϣ
	 * @return
	 */
	@Action(value = "updateComReq", results = { @Result(name = "success", location = "/pages/Assign/UpdateComReq.jsp", type = "dispatcher") })
	public String updateComReq() {
		ComReq cmq = service.queryById(comreq.getComReqId());
		request.setAttribute("comreq", cmq);
		return "success";
	}

	@Override
	public ComReq getModel() {
		return comreq;
	}
}