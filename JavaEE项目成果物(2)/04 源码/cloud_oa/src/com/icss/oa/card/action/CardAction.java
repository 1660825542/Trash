package com.icss.oa.card.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.icss.oa.card.pojo.Card;
import com.icss.oa.card.pojo.CardCategory;
import com.icss.oa.card.service.CardCategoryService;
import com.icss.oa.card.service.CardService;
import com.icss.oa.common.BaseAction;
import com.icss.oa.common.Pager;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.DepartmentService;
import com.icss.oa.system.service.EmployeeService;
import com.icss.oa.system.service.PositionService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/Card")
@Results({ @Result(name = "success", location = "/Card/query.action", type = "redirect") })
public class CardAction extends BaseAction implements ModelDriven<Card> {

	private Card card = new Card();
	
	private CardCategory cate = new CardCategory();
	
	//private Employee employee = new Employee();

	private int pageNum;// 页码

	private Integer[] ids;// 接收多个部门ID（测试用）
	
	private String empName;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Autowired
	private CardService service;
	
	@Autowired
	private CardCategoryService cateService;
	
	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	private PositionService posService;
	
	@Autowired
	private EmployeeService empservice;

	public Card getDept() {
		return card;
	}

	public void setDept(Card card) {
		this.card = card;
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
	public Card getModel() {
		return card;
	}

	@Action("insert")
	public String insert() {
		service.insert(card);
		return "success";
	}

	@Action("delete")
	public String delete() {
		service.delete(card.getCardId());
		return "success";
	}

	@Action(value = "toUpdate", results = { @Result(name = "success", location = "/pages/Card/UpdateCard.jsp", type = "dispatcher") })
	public String toUpdate() {
		Card cardObj = service.queryById(card.getCardId());
		request.setAttribute("card", cardObj);
		return "success";
	}

	@Action("update")
	public String update() throws IOException {
		System.out.println(card);
		service.update(card);
		
		return "success";
	}

	@Action(value = "query", results = { @Result(name = "success", location = "/pages/Card/QueryCard.jsp", type = "dispatcher") })
	public String query() {
		List<CardCategory> cateList = cateService.query(((Employee)request.getSession().getAttribute("queryemp")).getEmpId());//1表示当前用户Id
		int sum = 0;
		for(CardCategory temp:cateList) {
			sum += service.getCount(temp.getCataId());
		}		
		Pager pager = new Pager(sum, pageNum);
		List<Card> list = service.query(pager,((Employee)request.getSession().getAttribute("queryemp")).getEmpId());//1表示当前用户Id
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	
	

	@Action("getDeptJson")
	public String getDeptJson() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		Card deptObj = service.queryById(card.getCardId());
		Gson gson = new Gson();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cardName", deptObj.getCardName());
		out.write(gson.toJson(map));

		return null;
	}
	
	/*@Action(value = "toquery", results = { @Result(name = "success", location = "/pages/Card/toQueryCard.jsp", type = "dispatcher") })
	public String toquery() {
				
		Pager pager = new Pager(service.getCount(), pageNum);
		List<Card> list = service.query(pager);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}*/
	
	/*@Action(value = "toquery1", results = { @Result(name = "success", location = "/pages/Card/toQueryCard.jsp", type = "dispatcher") })
	public String toquery1() {
				
		Pager pager = new Pager(service.getCount(), pageNum);
		List<Card> list = service.query1(pager);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}*/
	
	@Action(value = "queryCatecard", results = { @Result(name = "success", location = "/pages/Card/QueryCatecard.jsp", type = "dispatcher") })
	public String queryCatecard() {
				
		Pager pager = new Pager(service.getCount(card.getCataId()), pageNum);
		//List<CardCategory> list = service.query(pager);
		List<Card> list = service.queryCatecard(pager, card.getCataId());
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	public CardCategory getCate() {
		return cate;
	}

	public void setCate(CardCategory cate) {
		this.cate = cate;
	}

	/**
	 * 带条件查询
	 * 
	 * @return
	 * @throws Exception 
	 */
	@Action(value = "queryByCondition", results = { @Result(name = "success", location = "/pages/Card/QueryConditionCard.jsp", type = "dispatcher") })
	public String queryByCondition() throws Exception {
		
		//get请求传递中文需要手动转码
		String cardName = new String(card.getCardName().getBytes("iso-8859-1"),"utf-8");
		card.setCardName(cardName);
		
		System.out.println("cardName=" + card.getCardName());
				
		Pager pager = new Pager(service.getConditionCount(card.getCardName()),
				pageNum, 6);
		List<Card> list = service.querByCondition(pager, card.getCardName());
		List<Card> cardList = new ArrayList<Card>();
		for(Card temp : list) {
			CardCategory cataTemp =cateService.queryById(temp.getCataId());
			if(((Employee)request.getSession().getAttribute("queryemp")).getEmpId()==cataTemp.getEmpId()) {
				cardList.add(temp);
			}
		} 
		request.setAttribute("list", cardList);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "queryByConditionEmp", results = { @Result(name = "success", location = "/pages/Card/QueryConditionEmp.jsp", type = "dispatcher") })
	public String queryByConditionEmp() throws Exception {
		
		//get请求传递中文需要手动转码
		String name = new String(empName.getBytes("iso-8859-1"),"utf-8");
		System.out.println(name);
		Pager pager = new Pager(empservice.conditionCount(name));
		List<Map<String, Object>> emp = empservice.queryByCondition(pager, name);
		for(Map<String,Object> temp : emp) {
			temp.put("deptName",(deptService.queryById(new BigDecimal(String.valueOf(temp.get("DEPARTMENT_ID"))).intValue()).getDeptName()));
			temp.put("posName",(posService.queryById(new BigDecimal(String.valueOf(temp.get("POSITION_ID"))).intValue()).getPosName()));
		}
		request.setAttribute("list", emp);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "queryAll", results = { @Result(name = "success", location = "/pages/Card/emps.jsp", type = "dispatcher") })
	public String queryAll(){
		// 分页对象
		Pager pager = new Pager(empservice.count(), pageNum);
		// 调用service获得数据集合
		List<Map<String,Object>> list = empservice.queryEmployee(pager);
		// 转发到JSP视图
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action("exportExcel")
	public String exportExcel() throws IOException{

		// 通知客户端以附件形式接收数据		
		response.setHeader("content-disposition", "attachment;filename=emp.xls");

		OutputStream os = response.getOutputStream();

		service.exportExcel(os);

		return null;
	}
	
}