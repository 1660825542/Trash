package com.icss.oa.folder.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;

import com.icss.oa.common.BaseAction;
import com.icss.oa.folder.pojo.Files;
import com.icss.oa.folder.service.FolderFileService;
import com.icss.oa.folder.upload.ResourceFileUploadStatus;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/file")
public class FileAction extends BaseAction implements ModelDriven<Files> {

	private Files file = new Files(); // 对应的pojo

	private File data; // 页面的file对象

	private String dataFileName;// 文件名称

	private String dataContentType;// 文件MIME类型

	@Autowired
	private FolderFileService service;

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

	@Override
	public Files getModel() {
		return file;
	}

	@Action(value = "upload")
	public String upload() {
		try {
			file.setFileName(dataFileName);
			
			byte[] fileBytes;

			fileBytes = FileCopyUtils.copyToByteArray(data);

			file.setFileContent(fileBytes);
			file.setFileSize((int) data.length());
			service.addFile(file);
			request.getSession().setAttribute("uploadOk", "ok");
			Thread.sleep(2000);
			request.getSession().removeAttribute("currentUploadStatus");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Action("getOK")
	public String getOK() throws IOException{
		PrintWriter out = response.getWriter();
		String ok = (String) request.getSession().getAttribute("uploadOk");
		if(ok!=null){
			out.print(ok);
			request.getSession().removeAttribute("uploadOk");
		} else{
			out.print("uploading");
		}
		return null;
	}
	

	@Action("download")
	public String download() {
		try {
			file = service.selectFile(file.getFileId());
			String filename = URLEncoder.encode(file.getFileName(), "UTF-8");// 显示的文件名

			response.setHeader("content-disposition", "attachment;filename="
					+ filename);

			OutputStream out;

			out = response.getOutputStream();

			FileCopyUtils.copy(file.getFileContent(), out);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Action(value = "queryByFolder", results = { @Result(name = "success", location = "/pages/cloud/files.jsp", type = "dispatcher") })
	public String query() {

		List<Files> list = service.selectFolderFile(file.getFileFolder());
		request.setAttribute("list", list);
		return "success";

	}

	@Action("deleteFile")
	public String delete() {
		service.deleteFile(file.getFileId());
		return null;
	}

	@Action("updateFile")
	public String update() {
		service.updateFile(file);
		return null;
	}

	@Action("getProgress")
	public String getProgress() throws IOException {
		response.setHeader("Cache-Control", "no-store"); // 禁止浏览器缓存
		response.setHeader("Pragrma", "no-cache"); // 禁止浏览器缓存
		response.setDateHeader("Expires", 0); // 禁止浏览器缓存

		HttpSession session = request.getSession();
		ResourceFileUploadStatus status = (ResourceFileUploadStatus) session
				.getAttribute("currentUploadStatus");

		// 已经完成的百分比
		int percent = (int) (100 * (double) status.getReadedBytes() / (double) status
				.getTotalBytes());
		PrintWriter out = response.getWriter();
		
		if(status.getReadedBytes()==status.getTotalBytes()){
			out.print("100");
		}else{
			out.print(String.valueOf(percent));
		}
		return null;
	}
}
