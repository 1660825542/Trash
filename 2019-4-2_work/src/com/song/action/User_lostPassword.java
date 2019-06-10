package com.song.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.song.entities.UserBeen;
import com.song.service.IUserService;
import com.song.service.Impl.IUserServiceImpl;


@WebServlet("/JSP/User_lostPassword")
public class User_lostPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IUserService iuserservice = null;
    public User_lostPassword() {
        super();
        // TODO Auto-generated constructor stub
        iuserservice = new IUserServiceImpl();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("method").equals("repassword"))
			this.lostPassword(request,response);
		
	}

	private void lostPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String phonenum = request.getParameter("phonenum");
		UserBeen user = iuserservice.lostPassword(phonenum);
		if(user==null){
			response.getWriter().write("{\"info\":\"用户名不存在，请仔细核实\"}");
		}
		else
			response.getWriter().write("{\"info\":"+"\"您的密保问题为："+user.getQuestion()+"\""+"}");	
	}

}
