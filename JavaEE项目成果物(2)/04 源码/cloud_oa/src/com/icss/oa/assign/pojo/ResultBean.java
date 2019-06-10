package com.icss.oa.assign.pojo;

import java.util.List;


/**
 * ��װͨ�������ѯ��ѧ�����
 * 
 * @author Administrator
 * 
 */
public class ResultBean {

	private int recordCount;

	private List<AssignEmp> list;

	public ResultBean() {
		super();
	}

	public ResultBean(int recordCount, List<AssignEmp> list) {
		super();
		this.recordCount = recordCount;
		this.list = list;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<AssignEmp> getList() {
		return list;
	}

	public void setList(List<AssignEmp> list) {
		this.list = list;
	}

}
