package com.icss.oa.app.pojo;

public class ReturnValue {
	private int code;
	private String message;
	private Object obj;

	public ReturnValue(int code, String message, Object obj) {
		super();
		this.code = code;
		this.message = message;
		this.obj = obj;
	}

	public ReturnValue(int code, String message) {
		super();
		this.code = code;
		this.message = message;
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

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "ReturnValue [code=" + code + ", message=" + message + ", obj="
				+ obj + "]";
	}

}
