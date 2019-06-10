package com.song.action;



import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.song.entities.Product;
import com.song.entities.Remark;
import com.song.service.IProductService;
import com.song.service.Impl.IProductServiceimpl;

@WebServlet(value = "/ProductInfoAction")
public class ProductInfoAction extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private IProductService iProductService=null;
    public ProductInfoAction()
    {
        iProductService=new IProductServiceimpl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private  double percent(List<Remark> r)
    {
        double x=0;
        for (int i=0;i<r.size();i++)
        {
            x=x+Integer.parseInt(r.get(i).getRank())*20;
        }
        x=x/r.size()*10;
        int a=(int)x;
        x=(double)a/10;
        return x;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String method=request.getParameter("method");

        if ("collect".equals(method))
        {
            System.out.println("收藏成功"+request.getParameter("productid"));
            request.getRequestDispatcher("/JSP/shopping.jsp").forward(request,response);
        }
        String n=request.getParameter("Product_id");
        Product product1=iProductService.findp(n);
        List<Remark> allremark=iProductService.allremark();
        HttpSession session=request.getSession(true);
        double x=percent(allremark);
        int x1=(int)x/20;
        session.setAttribute("percent",x);
        session.setAttribute("percent1",x1);
        session.setAttribute("product",product1);
        session.setAttribute("remarks",allremark);


        request.getRequestDispatcher("/JSP/shopping.jsp").forward(request,response);
    }
}
