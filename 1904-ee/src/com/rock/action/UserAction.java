package com.rock.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rock.entities.User;
import com.rock.service.IUserService;
import com.rock.serviceimpl.IUserServiceImpl;

@WebServlet("/UserAction")
public class UserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private  IUserService iUserService=null;
	
    public UserAction() {
    	iUserService=new IUserServiceImpl();
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("login".equals(method))
			this.login(request,response);
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  userid=request.getParameter("userid");
		String  password=request.getParameter("password");
		User user=iUserService.login(userid, password);
		if(null==user){
//			非法用户
			request.setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else{
//			合法用户
			request.getRequestDispatcher("/EmployeeAction?method=all").forward(request, response);
			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
