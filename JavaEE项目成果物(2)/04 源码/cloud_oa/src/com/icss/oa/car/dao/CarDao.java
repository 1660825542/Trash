package com.icss.oa.car.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.car.pojo.Car;

@Repository
public class CarDao {
	
	@Autowired
	private SqlSessionFactory factory ;
	
	public void insert(Car car) {		
		SqlSession session = factory.openSession();
		session.insert("CAR.insert",car);	
	}
	
	public void update(Car car) {		
		SqlSession session = factory.openSession();
		session.update("CAR.update",car);
	}

	public void delete(Integer carId) {
		SqlSession session = factory.openSession();
		session.delete("CAR.delete",carId);
	}

	public Car queryById(Integer carId) {
		SqlSession session = factory.openSession();
		Car car = session.selectOne("CAR.queryById",carId);
		return car;
	}
	
	public int getCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("CAR.getCount");
		return count;
	}

	public List<Car> queryByPager(Map map) {
		SqlSession session = factory.openSession();
		List<Car> list  = session.selectList("CAR.queryByPager",map);
		return list;
	}
		
	public List<Car> queryByTime(Map map) {
		SqlSession session = factory.openSession();
		List<Car> list  = session.selectList("CAR.queryByTime",map);
		return list;
	}
	
}