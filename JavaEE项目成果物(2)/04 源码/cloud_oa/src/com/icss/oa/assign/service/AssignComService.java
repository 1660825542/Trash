package com.icss.oa.assign.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.assign.dao.AssignComDao;
import com.icss.oa.assign.pojo.AssignCom;

@Service
@Transactional(rollbackFor = Exception.class)
public class AssignComService {

	@Autowired
	private AssignComDao dao;

	// ��ѯ��ʹ������
	@Transactional(readOnly = true)
	public List<AssignCom> query() {
		return dao.query();
	}

	@Transactional(readOnly = true)
	public int getCount() {
		return dao.getCount();
	}

	public void insert(AssignCom ase) throws IOException {	
		dao.insert(ase);
	}
	
	public void delete(Integer assId) throws IOException {
		dao.delete(assId);
	}
	
	public void update(AssignCom ase) throws IOException {
		dao.update(ase);
	}
	
	//���Ҹù�˾��������
	public int queryExist(Integer assComId) throws IOException {
		return dao.queryExist(assComId);
	}
	
	//��id��ѯ
	public AssignCom queryById(Integer assComId) {
		return dao.queryById(assComId);
	}

	//��ҳ��ѯ
	@Transactional(readOnly = true)
	public List<AssignCom> queryByPager(Pager pager) {

		int start = pager.getStart();
		int end = pager.getPageNum() * pager.getPageSize();

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);

		return dao.queryByPager(map);
	}
	
	//��������ѯ
	@Transactional(readOnly = true)
	public List<AssignCom> querByCondition(Integer assComId) {

		return dao.queryByCondition(assComId);
	}
}