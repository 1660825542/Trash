package com.icss.oa.card.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.card.dao.CardCategoryDao;
import com.icss.oa.card.pojo.CardCategory;
import com.icss.oa.common.Pager;

@Service
@Transactional(rollbackFor=Exception.class)
public class CardCategoryService {
	
	@Autowired
	private CardCategoryDao cardcategorydao;
	
	public void insert(CardCategory cardcategory){
		
		cardcategorydao.insert(cardcategory);
	}
	
	public void delete(Integer cataId){
		
		cardcategorydao.delete(cataId);
	}
	
	public void update(CardCategory cardcategory){
		
		cardcategorydao.update(cardcategory);
	}
	
	public List<CardCategory> queryByPager(Pager pager,Integer empId){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start",pager.getStart());
		map.put("end",pager.getStart()+pager.getPageSize()-1);
		List<CardCategory> cardcategory = cardcategorydao.queryByPager(map,empId);
		return cardcategory;
	}
	
	public CardCategory queryById(Integer cataId){
		
		CardCategory cardcategory = cardcategorydao.queryById(cataId);
		return cardcategory;
	}
	
	public int getCount(Integer empId) {

		return cardcategorydao.getCount(empId);
	}
	
	public List<CardCategory> query(Integer empId) {

		return cardcategorydao.query(empId);
	}
	

}
