package com.icss.oa.system.index;

import java.util.List;

import com.icss.oa.system.pojo.Position;

public class PosIndexResultBean {
	
	private int recordCount;
	
	private List<Position> list;

	public PosIndexResultBean(int recordCount, List<Position> list) {
		super();
		this.recordCount = recordCount;
		this.list = list;
	}

	public PosIndexResultBean() {
		super();
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<Position> getList() {
		return list;
	}

	public void setList(List<Position> list) {
		this.list = list;
	}

}
