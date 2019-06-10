package com.rock.action;

import com.rock.serivce.IUserService;
import com.rock.serivce.serviceimpl.IUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UpdatePwdAction")
public class UpdatePwdAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password=request.getParameter("password");
        String newpwd=request.getParameter("newpwd");

        HttpSession session=request.getSession(true);

        System.out.println("session.userid="+session.getAttribute("userid"));
        System.out.println("request.userid="+request.getAttribute("userid"));
        IUserService iUserService=new IUserServiceImpl();
        String userid=session.getAttribute("userid").toString();
        boolean flag=iUserService.updatePassword(userid,password,newpwd);
        if(flag){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        else{
            request.setAttribute("error","原始密码输入有误");
            request.getRequestDispatcher("/up_pwd.jsp").forward(request,response);
        }
    }
}
