package com.icss.oa.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.icss.oa.common.Pager;
import com.icss.oa.notice.pojo.Notice;

@Repository
@Scope("prototype")
public class AppNoticeDao {
	@Autowired
	private SqlSessionFactory factory;

	public List<Notice> getNoticeList(Pager pager) {
		SqlSession session = factory.openSession();
		Map<String, String> map = new HashMap<String, String>(2);
		map.put("start", Integer.toString(pager.getStart()));
		map.put("end",
				Integer.toString(pager.getStart() + pager.getPageSize() - 1));
		List<Notice> list = session.selectList("NOTICE.queryAllWithTop", map);
		session.close();
		return list;
	}

	public int getNoticeRecord() {
		SqlSession session = factory.openSession();
		return session.selectOne("NOTICE.countAll");
	}
}
