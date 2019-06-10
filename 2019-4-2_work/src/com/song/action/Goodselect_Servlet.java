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


@WebServlet("/JSP/Goodselect_Servlet")
public class Goodselect_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IGoodService igoodservice = null;
    public Goodselect_Servlet() {
        super();
        // TODO Auto-generated constructor stub
        igoodservice = new IGoodServiceImpl();
    }



	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// TODO Auto-generated method stub
    	String number = request.getParameter("number");
		GoodBeen good = igoodservice.selectGood(number);
		
		if(good==null){
			
			response.getWriter().write("{\"info\":\"商品编号不存在，请认真考虑后再操作\"}");
			
			
		}
		else{
			response.getWriter().write("{\"info\":\"商品编号以存在，请认真考虑后再操作\"}");			
		}

		
		
	}

}
