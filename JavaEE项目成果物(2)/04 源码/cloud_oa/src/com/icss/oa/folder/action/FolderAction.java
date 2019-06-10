package com.icss.oa.folder.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.icss.oa.common.BaseAction;
import com.icss.oa.folder.pojo.Files;
import com.icss.oa.folder.pojo.Folder;
import com.icss.oa.folder.service.FolderFileService;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.EmployeeService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/cloud")
public class FolderAction extends BaseAction implements ModelDriven<Folder> {

	Folder folder = new Folder();
	
	@Override
	public Folder getModel() {
		return folder;
	}

	@Autowired
	private FolderFileService service;
	
	@Autowired
	private EmployeeService empService;
	
	@Action("getFolderTree")
	public String getFolderTreeJson() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		List<Folder> list = service.allFolders(((Employee)request.getSession().getAttribute("queryemp")).getEmpId());
		Gson gson = new Gson();
		List<HashMap<String, Object>> jsonList = new ArrayList<HashMap<String,Object>>();
		for (Folder folder : list) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", folder.getFolderId());
			map.put("pId", folder.getFolderParent());		
			map.put("name", folder.getFolderName());
			map.put("isParent", true);
			map.put("open", true);
			map.put("drag", false);
			jsonList.add(map);
		}
		out.print(gson.toJson(jsonList));
		return null;
	}
	
	@Action("getShareFolderTree")
	public String getShareFolderTreeJson() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int empId;
		if(folder.getEmpId()==null){
			empId = ((Employee) request.getSession().getAttribute("queryemp")).getEmpId();
		}else{
			empId = folder.getEmpId();
		}
		List<Folder> list = service.getShareFolders(empId);
		Gson gson = new Gson();
		List<HashMap<String, Object>> jsonList = new ArrayList<HashMap<String,Object>>();
		for (Folder folder : list) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", folder.getFolderId());
			map.put("pId", folder.getFolderParent());		
			map.put("name", folder.getFolderName());
			map.put("isParent", true);
			map.put("open", true);
			map.put("drag", false);
			jsonList.add(map);
		}
		out.print(gson.toJson(jsonList));
		return null;
	}
	
	@Action(value = "folderDetail", results = { @Result(name = "success", location = "/pages/cloud/folderdetail.jsp", type = "dispatcher") })
	public String folderDetail() throws IOException{
		folder = service.selectFolderById(folder.getFolderId());
		List<Files> fileList = service.selectFolderFile(folder.getFolderId());
		List<Folder> folderList = service.selectChildFolderNotRecursion(folder.getFolderId());
		request.setAttribute("folder", folder);
		request.setAttribute("fileList", fileList);
		request.setAttribute("folderList", folderList);
		
		return "success";
	}
	
	@Action(value = "shareFolderDetail", results = { @Result(name = "success", location = "/pages/cloud/sharefolderdetail.jsp", type = "dispatcher") })
	public String shareFolderDetail() throws IOException{
		folder = service.selectFolderById(folder.getFolderId());
		List<Files> fileList = service.selectFolderFile(folder.getFolderId());
		List<Folder> folderList = service.selectShareChild(folder.getFolderId());
		request.setAttribute("folder", folder);
		request.setAttribute("fileList", fileList);
		request.setAttribute("folderList", folderList);
		
		return "success";
	}

	@Action(value = "update")
	public String update(){
		service.editFolder(folder);
		return null;
	}
	
	@Action(value = "insert")
	public String insert() throws IOException{
		folder.setCanDelete(1);
		folder.setFolderSize((long) 0);
		folder.setEmpId(((Employee)request.getSession().getAttribute("queryemp")).getEmpId());
		PrintWriter out = response.getWriter();
		out.print(service.addFolder(folder));
		return null;
	}
	
	@Action(value = "changeShare")
	public String changeShare(){
		if(folder.getFolderShare()==1)
			folder.setFolderShare(0);
		else
			folder.setFolderShare(1);
		service.setShare(folder);
		return null;
	}
	
	@Action(value = "delete")
	public String delete() throws IOException{
		
		PrintWriter out = response.getWriter();
		folder = service.selectFolderById(folder.getFolderId());
		if(folder.getCanDelete()!=0){
			out.print(service.deleteFolder(folder.getFolderId()));
		}
		return null;
	}
	
	@Action(value = "getLeftSize")
	public String getLeftSize() throws IOException{
		PrintWriter out = response.getWriter();
		Employee emp = (Employee) request.getSession().getAttribute("queryemp");
		folder = service.getEmpRootFolder(emp.getEmpId());
		out.print(folder.getFolderLeftSize());
		return null;
	}
	
	@Action(value = "getSize")
	public String getSize() throws IOException{
		PrintWriter out = response.getWriter();
		folder = service.getEmpRootFolder(folder.getEmpId());
		out.print(folder.getFolderSize());
		return null;
	}
	
	@Action(value = "setSize")
	public String setSize(){
		empService.setFolderSize(folder.getEmpId(), folder.getFolderSize()*1073741824);
		return null;
	}
	
}
