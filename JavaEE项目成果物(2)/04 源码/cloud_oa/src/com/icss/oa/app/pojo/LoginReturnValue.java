package com.icss.oa.app.pojo;

import com.icss.oa.system.pojo.Employee;

public class LoginReturnValue {
	private int code;
	private String message;
	private Employee emp;
	public static final int WRONG_PASSWORD = 1;
	public static final int WRONG_USERNAME = 0;
	public static final int LOGIN_SUCESS = 2;
	public static final int LOGIN_WRONG = 3;
	

	public LoginReturnValue(int code, String message, Employee emp) {
		super();
		this.code = code;
		this.message = message;
		this.emp = emp;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LoginReturnValue(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}
