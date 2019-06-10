package com.icss.oa.possession.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.common.Pager;
import com.icss.oa.possession.pojo.House;




@Repository
public class HouseDao {
 @Autowired
 private SqlSessionFactory factory;
public void insert(House house) {
	SqlSession session = factory.openSession();
	session.insert("HOUSE.insert", house);
}

public void update(House house) {
	SqlSession session = factory.openSession();
	session.update("HOUSE.update", house);
}
public void update1(Integer possId) {
	SqlSession session = factory.openSession();
	session.update("HOUSE.update1", possId);
}
public void delete(Integer possId) {
	SqlSession session = factory.openSession();
	session.delete("HOUSE.delete", possId);
}

public House queryById(Integer possId) {
	SqlSession session = factory.openSession();
	House house = session.selectOne("HOUSE.queryById", possId);
	return house;
}

public List<House> query(Pager pager) {
	SqlSession session = factory.openSession();
	HashMap<String, Integer> map = new HashMap<String, Integer>();
	map.put("start", pager.getStart());
	map.put("end", pager.getStart() + pager.getPageSize() - 1);
	List<House> list = session.selectList("HOUSE.query", map);
	return list;
}


public List<House> queryByCondition(Pager pager,String possName) {
	SqlSession session = factory.openSession();
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("start", pager.getStart());
	map.put("end", pager.getStart() + pager.getPageSize() - 1);
	map.put("possName", possName);
	List<House> list = session.selectList("HOUSE.queryByCondition", map);
	return list;
}
public List<House> queryByConditionId(Pager pager,Integer possId) {
	SqlSession session = factory.openSession();
	HashMap<String, Object> map = new HashMap<String, Object>();
	
	map.put("start", pager.getStart());
	map.put("end", pager.getStart() + pager.getPageSize() - 1);
	
	System.out.println(pager.getPageCount());
	
	map.put("possId", possId);
	List<House> list = session.selectList("HOUSE.queryByConditionId", map);
	return list;
}

public List<House> queryByConditionCate(Pager pager,String possCate) {
	SqlSession session = factory.openSession();
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("start", pager.getStart());
	map.put("end", pager.getStart() + pager.getPageSize() - 1);
	map.put("possCate", possCate);
	List<House> list = session.selectList("HOUSE.queryByConditionCate", map);
	return list;
}


public int getConditionIdCount(Integer possId) {
	SqlSession session = factory.openSession();
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("possId", possId);

	int count = session.selectOne("HOUSE.getConditionIdCount",map);
	return count;
}
public int getConditionCount(String possName) {
	SqlSession session = factory.openSession();
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("possName", possName);

	int count = session.selectOne("HOUSE.getConditionCount",map);
	return count;
}
public int getConditionCateCount(String possCate) {
	SqlSession session = factory.openSession();
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("possCate", possCate);

	int count = session.selectOne("HOUSE.getConditionCateCount",map);
	return count;
}
public int getIdCount(Integer possId) {
	SqlSession session = factory.openSession();
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("possId", possId);

	int count = session.selectOne("HOUSE.getIdCount",map);
	return count;
}
public int getCount() {
	SqlSession session = factory.openSession();
	int count = session.selectOne("HOUSE.getCount");
	return count;
}




		

}
