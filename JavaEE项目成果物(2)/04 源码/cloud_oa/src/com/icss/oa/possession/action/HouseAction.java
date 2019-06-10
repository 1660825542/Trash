package com.icss.oa.possession.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.google.gson.Gson;

import com.icss.oa.common.BaseAction;
import com.icss.oa.common.Pager;
import com.icss.oa.possession.pojo.House;
import com.icss.oa.possession.service.HouseService;









import com.opensymphony.xwork2.ModelDriven;

	@Controller
	@Scope("prototype")
	@ParentPackage("struts-default")
	@Namespace("/house")
	@Results({ @Result(name = "success", location = "/house/query.action?pageNum=${pageNum}", type = "redirect") })
	public  class HouseAction extends BaseAction implements ModelDriven<House>{

		
		private House house = new House();
		
		private int pageNum;
		
		
		@Autowired
		private HouseService service;
		
		
		public HouseService getService() {
			return service;
		}
		public void setService(HouseService service) {
			this.service = service;
		}
		public House getHouses() {
			return house;
		}
		public void setHouses(House house) {
			this.house = house;
		}
		public int getPageNum() {
			return pageNum;
		}
		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}
		@Override
		public House getModel() {
			return house;
		}
		
		@Action("insert")
		public String insert() {
			System.out.println(house);
			service.insert(house);
			return "success";
		}
		
		@Action("update")
		public String update() {
			service.update(house);
			return "success";
		}
	
		
		@Action("save")
		public String save(){
			
			if(house.getPossId()==null){
				
				service.insert(house);
			}else{
				service.update(house);
				
			}
			return "success";
		}
		
		@Action("delete")
		public String delete() {
			System.out.println(house.getPossId());
		 
			service.update1(house.getPossId());
			return "success";
		}
		@Action(value = "toUpdate", results = { @Result(name = "success", location = "/pages/kucun.jsp", type = "dispatcher") })
		public String toUpdate() {
			House possObj = service.queryById(house.getPossId());
			request.setAttribute("poss", possObj);
			return "success";
		}

	
		@Action(value = "query", results = { @Result(name = "success", location = "/pages/possinfo/kucun.jsp", type = "dispatcher") })
		public String query() {
					
			Pager pager = new Pager(service.getCount(),8, pageNum);
			List<House> list = service.query(pager);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			
			return "success";
		}
		
		//按资产名称查询
		@Action(value = "queryByCondition", results = { @Result(name = "success", location = "/pages/possinfo/kucun.jsp", type = "dispatcher") })
		public String queryByCondition() throws Exception {
		    //get请求传递中文需要手动转码
			System.out.println(house.getPossName());
			String possName = new String(house.getPossName().getBytes("iso-8859-1"),"utf-8");
			house.setPossName(possName);
			System.out.println("possName=" + house.getPossName());
					
			Pager pager = new Pager(service.getConditionCount(house.getPossName()),8,pageNum);
			
			System.out.print(pager.getPageCount());
			
			List<House> list = service.querByCondition(pager, house.getPossName());
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			return "success";
		}
		
		//资产类别查询
		@Action(value = "queryByConditionCate", results = { @Result(name = "success", location = "/pages/possinfo/kucun.jsp", type = "dispatcher") })
		public String queryByConditionCate() throws Exception {
		    //get请求传递中文需要手动转码
			System.out.println(house.getPossCate());
		String possCate = new String(house.getPossCate().getBytes("iso-8859-1"),"utf-8");
		house.setPossCate(possCate);
		System.out.println("possCate=" + house.getPossCate());
			Pager pager = new Pager(service.getConditionCateCount(house.getPossCate()),8,pageNum);
			List<House> list = service.querByConditionCate(pager, house.getPossCate());
			
			System.out.print(pager.getPageCount());
			
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			request.setAttribute("searchParam", "&possCate="+possCate);
			return "success";
		}
		/**@Action(value = "queryByConditionId", results = { @Result(name = "success", location = "/pages/possinfo/kucun.jsp", type = "dispatcher") })
		public String queryByConditionId() throws Exception {
		    
			try {
				//get请求传递中文需要手动转码
				System.out.println(house.getPossId());
				Integer possId = new Integer(house.getPossId());
				house.setPossId(possId);
				
				System.out.println("possId=" + house.getPossId());
				List<House> list;
				Pager pager;
				int count = service.getConditionIdCount(house.getPossId());
				if(count==0){
					list = new ArrayList<House>();
					pager= new Pager(count,pageNum);
				}else{
					pager = new Pager(count,8,pageNum);
					
					list = service.querByConditionId(pager, house.getPossId());
				}
				
				
				request.setAttribute("list", list);
				request.setAttribute("pager", pager);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}*/
		
		//按资产编号查询
		@Action(value = "queryByConditionId", results = { @Result(name = "success", location = "/pages/possinfo/kucun.jsp", type = "dispatcher") })
		public String queryByConditionId() throws Exception {
		    //get请求传递中文需要手动转码
			System.out.println(house.getPossId());
			Integer possId = new Integer(house.getPossId());
			house.setPossId(possId);
			System.out.println("possId=" + house.getPossId());
			Pager pager = new Pager(service.getConditionIdCount(house.getPossId()),8,pageNum);
			List<House> list = service.querByConditionId(pager, house.getPossId());
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			return "success";
		}
		@Action("getPossJson")
		public String getPossJson() throws IOException {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			House possObj = service.queryById(house.getPossId());
			Gson gson = new Gson();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("possName",possObj.getPossName());
			out.write(gson.toJson(map));
            return null;
		}
		
		@Action(value = "querySmall", results = { @Result(name = "success", location = "/pages/possinfo/AddPoss.jsp", type = "dispatcher") })
		public String querySmall() {
					
			Pager pager = new Pager(service.getCount(), pageNum);
			List<House> list = service.query(pager);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			return "success";
		}
	

}
