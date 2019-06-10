package com.song.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.song.entities.GoodBeen;
import com.song.service.IGoodService;
import com.song.service.Impl.IGoodServiceImpl;

@WebServlet(value = "/LikeAction")
public class LikeAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	IGoodService igoodservice = null;
	
    public  LikeAction(){
    	igoodservice = new IGoodServiceImpl();

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	
    	String name=request.getParameter("name");
        List<GoodBeen> all=igoodservice.selectlike(name);

        request.setAttribute("good", all);
        request.getRequestDispatcher("/JSP/index1.jsp").forward(request, response);
    }
}