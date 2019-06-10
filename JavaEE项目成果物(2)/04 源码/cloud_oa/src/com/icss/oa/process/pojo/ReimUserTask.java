package com.icss.oa.process.pojo;

import java.util.Date;

public class ReimUserTask {
	private String taskid;// 任务id

	private String procinsid;// 流程实例id

	private String taskname;// 任务名称

	private String description;// 任务描述

	private String assignee;// 任务代理人

	private String leadername;//部门领导名字
	
	private Integer recordid;// 报销记录id

	private String username;// 任务提交人
	
	private Integer userid;// 任务提交人id

	private String reason;// 报销用途

	private Date createtime;// 提交日期

	private Integer status;// 当前状态

	private Integer endstatus;// 最终状态

	private String opinion;// 审批意见

	private Integer action;// 当前动作

	public ReimUserTask() {
		super();
	}

	public ReimUserTask(String taskid, String procinsid, String taskname,
			String description, String assignee, String assigneename,
			Integer recordid, String username, Integer userid, String reason,
			Date createtime, Integer status, Integer endstatus, String opinion,
			Integer action) {
		this.taskid = taskid;
		this.procinsid = procinsid;
		this.taskname = taskname;
		this.description = description;
		this.assignee = assignee;
		this.leadername = assigneename;
		this.recordid = recordid;
		this.username = username;
		this.userid = userid;
		this.reason = reason;
		this.createtime = createtime;
		this.status = status;
		this.endstatus = endstatus;
		this.opinion = opinion;
		this.action = action;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Integer getRecordid() {
		return recordid;
	}

	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getProcinsid() {
		return procinsid;
	}

	public void setProcinsid(String procinsid) {
		this.procinsid = procinsid;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getLeadername() {
		return leadername;
	}

	public void setLeadername(String leadername) {
		this.leadername = leadername;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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

	@Override
	public String toString() {
		return "LeaveUserTask [taskid=" + taskid + ", procinsid=" + procinsid
				+ ", taskname=" + taskname + ", description=" + description
				+ ", assignee=" + assignee + ", assigneename=" + leadername
				+ ", recordid=" + recordid + ", username=" + username
				+ ", userid=" + userid + ", reason=" + reason + ", createtime="
				+ createtime + ", status=" + status + ", endstatus="
				+ endstatus + ", opinion=" + opinion + ", action=" + action
				+ "]";
	}

}
