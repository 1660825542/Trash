package com.icss.oa.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.notice.pojo.Notice;

@Repository
public class NoticeDao {
	
	@Autowired
	private SqlSessionFactory factory ;
	
	public void insert(Notice ntc) {		
		SqlSession session = factory.openSession();
		session.insert("NOTICE.insert",ntc);	
	}
	
	public void update(Notice ntc) {		
		SqlSession session = factory.openSession();
		session.update("NOTICE.update",ntc);
	}

	public void delete(Integer noticeId) {
		SqlSession session = factory.openSession();
		session.delete("NOTICE.delete",noticeId);
	}

	public List<Notice> query() {
		SqlSession session = factory.openSession();
		List<Notice> list  = session.selectList("NOTICE.queryAll");
		return list;
	}
	
	public List<Notice> queryTop() {
		SqlSession session = factory.openSession();
		List<Notice> list  = session.selectList("NOTICE.queryTop");
		return list;
	}
	
	public List<Notice> queryNotTop() {
		SqlSession session = factory.openSession();
		List<Notice> list  = session.selectList("NOTICE.queryNotTop");
		return list;
	}
	
	public Notice queryById(Integer noticeId) {
		SqlSession session = factory.openSession();
		Notice ntc = session.selectOne("NOTICE.queryById",noticeId);
		return ntc;
	}
	
	public int getCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("NOTICE.getCount");
		return count;
	}
	
	public List<Notice> queryByPager(Map map) {
		SqlSession session = factory.openSession();
		List<Notice> list  = session.selectList("NOTICE.queryByPager",map);
		return list;
	}
	
}