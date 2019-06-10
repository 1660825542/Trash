package com.icss.oa.process.pojo;

import java.util.Date;

public class Leave {
	private Integer leaveId;
	private Date time;
	private Date startTime;
	private Date endTime;
	private Integer empId;
	private String reason;
	private Integer leaderId;
	private Integer status;
	private Integer endstatus;
	private String empName;
	private String leaderName;

	public Leave() {
	}

	public Leave(Integer leaveId, Date time, Date startTime, Date endTime,
			Integer empId, String reason, Integer leaderId, Integer status,
			Integer endstatus, String empName, String leaderName) {
		this.leaveId = leaveId;
		this.time = time;
		this.startTime = startTime;
		this.endTime = endTime;
		this.empId = empId;
		this.reason = reason;
		this.leaderId = leaderId;
		this.status = status;
		this.endstatus = endstatus;
		this.empName = empName;
		this.leaderName = leaderName;
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason == null ? null : reason.trim();
	}

	public Integer getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(Integer leaderId) {
		this.leaderId = leaderId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getEndstatus() {
		return endstatus;
	}

	public void setEndstatus(Integer endstatus) {
		this.endstatus = endstatus;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName == null ? null : empName.trim();
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName == null ? null : leaderName.trim();
	}

	@Override
	public String toString() {
		return "Leave [leaveId=" + leaveId + ", time=" + time + ", startTime="
				+ startTime + ", endTime=" + endTime + ", empId=" + empId
				+ ", reason=" + reason + ", leaderId=" + leaderId + ", status="
				+ status + ", endstatus=" + endstatus + ", empName=" + empName
				+ ", leaderName=" + leaderName + "]";
	}
}