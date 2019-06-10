package com.icss.oa.system.index;

import java.util.List;
import java.util.Map;

public class EmpIndexResultBean {

	private int recordCount;
	
	private List<Map<String,Object>> list;

	public EmpIndexResultBean(int recordCount, List<Map<String,Object>> recordList) {
		super();
		this.recordCount = recordCount;
		this.list = recordList;
	}

	public EmpIndexResultBean() {
		super();
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<Map<String,Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
}
