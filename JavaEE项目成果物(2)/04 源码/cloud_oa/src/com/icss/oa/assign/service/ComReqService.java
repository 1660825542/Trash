package com.icss.oa.assign.service;

import java.io.IOException;
import java.util.List;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.assign.dao.ComReqDao;
import com.icss.oa.assign.pojo.ComReq;

@Service
@Transactional(rollbackFor = Exception.class)
public class ComReqService {

	@Autowired
	private ComReqDao dao;

	// 查询不使用事务
	@Transactional(readOnly = true)
	public List<ComReq> query() {
		return dao.query();
	}

	@Transactional(readOnly = true)
	public int getCount() {
		return dao.getCount();
	}

	public void insert(ComReq stu) throws IOException {	
		dao.insert(stu);
	}
	
	public void delete(Integer comReqId) throws IOException {
		dao.delete(comReqId);
	}
	
	public void update(ComReq ase) throws IOException {
		dao.update(ase);
	}
	
	//根据id查询
	public ComReq queryById(Integer comReqId) {
		return dao.queryById(comReqId);
	}

	//分页查询
	@Transactional(readOnly = true)
	public List<ComReq> queryByPager(Pager pager) {

		int start = pager.getStart();
		int end = pager.getPageNum() * pager.getPageSize();

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);

		return dao.queryByPager(map);
	}

}