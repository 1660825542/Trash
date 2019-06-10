package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shili.Goods;
import operation.Goods_Operation;

/**
 * Servlet implementation class Administrator_Servlet
 */
@WebServlet("/AdministratorSave_Servlet")
public class AdministratorSave_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministratorSave_Servlet() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String goods_name = request.getParameter("goods_name");
		double goods_price = Double.parseDouble(request.getParameter("goods_price"));
		String goods_number = request.getParameter("goods_number");
		String goods_image = request.getParameter("goods_image");
		Goods_Operation go = new Goods_Operation();
		Goods goods = new Goods();
		goods.setName(goods_name);
		goods.setPrice(goods_price);
		goods.setGoods_number(goods_number);
		goods.setImage(goods_image);
		session.setAttribute("goods_name", goods_name);
		session.setAttribute("goods_price", goods_price);
		session.setAttribute("goods_number", goods_number);
		session.setAttribute("goods_image", goods_image);
		go.save(goods);
		RequestDispatcher dis = request.getRequestDispatcher("AdministratorSave.jsp");
		dis.forward(request, response);
	}

}
