package com.icss.oa.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class ContactsDao {
	@Autowired
	private SqlSessionFactory factory;

	public List<Map<String, String>> queryContacts() {
		SqlSession session = factory.openSession();
		List<Map<String, String>> list = session
				.selectList("APPUSER.queryAllForContact");
		session.close();
		return list;
	}

	public Map<String, String> queryEmpDetailByNum(String empNum) {
		SqlSession session = factory.openSession();
		Map<String, String> map = session.selectOne("APPUSER.queryEmpDetail",
				empNum);
		session.close();
		return map;
	}
}
