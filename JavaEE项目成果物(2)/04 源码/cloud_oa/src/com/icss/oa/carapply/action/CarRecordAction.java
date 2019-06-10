package com.icss.oa.carapply.action;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.icss.oa.car.pojo.Car;
import com.icss.oa.carapply.pojo.CarRecord;
import com.icss.oa.carapply.service.CarRecordService;
import com.icss.oa.common.BaseAction;
import com.icss.oa.common.Pager;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/carrecord")
@Results({ @Result(name = "success", location = "/carrecord/query.action?pageNum=${pageNum}", type = "redirect")})

public class CarRecordAction extends BaseAction implements ModelDriven<CarRecord> {

	private CarRecord carrecord = new CarRecord();
	
	public CarRecord getAssignCom() {
		return carrecord;
	}

	public void setAssignCom(CarRecord asscom) {
		this.carrecord = asscom;
	}

	private int pageNum;	//页码

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


	@Autowired
	private CarRecordService service;//
	
	@Action(value = "insert")
	public String insert() throws IOException {
		service.insert(carrecord);		
		return null;
	}

	@Action(value = "delete")
	public String delete() throws IOException{
		service.delete(carrecord.getCarrecordId());
		return null;
	}

	@Action(value = "update")
	public String update() throws IOException {
		service.update(carrecord);
		return "success";
	}

	@Action(value = "query", results = { @Result(name = "success", location = "/pages/CarApply/QueryRecord.jsp", type = "dispatcher") })
	public String query() {
		Pager pager = new Pager(service.getCount(), pageNum);
		List<CarRecord> list = service.queryByPager(pager);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "selectCar", results = { @Result(name = "success", location = "/pages/Car/SmallQueryCar.jsp", type = "dispatcher") })
	public String selectCar() {				

		Date startTime = carrecord.getStartTime();
		Date endTime = carrecord.getEndTime();
		
		// 调用service获得数据集合
		List<Car> list = service.queryByTime(startTime,endTime);
		// 转发到JSP视图
		
		request.setAttribute("list", list);
		return "success";
	}
	
	@Action(value = "exportExcel")
	public String exportExcel() throws IOException {
		// 通知客户端以附件形式接收数据	
		String filename= URLEncoder.encode("用车.xls", "utf-8") ;
		response.setHeader("content-disposition", "attachment;filename="+ filename);
		
		//文件输出流
		OutputStream os = response.getOutputStream();

		service.exportExcel(os);

		return null;
	}

	@Override
	public CarRecord getModel() {
		return carrecord;
	}
}