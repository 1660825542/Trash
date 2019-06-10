package com.rock.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rock.entities.Employee;
import com.rock.service.IEmployeeService;
import com.rock.serviceimpl.IEmployeeServiceImpl;

/**
 * Servlet implementation class EmployeeAction
 */
@WebServlet("/EmployeeAction")
public class EmployeeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IEmployeeService  iEmployeeService=null;
    public EmployeeAction() {
    	iEmployeeService=new IEmployeeServiceImpl();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String  method=request.getParameter("method");
		if("all".equals(method))
			this.all(request,response);
	}
	protected void all(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		①、检索所有的员工信息
		List<Employee> all=iEmployeeService.all();
//		②、将员工信息存于请求域
		request.setAttribute("emps",all);
//		③、以转发方式响应main.jsp
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}
	
	
	
	protected void all1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void all2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
