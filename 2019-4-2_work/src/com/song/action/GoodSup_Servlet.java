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


@WebServlet("/GoodSup_Servlet")
public class GoodSup_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IGoodService igoodservice = null;
    public GoodSup_Servlet() {
        super();
        // TODO Auto-generated constructor stub
        igoodservice = new IGoodServiceImpl();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("method").equals("delete"))
			this.deleteGood(request,response);
		if(request.getParameter("method").equals("update"))
			this.updateGood(request,response);
		if(request.getParameter("method").equals("selectAll"))
			this.selectAllGood(request,response);
	}


	private void selectAllGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<GoodBeen> all = igoodservice.selectAllGood();
		if(all.size()>=0){
			request.setAttribute("good", all);
			
			request.getRequestDispatcher("/JSP/AdministratorResult.jsp").forward(request, response);
		}
		
	}

	private void updateGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String goodid = request.getParameter("number");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		int stock = Integer.parseInt(request.getParameter("stock"));
		int attri = Integer.parseInt(request.getParameter("attri"));
		GoodBeen good = new GoodBeen();
		good.setGoodid(goodid);
		good.setName(name);
		good.setPrice(price);
		good.setStock(stock);
		good.setAttri(attri);
		int a = igoodservice.updateGood(good);
		if(a>0){
			request.setAttribute("succer", "编号为："+goodid+"的商品修改成功请继续");
			request.getRequestDispatcher("/META-INF/Administrator.jsp").forward(request, response);
		}
		
	}

	private void deleteGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
    	
    	String number = request.getParameter("number");
    	int a = igoodservice.deleteGood(number);
    	if(a>0){
    		request.setAttribute("succer", "编号为："+number+"的商品删除成功请继续");
			request.getRequestDispatcher("/META-INF/Administrator.jsp").forward(request, response);
    	}
    	else{
    		request.setAttribute("error", "商品不存在就想着删除");
    		request.getRequestDispatcher("/JSP/AdministratorDelete.jsp").forward(request, response);
    	}
    		
	}

}
