package com.icss.oa.car.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;

import com.icss.oa.car.pojo.Car;
import com.icss.oa.car.service.CarService;
import com.icss.oa.common.BaseAction;
import com.icss.oa.common.Pager;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/car")
@Results({ @Result(name = "success", location = "/car/query.action?pageNum=${pageNum}", type = "redirect") })
public class CarAction extends BaseAction implements ModelDriven<Car> {

	private Car car = new Car(); // 鍛樺伐淇℃伅鐨勫疄浣撶被

	private File data;// 涓婁紶鐨勬枃浠舵暟鎹�

	private String dataFileName;// 鏂囦欢鍚嶇О

	private String dataContentType;// 鏂囦欢MIME绫诲瀷

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	private int pageNum; // 浼犲叆椤电爜

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public File getData() {
		return data;
	}

	public void setData(File data) {
		this.data = data;
	}

	public String getDataFileName() {
		return dataFileName;
	}

	public void setDataFileName(String dataFileName) {
		this.dataFileName = dataFileName;
	}

	public String getDataContentType() {
		return dataContentType;
	}

	public void setDataContentType(String dataContentType) {
		this.dataContentType = dataContentType;
	}

	@Autowired
	private CarService service;

	@Action(value = "insert")
	public String insert() throws IOException {

		if (data != null) {
			// 杞崲File涓篵yte[]
			byte[] docBytes = FileCopyUtils.copyToByteArray(data);
			car.setPicture(docBytes);
		}

		service.insert(car);
		return "success";
	}

	@Action(value = "delete")
	public String delete() throws IOException {
		service.delete(car.getCarId());
		System.out.println(car);
		return null;
	}

	@Action(value = "update")
	public String update() throws IOException {

		if (data != null) {
			// 杞崲File涓篵yte[]
			byte[] docBytes = FileCopyUtils.copyToByteArray(data);
			car.setPicture(docBytes);
		}

		service.update(car);
		return "success";
	}

	@Action(value = "query", results = { @Result(name = "success", location = "/pages/Car/QueryCar.jsp", type = "dispatcher") })
	public String query() {
		Pager pager = new Pager(service.getCount(), pageNum);
		List<Car> list = service.queryByPager(pager);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		return "success";
	}

	@SuppressWarnings("deprecation")
	@Action(value = "queryPic", results = { @Result(name = "success", location = "/pages/Car/ViewPicture.jsp", type = "dispatcher") })
	public String queryPic() throws IOException {

		Car cars = service.queryById(car.getCarId());

//		String photo = Base64.encode(cars.getPicture());

//		request.setAttribute("photo", photo);
		request.setAttribute("cars", cars);

		return "success";
	}

	@Action(value = "queryPic2")
	public String queryPic2() throws IOException {
		
		PrintWriter out = response.getWriter();

		Car cars = service.queryById(car.getCarId());

//		String photo = Base64.encode(cars.getPicture());
		
//		out.write(photo);

		return null;
	}

	@Action(value = "updateCar", results = { @Result(name = "success", location = "/pages/Car/UpdateCar.jsp", type = "dispatcher") })
	public String updateCar() throws IOException {
		Car ntc = service.queryById(car.getCarId());
		request.setAttribute("car", ntc);
		return "success";
	}

	@Override
	public Car getModel() {
		return car;
	}
}