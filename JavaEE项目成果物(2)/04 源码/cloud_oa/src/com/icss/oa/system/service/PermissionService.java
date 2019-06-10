package com.icss.oa.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.system.dao.PermissionRoleDao;
import com.icss.oa.system.pojo.Permission;

@Service
@Transactional(rollbackFor=Exception.class)
public class PermissionService {

	@Autowired
	private PermissionRoleDao pDao;
	
	/**
	 * 增加新权限
	 * @param perm
	 */
	public int addPerm(Permission perm){
		pDao.insertPermission(perm);
		return pDao.getPermPrimaryKey();
	}
	
	/**
	 * 修改权限
	 * @param perm
	 */
	public void editPerm(Permission perm){
		pDao.updatePermission(perm);
	}
	
	/**
	 * 权限是否分配给了角色
	 * @param permId
	 * @return
	 */
	public boolean isToRole(Integer permId){
		if(pDao.permissionIsToRole(permId).isEmpty())
			return false;
		else
			return true;
	}
	
	/**
	 * 查看数据库中已有权限
	 * @param pager
	 * @return
	 */
	public List<Permission> selectPerm(Pager pager){
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getPageNum()*pager.getPageSize());
		return pDao.selectPermissionByPage(map);
	}
	
	public int count(){
		return pDao.permCount();
	}
	
	public Map<String, Object> selectNotToRole(Integer roleId, int PageNum){
		List<Permission> havePermList = pDao.selectRolePerm(roleId);
		Pager pager = new Pager(pDao.countNotToRole(havePermList), 18, PageNum);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", havePermList);
		map.put("start", pager.getStart());
		map.put("end", pager.getPageNum()*pager.getPageSize());
		List<Permission> notHaveList = pDao.selectNotToRole(map);
		Map<String,Object> returnMap= new HashMap<String,Object>();
		returnMap.put("notHaveList", notHaveList);
		returnMap.put("haveList", havePermList);
		returnMap.put("Pager", pager);
		return returnMap;
	}
	
	public List<String> queryPerm(String username){
		return pDao.queryPermByUsername(username);
	}
}
