package com.song.action;

import java.io.File;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.RandomStringUtils;


import com.song.service.IGoodService;
import com.song.service.Impl.IGoodServiceImpl;


@WebServlet("/PictureSave_Servlet")
public class PictureSave_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	IGoodService igoodservice = null;
    public PictureSave_Servlet() {
        super();
        // TODO Auto-generated constructor stub
        igoodservice = new IGoodServiceImpl();
    }
  
	protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
	    // 为解析类提供配置信息
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    // 创建解析类的实例
	    ServletFileUpload fileupload = new ServletFileUpload(factory);
	    // 开始解析,解析实例空间大小的设置
	    fileupload.setFileSizeMax(1024 * 1024 * 40);
	    // 每个表单域中数据会封装到一个对应的FileItem对象上
	    
	    try {
	    		
	    	List<FileItem> items = fileupload.parseRequest(request);
	    	// 区分表单域
	    	for (int i = 0; i < items.size(); i++) {
	    		FileItem item = items.get(i);
	    		// isFormField为true，表示这不是文件上传表单域
	    		if (!item.isFormField()) {
	    			
	    			String fileName = item.getName();
	    			
	    			// 该方法在某些平台(操作系统),会返回路径+文件名
	    			fileName = fileName.substring(fileName.lastIndexOf("."));
	    			
	    			fileName=RandomStringUtils.randomAlphanumeric(10)+fileName;
	    			String picture="/imgaa/"+fileName;
	    			File file = new File("D:/temp/"+ fileName);
	    			if (!file.exists()) {
	    				item.write(file);
	    				// 将上传图片的名字记录到数据库中
	    			}
	    			
	    			
	    			
	    			String number = request.getSession().getAttribute("number").toString();
	    			int a = igoodservice.updateGood(number,picture);
	    			if(a>0){
	    				request.setAttribute("succer", "图片已被上传到数据库,商品信息添加成功");
	    				request.getRequestDispatcher("/META-INF/Administrator.jsp").forward(request, response);
	    			}
		    	}
	    	}
	    	
	    
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	System.out.println("文件上传出现问题");
	    }	
	    
	}

}
