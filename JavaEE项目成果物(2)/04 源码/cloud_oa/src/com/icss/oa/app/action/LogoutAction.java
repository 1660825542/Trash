package com.icss.oa.app.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping(value = "appuser")
public class LogoutAction {

	@RequestMapping(value = "logout")
	public void getNotice(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		try {
			response.sendRedirect("/pages/indexLogin.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
