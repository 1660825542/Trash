package com.icss.oa.possession.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.possession.dao.HouseDao;
import com.icss.oa.possession.pojo.House;

@Service
@Transactional(rollbackFor = Exception.class)
public class HouseService {
	
	@Autowired
	private HouseDao dao;
	
	public void insert(House house) {
		dao.insert(house);
	}

	public void update(House house) {
		dao.update(house);
	}
	public void update1(Integer possId) {
		dao.update1(possId);
	}

	public void delete(Integer possId) {
		dao.delete(possId);
	}

	public House queryById(Integer possId) {
		return dao.queryById(possId);
	}

	public List<House> query(Pager pager) {
		return dao.query(pager);
	}
	public List<House> querByCondition(Pager pager, String possName) {

		return dao.queryByCondition(pager,  possName);
		
	}
	
	public int getConditionCount(String possName) {

		return dao.getConditionCount(  possName);
		
	}
	public List<House> querByConditionId(Pager pager, Integer possId) {

		return dao.queryByConditionId(pager,  possId);
	}
	
	public int getConditionIdCount(Integer possId) {

		return dao.getConditionIdCount(  possId);
	}
	
	public List<House> querByConditionCate(Pager pager, String possCate) {

		return dao.queryByConditionCate(pager,  possCate);
	}
	
	public int getConditionCateCount(String possCate) {

		return dao.getConditionCateCount(  possCate);
	}



	public int getCount() {
		return dao.getCount();
	}


	
  
}
