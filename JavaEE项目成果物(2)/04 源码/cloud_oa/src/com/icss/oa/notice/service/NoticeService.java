package com.icss.oa.notice.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.notice.dao.NoticeDao;
import com.icss.oa.notice.pojo.Notice;

@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeService {

	@Autowired
	private NoticeDao dao;

	@Transactional(readOnly = true)
	public List<Notice> query() {
		return dao.query();
	}
	
	public List<Notice> queryNotTop() {
		return dao.queryNotTop();
	}
	
	public List<Notice> queryTop() {
		return dao.queryTop();
	}
	
	public Notice queryById(Integer noticeId) throws IOException {	
		return dao.queryById(noticeId);
	}

	public void insert(Notice ntc) throws IOException {	
		dao.insert(ntc);
	}
	
	public void delete(Integer noticeId) throws IOException {
		dao.delete(noticeId);
	}
	
	public void update(Notice ntc) throws IOException {
		dao.update(ntc);
	}
	
	@Transactional(readOnly = true)
	public int getCount() {
		return dao.getCount();
	}
	
	@Transactional(readOnly = true)
	public List<Notice> queryByPager(Pager pager) {

		int start = pager.getStart();
		int end = pager.getPageNum() * pager.getPageSize();

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);

		return dao.queryByPager(map);
	}

}