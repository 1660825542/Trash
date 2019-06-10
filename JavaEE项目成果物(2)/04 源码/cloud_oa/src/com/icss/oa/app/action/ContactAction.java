package com.icss.oa.app.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.icss.oa.app.pojo.ReturnValue;
import com.icss.oa.app.service.ContactService;
import com.icss.oa.app.service.EmployeeService;

@Controller
@Scope("prototype")
@RequestMapping(value = "appuser")
public class ContactAction {
	@Autowired
	ContactService contactServiceImpl;
	@Autowired
	EmployeeService employeeServiceImpl;
	private PrintWriter writer = null;

	@RequestMapping(value = "getContacts")
	public void getContacts(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			writer.print(new Gson().toJson(new ReturnValue(1, "服务器错误", null)));
			writer.close();
			return;
		}
		writer.print(new Gson().toJson(new ReturnValue(0, "请求成功",
				contactServiceImpl.getContacts())));
		writer.close();
	}

	@RequestMapping(value = "getDetailInfo")
	public void getDetailInfo(HttpServletRequest request,
			HttpServletResponse response, String empNum) {
		response.setContentType("text/html;charset=utf-8");
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			writer.print(new Gson().toJson(new ReturnValue(2, "服务器错误", null)));
			writer.close();
			return;
		}
		writer.print(new Gson().toJson(employeeServiceImpl
				.getEmployeeByEmpNum(empNum)));
		writer.close();
	}
}
