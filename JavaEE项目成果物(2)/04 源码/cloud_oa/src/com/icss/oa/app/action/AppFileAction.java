package com.icss.oa.app.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.icss.oa.app.pojo.ReturnValue;
import com.icss.oa.folder.pojo.Files;
import com.icss.oa.folder.service.FolderFileService;

@Controller
@Scope("prototype")
@RequestMapping(value = "appuser")
public class AppFileAction {
	private PrintWriter writer = null;
	@Autowired
	private FolderFileService folderFileService;

	@RequestMapping(value = "getFiles")
	public void getFiles(HttpServletRequest request,
			HttpServletResponse response, String empId) {
		response.setContentType("text/html;charset=utf-8");
		Gson gson = new Gson();
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			writer.print(gson.toJson(new ReturnValue(0, "服务器错误", null)));
			writer.close();
			return;
		}
		writer.print(gson.toJson(new ReturnValue(1, "请求成功", folderFileService
				.getEmpFiles(Integer.parseInt(empId)))));
		writer.close();

	}

	@RequestMapping(value = "download")
	public void download(HttpServletRequest request,
			HttpServletResponse response, String fileId)
			throws UnsupportedEncodingException {
		Files file = folderFileService.selectFile(Integer.parseInt(fileId
				.trim()));
		String type = "";
		String filename = URLEncoder.encode(file.getFileName(), "UTF-8");// 显示的文件名
		response.setHeader("content-disposition", "attachment;filename="
				+ filename);

		switch (file.getFileName().substring(
				file.getFileName().lastIndexOf(".") + 1)) {
		case "png":
			type="image/png";
			break;
		case "gif":
			type="image/gif";
			break;
		case "ico":
			type="image/x­icon";
			break;
		case "jpg":
		case "jpeg":
			type="image/jpeg";
			break;
		case "bmp":
			type="application/x­bmp";
			break;
		case "wav":
			type="audio/wav";
			break;
		case "mp3":
		case "ape":
		case "m4a":
		case "aac":
		case "flac":
		case "amr":
			type="audio/mp3";
			break;
		case "doc":
			type="application/msword";
			break;
		case "ppt":
			type="application/vnd.ms­powerpoint";
			break;
		case "xls":
			type="application/vnd.ms­excel";
			break;
		case "pdf":
			type="application/pdf";
			break;
		case "txt":
			type="text/plain";
			break;
		case "htm":
		case "html":
			type="text/html";
			break;
		case "avi":
			type="video/avi";
			break;
		case "mkv":
		case "mp4":
		case "mov":
			type="video/mpeg4";
			break;
		case "wmv":
			type="video/x­ms­wmv";
			break;
		case "mpeg":
			type="video/mpg";
			break;
		default:
			break;
		}
		// 告诉客户端文件类型
		if (type.equals(""))
			response.setContentType("application/octet­stream;charset=utf-8");
		else
			response.setContentType(type + ";charset=utf-8");
		// 告诉服务器响应的编码格式为utf-8
		response.setCharacterEncoding("UTF_8");
		ServletOutputStream stream = null;
		try {
			stream = response.getOutputStream();
			stream.write(file.getFileContent());
			stream.flush();
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
