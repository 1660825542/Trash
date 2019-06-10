package com.icss.oa.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.PermissionService;
import com.icss.oa.system.service.RoleService;
import com.icss.oa.login.service.UserService;

/**
 * shiro的realm
 * 
 * @author Administrator
 * 
 */
public class MyRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private PermissionService permService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 获得授权信息
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		System.out.println("进行授权...doGetAuthorizationInfo");

		// 获得当前用户名
		String userName = (String) getAvailablePrincipal(principals);
		
		// 通过用户名去获得用户的所有资源，并把资源存入info中
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		// 设置权限
		Set<String> p = new HashSet<String>();
		List<String> list = permService.queryPerm(userName);
		for (String string : list) {
			p.add(string);
		}
		info.setStringPermissions(p);

		// 设置角色
		Set<String> r = new HashSet<String>();
		list = roleService.queryRole(userName);
		for (String string : list) {
			System.out.println(string);
			r.add(string);
		}
		info.setRoles(r);

		// 返回此用户的授权信息
		return info;
	}

	/**
	 * 获得认证信息
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		System.out.println("进行登录验证...doGetAuthenticationInfo");

		// token中储存着输入的用户名和密码
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		String password = String.valueOf(upToken.getPassword());

		//根据用户名查询数据库账号密码
		Employee user = service.empObj(username);
		
		if (user == null) {
			//账号不存在
			throw new UnknownAccountException("用户名不存在 ");
		} else if (!password.equals(user.getPassword())) {
			//密码错误
			throw new IncorrectCredentialsException("密码错误");
		} 
		
		// 比对成功则返回info，比对失败则抛出对应信息的异常AuthenticationException
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,
				password.toCharArray(), getName());

		return info;
	}

	/**
	 * 删除当前用户的缓存
	 * 
	 * @param userId
	 */
	public void removeUserCache(String userId) {
		SimplePrincipalCollection pc = new SimplePrincipalCollection();
		pc.add(userId, super.getName());
		super.clearCachedAuthorizationInfo(pc);
	}

}