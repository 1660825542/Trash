package com.icss.oa.card.action;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.icss.oa.common.BaseAction;
import com.icss.oa.common.Pager;
import com.icss.oa.card.pojo.AddressList;
import com.icss.oa.card.service.AddressListService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/addresslist")
//@Results({ @Result(name = "success", location = "/addresslist/query.action", type = "redirect") })
public class AddressListAction extends BaseAction implements ModelDriven<AddressList>{
	
	private AddressList addresslist = new AddressList();
	
	private int pageNum;
     
	@Autowired
	private AddressListService service;
	
	@Override
	public AddressList getModel() {

		return addresslist;
	}

	public AddressList getAddresslist() {
		return addresslist;
	}

	public void setAddresslist(AddressList addresslist) {
		this.addresslist = addresslist;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	@Action(value = "query", results = { @Result(name = "success", location = "/pages/Card/AddressList.jsp", type = "dispatcher") })
	public String query() {
		Pager pager = new Pager(service.getCount(), pageNum);
		List<Map<String, Object>> list = service.query(pager);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "queryByCondition", results = { @Result(name = "success", location = "/pages/Card/QueryConditionAddressList.jsp", type = "dispatcher") })
	public String queryByCondition() throws Exception {
		
		//get请求传递中文需要手动转码
		String empName = new String(addresslist.getEmpName().getBytes("iso-8859-1"),"utf-8");
		addresslist.setEmpName(empName);
		
		System.out.println("empName=" + addresslist.getEmpName());
				
		Pager pager = new Pager(service.getConditionCount(addresslist.getEmpName()),
				pageNum, 6);
		List<Map<String, Object>> list = service.queryByCondition(pager, addresslist.getEmpName());
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action("exportExcel")
	public String exportExcel(){
		
//		System.out.println(addresslist.getEmpName());	
		// 通知客户端以附件形式接收数据		
		try{
			
			response.setHeader("content-disposition", "attachment;filename=job.xls");
			
			OutputStream os = response.getOutputStream();
//			String empName = new String(addresslist.getEmpName().getBytes("iso-8859-1"),"utf-8");
			service.exportExcel(os,addresslist.getEmpName());
		}catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}
}
