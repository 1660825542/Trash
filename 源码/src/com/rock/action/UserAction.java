package com.rock.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

import com.rock.HDFS.HDFSTest;
import com.rock.master.WordCountMaster;
import com.rock.master.WordCountMaster1;
import com.rock.master.WordCountMaster2;



@WebServlet(value="/User_Servlet")
public class UserAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	WordCountMaster wordCountMaster = null;
	WordCountMaster1 wordCountMaster1 = null;
	WordCountMaster2 wordCountMaster2 = null;
	public UserAction() {
        super();
        // TODO Auto-generated constructor stub
        wordCountMaster = new WordCountMaster();
        wordCountMaster1 = new WordCountMaster1();
        wordCountMaster2 = new WordCountMaster2();
        
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("method").equals("login1"))
			try {
				wordCountMaster.test();
				wordCountMaster.test1();
				this.login1Test(request,response);
			} catch (ClassNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(request.getParameter("method").equals("login2"))
			try {
				wordCountMaster1.test();
				wordCountMaster1.test1();
				this.login2Test(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(request.getParameter("method").equals("login3"))
			try {
				wordCountMaster2.test();
				this.login3Test(request,response);
			} catch (ClassNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	private void login3Test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		Map<String,String> map2=HDFSTest.read("/output/sal/part-r-00000");
		JSONObject json =new JSONObject(map2);
		request.setAttribute("res", json.toString());
		request.getRequestDispatcher("/META-INF/success3.jsp").forward(request, response);
		
	}

	private void login2Test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map<String,String> map1=HDFSTest.read("/output/outdata2/part-r-00000");
		JSONObject json =new JSONObject(map1);
		request.setAttribute("res", json.toString());
		request.getRequestDispatcher("/META-INF/success2.jsp").forward(request, response);
	}

	private void login1Test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
		
		Map<String,String> map = HDFSTest.read("/output/test1/part-r-00000");
		JSONObject json =new JSONObject(map);
		request.setAttribute("res", json.toString());
		request.getRequestDispatcher("/META-INF/success1.jsp").forward(request, response);
	}
}
