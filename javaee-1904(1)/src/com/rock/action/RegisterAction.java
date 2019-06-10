package com.rock.action;

import com.rock.entities.Userinfo;
import com.rock.serivce.IUserService;
import com.rock.serivce.serviceimpl.IUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterAction")
public class RegisterAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid=request.getParameter("userid");
        String password=request.getParameter("password");
        String sex=request.getParameter("sex");
        //注意：复选框的值得获取不能使用.getPrameter();
        //String hobby=request.getParameter("hobby");
        String [] hobbys=request.getParameterValues("hobby");
        String hobby="";
        for(String s:hobbys){
            hobby=hobby+s+"||";
        }
        String qid=request.getParameter("qid");
        String answer=request.getParameter("answer");
        String degree=request.getParameter("degree");
        String remark=request.getParameter("remark");

        Userinfo userinfo=new Userinfo(userid,password,sex,hobby,qid,answer,degree,remark);
        IUserService iUserService=new IUserServiceImpl();
        boolean flag=iUserService.register(userinfo);
        if(flag){
            //注册成功
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        else{
            request.setAttribute("error","注册失败，请重新注册");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }
}
