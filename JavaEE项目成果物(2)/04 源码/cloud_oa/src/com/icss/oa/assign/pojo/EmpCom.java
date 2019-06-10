package com.icss.oa.assign.pojo;

public class EmpCom {

	
    private Integer empComId;

    private Integer assComId;

    private Integer assId;

    private String assName;
    
    private String comName;
    
    private String available;
    
    private String skill;
	
	//变量的get set方法

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Integer getEmpComId() {
		return empComId;
	}

	public void setEmpComId(Integer empComId) {
		this.empComId = empComId;
	}

	public Integer getAssComId() {
		return assComId;
	}

	public void setAssComId(Integer assComId) {
		this.assComId = assComId;
	}

	public Integer getAssId() {
		return assId;
	}

	public void setAssId(Integer assId) {
		this.assId = assId;
	}

	public String getAssName() {
		return assName;
	}

	public void setAssName(String assName) {
		this.assName = assName;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}
	
	//构造方法
	public EmpCom(Integer empComId, Integer assComId, Integer assId,
			String assName, String comName, String available) {
		super();
		this.empComId = empComId;
		this.assComId = assComId;
		this.assId = assId;
		this.assName = assName;
		this.comName = comName;
		this.available = available;
	}

	public EmpCom(Integer assComId, Integer assId, String assName,
			String comName, String available) {
		super();
		this.assComId = assComId;
		this.assId = assId;
		this.assName = assName;
		this.comName = comName;
		this.available = available;
	}

	public EmpCom(Integer empComId, Integer assComId, Integer assId) {
		super();
		this.empComId = empComId;
		this.assComId = assComId;
		this.assId = assId;
	}

	public EmpCom(Integer assComId, Integer assId) {
		super();
		this.assComId = assComId;
		this.assId = assId;
	}

	public EmpCom(Integer assComId, Integer assId, String assName,
			String comName, String available, String skill) {
		super();
		this.assComId = assComId;
		this.assId = assId;
		this.assName = assName;
		this.comName = comName;
		this.available = available;
		this.skill = skill;
	}

	public EmpCom(Integer empComId, Integer assComId, Integer assId,
			String assName, String comName, String available, String skill) {
		super();
		this.empComId = empComId;
		this.assComId = assComId;
		this.assId = assId;
		this.assName = assName;
		this.comName = comName;
		this.available = available;
		this.skill = skill;
	}

	public EmpCom() {
		super();
	}

	@Override
	public String toString() {
		return "EmpCom [empComId=" + empComId + ", assComId=" + assComId
				+ ", assId=" + assId + ", assName=" + assName + ", comName="
				+ comName + ", available=" + available + ", skill=" + skill
				+ "]";
	}
	
	
}