package com.song.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.song.entities.QuestionBeen;
import com.song.service.IQuestionService;
import com.song.service.Impl.IQuestionServiceImpl;


@WebServlet("/Question_Servlet")
public class Question_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    IQuestionService iquestion = null;
    public Question_Servlet() {
        super();
        // TODO Auto-generated constructor stub
        iquestion = new IQuestionServiceImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<QuestionBeen> all = iquestion.selectQuestion();
		request.setAttribute("ques",all);
		request.getRequestDispatcher("/JSP/register.jsp").forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
