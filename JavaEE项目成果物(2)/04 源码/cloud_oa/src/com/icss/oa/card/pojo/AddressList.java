package com.icss.oa.card.pojo;



public class AddressList {

    private Integer empId;
    
    private Integer deptId;
    
    private String deptName;

    private String empName;

    private String empPhone;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName == null ? null : deptName.trim();
	}

	public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public AddressList() {
		super();
	}

	public AddressList(Integer deptId, String deptName, String empName,
			String empPhone) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.empName = empName;
		this.empPhone = empPhone;
	}

	public AddressList(Integer empId, Integer deptId, String deptName,
			String empName, String empPhone) {
		super();
		this.empId = empId;
		this.deptId = deptId;
		this.deptName = deptName;
		this.empName = empName;
		this.empPhone = empPhone;
	}

	public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone == null ? null : empPhone.trim();
    }
}