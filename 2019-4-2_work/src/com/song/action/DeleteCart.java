package com.song.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rock.util.JDBCUtil;
import com.song.entities.Cart;

@WebServlet("/DeleteCart")
public class DeleteCart extends HttpServlet {
   
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String goodsId = request.getParameter("goodsId");

        List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");

        for (int i = 0; i < cartList.size(); i++) {

            if (cartList.get(i).getId().equals(goodsId)) {
                JDBCUtil jdbcUtil=new JDBCUtil();
                jdbcUtil.runSql("delete from shopcart where id="+"'"+goodsId+"'");
                cartList.remove(i);  //移除相应ID的花
            }
        }
        double totalPrice=0;
        for (Cart cart : cartList) {
            totalPrice += cart.getPrice() * cart.getNum();  //更新结算价格
        }
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("/JSP/cart.jsp").forward(request,response);
    }
}
