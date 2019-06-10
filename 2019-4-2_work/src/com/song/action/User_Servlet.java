package com.song.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.song.entities.SupUserBeen;
import com.song.entities.UserBeen;
import com.song.service.IUserService;
import com.song.service.Impl.IUserServiceImpl;


@WebServlet(value="/User_Servlet")
public class User_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    IUserService iuserservice = null;
    public User_Servlet() {
        super();
        // TODO Auto-generated constructor stub
        iuserservice = new IUserServiceImpl();
        
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("method").equals("login"))
			this.loginVal(request,response);
		if(request.getParameter("method").equals("register"))
			this.register(request,response);
		if(request.getParameter("method").equals("suplogin"))
			this.supLoginVal(request,response);
		if(request.getParameter("method").equals("proving"))
			this.userProving(request,response);
		if(request.getParameter("method").equals("repassword"))
			this.rePassword(request,response);
		
	}


	


	private void rePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String phonenum = request.getParameter("phonenum");
		String answer = request.getParameter("answer");
		UserBeen user = iuserservice.rePassword(phonenum,answer);
		if(user!=null){
			request.setAttribute("error", "您的密码为："+user.getPassword());
			request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
		}
		else{
			request.setAttribute("error", "用户名或密保答案有误");
			request.getRequestDispatcher("/JSP/repassword.jsp").forward(request, response);
		}
		
	}


	private void userProving(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String phonenum = request.getParameter("phonenum");
		UserBeen user = iuserservice.userProving(phonenum);
		
		if(user==null){
			
			response.getWriter().write("{\"info\":\"用户名未注册，可以继续注册\"}");
			
			
		}
		else{
			response.getWriter().write("{\"info\":\"用户名已经注册，请重新输入用户名\"}");			
		}
		
		
	}


	private void supLoginVal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rootId = request.getParameter("rootId");
		String password = request.getParameter("password");
		
		SupUserBeen supuser = iuserservice.supLoginVal(rootId,password);
		if(supuser!=null){
			request.getSession(true).setAttribute("rootId", "欢迎管理员【"+rootId+"】登录");
			request.getSession(true).setAttribute("supuser", rootId);
			request.getSession(true).setAttribute("suppassword", password);
			request.getRequestDispatcher("/META-INF/Administrator.jsp").forward(request, response);
		}
		else{
			request.setAttribute("error", "用户名或密码错误");
			request.getRequestDispatcher("/JSP/suplogin.jsp").forward(request, response);
		}
			
	}


	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String phonenum = request.getParameter("phonenum");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		
		UserBeen user = new UserBeen();
		user.setPhonenum(phonenum);
		user.setPassword(password);
		user.setName(name);
		user.setAddress(address);
		user.setQuestion(question);
		user.setAnswer(answer);
		
		int a = iuserservice.insertUser(user);
		if(a>0){
			request.setAttribute("error", "用户注册成功，欢迎登录");
			request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
		}
		
		
		
	}


	private void loginVal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String phonenum = request.getParameter("phonenum");
		String password = request.getParameter("password");
		
		UserBeen user = iuserservice.loginVal(phonenum,password);
		
		if(user!=null){
			request.getSession(true).setAttribute("user", phonenum);
			request.getSession(true).setAttribute("password", password);
			request.getRequestDispatcher("/SelectAllGood_Servlet").forward(request, response);
		
		}
		else{
			request.setAttribute("error", "用户名或密码错误");
			request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
		}
	}


	

}
