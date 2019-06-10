package com.icss.oa.possession.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.common.Pager;
import com.icss.oa.possession.pojo.Log;

@Repository
public class LogDao {
	 @Autowired
	 private SqlSessionFactory factory;
	public void insert(Log log) {
		SqlSession session = factory.openSession();
		session.insert("Log.insert", log);
	}

	public void update(Log log) {
		SqlSession session = factory.openSession();
		session.update("Log.updateupdateByPrimaryKey", log);
	}
	public void update1(Integer broNum) {
		SqlSession session = factory.openSession();
		session.update("Log.update1", broNum);
	}
	

	public void delete(Integer logId) {
		SqlSession session = factory.openSession();
		session.delete("Log.deleteByPrimaryKey", logId);
	}

	public Log queryById(Integer logId) {
		SqlSession session = factory.openSession();
		Log log = session.selectOne("Poss.queryById", logId);
		return log;
	}

	public List<Log> query(Pager pager) {
		SqlSession session = factory.openSession();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		List<Log> list = session.selectList("Poss.query", map);
		return list;
	}

	public int getCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("Log.getCount");
		return count;
	}
	//查询全部数据用来输出成EXCEL报表
		public List<Log> exportExcel() {
			SqlSession session = factory.openSession();
			List<Log> list  = session.selectList("Log.queryAll");
			return list;
		}
	


}
