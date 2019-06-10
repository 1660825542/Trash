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
import com.google.gson.GsonBuilder;
import com.icss.oa.app.pojo.ReturnValue;
import com.icss.oa.app.service.TaskService;

@Controller
@Scope("prototype")
@RequestMapping(value = "appuser")
public class AppTaskAction {
	@Autowired
	private TaskService taskServiceImpl;
	private PrintWriter writer = null;

	@RequestMapping(value = "getTask")
	public void getContacts(HttpServletRequest request,
			HttpServletResponse response, String empId, String pageNum) {
		response.setContentType("text/html;charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			writer.print(gson.toJson(new ReturnValue(0, "服务器错误", null)));
			writer.close();
			return;
		}
		writer.print(gson.toJson(taskServiceImpl.getTaskList(pageNum, empId)));
		writer.close();
	}

	@RequestMapping(value = "deleteTask")
	public void getContacts(HttpServletRequest request,
			HttpServletResponse response, String taskId) {
		response.setContentType("text/html;charset=utf-8");
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			writer.print(new Gson().toJson(new ReturnValue(0, "服务器错误")));
			writer.close();
			return;
		}
		writer.print(new Gson().toJson(taskServiceImpl.deleteTask(taskId)));
		writer.close();
	}

	@RequestMapping(value = "finishTask")
	public void finishTask(HttpServletRequest request,
			HttpServletResponse response, String taskId) {
		response.setContentType("text/html;charset=utf-8");
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			writer.print(new Gson().toJson(new ReturnValue(0, "服务器错误")));
			writer.close();
			return;
		}
		writer.print(new Gson().toJson(taskServiceImpl.finishTask(taskId)));
		writer.close();
	}

}
