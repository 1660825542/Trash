package com.icss.oa.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.system.dao.EmployeeDao;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrgService {

	@Autowired
	EmployeeDao empDao;
	
	public List<Map<String, Object>> queryEmpByDept(Pager pager, Integer deptId) {
		List<Map<String, Object>> list = empDao.queryEmpByDept(pager, deptId);
		return list;
	}

	public List<Map<String, Object>> query(Pager pager) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		List<Map<String, Object>> list = empDao.query(map);
		return list;
	}
	
	public int getCount() {	
		return empDao.getCount();
	}
	
	public int getCountByDept(Integer deptId) {
		
		return empDao.getEmpCountByDept(deptId);
	}
}

