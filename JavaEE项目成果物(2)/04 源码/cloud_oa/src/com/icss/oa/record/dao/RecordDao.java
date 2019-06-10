package com.icss.oa.record.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.record.pojo.Record;

@Repository
public class RecordDao {
	
	@Autowired
	private SqlSessionFactory factory ;

	public void insert(Record record) {		
		SqlSession session = factory.openSession();
		session.insert("RECORD.insert",record);		
	}
	
	public Record query(Integer meetId){
		SqlSession session = factory.openSession();
		Record record = session.selectOne("RECORD.query",meetId);
		return record;
	}
	
	public void delete(Integer meetId) {
		SqlSession session = factory.openSession();
		session.delete("RECORD.delete",meetId);
	}
	
}	