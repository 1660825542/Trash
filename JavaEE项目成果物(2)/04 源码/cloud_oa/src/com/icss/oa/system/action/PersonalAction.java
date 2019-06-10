package com.icss.oa.system.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.icss.oa.common.BaseAction;
import com.icss.oa.common.MyRealm;
import com.icss.oa.common.Pager;
import com.icss.oa.login.service.UserService;
import com.icss.oa.system.index.EmpIndexResultBean;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.DepartmentService;
import com.icss.oa.system.service.EmployeeService;
import com.icss.oa.system.service.PersonalService;
import com.icss.oa.system.service.PositionService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/empInfo")
public class PersonalAction extends BaseAction implements ModelDriven<Employee> {

	private Employee emp = new Employee();
	
	private int pageNum=1;// 页码
	
	private String newPwd;
	
	private String queryStr;
	
	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	@Autowired
	private UserService userService;
	
	@Autowired
	private MyRealm realm;

	@Autowired
	private PersonalService service;
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	private PositionService posService;

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	@Override
	public Employee getModel() {
		return emp;
	}
	
	@Action(value = "query" , results = { @Result(name = "success", location = "/pages/EmpInfo/PersonalInfo.jsp", type = "dispatcher")})
	public String query() throws IOException {
		Employee emp = new Employee();
		emp = service.queryById(((Employee)request.getSession().getAttribute("queryemp")).getEmpId());
		String departmentName = deptService.queryById(emp.getDepartmentId()).getDeptName();
		String positionName = posService.queryById(emp.getPositionId()).getPosName();
		String gender;
		if(emp.getGender()==0){
			gender = "女";
		}else {
			gender = "男";
		}
		request.setAttribute("emp", emp);
		request.setAttribute("gender", gender);
		request.setAttribute("department", departmentName);
		request.setAttribute("position", positionName);
		return "success";
	}
	
	@Action(value = "changeInfo" , results = { @Result(name = "success", location = "/pages/EmpInfo/changeInfo.jsp", type = "dispatcher")})
	public String changeInfo() throws IOException {
		Employee emp = new Employee();
		emp = service.queryById(((Employee)request.getSession().getAttribute("queryemp")).getEmpId());
		request.setAttribute("emp", emp);
		return "success";
	}
	
	@Action(value = "edit" , results = { @Result(name = "success", location = "/empInfo/query.action", type = "redirect")})
	public String edit() throws IOException {
		Employee employee = new Employee(emp.getEmpId(), emp.getEmpName(), emp.getEmpNum(),
				emp.getPassword(), emp.getGender(), emp.getBirthday(), emp.getDepartmentId(),
				emp.getPositionId(), emp.getPhone(), emp.getEmail(), emp.getQq(),
				emp.getIntroduction());
		System.out.println(employee);
		service.update(employee);
		return "success";
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
		
		Gson gson = new Gson();
		HashMap<String, Object> map = new HashMap<String,Object>();
						
		try {
			currentUser.login(token);
		} catch (UnknownAccountException e) {
			map.put("info", "用户名不存在");
			map.put("status", "n");
			out.println(gson.toJson(map));
			return null;
		} catch (IncorrectCredentialsException e) {
			map.put("info", "密码错误");
			map.put("status", "n");
			out.println(gson.toJson(map));
			return null;
		}
		
		//清理用户权限缓存
		realm.removeUserCache(emp.getEmpNum());
		
		//在session范围中存储用户数据
		request.getSession().setAttribute("empnum", emp.getEmpNum());
		
		Employee queryemp= userService.empObj(emp.getEmpNum());
		
		request.getSession().setAttribute("queryemp", queryemp);
		
		//获得session的数据
		String username = (String) request.getSession().getAttribute("empnum");
		Employee empele = (Employee) request.getSession().getAttribute("queryemp");
		
		System.out.println(username);		
		String elename = empele.getEmpName();
		
		System.out.println(elename);
		map.put("empId",empele.getEmpId());
		map.put("status", "y");
		out.println(gson.toJson(map));
		
		return null;
	}
	
	@Action(value = "savePwd", results = { @Result(name = "success", location = "/pages/EmpInfo/PersonalInfo.jsp", type = "dispatcher")})
	public String savePwd() throws IOException {
		String password = new Sha256Hash(newPwd, "icssoa", 10).toBase64();
		service.changePwd(((Employee)request.getSession().getAttribute("queryemp")).getEmpId(), password);
		Employee emp = new Employee();
		emp = service.queryById(((Employee)request.getSession().getAttribute("queryemp")).getEmpId());
		String departmentName = deptService.queryById(emp.getDepartmentId()).getDeptName();
		String positionName = posService.queryById(emp.getPositionId()).getPosName();
		String gender;
		if(emp.getGender()==0){
			gender = "女";
		}else {
			gender = "男";
		}
		request.setAttribute("emp", emp);
		request.setAttribute("gender", gender);
		request.setAttribute("department", departmentName);
		request.setAttribute("position", positionName);
		return "success";
	}
	
	@Action(value = "queryAll", results = { @Result(name = "success", location = "/pages/EmpInfo/lucene.jsp", type = "dispatcher")})
	public String queryAll() throws IOException {
		Pager pager = new Pager(empService.count(),pageNum);		
		List<Map<String, Object>> list = empService.queryEmployee(pager);
		request.setAttribute("empList", list);
		request.setAttribute("pager", pager);
		return "success";
	}
	
	@Action(value = "view", results = { @Result(name = "success", location = "/pages/EmpInfo/view.jsp", type = "dispatcher")})
	public String view() throws IOException {		
		Employee employee = empService.queryById(emp.getEmpId());
		String departmentName = deptService.queryById(employee.getDepartmentId()).getDeptName();
		String positionName = posService.queryById(employee.getPositionId()).getPosName();
		request.setAttribute("emp", employee);
		request.setAttribute("department", departmentName);
		request.setAttribute("position", positionName);
		return "success";
	}
	
	/**
	 * 全文检索员工
	 * 
	 * @return
	 * @throws Exception 
	 * @throws IOException
	 */
	@Action(value = "queryByIndex", results = { @Result(name = "success", location = "/pages/EmpInfo/queryByIndex.jsp", type = "dispatcher") })
	public String queryByIndex() throws Exception {

		Pager pager = new Pager(pageNum);
		EmpIndexResultBean queryResult = empService.queryByIndex(queryStr, pager);
		for(Map<String, Object> temp : queryResult.getList()) {
			temp.put("phone", empService.queryById((Integer)temp.get("empId")).getPhone());
			temp.put("posName", posService.queryById(empService.queryById((Integer)temp.get("empId")).getPositionId()).getPosName());
		}
		request.setAttribute("empList", queryResult);
		request.setAttribute("queryStr",queryStr);
		request.setAttribute("pager", pager);
		return "success";
	}
}
