package com.icss.oa.possession.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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

import com.google.gson.Gson;
import com.icss.oa.common.BaseAction;
import com.icss.oa.common.Pager;
import com.icss.oa.possession.pojo.House;
import com.icss.oa.possession.pojo.Poss;
import com.icss.oa.possession.service.HouseService;
import com.icss.oa.possession.service.PossService;
import com.opensymphony.xwork2.ModelDriven;

	@Controller
	@Scope("prototype")
	@ParentPackage("struts-default")
	@Namespace("/poss")
	@Results({ @Result(name = "success", location = "/poss/query.action?pageNum=${pageNum}", type = "redirect") })
	public  class PossAction extends BaseAction implements ModelDriven<Poss>{

		
		private Poss poss = new Poss();
		
		private int pageNum;
		private int possBack;
		
		
		
		public int getPossBack() {
			return possBack;
		}
		public void setPossBack(int possBack) {
			this.possBack = possBack;
		}

		@Autowired
		private PossService service;
		@Autowired
		private HouseService service2;
		
		public PossService getService() {
			return service;
		}
		public void setService(PossService service) {
			this.service = service;
		}
		public Poss getPoss() {
			return poss;
		}
		public void setPoss(Poss poss) {
			this.poss = poss;
		}
		public int getPageNum() {
			return pageNum;
		}
		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}
		@Override
		public Poss getModel() {
			return poss;
		}
		
		@Action("insert")
		public String insert() {
			
			service.insert(poss);
			return "success";
		}
		
		@Action("update")
		public String update() {
			service.update(poss,possBack);
			return "success";
		}
		@Action("delete")
		public String delete() {
			System.out.println(poss);
			service.delete(poss.getPossId());
			return "success";
		}
		@Action(value = "toUpdate", results = { @Result(name = "success", location = "/pages/possmanage.jsp", type = "dispatcher") })
		public String toUpdate() {
			Poss possObj = service.queryById(poss.getPossId());
			request.setAttribute("poss", possObj);
			return "success";
		}

	
		@Action(value = "query", results = { @Result(name = "success", location = "/pages/possinfo/possmanage.jsp", type = "dispatcher") })
		public String query() {
					
			Pager pager = new Pager(service.getCount(), pageNum);
			List<Poss> list = service.query(pager);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			return "success";
		}
		@Action("getPossJson")
		public String getPossJson() throws IOException {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			Poss possObj = service.queryById(poss.getPossId());
			Gson gson = new Gson();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("possName",possObj.getPossName());
			out.write(gson.toJson(map));
            return null;
		}
		
		// 获取员工姓名
		/**@Action(value = "getEmpName")
		public String getEmpName() throws IOException {
			try{
				response.setContentType("text/html;charset=utf-8");
				String name = "";
				PrintWriter out = response.getWriter();
				try {
					System.out.println("么么");
					name = service.queryEmpById(Integer.parseInt(request.getParameter("empId")));
				} catch (Exception e) {
					e.printStackTrace();
					out.print(" ");
					return null;
				}
				out.write(name);
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
			return null;
		}*/

		@Action(value = "getPossUnuse")
		public Integer getPossUnuse() throws IOException {
			try{
				response.setContentType("text/html;charset=utf-8");
				Integer possUnuse;
				PrintWriter out = response.getWriter();
			
				try {
					possUnuse = service.queryPossUnusedById(poss.getPossId());
				} catch (Exception e) {
					e.printStackTrace();
					out.print(" ");
					return null;
				}
				out.write(possUnuse);
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
			return null;
		}
		
		@Action(value = "queryIsExist")
		public String queryIsExist() throws IOException{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String input = request.getParameter("param");
			Integer inputNum = Integer.parseInt(input);
			Integer possUnuse;
			possUnuse = service.queryPossUnusedById(poss.getPossId());
			
			System.out.println(possUnuse);
			
			if(inputNum>possUnuse)
				out.print("{\"info\":\"消耗数量大于库存数量，请重新输入\",\"status\":\"n\"}");
			else
				out.print("{\"info\":\" \",\"status\":\"y\"}");
			return null;
		}
		@Action(value = "queryByCondition", results = { @Result(name = "success", location = "/pages/possinfo/possmanage.jsp", type = "dispatcher") })
		public String queryByCondition() throws Exception {
		    //get请求传递中文需要手动转码
			System.out.println(poss.getPossName());
			String possName = new String(poss.getPossName().getBytes("iso-8859-1"),"utf-8");
			poss.setPossName(possName);
			System.out.println("possName=" + poss.getPossName());
					
			Pager pager = new Pager(service2.getConditionCount(poss.getPossName()),8,pageNum);
			
			System.out.print(pager.getPageCount());
			
			List<House> list = service2.querByCondition(pager, poss.getPossName());
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			return "success";
		}
		
		//资产类别查询
		@Action(value = "queryByConditionCate", results = { @Result(name = "success", location = "/pages/possinfo/possmanage.jsp", type = "dispatcher") })
		public String queryByConditionCate() throws Exception {
		    //get请求传递中文需要手动转码
			System.out.println(poss.getPossCate());
		    String possCate = new String(poss.getPossCate().getBytes("iso-8859-1"),"utf-8");
		    poss.setPossCate(possCate);
		    System.out.println("possCate=" + poss.getPossCate());
			Pager pager = new Pager(service.getConditionCateCount(poss.getPossCate()),8,pageNum);
			List<Poss> list = service.querByConditionCate(pager, poss.getPossCate());
			
			System.out.print(pager.getPageCount());
			
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			request.setAttribute("searchParam", "&possCate="+possCate);
			return "success";
		}
		
		
		@Action(value = "queryByConditionId", results = { @Result(name = "success", location = "/pages/possinfo/possmanage.jsp", type = "dispatcher") })
		public String queryByConditionId() throws Exception {
		    //get请求传递中文需要手动转码
			System.out.println(poss.getPossId());
			Integer possId = new Integer(poss.getPossId());
			poss.setPossId(possId);
			System.out.println("possId=" + poss.getPossId());
			Pager pager = new Pager(service2.getConditionIdCount(poss.getPossId()),8,pageNum);
			
			
			
			List<House> list = service2.querByConditionId(pager, poss.getPossId());
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			return "success";
		}
		
		@Action(value = "queryEmpSmall", results = { @Result(name = "success", location = "/pages/possinfo/AddEmp.jsp", type = "dispatcher") })
		public String querySmall() {
					
			Pager pager = new Pager(service.getEmpCount(), pageNum);
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", pager.getStart());
			map.put("end", pager.getPageSize()+pager.getStart()-1);
			List<Map<String, Object>> list = service.queryEmp(map);
			System.out.println(service.getEmpCount());
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			return "success";
		}
	

}
