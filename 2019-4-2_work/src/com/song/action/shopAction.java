package com.song.action;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.song.service.IProductService;
import com.song.service.Impl.IProductServiceimpl;

@WebServlet(value = "/shopAction")
public class shopAction extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private IProductService iProductService=null;
    public shopAction()
    {
        iProductService=new IProductServiceimpl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        if ("shopcar".equals(method))
        {
            HttpSession session=request.getSession(true);
            String user= (String) session.getAttribute("user");
            String id=request.getParameter("id");
            String number=request.getParameter("number");

            int a=iProductService.addcar(user,id,number);

        }
        if ("buy".equals(method))
        {
            String id=request.getParameter("id");
            String number=request.getParameter("number");
            System.out.println(id+"`````"+number);
        }
    }
}
