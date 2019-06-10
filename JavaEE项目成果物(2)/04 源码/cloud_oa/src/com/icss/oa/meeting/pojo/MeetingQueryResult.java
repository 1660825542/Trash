package com.icss.oa.meeting.pojo;

import java.util.List;

import com.icss.oa.meeting.pojo.Meeting;


/**
 * 封装通过索引查询的会议数据
 * 
 * @author Administrator
 * 
 */
public class MeetingQueryResult {

	private int recordCount;

	private List<Meeting> recordlist;

	public MeetingQueryResult() {
		super();
	}

	public MeetingQueryResult(int recordCount, List<Meeting> recordlist) {
		super();
		this.recordCount = recordCount;
		this.recordlist = recordlist;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<Meeting> getrecordList() {
		return recordlist;
	}

	public void setrecordList(List<Meeting> recordlist) {
		this.recordlist = recordlist;
	}

	
}
