package com.icss.oa.record.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.record.dao.RecordDao;
import com.icss.oa.record.pojo.Record;

@Service
@Transactional(rollbackFor = Exception.class)
public class RecordService {
	
	@Autowired
	private RecordDao dao;
	
	public void insert(Record record) throws IOException {
		dao.insert(record);	
	}

	public void delete(Integer meetId) throws IOException {
		dao.delete(meetId);
	}
	public Record query(Integer meetId){
		return dao.query(meetId);
	}
	
	
}
