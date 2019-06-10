package com.icss.oa.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.system.pojo.Position;

@Repository
public class PositionDao {
	
	@Autowired
	private SqlSessionFactory factory;
	
	public void insert(Position pos){
		SqlSession session = factory.openSession(); 
		session.insert("POSITION.insert", pos);
	}
	
	public void delete(Integer posId){
		SqlSession session = factory.openSession(); 
		session.delete("POSITION.delete", posId);
	}
	
	public void update(Position pos){
		SqlSession session = factory.openSession(); 
		session.update("POSITION.update", pos);
	}
	
	public Position queryById(Integer posId){
		SqlSession session = factory.openSession(); 
		Position pos = session.selectOne("POSITION.selectById", posId);
		return pos;
	}
	
	public List<Position> queryAll(){
		SqlSession session = factory.openSession(); 
		List<Position> list = session.selectList("POSITION.selectAll");
		return list;
	}
	
	public List<Position> queryByPage(Map<String,Integer> map){
		SqlSession session = factory.openSession();
		List<Position> list = session.selectList("POSITION.selectPage", map);
		return list;
	}
	
	public int count(){
		SqlSession session = factory.openSession();
		int count = session.selectOne("POSITION.count");
		return count;
	}
	
	
	public boolean haveEmp(Integer posId){
		SqlSession session = factory.openSession();
		List<Position> list = session.selectList("POSITION.haveEmp",posId);
		if(!list.isEmpty())
			return true;
		else
			return false;
	}
	
	/**
	 * 获得当前主键，供Action获得主键
	 * @return
	 */
	public int getPrimaryKey() {
		SqlSession session = factory.openSession();
		int primaryKey = session.selectOne("POSITION.getPrimaryKey");
		return primaryKey;
	}
	
}
