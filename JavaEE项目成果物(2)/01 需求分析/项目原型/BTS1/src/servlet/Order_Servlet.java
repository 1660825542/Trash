package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import operation.Goods_Operation;
import operation.Order_Goods_Operation;
import operation.Order_Operation;
import operation.Shopping_Operation;
import operation.User_Operation;
import shili.Goods;
import shili.Order;
import shili.Order_Goods;
import shili.Shopping;
import shili.User;

import java.util.Iterator;
import java.util.List;

/**
 * Servlet implementation class Order_Servlet
 */
@WebServlet("/Order_Servlet")
public class Order_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Order_Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User_Operation uo = new User_Operation();
		Goods_Operation go = new Goods_Operation();
		Order_Goods_Operation ogo = new Order_Goods_Operation();
		Order_Operation oo = new Order_Operation();

		Long user_id = (Long) session.getAttribute("id");
		User user = uo.initUser(user_id);
		String orderNumber = oo.getOrdernumber();
		Order order = new Order();

		order.setOrderNumber(orderNumber);
		order.setUser(user);

		List Shopping = (List) session.getAttribute("result");
		Iterator it = Shopping.iterator();
		while (it.hasNext()) {
			Shopping sp = (Shopping) it.next();
			Goods goods = sp.getGoods();

			Order_Goods order_goods = new Order_Goods();
			order_goods.setGoods(goods);
			order_goods.setOrder(order);
			order_goods.setNumber(sp.getNumber());
			order.getOrder_Goods().add(order_goods);
			ogo.saveOrder_good(order_goods);
		}
		user.getOrders().add(order);
		oo.saveOrder(order);
		
		RequestDispatcher dis = request.getRequestDispatcher("js.jsp");
		dis.forward(request, response);

	}

}
