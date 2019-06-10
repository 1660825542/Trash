package com.icss.oa.room.action;


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
import com.icss.oa.room.pojo.Room;

import com.icss.oa.room.service.RoomService;


import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/room")
@Results({ @Result(name = "success", location = "/room/query.action", type = "redirect") })
public class RoomAction extends BaseAction implements ModelDriven<Room> {
	
		private Room room = new Room();
		
		private int pageNum;
		
		private Short checkbox1;
		
		private Short radio1;
		
		public Short getRadio1() {
			return radio1;
		}
		public void setRadio1(Short radio1) {
			this.radio1 = radio1;
		}
		public Short getCheckbox1() {
			return checkbox1;
		}
		public void setCheckbox1(Short checkbox1) {
			this.checkbox1 = checkbox1;
		}
		private Short checkbox2;
		
		public Short getCheckbox2() {
			return checkbox2;
		}
		public void setCheckbox2(Short checkbox2) {
			this.checkbox2 = checkbox2;
		}

		@Autowired
		private RoomService service;
		
		public Room getRoom(){
			return room;
		}
		public void setRoom(Room room){
			this.room = room;
		} 

		public int getPageNum() {
			return pageNum;
		}

		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}

		/*
		 * 全文检索
		 * 
		 * 
		 * public String getQueryStr() {
			return queryStr;
		}

		public void setQueryStr(String queryStr) {
			this.queryStr = queryStr;
		}*/

		@Override
		public Room getModel() {

			return room;
		}

		@Action("insert")
		public String insert() throws IOException {
			Short airCon = radio1;

			room.setAirCon(airCon);
			room.setRoomState((short) 0);
			service.insert(room);
			return "success";
		}


		@Action("delete")
		public String delete() throws IOException {
			service.delete(room.getRoomId());
			return null;
		}

/*		@Action(value = "toUpdate", results = { @Result(name = "success", location = "/pages/XXXXXXX.jsp", type = "dispatcher") })
		public String toUpdate() {
			Room roomObj = service.queryById(room.getRoomId());
			request.setAttribute("room", roomObj);
			return "success";
		}*/

		@Action(value = "query", results = { @Result(name = "success", location = "/pages/Room/QueryRoom.jsp", type = "dispatcher") })
		public String query() {
			Pager pager = new Pager(service.getCount(), pageNum);
			List<Room> list = service.query(pager);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			System.out.println(list);

			return "success";
			
		}
		@Action(value = "querySmall", results = { @Result(name = "success", location = "/pages/Room/QueryRoomSmall.jsp", type = "dispatcher") })
		public String querySmall() {
			Pager pager = new Pager(service.getCount(), pageNum);
			List<Room> list = service.query(pager);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			return "success";
		}
		
		/**
		 * 带条件查询
		 * 
		 * @return
		 * @throws Exception 
		 */
		
		@Action(value = "queryByCondition", results = { @Result(name = "success", location = "/pages/Room/QueryConditionRoom.jsp", type = "dispatcher") })
		public String queryByCondition() throws Exception  {

			String roomPlace = new String(room.getRoomPlace().getBytes("iso-8859-1"),"utf-8");		
			Short airCon = checkbox1;
			Short roomState = checkbox2;
			Integer accomNum =room.getAccomNum();
			
			room.setRoomPlace(roomPlace);
			room.setAirCon(airCon);
			room.setRoomState(roomState);
			room.setAccomNum(accomNum);
			
			System.out.println("roomPlace=" + room.getRoomPlace());
			System.out.println("airCon=" + room.getAirCon());
			System.out.println("state=" + room.getRoomState());
			System.out.println("accomNum=" + room.getAccomNum());

			Pager pager = new Pager(service.getConditionCount(roomPlace, airCon, roomState, accomNum),pageNum);
			System.out.println(pager);
			List<Room> list = service.queryByCondition(pager, roomPlace, airCon, roomState, accomNum);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			System.out.println(list);
			return "success";
		}



}
