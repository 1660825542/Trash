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


@WebServlet("/StockServlet2")
public class StockServlet2 extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCUtil jdbcUtil=new JDBCUtil();
        String id=request.getParameter("cartid");
        String sql="SELECT * FROM shopcart a WHERE  a.id='"+id+"';";
        Cart cart=jdbcUtil.queryById(Cart.class,sql);
        String sql1="update shopcart set num=num-1 where id='"+id+"';";
        jdbcUtil.runSql(sql1);
        response.getWriter().write(""+cart.getStock());
        
        String phonenum=request.getSession(true).getAttribute("user").toString();
        sql="select * from shopcart where phonenum = ?" ;
        List<Cart> cartList = jdbcUtil.queryAll(Cart.class,sql,phonenum);
        HttpSession session = request.getSession(true);
        
        double totalPrice = 0;
        for (Cart cart1 : cartList) {
            totalPrice += cart1.getPrice() * cart1.getNum();  //更新结算价格
        }
        if(cartList.size()>0){
        	session.setAttribute("cartList",cartList);
        	request.setAttribute("totalPrice", totalPrice);
        }
        
        response.getWriter().write(""+cart.getStock());
    }
}
