package com.icss.oa.system.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.icss.oa.common.BaseAction;
import com.icss.oa.common.Pager;
import com.icss.oa.system.index.PosIndexResultBean;
import com.icss.oa.system.pojo.Position;
import com.icss.oa.system.service.PositionService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/pos")
public class PositionAction extends BaseAction implements ModelDriven<Position> {

	private Position pos = new Position();
	
	private int pageNum=1;// 页码

	private String keyword;// 搜索关键字
	
	@Autowired
	private PositionService service;

	public Position getDept() {
		return pos;
	}

	public void setDept(Position dept) {
		this.pos = dept;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public Position getModel() {
		return pos;
	}

	@Action(value = "query", results = { @Result(name = "success", location = "/pages/system/poss.jsp", type = "dispatcher") })
	public String query() {
		// 分页对象
		Pager pager = new Pager(service.count(), pageNum);
		// 调用service获得数据集合
		List<Position> list = service.query(pager);
		// 转发到JSP视图
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "insert")
	public String insert() throws IOException{
		PrintWriter out = response.getWriter();
		int id = service.addPos(pos);
		out.print(id);
		return null;
	}
	
	@Action(value = "update")
	public String update(){
		try {
			service.editPos(pos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Action(value = "delete")
	public String delete(){
		try {
			if(pos.getPosId()!=0){
				service.deletePos(pos.getPosId());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Action(value = "queryAjaxHaveChild")
	public String queryAjaxHaveChild() throws IOException{
		PrintWriter out = response.getWriter();
		if(service.isHaveEmp(pos.getPosId()))
			out.print("1");
		else
			out.print("0");
		
		return null;
	}
	

	@Action(value = "queryByIndex", results = { @Result(name = "success", location = "/pages/system/indexPoss.jsp", type = "dispatcher") })
	public String queryByIndex() throws Exception {

		Pager pager = new Pager(pageNum);

		PosIndexResultBean resultBean = service.queryByIndex(keyword, pager);

		request.setAttribute("queryResult", resultBean);
		request.setAttribute("pager", pager);

		return "success";
	}
	
	/**
	 * 使用小窗口查询职务信息选择返回给员工
	 * @return
	 */
	@Action(value = "querySelectPos", results = { @Result(name = "success", location = "/pages/system/selectposs.jsp", type = "dispatcher") })
	public String querySelectDept(){
		// 分页对象
		Pager pager = new Pager(service.count(), pageNum);
		// 调用service获得数据集合
		List<Position> list = service.query(pager);
		
		//不显示系统管理员
		Position pos = null;
		for (Position position : list) {
			if(position.getPosId()==0){
				pos = position;
				break;
			}
		}
		list.remove(pos);
		// 转发到JSP视图
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
}