package com.icss.oa.app.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.icss.oa.app.pojo.LoginReturnValue;
import com.icss.oa.app.pojo.ReturnValue;
import com.icss.oa.app.service.EmployeeService;
import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.system.pojo.Employee;

@Controller
@Scope("prototype")
@RequestMapping(value = "appuser")
public class EmployeeAppAction {
	@Autowired
	EmployeeService employeeServiceImpl;
	@Autowired
	private EmployeeDao employeeDao;
	private PrintWriter writer;

	private final String SESSION_SIGN = "sign";
	private final String SESSION_EMP_NUM = "empNum";
	private final String SESSION_PASSWORD = "password";

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public void appLogin(HttpServletRequest request,
			HttpServletResponse response, String empNum, String password)
			throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()) + " - empNum:" + empNum
				+ ",password:" + password + " ��app��¼ϵͳ...");
		response.setContentType("text/html;charset=utf-8");
		/**
		 * printWriter:����һ�����������Ƕ��� ��outputStream���������Ƕ����ƣ����ϴ��ļ�ʱ��һ��Ҫʹ�ôˡ�
		 */
		response.setCharacterEncoding("UTF_8");// ����Response�ı��뷽ʽΪUTF-8
		/**
		 * �����������һ����Ӧͷ������������Ľ��뷽ʽΪUTF-8,��ʵ�����˱��䣬 ҲĬ��������Response�ı��뷽ʽΪUTF-8��
		 * ���ǿ������������������ʹ��
		 */
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		writer = response.getWriter();
		if (empNum == null || password == null) {
			writer.print(new Gson().toJson(new LoginReturnValue(
					LoginReturnValue.LOGIN_WRONG, "δ֪����")));
		} else {
			String help = new Gson().toJson(employeeServiceImpl.login(empNum,
					new Sha256Hash(password, "icssoa", 10).toBase64()
							.toString().trim()));
			writer.print(help);
			System.out.println(help);
		}
		writer.flush();
		writer.close();
	}

	@RequestMapping(value = "updateInfo", method = RequestMethod.POST)
	public void updateInfo(HttpServletRequest request,
			HttpServletResponse response, String empNum, String email,
			String phone, String introduction, String qq) throws IOException {
		Employee emp = employeeDao.empNumIsExist(empNum);
		emp.setEmail(email);
		emp.setPhone(phone);
		emp.setQq(qq);
		emp.setIntroduction(introduction);

		response.setContentType("text/html;charset=utf-8");
		Gson gson = new Gson();
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			writer.print(gson.toJson(new ReturnValue(0, "����������", null)));
			writer.flush();
			writer.close();
			return;
		}
		writer.print(gson.toJson(employeeServiceImpl.updateInfo(emp)));
		writer.flush();
		writer.close();

	}

	@RequestMapping(value = "getQRcode", method = RequestMethod.GET)
	public void getQRcode(HttpServletRequest request,
			HttpServletResponse response, String uuid) throws IOException {
		response.setContentType("application/x-png;charset=utf-8");
		response.setCharacterEncoding("UTF_8");
		ServletOutputStream stream = null;
		try {
			stream = response.getOutputStream();
			Gson gson = new Gson();
			QRCodeWriter codeWriter = new QRCodeWriter();
			Map<String, String> args = new HashMap<String, String>(2);
			args.put("uuid", uuid);
			args.put("JSESSIONID", request.getSession().getId());
			System.out.println("JSESSIONID=" + request.getSession().getId());
			System.out.println(gson.toJson(args));
			BitMatrix m = codeWriter.encode(gson.toJson(args),
					BarcodeFormat.QR_CODE, 500, 500);
			MatrixToImageWriter.writeToStream(m, "PNG", stream);
		} catch (IOException | WriterException e) {
			e.printStackTrace();
		} finally {
			stream.flush();
			stream.close();
		}

	}

	@RequestMapping(value = "getUUID", method = RequestMethod.GET)
	public void getUUID(HttpServletRequest request, HttpServletResponse response) {
		String uuid = createUUID();
		System.out.println(uuid);
		HttpSession session = request.getSession();
		Map<String, String> args = new HashMap<String, String>(3);
		args.put(SESSION_SIGN, "false");
		args.put(SESSION_EMP_NUM, null);
		args.put(SESSION_PASSWORD, null);
		session.setAttribute(uuid, args);
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.print(uuid);
		writer.flush();
		writer.close();
	}

	public String createUUID() {
		String s = UUID.randomUUID().toString();
		// ȥ����-������
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	} 

	@RequestMapping(value = "checkUUID", method = RequestMethod.POST)
	public void checkUUID(HttpServletRequest request,
			HttpServletResponse response, String uuid) {
		System.out.println("j");
		HttpSession session = request.getSession();
		Map<String, String> info = (Map<String, String>) session
				.getAttribute(uuid);
		Gson gson = new Gson();
		response.setContentType("text/html;charset=utf-8");
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			writer.print(gson.toJson(new ReturnValue(0, "����������", null)));
			e.printStackTrace();
		} 
		if (info == null || !info.get(SESSION_SIGN).equals("false")) {
			System.out.println("fillValue��Ч�Ĵ������");
			writer.print(gson.toJson(new ReturnValue(1, "��Ч�Ķ�ά��", null)));
		} else {
			writer.print(gson.toJson(new ReturnValue(2, "UUID��Ч", null)));
		}
		writer.flush();
		writer.close();
	}

	@RequestMapping(value = "fillValue")
	public void fillValue(HttpServletRequest request,
			HttpServletResponse response, String uuid, String empNum,
			String password) {
		System.out.println(uuid);
		response.setContentType("text/html;charset=utf-8");
		Gson gson = new Gson();
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			writer.print(gson.toJson(new ReturnValue(0, "����������", null)));
		}

		HttpSession session = request.getSession();
		Map<String, String> info = (Map<String, String>) session
				.getAttribute(uuid);
		if (info == null || !info.get(SESSION_SIGN).equals("false")) {
			System.out.println("fillValue��Ч�Ĵ������");
			writer.print(gson.toJson(new ReturnValue(1, "��Ч�Ķ�ά��", null)));
			writer.flush();
			writer.close();
			return;
		}
		Map<String, String> args = new HashMap<String, String>(3);
		args.put(SESSION_SIGN, "true");
		args.put(SESSION_EMP_NUM, empNum);
		args.put(SESSION_PASSWORD, password);
		session.setAttribute(uuid, args);
		System.out.println(args.toString());
		writer.print(gson.toJson(new ReturnValue(2, "ȷ�ϵ�¼", null)));
		writer.flush();
		writer.close();
	}

	@RequestMapping(value = "qrcodeLogin", method = RequestMethod.GET)
	public void qrcodeLogin(HttpServletRequest request,
			HttpServletResponse response, String uuid) {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		Map<String, String> info = (Map<String, String>) session
				.getAttribute(uuid);
		if (info.get(SESSION_SIGN).equals("true")) {
			try {
				response.sendRedirect(request.getContextPath()
						+ "/login/login.action?empNum="
						+ info.get(SESSION_EMP_NUM) + "&password="
						+ info.get(SESSION_PASSWORD));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				writer = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			writer.print("stillfalse");
			writer.flush();
			writer.close();
		}
	}
	@RequestMapping(value = "getApkQRcode", method = RequestMethod.GET)
	public void getApkQRcode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("application/x-png;charset=utf-8");
		response.setCharacterEncoding("UTF_8");
		ServletOutputStream stream = null;
		try {
			stream = response.getOutputStream();
			QRCodeWriter codeWriter = new QRCodeWriter();
			BitMatrix m = codeWriter.encode("http://"+request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()+"/app/cloud.apk",
					BarcodeFormat.QR_CODE, 200, 200);
			MatrixToImageWriter.writeToStream(m, "PNG", stream);
		} catch (IOException | WriterException e) {
			e.printStackTrace();
		} finally {
			stream.flush();
			stream.close();
		}
	}
}
