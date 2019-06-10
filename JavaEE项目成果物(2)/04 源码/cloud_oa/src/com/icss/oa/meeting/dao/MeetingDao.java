package com.icss.oa.meeting.dao;



import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.common.Pager;
import com.icss.oa.meeting.pojo.Meeting;



@Repository
public class MeetingDao {
	
	@Autowired
	private SqlSessionFactory factory ;

	public void insert(Meeting meet) {		
		SqlSession session = factory.openSession();
		session.insert("MEETING.insert",meet);		
	}
	
	
	public void update(Meeting meet) {		
		SqlSession session = factory.openSession();
		session.update("MEETING.update",meet);		
	}
	
	public void deleteByState(Integer meetState) {
		SqlSession session = factory.openSession();
		session.delete("MEETING.deleteByState",meetState);
	}
	
	public void deleteById(Integer meetId) {
		SqlSession session = factory.openSession();
		session.delete("MEETING.deleteById",meetId);
	}
	
	public Meeting queryById(Integer meetId) {
		SqlSession session = factory.openSession();
		Meeting meeting = session.selectOne("MEETING.queryById",meetId);
		return meeting;
	}
	
	public int getCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("MEETING.getCount");
		return count;
	}
	public int getByRoomIdCount() {
		SqlSession session = factory.openSession();
		int count = session.selectOne("MEETING.getByRoomIdCount");
		return count;
	}
	public List<Map<String,Object>> query(Pager pager) {
		
		SqlSession session = factory.openSession();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		List<Map<String,Object>> list  = session.selectList("MEETING.query",map);
		return list;	
	}
	

	
	/**
	 * 根据会议室id查询
	 * @param pager
	 * @return
	 */
	public List<Map<String,Integer>> queryByRoomId(Pager pager,Integer roomId) {
		SqlSession session = factory.openSession();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("roomId", roomId);
		List<Map<String,Integer>> list  = session.selectList("MEETING.queryByRoomId",map);
		return list;
	}
	
	/**
	 * 根据发起者查询
	 * @param pager
	 * @return
	 */
	public List<Map<String,Object>> queryByOriginator(Pager pager,Integer originator) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("originator", originator);
		List<Map<String,Object>> list = session.selectList("MEETING.queryByOriginator", map);
		return list;
	}
	/**
	 * 根据会议主题查询
	 * @param pager
	 * @return
	 */
	public List<Map<String,Object>> queryByTheme(Pager pager,String theme) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("theme", theme);
		List<Map<String,Object>> list = session.selectList("MEETING.queryByTheme", map);
		return list;
	}
	
	/**
	 * 根据员工查询需要参加会议
	 * @param pager
	 * @return
	 */
	public List<Map<String,Object>> queryByEmpId(Pager pager,Integer empId) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("empId", empId);
		List<Map<String,Object>> list = session.selectList("MEETING.queryByEmpId", map);
		return list;
	}
	/**
	 * 根据预约时间查询
	 * @param pager
	 * @return
	 */
	public List<Meeting> queryResult(Integer roomId,Timestamp meetDateBegin,Timestamp meetDateEnd) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roomId", roomId);
		map.put("meetDateBegin", meetDateBegin);
		map.put("meetDateEnd", meetDateEnd);
		List<Meeting> list = session.selectList("MEETING.queryResult", map);
		return list;
	}
	/**
	 * 根据会议状态查询
	 * @param pager
	 * @return
	 */
	public List<Map<String,Object>> queryByState(Pager pager,Integer meetState,Integer departmentId) {		
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("meetState", meetState);
		map.put("departmentId", departmentId);
		List<Map<String,Object>> list  = session.selectList("MEETING.queryByState",map);
		return list;	
	}
	/**
	 * 返回满足条件的总记录数
	 * @return
	 */
	public int getRoomCount(Integer roomId) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roomId", roomId);
		int count = session.selectOne("MEETING.getRoomCount",map);
		return count;
	}
	public int getByRoomIdCount(Integer roomId) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roomId", roomId);
		int count = session.selectOne("MEETING.getByRoomIdCount",map);
		return count;
	}
	
	public int getStateCount(Integer meetState,Integer departmentId) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("meetState", meetState);
		map.put("departmentId", departmentId);
		int count = session.selectOne("MEETING.getStateCount",map);
		return count;
	}
	
	public int getOriginatorCount(Integer originator) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("originator", originator);
		int count = session.selectOne("MEETING.getByOriginatorCount",map);
		return count;
	}
	public int getByEmpIdCount(Integer empId) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("empId", empId);
		int count = session.selectOne("MEETING.getByEmpIdCount",map);
		return count;
	}
	public int getThemeCount(String theme) {
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("theme", theme);
		int count = session.selectOne("MEETING.getByThemeCount",map);
		return count;
	}
	public int getPrimaryKey() {
		SqlSession session = factory.openSession();
		int primaryKey = session.selectOne("MEETING.getPrimaryKey");
		return primaryKey;
	}
}