package com.icss.oa.meeting.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.emp_meeting.dao.EmpMeetingDao;
import com.icss.oa.meeting.dao.MeetingDao;
import com.icss.oa.meeting.index.MeetingIndexDao;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingQueryResult;
import com.icss.oa.message.pojo.Message;
import com.icss.oa.system.dao.EmployeeDao;
import com.icss.oa.system.pojo.Employee;




@Service
@Transactional(rollbackFor = Exception.class)
public class MeetingService {

	
	@Autowired
	private MeetingDao dao;
	@Autowired
	private EmployeeDao dao1;
	@Autowired
	private EmpMeetingDao dao2;
	
	private MeetingIndexDao indexDao;

	
	
	public int insert(Meeting meeting) throws IOException {

		dao.insert(meeting);
		
/*		// 创建索引
		Document document = new Document();
		document.add(new TextField("meetId", String.valueOf(dao.getPrimaryKey()),Store.YES));
		document.add(new TextField("originator", String.valueOf(meeting.getOriginator()),Store.YES));
		document.add(new TextField("roomId", String.valueOf(meeting.getRoomId()),Store.YES));
		document.add(new TextField("theme", meeting.getTheme(),Store.YES));


		indexDao.create(document);		*/
		return dao.getPrimaryKey();
	}

	public void update(Meeting meeting) throws IOException {
		dao.update(meeting);

/*		// 修改索引
		Document document = new Document();

		Term term = new Term("meetId", String.valueOf(meeting.getMeetId()));
		

		document.add(new TextField("meetId", String.valueOf(meeting.getMeetId()),Store.YES));
		document.add(new TextField("originator", String.valueOf(meeting.getOriginator()),Store.YES));
		document.add(new TextField("roomId", String.valueOf(meeting.getRoomId()),Store.YES));
		document.add(new TextField("theme", meeting.getTheme(),Store.YES));

		indexDao.update(term, document);*/
	}

	public void deleteByState(Integer meetState) throws IOException {
		dao.deleteByState(meetState);
	}
	
	public void deleteById(Integer meetId) throws IOException {
		dao.deleteById(meetId);

/*		// 删除索引
		Term term = new Term("meetId", String.valueOf(meetId));
	
		indexDao.delete(term);*/
	}
	
	/**
	 * 全文检索
	 * @param queryStr
	 * @return
	 * @throws Exception 
	 */
	public MeetingQueryResult queryByIndex(String queryStr,Pager pager)throws Exception{
		// 中文分词器
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);
		
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_47,
				new String[] { "originator", "roomId", "theme" }, analyzer);
		
		Query query = queryParser.parse(queryStr);

		MeetingQueryResult queryResult = indexDao.search(query, pager.getStart(), pager.getPageSize());
		
		return queryResult;
	}
	
	public Meeting queryById(Integer meetId){
		
		return dao.queryById(meetId);
	}
	
	public List<Employee> queryEmpByDept(Pager pager,Integer deptId){
		
		return  dao1.queryByDeptByPager(pager,deptId);
	}
	public int getEmpCountByDept(Integer deptId){
		return dao1.getEmpCountByDept(deptId);
	}
	public int getCount(){
		
		return dao.getCount();
	}
	
	public List<Map<String,Object>> query(Pager pager){
		return dao.query(pager);
	}
	
	public List<Meeting> queryResult(Integer roomId,Timestamp meetDateBegin,Timestamp meetDateEnd){
		return dao.queryResult(roomId,meetDateBegin, meetDateEnd);
	}
	public List<Map<String,Object>> queryByState(Pager pager,Integer meetState,Integer departmentId){
		return dao.queryByState(pager,meetState,departmentId);
	}
	
	public List<Map<String,Integer>> queryByRoomId(Pager pager,Integer roomId){
		
		return dao.queryByRoomId(pager,roomId);
	}
	
	public List<Map<String,Object>> queryByEmpId(Pager pager,Integer empId){
		
		return dao.queryByEmpId(pager, empId);
	}
	
	public int getByEmpIdCount(Integer empId){
		return dao.getByEmpIdCount(empId);
	}
	
	public int getByRoomIdCount(Integer roomId){
		
		return dao.getByRoomIdCount(roomId);
	}
	
	public List<Map<String,Object>> queryByOriginator(Pager pager,Integer originator){
		
		return dao.queryByOriginator(pager, originator);
	}
	
	public List<Map<String,Object>> queryByTheme(Pager pager,String theme){
		
		return dao.queryByTheme(pager, theme);
	}
	
	public int getRoomCount(Integer roomId){
		
		return dao.getRoomCount(roomId);
	}
	
	public int getOriginatorCount(Integer originator){

		return dao.getOriginatorCount(originator);
	}
	public int getThemeCount(String theme){

		return dao.getThemeCount(theme);
	}
	public int getStateCount(Integer meetState,Integer departmentId){
		return dao.getStateCount(meetState,departmentId);
	}
	public int getPrimaryKey(){
		return dao.getPrimaryKey();
	}
	public List<Integer> getEmpId(Integer meetId){
		List<Integer> list=dao2.queryByMeetId(meetId);

		return list;
	}
}
