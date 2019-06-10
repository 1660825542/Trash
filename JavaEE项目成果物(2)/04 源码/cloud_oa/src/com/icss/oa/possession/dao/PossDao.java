package com.icss.oa.possession.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.common.Pager;
import com.icss.oa.possession.pojo.Poss;
@Repository
public class PossDao {
 @Autowired
 private SqlSessionFactory factory;
public void insert(Poss poss) {
	SqlSession session = factory.openSession();
	session.insert("POSS.insert", poss);
}

public void update(Poss poss) {
	SqlSession session = factory.openSession();
	session.update("POSS.update", poss);
}

public void delete(Integer possId) {
	SqlSession session = factory.openSession();
	session.delete("POSS.delete", possId);
}

public Poss queryById(Integer possId) {
	SqlSession session = factory.openSession();
	Poss poss = session.selectOne("POSS.queryById", possId);
	return poss;
}

public List<Poss> query(Pager pager) {
	SqlSession session = factory.openSession();
	HashMap<String, Integer> map = new HashMap<String, Integer>();
	map.put("start", pager.getStart());
	map.put("end", pager.getStart() + pager.getPageSize() - 1);
	List<Poss> list = session.selectList("POSS.query", map);
	return list;
}

public int getCount() {
	SqlSession session = factory.openSession();
	int count = session.selectOne("POSS.getCount");
	return count;
}

public int getConditionCateCount(String possCate) {
	SqlSession session = factory.openSession();
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("possCate", possCate);

	int count = session.selectOne("POSS.getConditionCateCount",map);
	return count;
}
public List<Poss> queryByConditionCate(Pager pager,String possCate) {
	SqlSession session = factory.openSession();
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("start", pager.getStart());
	map.put("end", pager.getStart() + pager.getPageSize() - 1);
	map.put("possCate", possCate);
	List<Poss> list = session.selectList("POSS.queryByConditionCate", map);
	return list;
}

		

}
