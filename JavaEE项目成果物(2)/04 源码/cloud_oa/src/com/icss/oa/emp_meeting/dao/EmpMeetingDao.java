package com.icss.oa.emp_meeting.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.emp_meeting.pojo.EmpMeeting;

@Repository
public class EmpMeetingDao {

	@Autowired
	private SqlSessionFactory factory;

	public void insert(EmpMeeting empMeeting) {
		SqlSession session = factory.openSession();
		session.insert("EMP_MEETING.insert", empMeeting);
	}

	public void delete(Integer meetId) {
		SqlSession session = factory.openSession();
		session.delete("EMP_MEETING.delete", meetId);
	}

	public List<Integer> queryByMeetId(Integer meetId) {

		SqlSession session = factory.openSession();
		List<Integer> list = session.selectList("EMP_MEETING.queryByMeetId",
				meetId);
		return list;

	}
}