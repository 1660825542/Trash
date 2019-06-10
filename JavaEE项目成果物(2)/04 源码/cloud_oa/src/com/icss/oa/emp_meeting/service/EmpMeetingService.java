package com.icss.oa.emp_meeting.service;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.icss.oa.emp_meeting.dao.EmpMeetingDao;
import com.icss.oa.emp_meeting.pojo.EmpMeeting;






@Service
@Transactional(rollbackFor = Exception.class)
public class EmpMeetingService {

	@Autowired
	private EmpMeetingDao dao;
	
	public void insert(EmpMeeting empMeeting) throws IOException {

		dao.insert(empMeeting);

	}
	public void delete(Integer meetId){
		dao.delete(meetId);
	}
}
