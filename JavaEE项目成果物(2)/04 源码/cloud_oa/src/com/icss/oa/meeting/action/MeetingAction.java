package com.icss.oa.meeting.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.service.MeetingService;
import com.icss.oa.message.pojo.Message;
import com.icss.oa.message.service.MessageService;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.emp_meeting.pojo.EmpMeeting;
import com.icss.oa.emp_meeting.service.*;



import com.opensymphony.xwork2.ModelDriven;





@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/meeting")
@Results({ @Result(name = "success", location = "/meeting/query.action", type = "redirect") })
public class MeetingAction extends BaseAction implements ModelDriven<Meeting>{
	private Meeting meeting = new Meeting();
	private int pageNum;
	private EmpMeeting empMeeting = new EmpMeeting();
	private Integer ids[];
	private int meetId;
	private int empId;
	
	@Autowired
	private MeetingService service;
	@Autowired
	private EmpMeetingService service1;
	@Autowired
	private MessageService service2;
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	
	public int getMeetId() {
		return meetId;
	}

	public void setMeetId(int meetId) {
		this.meetId = meetId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}
	@Override
	public Meeting getModel() {

		return meeting;
	}
	@Action("insert")
	public String insert() throws IOException {
		if (ids != null) {
			int seq = service.insert(meeting);
			for (Integer empId : ids) {
				empMeeting = new EmpMeeting(empId, seq);
				service1.insert(empMeeting);
			}
		}
		return "success";
	}



	@Action(value = "query", results = {@Result(name = "success", location = "/pages/Meeting/QueryMeeting.jsp", type = "dispatcher") })
	public String query() throws IOException {
		service.deleteByState(2);
		
		Pager pager = new Pager(service.getCount(), pageNum);
		List<Map<String,Object>> list = service.query(pager);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
		
	}
	
	@Action(value = "queryByRoomId",results = { @Result (name = "success",location = "/pages/Meeting/QueryApplySmall.jsp",type = "dispatcher")})
	public String queryByRoomId(){
		Pager pager = new Pager(service.getRoomCount(meeting.getRoomId()),pageNum);
		List<Map<String,Integer>> list = service.queryByRoomId(pager,meeting.getRoomId());
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "queryByState", results = {@Result(name = "success", location = "/pages/Meeting/ApproveMeeting.jsp", type = "dispatcher") })
	public String queryByState() {
		int departmentId = (int) ((Employee)request.getSession().getAttribute("queryemp")).getDepartmentId();
		Pager pager = new Pager(service.getStateCount(0,departmentId), pageNum);
		List<Map<String,Object>> list = service.queryByState(pager, 0,departmentId);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";		
	}
	
	@Action(value = "queryByOriginator", results = {@Result(name = "success", location = "/pages/Meeting/OriginatorMeeting.jsp", type = "dispatcher") })
	public String queryByOriginator(){
		int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
		Pager pager = new Pager (service.getOriginatorCount(id),pageNum);
		List<Map<String,Object>> list =service.queryByOriginator(pager, id);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
			
		return "success";
	}
	@Action(value = "queryByTheme", results = {@Result(name = "success", location = "/pages/Meeting/ThemeMeeting.jsp", type = "dispatcher") })
	public String queryByTheme(){
		
		Pager pager = new Pager (service.getThemeCount(meeting.getTheme()),pageNum);
		List<Map<String,Object>> list =service.queryByTheme(pager, meeting.getTheme());
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
			
		return "success";
	}
	
	@Action(value ="queryResult")
	public String queryResult() throws IOException{
		PrintWriter out = response.getWriter();
		List<Meeting> list =service.queryResult(meeting.getRoomId(),meeting.getMeetDateBegin(), meeting.getMeetDateEnd());
		request.setAttribute("list", list);
		
		if(list.size() == 0)
			out.print(0);
		else
			out.print(1);
		return null;
	}
	
	@Action(value="queryByDept", results = { @Result(name = "success", location = "/pages/Meeting/QueryEmpSmall.jsp", type = "dispatcher") })
	public String queryByDept() {
		int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getDepartmentId();
		Pager pager = new Pager(service.getEmpCountByDept(id),pageNum);
		List<Employee> list = service.queryEmpByDept(pager, id);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "queryByEmpId", results = {@Result(name = "success", location = "/pages/Meeting/QueryByEmpId.jsp", type = "dispatcher") })
	public String queryByEmpId(){
		int id = (int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId();
		Pager pager = new Pager (service.getByEmpIdCount(id),pageNum);
		List<Map<String,Object>> list =service.queryByEmpId(pager, id);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
			
		return "success";
	}
	
	@Action(value = "apply",results = {@Result(name ="success",location="/pages/Meeting/QueryMeeting.jsp",type = "dispatcher")})
	public String apply() throws IOException{
		
		service.insert(meeting);
		return "success";
	}
	
	@Action("agreeState")
	public String agreeState() throws IOException {
		Meeting meetobj = service.queryById(meeting.getMeetId());
		meetobj.setMeetState(1);
		service.update(meetobj);
		List<Integer> list=service.getEmpId(meeting.getMeetId());
		System.out.println(list);
		for(Integer empId:list){
			System.out.println("for");
			try{Message message = new Message(0,empId,"您有新的会议，请<a href='"+request.getContextPath()+"/meeting/queryByEmpId.action' style='color:#FF0000' target='_top'>点击</a>查看");
			System.out.println(message);
			
			service2.sendMessage(message);}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		return null;
	}
	@Action("refuseState")
	public String refuseState() throws IOException {
		Meeting meetobj = service.queryById(meeting.getMeetId());
		meetobj.setMeetState(2);
		service.update(meetobj);
		service1.delete(meeting.getMeetId());
		return null;
		
	}
	@Action("deleteById")
	public String deleteById() throws IOException {
		service.deleteById(meeting.getMeetId());
		return null;
	}
	
}
