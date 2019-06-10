package com.icss.oa.login.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.icss.oa.common.BaseAction;
import com.icss.oa.login.service.UserService;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.common.MyRealm;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/login")
public class LoginAction extends BaseAction implements ModelDriven<Employee> {

	private Employee emp = new Employee();
	
	@Autowired
	UserService service;
	
	@Autowired
	private MyRealm realm;

	
	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}
		
	/**
	 * 提供ajax接口，做异步用户名密码验证
	 * @return
	 * @throws IOException
	 */
	
	@Action(value = "login")
	public String login() throws IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String security = new Sha256Hash(emp.getPassword(), "icssoa", 10).toBase64();
		emp.setPassword(security);

		// 封装用户名和密码
		UsernamePasswordToken token = new UsernamePasswordToken(emp.getEmpNum(),security);
		
		// 设置RememberMe
		token.setRememberMe(false);
		
		/*通过shiro登录*/
		Subject currentUser = SecurityUtils.getSubject();
						
		try {
			currentUser.login(token);
		} catch (UnknownAccountException e) {
			out.write("nw");
			System.out.print("用户名错误");
			return null;
		} catch (IncorrectCredentialsException e) {
			out.write("pw");
			System.out.print("密码错误");
			return null;
		}
		
		//清理用户权限缓存
		realm.removeUserCache(emp.getEmpNum());
		
		//在session范围中存储用户数据
		request.getSession().setAttribute("empnum", emp.getEmpNum());
		
		Employee queryemp= service.empObj(emp.getEmpNum());
		
		request.getSession().setAttribute("queryemp", queryemp);
		
		//获得session的数据
		String username = (String) request.getSession().getAttribute("empnum");
		Employee empele = (Employee) request.getSession().getAttribute("queryemp");
		
		System.out.println(username);		
		String elename = empele.getEmpName();
		
		System.out.println(elename);
		
		out.write("y");
		
		return null;
	}

	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		return emp;
	}

}