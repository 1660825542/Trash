package com.icss.oa.record.action;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;

import com.icss.oa.common.BaseAction;
import com.icss.oa.record.pojo.Record;
import com.icss.oa.record.service.RecordService;


import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/record")
/*@Results({ @Result(name = "success", location = "/meeting/queryByOriginator.action?originator=1", type = "redirect") })*/
public class RecordAction extends BaseAction implements ModelDriven<Record>{
	private Record record = new Record();
	private File data;
	@Autowired
	private RecordService service;

	
	public File getData() {
		return data;
	}
	public void setData(File data) {
		this.data = data;
	}
	@Override
	public Record getModel() {

		return record;
	}
	
	@Action(value="upload" )
	public String upload() throws IOException {
		
/*			if (data == null || data.length() == 0) {
				return "error";
			}*/
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		Record preRecord=service.query(record.getMeetId());
		System.out.println(preRecord);
		if(preRecord==null){
			byte[] fileBytes;
			fileBytes = FileCopyUtils.copyToByteArray(data);	
			record.setRecHtml(fileBytes);
			service.insert(record);
			out.write("上传成功");}
		else{
			out.write("已存在会议记录，不可再次上传");
		}
			return null;
	}
	
	@Action("download")
	public String download() throws IOException{

		record=service.query(record.getMeetId());


		String filename = record.getMeetId().toString() + ".txt";


		response.setHeader("content-disposition", "attachment;filename=" + filename);
		
		OutputStream out = response.getOutputStream();
		
		System.out.println(record.getRecHtml());
		
		FileCopyUtils.copy(record.getRecHtml(), out);

		return null;
	}
	@Action("del")
	public String delete() throws IOException{
		response.setContentType("text/html;charset=utf-8");


			service.delete(record.getMeetId());

		return null;
	}
	@Action("query")
	public String query() throws IOException{
		PrintWriter out = response.getWriter();
		record=service.query(record.getMeetId());
		System.out.println(record);
		if(record==null){
			out.print(0);
		}
		else{
			out.print(1);
		}
		return null;
			
			
	}
	
}