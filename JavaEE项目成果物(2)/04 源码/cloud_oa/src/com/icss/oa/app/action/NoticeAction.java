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
import com.icss.oa.app.service.NoticeService;

@Controller("appNoticeAction")
@Scope("prototype")
@RequestMapping(value = "appuser")
public class NoticeAction {
	private PrintWriter writer = null;

	@Autowired
	private NoticeService noticeServiceImpl;

	@RequestMapping(value = "getNotice")
	public void getNotice(HttpServletRequest request,
			HttpServletResponse response, String pageNum) {
		response.setContentType("text/html;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		System.out.println("pageNum:" + pageNum);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
				.create();
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			writer.print(gson.toJson(new ReturnValue(0, "·þÎñÆ÷´íÎó", null)));
			writer.close();
			return;
		}
		writer.print(gson.toJson(noticeServiceImpl.getNoticeList(pageNum)));
		writer.close();
	}

}
