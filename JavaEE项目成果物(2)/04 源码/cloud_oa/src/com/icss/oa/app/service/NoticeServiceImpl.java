package com.icss.oa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.app.dao.AppNoticeDao;
import com.icss.oa.app.pojo.ReturnValue;
import com.icss.oa.common.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private AppNoticeDao appNoticeDao;

	@Override
	public ReturnValue getNoticeList(String pageNum) {
		ReturnValue rv = null;
		int recordCount = appNoticeDao.getNoticeRecord();
		Pager pager = new Pager(recordCount, 15, Integer.parseInt(pageNum));
		if (Integer.parseInt(pageNum) > (pager.getPageCount() - 1)) {
			rv = new ReturnValue(2, "已加载全部数据",
					appNoticeDao.getNoticeList(pager));
		} else {
			rv = new ReturnValue(1, "请求成功", appNoticeDao.getNoticeList(pager));
		}
		return rv;
	}

}
