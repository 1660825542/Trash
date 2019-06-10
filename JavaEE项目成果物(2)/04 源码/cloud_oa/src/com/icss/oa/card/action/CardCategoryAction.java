package com.icss.oa.card.action;

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
import com.icss.oa.card.pojo.CardCategory;
import com.icss.oa.card.service.CardCategoryService;
import com.opensymphony.xwork2.ModelDriven;
import com.icss.oa.system.pojo.Employee;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/CardCategory")
@Results({ @Result(name = "success", location = "/CardCategory/query1.action", type = "redirect") })
public class CardCategoryAction extends BaseAction implements ModelDriven<CardCategory> {

	private CardCategory cardcategory = new CardCategory();
	
	//private Employee employee = new Employee();

	private int pageNum;// 页码

	private Integer[] ids;// 接收多个部门ID（测试用）

	@Autowired
	private CardCategoryService service;

	public CardCategory getDept() {
		return cardcategory;
	}

	public void setDept(CardCategory cardcategory) {
		this.cardcategory = cardcategory;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public CardCategory getModel() {
		return cardcategory;
	}

	@Action( "insert")
	public String insert() {
		cardcategory.setCanDelete(1);
		cardcategory.setEmpId(((Employee)request.getSession().getAttribute("queryemp")).getEmpId());
		service.insert(cardcategory);
		return "success";
	}

	@Action("delete")
	public String delete() {
		cardcategory = service.queryById(cardcategory.getCataId());
		if(cardcategory.getCanDelete()==1){
			service.delete(cardcategory.getCataId());
			return "success";
		}else{
			return "error";
		}
	}

	@Action(value = "toUpdate", results = { @Result(name = "success", location = "/pages/Card/UpdateCategory.jsp", type = "dispatcher") })
	public String toUpdate() {
		CardCategory cardcategoryObj = service.queryById(cardcategory.getCataId());
		request.setAttribute("cardcategory", cardcategoryObj);
		return "success";
	}

	@Action(value = "update")
	public String update() throws IOException {
		 CardCategory cata = service.queryById(cardcategory.getCataId());
		 cata.setCataName(cardcategory.getCataName());
		 service.update(cata);
		 return "success";
	}

	@Action(value = "query1", results = { @Result(name = "success", location = "/pages/Card/QueryCategory.jsp", type = "dispatcher") })
	public String query() {
				
		Pager pager = new Pager(service.getCount(((Employee)request.getSession().getAttribute("queryemp")).getEmpId()), pageNum); //1是当前用户id
		List<CardCategory> list = service.queryByPager(pager,((Employee)request.getSession().getAttribute("queryemp")).getEmpId());//1是当前用户id
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	

	@Action("getCateJson")
	public String getDeptJson() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		CardCategory deptObj = service.queryById(cardcategory.getCataId());
		Gson gson = new Gson();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cataName", deptObj.getCataName());
		out.write(gson.toJson(map));

		return null;
	}
	
	@Action(value = "querySelectCate", results = { @Result(name = "success", location = "/pages/Card/selectcates.jsp", type = "dispatcher") })
	public String querySelectDept(){
		// 分页对象
		Pager pager = new Pager(service.getCount(((Employee)request.getSession().getAttribute("queryemp")).getEmpId()), pageNum);//1是当前用户id
		// 调用service获得数据集合
		List<CardCategory> list = service.queryByPager(pager,((Employee)request.getSession().getAttribute("queryemp")).getEmpId());//1是当前用户id
		// 转发到JSP视图
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	
	

}