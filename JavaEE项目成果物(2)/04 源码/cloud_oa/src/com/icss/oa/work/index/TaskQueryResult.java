package com.icss.oa.work.index;

import java.util.List;

import com.icss.oa.system.pojo.Employee;
import com.icss.oa.work.pojo.Task;

/**
 * 封装总记录数和查询结果集合
 * 
 * @author Administrator
 * 
 */
public class TaskQueryResult {

	private int recordCount;// 满足条件的总记录数
	private List<Task> recordList;// 数据集合

	public TaskQueryResult() {
		super();
	}

	public TaskQueryResult(int recordCount, List<Task> recordList) {
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

	public List<Task> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Task> recordList) {
		this.recordList = recordList;
	}

}