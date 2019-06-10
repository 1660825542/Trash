package com.icss.oa.room.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.room.pojo.Room;

@Repository
@Transactional
public class RoomDao {
	
	@Autowired
	private SqlSessionFactory factory ;

	public void insert(Room room) {		
		SqlSession session = factory.openSession();
		session.insert("ROOM.insert",room);		
	}
	
	public void update1(Integer roomId) {		
		SqlSession session = factory.openSession();
		session.update("ROOM.update1",roomId);				
	}
	public void update0(Integer roomId) {		
		SqlSession session = factory.openSession();
		session.update("ROOM.update0",roomId);				
	}
	
	public void delete(Integer roomId) {
		SqlSession session = factory.openSession();
		session.delete("ROOM.delete",roomId);
	}
	
	public Room queryById(Integer roomId) {
		SqlSession session = factory.openSession();
		Room room = session.selectOne("ROOM.queryById",roomId);
		return room;
	}
	
	public List<Room> query(Pager pager) {
		
		SqlSession session = factory.openSession();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		List<Room> list  = session.selectList("ROOM.query",map);
		return list;
		
	}
	
	public int getCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("ROOM.getCount");
		return count;
	}
	

	/**
	 * 根据会议室名称查询
	 * @param pager
	 * @return
	 */
	public List<Room> queryByName(Pager pager,String roomName) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("roomName", roomName);
		List<Room> list = session.selectList("ROOM.queryByName", map);
		return list;
	}
	
	public List<Integer> query1() {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Integer> list = session.selectList("ROOM.query1", map);
		return list;
	}

	/**
	 * 根据会议室地点查询
	 * @param pager
	 * @return
	 */
	public List<Room> queryByPlace(Pager pager,String roomPlace) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("roomPlace", roomPlace);
		List<Room> list = session.selectList("ROOM.queryByPlace", map);
		return list;
	}
	
	/**
	 * 根据会议室状态查询
	 * @param pager
	 * @return
	 */
	public List<Room> queryByState(Pager pager,Short roomState) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("roomState", roomState);
		List<Room> list = session.selectList("ROOM.queryByState", map);
		return list;
	}
	
	/**
	 * 根据容纳人数查询
	 * @param pager
	 * @return
	 */
	public List<Room> queryByAccomNum(Pager pager,Integer accomNum) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("accomNum", accomNum);
		List<Room> list = session.selectList("ROOM.queryByAccomNum", map);
		return list;
	}
	/**
	 * 根据空调状态查询
	 * @param pager
	 * @return
	 */
	public List<Room> queryByAirCon(Pager pager,Short airCon) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("airCon", airCon);
		List<Room> list = session.selectList("ROOM.queryByAircon", map);
		return list;
	}
	/**
	 * 组合查询
	 * @param pager
	 * @return
	 */
	public List<Room> queryByCondition(Pager pager,String roomPlace ,Short airCon,Short roomState, Integer accomNum) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("roomPlace", roomPlace);
		map.put("airCon", airCon);
		map.put("roomState", roomState);
		map.put("accomNum", accomNum);
		List<Room> list = session.selectList("ROOM.queryByCondition", map);
		return list;
	}

	/**
	 * 返回满足条件的总记录数
	 * @return
	 */
	public int getConditionCount(String roomPlace,Short airCon,Short roomState,Integer accomNum) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roomPlace", roomPlace);
		map.put("airCon", airCon);
		map.put("roomState", roomState);
		map.put("roomPlace", roomPlace);
		map.put("accomNum", accomNum);
		int count = session.selectOne("ROOM.getConditionCount",map);
		return count;
	}

	
	public int getNameCount(String roomName) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roomName", roomName);
		int count = session.selectOne("ROOM.getNameCount",map);
		return count;
	}
	
	public int getPlaceCount(String roomPlace) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roomPlace", roomPlace);
		int count = session.selectOne("ROOM.getPlaceCount",map);
		return count;
	}
	
	public int getStateCount(Short roomState) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roomState", roomState);
		int count = session.selectOne("ROOM.getStateCount",map);
		return count;
	}
	
	public int getAccomNumCount(Integer accomNum) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("accomNum", accomNum);
		int count = session.selectOne("ROOM.getAccomNumCount",map);
		return count;
	}
	
	public int getAirConCount(Short airCon) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("airCon", airCon);
		int count = session.selectOne("ROOM.getAirConCount",map);
		return count;
	}
	
	public int getPrimaryKey() {
		SqlSession session = factory.openSession();
		int primaryKey = session.selectOne("ROOM.getPrimaryKey");
		return primaryKey;
	}
	
}