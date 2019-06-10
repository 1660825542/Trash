package com.icss.oa.car.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.car.dao.CarDao;
import com.icss.oa.car.pojo.Car;

@Service
@Transactional(rollbackFor = Exception.class)
public class CarService {

	@Autowired
	private CarDao dao;

	public void insert(Car car) {	
		dao.insert(car);
	}
	
	public void delete(Integer assId) {
		dao.delete(assId);
	}
	
	public void update(Car car) throws IOException {
		dao.update(car);
	}
	
	public Car queryById(Integer carId) {
		return dao.queryById(carId);
	}

	@Transactional(readOnly = true)
	public int getCount() {
		return dao.getCount();
	}
	
	@Transactional(readOnly = true)
	public List<Car> queryByPager(Pager pager) {

		int start = pager.getStart();
		int end = pager.getPageNum() * pager.getPageSize();

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);

		return dao.queryByPager(map);
	}
}