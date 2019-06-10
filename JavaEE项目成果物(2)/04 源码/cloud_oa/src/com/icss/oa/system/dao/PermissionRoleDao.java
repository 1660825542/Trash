package com.icss.oa.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.system.pojo.Permission;
import com.icss.oa.system.pojo.Role;

@Repository
public class PermissionRoleDao {

	@Autowired
	private SqlSessionFactory factory;
	
	public List<Permission> selectAllPermissions(){
		SqlSession session = factory.openSession();
		List<Permission> list = session.selectList("PERMISSION.selectPermission");
		return list;
	}
	
	public Permission selectPermissionById(Integer permId){
		SqlSession session = factory.openSession();
		Permission perm = session.selectOne("PERMISSION.selectPermissionById",permId);
		return perm;
	}
	
	public void insertPermission(Permission perm){
		SqlSession session = factory.openSession();
		session.insert("PERMISSION.insertPermission", perm);
	}
	
	public void updatePermission(Permission perm){
		SqlSession session = factory.openSession();
		session.update("PERMISSION.updatePermission", perm);
	}
	
	public void insertRole(Role role){
		SqlSession session = factory.openSession();
		session.insert("ROLE.insertRole", role);
	}
	
	public List<Role> selectAllRoles(){
		SqlSession session = factory.openSession();
		List<Role> list = session.selectList("ROLE.selectRole");
		return list;
	}
	
	public void updateRole(Role role){
		SqlSession session = factory.openSession();
		session.update("ROLE.updateRole", role);
	}
	
	public void deleteRole(Integer roleId){
		SqlSession session = factory.openSession();
		session.delete("ROLE.deleteRole", roleId);
	}
	
	public Role selectRoleById(Integer roleId){
		SqlSession session = factory.openSession();
		Role role = session.selectOne("ROLE.selectRoleById", roleId);
		return role;
	}
	
	public void addPermissionToRole(Map<String, Integer> map){
		SqlSession session = factory.openSession();
		session.insert("ROLE.addPermission", map);
	}
	
	public List<Permission> selectPermissionByPage(Map<String,Integer> map){
		SqlSession session = factory.openSession();
		List<Permission> list = session.selectList("PERMISSION.selectPage", map);
		return list;
	}
	
	public List<Role> selectRoleByPage(Map<String,Integer> map){
		SqlSession session = factory.openSession();
		List<Role> list = session.selectList("ROLE.selectPage", map);
		return list;
	}
	
	public List<Permission> permissionIsToRole(Integer permId){
		SqlSession session = factory.openSession();
		List<Permission> list = session.selectList("PERMISSION.permissionIsToRole",permId);
		return list;
	}
	
	public List<Role> roleIsToEmp(Integer roleId){
		SqlSession session = factory.openSession();
		List<Role> list = session.selectList("ROLE.roleIsToEmp",roleId);
		return list;
	}
	
	public Integer permCount(){
		SqlSession session = factory.openSession();
		int count = session.selectOne("PERMISSION.countPerm");
		return count;
	}
	
	public Integer roleCount(){
		SqlSession session = factory.openSession();
		int count = session.selectOne("ROLE.countRole");
		return count;
	}
	
	public int getPermPrimaryKey(){
		SqlSession session = factory.openSession();
		int pk = session.selectOne("PERMISSION.getPermPrimaryKey");
		return pk;
	}
	
	public int getRolePrimaryKey(){
		SqlSession session = factory.openSession();
		int pk = session.selectOne("ROLE.getRolePrimaryKey");
		return pk;
	}
	
	/**
	 * ѡ��ĳ��Ա���еĽ�ɫ
	 * @param empId
	 * @return
	 */
	public List<Role> selectEmpRole(Integer empId){
		SqlSession session = factory.openSession();
		List<Role> list = session.selectList("ROLE.empHaveRole", empId);
		return list;
	}
	
	/**
	 * ��ѯĳ��Ա��û�еĽ�ɫ�������һ��һ��ʹ��
	 * @param permlist
	 * @return
	 */
	public List<Role> selectNotToEmp(Map<String,Object> map){
		SqlSession session = factory.openSession();
		List<Role> list = session.selectList("ROLE.selectNotToEmp", map);
		return list;
	}
	
	/**
	 * ��ѯĳ��Ա��û�еĽ�ɫ���������������һ��ʹ��
	 * @param list
	 * @return
	 */
	public int countNotToEmp(List<Role> list){
		SqlSession session = factory.openSession();
		int count = session.selectOne("ROLE.countNotToEmp",list);
		return count;
	}
	
	/**
	 * ѡ��ĳ����ɫ�е�Ȩ��
	 * @param roleId
	 * @return
	 */
	public List<Permission> selectRolePerm(Integer roleId){
		SqlSession session = factory.openSession();
		List<Permission> list = session.selectList("PERMISSION.roleHavePerm", roleId);
		return list;
	}
	
	/**
	 * ��ѯĳ����ɫû�е�Ȩ�ޣ������һ��һ��ʹ��
	 * @param permlist
	 * @return
	 */
	public List<Permission> selectNotToRole(Map<String,Object> map){
		SqlSession session = factory.openSession();
		List<Permission> list = session.selectList("PERMISSION.selectNotToRole", map);
		return list;
	}
	
	/**
	 * ��ѯĳ����ɫû�е�Ȩ�޵��������������һ��ʹ��
	 * @param list
	 * @return
	 */
	public int countNotToRole(List<Permission> list){
		SqlSession session = factory.openSession();
		int count = session.selectOne("PERMISSION.countNotToRole",list);
		return count;
	}
	
	/**
	 * �����û���¼����ѯȨ��
	 * @param username
	 * @return
	 */
	public List<String> queryPermByUsername(String username){
		SqlSession session = factory.openSession();
		List<String> perm = session.selectList("PERMISSION.queryPermByUsername", username);
		return perm;
	}

	/**
	 * ���ݵ�¼����ѯ��ɫ
	 * @param username
	 * @return
	 */
	public List<String> queryRoleByUsername(String username){
		SqlSession session = factory.openSession();
		List<String> role = session.selectList("ROLE.queryRoleByUsername", username);
		return role;
	}
}
