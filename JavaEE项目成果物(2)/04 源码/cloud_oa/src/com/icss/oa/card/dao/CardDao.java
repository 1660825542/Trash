package com.icss.oa.card.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.card.pojo.Card;
import com.icss.oa.common.Pager;



@Repository
public class CardDao {
	@Autowired
	private SqlSessionFactory factory;
	
	public void insert(Card card){
		SqlSession session = factory.openSession();
		session.insert("CARD.insert",card);	
	}
	
	public void update(Card card) {		
		SqlSession session = factory.openSession();
		session.update("CARD.update",card);		
	}
	
	public void delete(Integer cardId){
		SqlSession session = factory.openSession();
		session.delete("CARD.delete",cardId);
	}
	
	public Card queryById(Integer cardId) {
		SqlSession session = factory.openSession();
		Card card = session.selectOne("CARD.queryById",cardId);
		return card;
	}
	
	public List<Card> query(Pager pager,Integer empId) {
		SqlSession session = factory.openSession();
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("empId", empId);
		List<Card> list = session.selectList("CARD.query",map);
		return list;
	}
	
	public int getCount(Integer cataId) {
		SqlSession session = factory.openSession();
		int count = session.selectOne("CARD.getCount",cataId);
		return count;
	}
	
	public List<Card> queryByPager(Map map) {
		SqlSession session = factory.openSession();
		List<Card> list  = session.selectList("CARD.queryByPager",map);
		return list;
	}
	
	public int getPrimaryKey() {
		SqlSession session = factory.openSession();
		int primaryKey = session.selectOne("CARD.getPrimaryKey");
		return primaryKey;
	}

	public List<Card> query1(Pager card) {
		SqlSession session = factory.openSession();
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		map.put("start", card.getStart());
		map.put("end", card.getStart() + card.getPageSize() - 1);
		List<Card> list = session.selectList("CARD.query1",map);
		return list;
	}
	
	public List<Card> queryCatecard(Pager pager,Integer cataId) {
		SqlSession session = factory.openSession();
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("cataId", cataId);
		List<Card> list = session.selectList("CARD.queryCatecard",map);
		return list;
	}

	public int getConditionCount(String cardName) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cardName", cardName);
		int count = session.selectOne("CARD.getConditionCount",map);
		return count;
	}

	public List<Card> queryByCondition(Pager pager,String cardName) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("cardName", cardName);
		List<Card> list = session.selectList("CARD.queryByCondition", map);
		return list;
	}
}
