package com.rock.action;

import com.rock.entities.User;
import com.rock.serivce.IUserService;
import com.rock.serivce.serviceimpl.IUserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/userLogin")
public class UserLogin extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String userid=request.getParameter("userid");
        String password=request.getParameter("password");

        User user=new User();
        user.setUserid(userid);
        user.setPassword(password);


        IUserService iUserService=new IUserServiceImpl();
        User u=iUserService.login(user);
        if(u==null){
            //非法
            request.setAttribute("error","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        else{
            //合法
            HttpSession session=request.getSession(true);
            session.setAttribute("userid",u.getUserid());
            request.getRequestDispatcher("/main.jsp").forward(request,response);
        }
    }
}
