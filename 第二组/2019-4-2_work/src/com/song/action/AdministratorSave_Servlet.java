package com.song.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.song.entities.GoodBeen;
import com.song.service.IGoodService;
import com.song.service.Impl.IGoodServiceImpl;


@WebServlet("/AdministratorSave_Servlet")
public class AdministratorSave_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	IGoodService igoodservice = null;
	
    public AdministratorSave_Servlet() {
        super();
        igoodservice = new IGoodServiceImpl();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String number = request.getParameter("number");
    	String name = request.getParameter("name");
    	String price = request.getParameter("price");
    	int stock = Integer.parseInt(request.getParameter("stock"));
    	int attri = Integer.parseInt(request.getParameter("attri"));
    	GoodBeen good = new GoodBeen();
    	good.setNumber(number);
    	good.setName(name);
    	good.setPrice(price);
    	good.setStock(stock);
    	good.setAttri(attri);
    	int a = igoodservice.insertGood(good);
		
		if(a>0){
			request.getSession(true).setAttribute("number", number);
			request.setAttribute("succer", "商品其他信息上传成功，请上传图片");
			request.getRequestDispatcher("/JSP/Picture.jsp").forward(request, response);
		}
		
		
	}

}
