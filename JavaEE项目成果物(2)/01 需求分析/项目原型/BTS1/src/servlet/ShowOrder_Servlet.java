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

import operation.Order_Operation;

/**
 * Servlet implementation class ShowOrder_Setvlet
 */
@WebServlet("/ShowOrder_Servlet")
public class ShowOrder_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowOrder_Servlet() {
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
		HttpSession session = request.getSession();
		int counts=0;//订单总数量
		int now_page=Integer.parseInt(request.getParameter("now_page"));//获取当前页面
		request.setAttribute("now_page", now_page);//保存当前页面
		int first=(now_page-1)*10;
		int size=10;
		Long user_id = (Long) session.getAttribute("id");
		Order_Operation oo = new Order_Operation();
		List orders =oo.getOrder_ByUserId(first,size,user_id);		
		request.setAttribute("counts",oo.getOrder_Counts(user_id));//查询获取总数量
		counts=(Integer)request.getAttribute("counts");//赋值
		if(counts%10!=0) {
		request.setAttribute("allPage", ((counts/10)+1));//保存总页面
		}
		else {
			request.setAttribute("allPage", (counts/10));
		}
		request.setAttribute("orders", orders);	
		RequestDispatcher dis = request.getRequestDispatcher("ShowOrder.jsp");
		dis.forward(request, response);
		
	}

}
