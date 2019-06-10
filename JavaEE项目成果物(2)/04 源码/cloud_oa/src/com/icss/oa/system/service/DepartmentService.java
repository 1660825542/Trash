package com.icss.oa.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.system.dao.DepartmentDao;
import com.icss.oa.system.pojo.Department;

@Service
@Transactional(rollbackFor=Exception.class)
public class DepartmentService {

	@Autowired
	private DepartmentDao deptDao;
	
	/**
	 * ���Ӳ���
	 * @param dept
	 */
	public int addDept(Department dept){
		deptDao.insert(dept);
		return deptDao.getPrimaryKey();
	}
	
	/**
	 * �޸Ĳ���
	 * @param dept
	 * @return
	 */
	public void editDept(Department dept){
		deptDao.update(dept);
	}
	
	/**
	 * ɾ������
	 * @param deptId
	 * @return
	 */
	public void deleteDept(Integer deptId){
		deptDao.delete(deptId);
	}
	
	/**
	 * ��ҳ�鿴���в���
	 * @param pager
	 * @return
	 */
	public List<Department> queryDepts(Pager pager){
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getPageNum()*pager.getPageSize());
		return deptDao.queryByPager(map);
	}
	
	/**
	 * ��ID��ѯ������Ϣ
	 * @param deptId
	 * @return
	 */
	public Department queryById(Integer deptId){
		return deptDao.queryById(deptId);
	}
	
	/**
	 * ��ѯ��������
	 * @return
	 */
	public int count(){
		return deptDao.count();
	}
	
	/**
	 * ��ѯ�Ƿ�������Ա��
	 * @param deptId
	 * @return
	 */
	public boolean isHaveEmp(Integer deptId){
		if(deptDao.haveEmp(deptId))
			return true;
		else
			return false;
	}

	public List<Department> query() {
		return deptDao.query();
	}
}
