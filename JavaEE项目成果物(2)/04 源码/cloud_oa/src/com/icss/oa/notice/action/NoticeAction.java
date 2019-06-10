package com.icss.oa.notice.action;

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
import com.icss.oa.notice.pojo.Notice;
import com.icss.oa.notice.service.NoticeService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/notice")
@Results({ @Result(name = "success", location = "/notice/query.action", type = "redirect")})
public class NoticeAction extends BaseAction implements ModelDriven<Notice> {

	private Notice notice = new Notice();	//员工信息的实体类
	
	private int noticeId;
	
	private int pageNum;	//传入页码

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	@Autowired
	private NoticeService service;

	@Action(value = "insert")
	public String insert() throws IOException {
		try {
			service.insert(notice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	@Action(value = "delete")
	public String delete() throws IOException {
		service.delete(notice.getNoticeId());
		return null;
	}

	@Action(value = "update")
	public String update() throws IOException {
		service.update(notice);
		return "success";
	}
	
	@Action(value = "onePieceNotice" , results = { @Result(name = "success", location = "/pages/Notice/QueryNoticeMobile.jsp", type = "dispatcher") })
	public String onePieceNotice() throws IOException {
		Notice ntc = service.queryById(notice.getNoticeId());
		request.setAttribute("notice", ntc);
		System.out.println(ntc);
		return "success";
	}
	
	@Action(value = "query", results = { @Result(name = "success", location = "/pages/Notice/QueryNotice.jsp", type = "dispatcher") })
	public String query() {
		List<Notice> listTop = service.queryTop();
		
		List<Notice> listNotTop = service.queryNotTop();
		
		List<Notice> list = service.query();
		
		request.setAttribute("listTop", listTop);
		
		request.setAttribute("listNotTop", listNotTop);
		
		request.setAttribute("list", list);
		
		return "success";
	}
	
	@Action(value = "queryByPager", results = { @Result(name = "success", location = "/pages/Notice/QueryNotice.jsp", type = "dispatcher") })
	public String queryByPager() {
		Pager pager = new Pager(service.getCount(), pageNum);
		List<Notice> list = service.queryByPager(pager);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "queryByPager2", results = { @Result(name = "success", location = "/include/noticePanel.jsp", type = "dispatcher") })
	public String queryByPager2() {
		Pager pager = new Pager(service.getCount(), pageNum);
		List<Notice> list = service.queryByPager(pager);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "queryByPager3")
	public String queryByPager3() {
		Pager pager = new Pager(service.getCount(), pageNum);
		List<Notice> list = service.queryByPager(pager);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return null;
	}

	/**
	 * 模态层修改
	 * @return
	 * @throws IOException 
	 */
	@Action(value = "updateNotice", results = { @Result(name = "success", location = "/pages/Notice/UpdateNotice.jsp", type = "dispatcher") })
	public String updateNotice() throws IOException {
		Notice ntc = service.queryById(notice.getNoticeId());
		request.setAttribute("notice", ntc);
		return "success";
	}
	
	@Override
	public Notice getModel() {
		// TODO Auto-generated method stub
		return notice;
	}	
	
}