package com.icss.oa.card.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.common.Pager;

	
	@Repository
	public class AddressListDao {
		
		@Autowired
		private SqlSessionFactory factory;
		
		public Map<String,Object> queryById(Integer empId) {
			SqlSession session = factory.openSession();
			Map<String,Object> addresslist = session.selectOne("ADDRESSLIST.queryById",empId);
			return addresslist;
		}

		public List<Map<String,Object>> query(Pager pager) {
			SqlSession session = factory.openSession();
			HashMap<String, Integer> map = new HashMap<String,Integer>();
			map.put("start", pager.getStart());
			map.put("end", pager.getStart() + pager.getPageSize() - 1);
			List<Map<String,Object>> list= session.selectList("ADDRESSLIST.query",map);
			return list;
		}
		
		public List<Map<String,Object>> queryByCondition(Pager pager,String empName) {
			SqlSession session = factory.openSession();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", pager.getStart());
			map.put("end", pager.getStart() + pager.getPageSize() - 1);
			map.put("empName", empName);
			List<Map<String,Object>> list = session.selectList("ADDRESSLIST.queryByCondition", map);
			return list;
		}
		
		
		public int getConditionCount(String empName) {
			SqlSession session = factory.openSession();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("empName", empName);
			int count = session.selectOne("ADDRESSLIST.getConditionCount",map);
			return count;
		}
		
		public int getCount() {
			SqlSession session = factory.openSession();
			int count =  session.selectOne("ADDRESSLIST.getCount");
			return count;
		}
	}
