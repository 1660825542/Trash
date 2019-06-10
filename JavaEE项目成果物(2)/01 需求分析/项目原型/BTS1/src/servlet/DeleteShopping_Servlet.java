package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import shili.Shopping;
import shili.User;
import operation.Shopping_Operation;
import operation.User_Operation;

/**
 * Servlet implementation class DeleteShopping_Servlet
 */
@WebServlet("/DeleteShopping_Servlet")
public class DeleteShopping_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteShopping_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		Long id=Long.parseLong(request.getParameter("id"));
		Shopping_Operation so=new Shopping_Operation();
		User_Operation uo=new User_Operation();
		Shopping shopping=so.findById(id);
		User user=uo.findById(shopping.getUser().getId());
		user.getShopping().remove(shopping);//获取购物车对象的信息移除
		shopping.setGoods(null);
		shopping.setUser(null);
			so.deleteShopping(shopping);
		RequestDispatcher dis = request.getRequestDispatcher("ShowShopping_Servlet");
		dis.forward(request, response);
	}

}
