package com.icss.oa.possession.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.possession.dao.HouseDao;
import com.icss.oa.possession.dao.PossDao;
import com.icss.oa.possession.pojo.House;
import com.icss.oa.possession.pojo.Poss;
import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.system.pojo.Employee;

@Service
@Transactional(rollbackFor = Exception.class)
public class PossService {
	
	@Autowired
	private PossDao dao;
	@Autowired
	private HouseDao houseDao;
	@Autowired
	private EmployeeDao empDao;
	
	

	
	public void insert(Poss poss) {
		dao.insert(poss);
	}

	public void update(Poss poss,Integer possBack) {
		House house = houseDao.queryById(poss.getPossId());
		house.setPossUnuse(house.getPossUnuse()+possBack);
		house.setPossUse(house.getPossUse()-possBack);
		houseDao.update(house);
	}

	public void delete(Integer possId) {
		House house = houseDao.queryById(possId);
		house.setPossUnuse(house.getPossUnuse()+house.getPossUse());
		house.setPossUse(0);
		
		houseDao.update(house);
	}

	public Poss queryById(Integer possId) {
		return dao.queryById(possId);
	}

	public List<Poss> query(Pager pager) {
		return dao.query(pager);
	}

	public int getCount() {
		return dao.getCount();
	}
	
	//得到员工的姓名
	/**	public String queryEmpById(Integer empId){
			return empDao.queryById(empId).getEmpName();
		}*/
	
	public Employee queryEmpById(Integer empId){
		return empDao.queryById(empId);
	}
	
	public int getEmpCount() {
		return empDao.getCount();
	}
		
	public Integer queryPossUnusedById(Integer possId) {
		return dao.queryById(possId).getPossUnuse();
	}

	public List<Map<String, Object>> queryEmp(Map<String, Integer> map){
		return empDao.query(map);
	}
	
	public int getConditionCateCount(String possCate) {

		return dao.getConditionCateCount(  possCate);
	}
	
	public List<Poss> querByConditionCate(Pager pager, String possCate) {

		return dao.queryByConditionCate(pager,  possCate);
	}
	
  
}
