package com.icss.oa.carapply.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.carapply.pojo.CarRecord;

@Repository
public class CarRecordDao {
	
	@Autowired
	private SqlSessionFactory factory ;
	
	public void insert(CarRecord ase) {		
		SqlSession session = factory.openSession();
		session.insert("CAR_RECORD.insert",ase);	
	}
	
	public void update(CarRecord ase) {		
		SqlSession session = factory.openSession();
		session.update("CAR_RECORD.update",ase);
	}

	public void delete(Integer assId) {
		SqlSession session = factory.openSession();
		session.delete("CAR_RECORD.delete",assId);
	}
	public CarRecord queryById(Integer assId) {
		SqlSession session = factory.openSession();
		CarRecord emp = session.selectOne("CAR_RECORD.queryById",assId);
		return emp;
	}
	
	public int getCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("CAR_RECORD.getCount");
		return count;
	}

	public List<CarRecord> queryByPager(Map map) {
		SqlSession session = factory.openSession();
		List<CarRecord> list  = session.selectList("CAR_RECORD.queryByPager",map);
		return list;
	}
	
	//查询全部数据用来输出成EXCEL报表
	public List<CarRecord> exportExcel(Map map) {
		SqlSession session = factory.openSession();
		List<CarRecord> list  = session.selectList("CAR_RECORD.queryAll",map);
		return list;
	}
	
}