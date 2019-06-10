package com.icss.oa.message.index;

import java.util.List;

import com.icss.oa.message.pojo.Message;

public class MessageQueryResult {

	private int recordCount;// 满足条件的总记录数
	
	private List<Message> recordList;

	public MessageQueryResult() {
		super();
	}

	public MessageQueryResult(int recordCount, List<Message> recordList) {
		super();
		this.recordCount = recordCount;
		this.recordList = recordList;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<Message> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Message> recordList) {
		this.recordList = recordList;
	}
}
