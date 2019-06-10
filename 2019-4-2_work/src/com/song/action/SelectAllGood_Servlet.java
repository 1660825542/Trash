package com.song.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.song.entities.AttrBeen;
import com.song.entities.GoodBeen;
import com.song.service.IGoodService;
import com.song.service.Impl.IGoodServiceImpl;


@WebServlet("/SelectAllGood_Servlet")
public class SelectAllGood_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IGoodService igoodservice = null;
    public SelectAllGood_Servlet() {
        super();
        // TODO Auto-generated constructor stub
        igoodservice = new IGoodServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Integer,List<GoodBeen>> all= igoodservice.selectAll();
		List<AttrBeen> alls=igoodservice.selectall();
		request.getSession(true).setAttribute("emps1",all);
		request.getSession(true).setAttribute("attrs",alls);
        request.getRequestDispatcher("/META-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
