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

import operation.Order_Goods_Operation;

/**
 * Servlet implementation class ShowOrder_Goods_Servlet
 */
@WebServlet("/ShowOrder_Goods_Servlet")
public class ShowOrder_Goods_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowOrder_Goods_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Long order_id=Long.parseLong(request.getParameter("order_id"));
		Order_Goods_Operation ogo=new Order_Goods_Operation();
		List order_goods=ogo.getOrder_Good_ByOrderId(order_id);
		request.setAttribute("order_goods", order_goods);
		RequestDispatcher dis = request.getRequestDispatcher("ShowOrder_Goods.jsp");
		dis.forward(request, response);
	}

}
