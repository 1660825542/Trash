package com.icss.oa.carapply.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.carapply.pojo.CarApply;


@Repository
public class CarApplyDao {

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	public void insert(CarApply carapply) {
		SqlSession session = sessionFactory.openSession();
		session.insert("CAR_APPLY.insert", carapply);
	}

	public int getLastId() {
		SqlSession session = sessionFactory.openSession();
		int id = session.selectOne("CAR_APPLY.getLastId");
		return id;
	}

	public List<CarApply> query(Map<String,Integer> map) {
		SqlSession session = sessionFactory.openSession();
		List<CarApply> list = session.selectList("CAR_APPLY.query",map);
		return list;
	}
	
	public void updateStatus(CarApply carapply) {
		SqlSession sqlSession = sessionFactory.openSession();
		sqlSession.update("CAR_APPLY.updateStatus",carapply);
	}
	
	public CarApply queryById(Integer carApplyId) {
		SqlSession sqlSession = sessionFactory.openSession();
		CarApply empLeave = sqlSession.selectOne("CAR_APPLY.queryById",carApplyId);
		return empLeave;
	}
	
	public int count(Integer empId){
		SqlSession sqlSession = sessionFactory.openSession();
		int count = sqlSession.selectOne("CAR_APPLY.count", empId);
		return count;
	}
}