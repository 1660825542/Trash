package com.icss.oa.message.service;

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
public class EmpService {

	@Autowired
	private EmployeeDao dao;
	
	//��ҳ��ѯ����Ա��������������
	@Transactional(readOnly = true)
	public List<Map<String,Object>> queryEmployee(Pager pager){
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		return dao.query(map);
	}
	
	//�õ�Ա������
	@Transactional(readOnly = true)
	public int getCount(){
		return dao.getCount();
	}
	
	//�õ�Ա��������
	public String queryById(Integer empId){
		return dao.queryById(empId).getEmpName();
	}
}
