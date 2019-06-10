package com.rock.ssm.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rock.ssm.entities.Userinfo;
import com.rock.ssm.service.UserinfoService;

/**
 * FileName		:com.rock.ssm.action 	UserAction.java
 * TODO			:
 * Copyright	:Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
 * @author: 	:老张
 * @Date		:2019年4月27日:下午4:47:38
 * @version 	:1.0
 * 
 *           Modification History: Date			Author 		Version 	Description
 *           ----------------------------------------------------------------------
 *           					   2019年4月27日 	老张 			1.0 		1.0 Version
 * 
 */
@Controller
public class UserAction {
	
	@Autowired
	private UserinfoService userinfoService;
	/**
	 * @TODO	 :根据页码获取指定页数据
	 * @Date	 :2019年4月27日 下午4:48:33
	 * @Author	 :老张
	 * @return   :
	 */
	@ResponseBody
	@RequestMapping("/users")
	public List<Userinfo> getUsersByCpage(@RequestParam(value="cpage",defaultValue="1")String cpage){
		return userinfoService.queryAll();
	}
}
