package com.icss.oa.card.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.card.pojo.CardCategory;

@Repository
public class CardCategoryDao {
	@Autowired
	private SqlSessionFactory factory;
	
	public void insert(CardCategory cardcategory){
		SqlSession session = factory.openSession();
		session.insert("CARD_CATEGORY.insert",cardcategory);	
	}
	
	public void update(CardCategory cardcategory) {		
		SqlSession session = factory.openSession();
		session.update("CARD_CATEGORY.update",cardcategory);		
	}
	
	public void delete(Integer cataId){
		SqlSession session = factory.openSession();
		session.delete("CARD_CATEGORY.delete",cataId);
	}
	
	public CardCategory queryById(Integer cataId) {
		SqlSession session = factory.openSession();
		CardCategory cardcategory = session.selectOne("CARD_CATEGORY.queryById",cataId);
		return cardcategory;
	}
	
	public List<CardCategory> query(Integer empId) {
		SqlSession session = factory.openSession();
		List<CardCategory> list = session.selectList("CARD_CATEGORY.query",empId);
		return list;
	}
	
	public int getCount(Integer empId) {
		SqlSession session = factory.openSession();
		int count = session.selectOne("CARD_CATEGORY.getCount",empId);
		return count;
	}
	
	public List<CardCategory> queryByPager(Map<String, Object> map,Integer empId) {
		SqlSession session = factory.openSession();
		map.put("empId",empId);
		List<CardCategory> list  = session.selectList("CARD_CATEGORY.queryByPager",map);
		return list;
	}
	
	public int getPrimaryKey() {
		SqlSession session = factory.openSession();
		int primaryKey = session.selectOne("CARD_CATEGORY.getPrimaryKey");
		return primaryKey;
	}
	

}