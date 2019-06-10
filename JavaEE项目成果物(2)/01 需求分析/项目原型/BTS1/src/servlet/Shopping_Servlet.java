package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import operation.Goods_Operation;
import operation.Shopping_Operation;
import operation.User_Operation;
import shili.Goods;
import shili.Shopping;
import shili.User;

/**
 * Servlet implementation class Shopping_Servlet
 */
@WebServlet("/Shopping_Servlet")
public class Shopping_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shopping_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Long id=(Long)session.getAttribute("id");
		Long goods_id=Long.parseLong(request.getParameter("goods_id"));
		int goods_counts=Integer.parseInt(request.getParameter("counts"));
		Goods_Operation go=new Goods_Operation();
		Shopping_Operation sp=new Shopping_Operation();
		User_Operation uo=new User_Operation();
		User user=uo.findById(id);
		Goods good=go.findById(goods_id); 
		Shopping shopping=sp.find(goods_id, id);
		if(shopping!=null)
        {
			
			int num=shopping.getNumber()+goods_counts;
	        shopping.setGoods(good);
	        shopping.setUser(user);
	        shopping.setNumber(num);
	       /* good.getShopping().add(shopping);*/
	        user.getShopping().add(shopping);
	        sp.Save(shopping);
        }
		else
		{
		Shopping shoppings=new Shopping();  
        shoppings.setGoods(good);
        shoppings.setUser(user);
        shoppings.setNumber(goods_counts);
        /*good.getShopping().add(shoppings);*/
        user.getShopping().add(shoppings);
        sp.Save(shoppings);
		}
        RequestDispatcher dis = request.getRequestDispatcher("ShowShopping_Servlet");
		dis.forward(request, response);
	}

}
