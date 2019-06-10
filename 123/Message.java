package com.rock.ssm.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * FileName		:com.rock.ssm.entities 	Message.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:老张
 * @Date		:2019年4月28日:上午10:23:51
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年4月28日 	老张 			1.0 		1.0 Version
 * 
 */
public class Message {
//	当前请求的处理结果的描述内容 （当前操作成功的，失败）
	private String info;
//	约定当前操作成功与否
//	1：成功
//	-1：失败
	private int code;
//	用于存放相关List集合数据等
	private Map<String,Object> dbinfo=new HashMap<>();
	
	public static Message success(){
		Message msg=new Message();
		msg.setCode(1);
		msg.setInfo("恭喜你，当前请求处理成功");
		return msg;
	}
	public static Message fail(){
		Message msg=new Message();
		msg.setCode(-1);
		msg.setInfo("抱歉你，当前请求处理失败");
		return msg;
	}
	public Message add(String key ,Object value){
		this.getDbinfo().put(key, value);
		return this;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Map<String, Object> getDbinfo() {
		return dbinfo;
	}

	public void setDbinfo(Map<String, Object> dbinfo) {
		this.dbinfo = dbinfo;
	}
	@Override
	public String toString() {
		return "Message [info=" + info + ", code=" + code + ", dbinfo=" + dbinfo + "]";
	}
	
	
	
}
