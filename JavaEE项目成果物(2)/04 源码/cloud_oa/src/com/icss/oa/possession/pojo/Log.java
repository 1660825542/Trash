package com.icss.oa.possession.pojo;

import java.util.Date;

public class Log {
private Integer logId;
private Integer empId;
private Date  borDate;
private Integer borNum;
private String borDes;
private Integer possId;
private String empName;
private String possCate;
private String possName;
private String possUse;
private String possUnuse;

public Log(Integer empId, Date borDate, Integer borNum, String borDes,
		Integer possId, String empName, String possCate,
		String possName) {
	this.empId = empId;
	this.borDate = borDate;
	this.borNum = borNum;
	this.borDes = borDes;
	this.possId = possId;
	this.empName = empName;
	this.possCate = possCate;
	this.possName = possName;
	
}

public Log() {
	super();
}

public String getPossName() {
	return possName;
}
public void setPossName(String possName) {
	this.possName = possName;
}
public String getEmpName() {
	return empName;
}
public void setEmpName(String empName) {
	this.empName = empName;
}
public String getPossCate() {
	return possCate;
}
public void setPossCate(String possCate) {
	this.possCate = possCate;
}
public Integer getLogId() {
	return logId;
}
public void setLogId(Integer logId) {
	this.logId = logId;
}
public Integer getEmpId() {
	return empId;
}
public void setEmpId(Integer empId) {
	this.empId = empId;
}
public Date getBorDate() {
	return borDate;
}
public void setBorDate(Date borDate) {
	this.borDate = borDate;
}
public Integer getBorNum() {
	return borNum;
}
public void setBorNum(Integer borNum) {
	this.borNum = borNum;
}
public String getBorDes() {
	return borDes;
}
public void setBorDes(String borDes) {
	this.borDes = borDes;
}
public Integer getPossId() {
	return possId;
}
public void setPossId(Integer possId) {
	this.possId = possId;
}

public String getPossUse() {
	return possUse;
}

public void setPossUse(String possUse) {
	this.possUse = possUse;
}

public String getPossUnuse() {
	return possUnuse;
}

public void setPossUnuse(String possUnuse) {
	this.possUnuse = possUnuse;
}

@Override
public String toString() {
	return "Log [ empId=" + empId + ", borDate=" + borDate
			+ ", borNum=" + borNum + ", borDes=" + borDes + ", possId="
			+ possId + ", empName=" + empName + ", possCate=" + possCate
			+ ", possName=" + possName + "]";
}


}
