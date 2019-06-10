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
	 * ���ӽ�ɫ
	 * @param role
	 */
	public int addRole(Role role){
		rDao.insertRole(role);
		return rDao.getRolePrimaryKey();
	}
	
	/**
	 * �޸Ľ�ɫ��Ϣ
	 * @param role
	 */
	public void editRole(Role role){
		rDao.updateRole(role);
	}
	
	/**
	 * ɾ����ɫ��Ϣ
	 * @param roleId
	 */
	public void deleteRole(Integer roleId){
		rDao.deleteRole(roleId);
	}
	
	public Role queryById(Integer roleId){
		return rDao.selectRoleById(roleId);
	}
	
	/**
	 * ��ҳ��ѯ��ɫ��Ϣ
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
	 * Ϊ��ɫ����Ȩ��
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
	 * ��ɫ�Ƿ��Ѿ������Ա��
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
