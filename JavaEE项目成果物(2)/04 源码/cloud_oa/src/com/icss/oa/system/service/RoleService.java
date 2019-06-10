package com.icss.oa.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.system.dao.PermissionRoleDao;
import com.icss.oa.system.pojo.Role;

@Service
@Transactional(rollbackFor=Exception.class)
public class RoleService {
	
	@Autowired
	private PermissionRoleDao rDao;

	/**
	 * 增加角色
	 * @param role
	 */
	public int addRole(Role role){
		rDao.insertRole(role);
		return rDao.getRolePrimaryKey();
	}
	
	/**
	 * 修改角色信息
	 * @param role
	 */
	public void editRole(Role role){
		rDao.updateRole(role);
	}
	
	/**
	 * 删除角色信息
	 * @param roleId
	 */
	public void deleteRole(Integer roleId){
		rDao.deleteRole(roleId);
	}
	
	public Role queryById(Integer roleId){
		return rDao.selectRoleById(roleId);
	}
	
	/**
	 * 分页查询角色信息
	 * @param pager
	 * @return
	 */
	public List<Role> query(Pager pager){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getPageNum()*pager.getPageSize());
		return rDao.selectRoleByPage(map);
	}
	
	/**
	 * 为角色增加权限
	 * @param roleId
	 * @param permId
	 */
	public void addPermissionToRole(Integer roleId, Integer permId){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("roleId", roleId);
		map.put("permId", permId);
		rDao.addPermissionToRole(map);
	}
	
	public int count(){
		return rDao.roleCount();
	}
	
	/**
	 * 角色是否已经分配给员工
	 * @param roleId
	 * @return
	 */
	public boolean isToEmp(Integer roleId){
		if (rDao.roleIsToEmp(roleId).isEmpty()) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public void addPermToRole(Integer PermId,Integer roleId){
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("permId", PermId);
		map.put("roleId", roleId);
		rDao.addPermissionToRole(map);
	}

	public Map<String, Object> selectNotToEmp(Integer empId) {
		List<Role> haveRoleList = rDao.selectEmpRole(empId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", haveRoleList);
		List<Role> notHaveList = rDao.selectNotToEmp(map);
		Map<String,Object> returnMap= new HashMap<String,Object>();
		returnMap.put("notHaveList", notHaveList);
		returnMap.put("haveList", haveRoleList);
		return returnMap;
	}
	
	public List<String> queryRole(String username){
		return rDao.queryRoleByUsername(username);
	}
}
