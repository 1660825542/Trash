package com.song.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.song.entities.Cart;

@WebServlet("/TotalPriceServlet")
public class TotalPriceServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
        double totalPrice = 0;
        if (cartList == null) {
            request.setAttribute("message","您还没有选择商品哦，不需要结算");
        } else {
            for (Cart cart : cartList) {
                totalPrice += cart.getPrice() * cart.getNum();  //更新结算价格
            }
//            JDBCUtil jdbcUtil=new JDBCUtil();
//            String sql="delete * from shopcart";
//            jdbcUtil.runSql(sql);
        }
        request.getSession(true).setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("/JSP/Success.jsp").forward(request, response);
    }
}
