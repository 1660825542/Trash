package com.icss.oa.carapply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.carapply.pojo.CarFlowLog;

@Repository
public class CarFlowLogDao {

	@Autowired
	private SqlSessionFactory factory;
	
	public void insert(CarFlowLog flowLog) {
		SqlSession session = factory.openSession();
		session.insert("CAR_FLOW_LOG.insert",flowLog);
	}
	
	public List<CarFlowLog> query() {
		SqlSession session = factory.openSession();
		List<CarFlowLog> list = session.selectList("CAR_FLOW_LOG.query");
		return list;
	}
	
	public List<CarFlowLog> queryByCarApplyId(Integer Id) {
		SqlSession session = factory.openSession();
		List<CarFlowLog> list = session.selectList("CAR_FLOW_LOG.queryByCarApplyId",Id);
		return list;
	}
	
	public String queryRecordidByProId(String proId){
		SqlSession session = factory.openSession();
		String recordid = session.selectOne("CAR_FLOW_LOG.queryRecordidByProid", proId);
		return recordid;
	}
}