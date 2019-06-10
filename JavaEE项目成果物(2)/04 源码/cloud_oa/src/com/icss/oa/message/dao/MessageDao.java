package com.icss.oa.message.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.oa.common.Pager;
import com.icss.oa.message.pojo.Message;

@Repository
public class MessageDao {

	@Autowired
	private SqlSessionFactory factory ;
	
	public long insertMessage(Message message) {		
		SqlSession session = factory.openSession();
		session.insert("MESSAGE.insert",message);
		return session.selectOne("MESSAGE.getPrimaryKey");	
	}
	
	public void insertSendedMessage(Map<String,Object> map){
		SqlSession session = factory.openSession();
		session.insert("MESSAGE.insertSendedMessage",map);
	}
	
	public void insertReceivedMessage(Map<String,Object> map){
		SqlSession session = factory.openSession();
		session.insert("MESSAGE.insertReceivedMessage",map);
	}
	
	public void update(Message message) {		
		SqlSession session = factory.openSession();
		session.update("MESSAGE.update",message);		
	}
	
	public void updateSendStatus(long messageId) {		
		SqlSession session = factory.openSession();
		session.update("MESSAGE.updateSendStatus",messageId);		
	}
	
	public void updateReadStatus(long messageId) {		
		SqlSession session = factory.openSession();
		session.update("MESSAGE.updateReadStatus",messageId);		
	}
	
	public void updateSendDate(long messageId) {		
		SqlSession session = factory.openSession();
		session.update("MESSAGE.updateSendDate",messageId);		
	}
	
	public int getSendedCount(Integer id) {
		SqlSession session = factory.openSession();
		int count = session.selectOne("MESSAGE.getSendedCount",id);
		return count;
	}
	
	public List<Message> querySendedMessage(Pager pager,Integer id) {
		SqlSession session = factory.openSession();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("id", id);
		List<Message> list = session.selectList("MESSAGE.querySendedMessage", map);
		return list;
	}
	
	public int getReceivedCount(Integer id) {
		SqlSession session = factory.openSession();
		int count = session.selectOne("MESSAGE.getReceivedCount",id);
		return count;
	}

	public List<Message> queryReceivedMessage(Pager pager,Integer id) {
		SqlSession session = factory.openSession();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("id", id);
		List<Message> list = session.selectList("MESSAGE.queryReceivedMessage", map);
		return list;
	}
	
	public int getNotSendCount(Integer id) {
		SqlSession session = factory.openSession();
		int count = session.selectOne("MESSAGE.getNotSendCount",id);
		return count;
	}
	
	public List<Message> queryNotSendMessage(Pager pager,Integer id) {
		SqlSession session = factory.openSession();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("id", id);
		List<Message> list = session.selectList("MESSAGE.queryNotSendMessage", map);
		return list;
	}
	
	public int getNotReadCount(Integer id) {
		SqlSession session = factory.openSession();
		int count = session.selectOne("MESSAGE.getNotReadCount",id);
		return count;
	}
	
	public List<Message> queryNotReadMessage(Pager pager,Integer id) {
		SqlSession session = factory.openSession();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", pager.getStart());
		map.put("end", pager.getStart() + pager.getPageSize() - 1);
		map.put("id", id);
		List<Message> list = session.selectList("MESSAGE.queryNotReadMessage", map);
		return list;
	}
	
	public void deleteMessage(long messageId){
		SqlSession session = factory.openSession();
		session.delete("MESSAGE.deleteMessage",messageId);
		
	}
	
	public void deleteSendedMessage(Map<String,Object> map) {
		SqlSession session = factory.openSession();
		session.delete("MESSAGE.deleteSendedMessage", map);
	}
	
	public void deleteReceivedMessage(Map<String,Object> map) {
		SqlSession session = factory.openSession();
		session.delete("MESSAGE.deleteReceivedMessage", map);
	}
	
	//ssi中可以用来插入索引
//	public int getPrimaryKey() {
//		SqlSession session = factory.openSession();
//		int primaryKey = session.selectOne("MESSAGE.getPrimaryKey");
//		return primaryKey;
//	}
	public Message queryById(long id){
		SqlSession session = factory.openSession();
		return session.selectOne("MESSAGE.queryById", id);
	}
	
	public List<Message> queryAll(){
		SqlSession session = factory.openSession();
		List<Message> list = session.selectList("MESSAGE.queryAll");
		return list;
	}
}
