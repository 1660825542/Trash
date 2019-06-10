package com.icss.oa.work.index;

import java.util.List;

import com.icss.oa.system.pojo.Employee;
import com.icss.oa.work.pojo.Task;

/**
 * ��װ�ܼ�¼���Ͳ�ѯ�������
 * 
 * @author Administrator
 * 
 */
public class TaskQueryResult {

	private int recordCount;// �����������ܼ�¼��
	private List<Task> recordList;// ���ݼ���

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