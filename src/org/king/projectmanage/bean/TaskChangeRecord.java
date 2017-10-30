package org.king.projectmanage.bean;

import java.util.Date;

import org.king.security.domain.Account;

/**
 * TaskChangeRecord entity. @author MyEclipse Persistence Tools
 */

public class TaskChangeRecord extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tcrId;
	private Integer tcrTask;
	private String tcrPoint;
	private String tcrReason;
	private Integer tcrFlag;
	private String tcrChangePerson;
	private Date tcrChangeTime;
	private String tcrCheckPerson;
	private Date tcrCheckTime;
	
	private Account changePerson;
	private Account checkPerson;

	// Constructors

	/** default constructor */
	public TaskChangeRecord() {
	}

	/** minimal constructor */
	public TaskChangeRecord(Integer tcrTask, String tcrReason, Integer tcrFlag,
			String tcrChangePerson, Date tcrChangeTime) {
		this.tcrTask = tcrTask;
		this.tcrReason = tcrReason;
		this.tcrFlag = tcrFlag;
		this.tcrChangePerson = tcrChangePerson;
		this.tcrChangeTime = tcrChangeTime;
	}

	/** full constructor */
	public TaskChangeRecord(Integer tcrTask, String tcrPoint, String tcrReason,
			Integer tcrFlag, String tcrChangePerson, Date tcrChangeTime,
			String tcrCheckPerson, Date tcrCheckTime) {
		this.tcrTask = tcrTask;
		this.tcrPoint = tcrPoint;
		this.tcrReason = tcrReason;
		this.tcrFlag = tcrFlag;
		this.tcrChangePerson = tcrChangePerson;
		this.tcrChangeTime = tcrChangeTime;
		this.tcrCheckPerson = tcrCheckPerson;
		this.tcrCheckTime = tcrCheckTime;
	}

	// Property accessors

	public Integer getTcrId() {
		return this.tcrId;
	}

	public void setTcrId(Integer tcrId) {
		this.tcrId = tcrId;
	}

	public Integer getTcrTask() {
		return this.tcrTask;
	}

	public void setTcrTask(Integer tcrTask) {
		this.tcrTask = tcrTask;
	}

	public String getTcrPoint() {
		return this.tcrPoint;
	}

	public void setTcrPoint(String tcrPoint) {
		this.tcrPoint = tcrPoint;
	}

	public String getTcrReason() {
		return this.tcrReason;
	}

	public void setTcrReason(String tcrReason) {
		this.tcrReason = tcrReason;
	}

	public Integer getTcrFlag() {
		return this.tcrFlag;
	}

	public void setTcrFlag(Integer tcrFlag) {
		this.tcrFlag = tcrFlag;
	}

	public String getTcrChangePerson() {
		return this.tcrChangePerson;
	}

	public void setTcrChangePerson(String tcrChangePerson) {
		this.tcrChangePerson = tcrChangePerson;
	}

	public Date getTcrChangeTime() {
		return this.tcrChangeTime;
	}

	public void setTcrChangeTime(Date tcrChangeTime) {
		this.tcrChangeTime = tcrChangeTime;
	}

	public String getTcrCheckPerson() {
		return this.tcrCheckPerson;
	}

	public void setTcrCheckPerson(String tcrCheckPerson) {
		this.tcrCheckPerson = tcrCheckPerson;
	}

	public Date getTcrCheckTime() {
		return this.tcrCheckTime;
	}

	public void setTcrCheckTime(Date tcrCheckTime) {
		this.tcrCheckTime = tcrCheckTime;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof TaskChangeRecord)
		{
			TaskChangeRecord tcr = (TaskChangeRecord) arg0;
			if(tcr.getTcrId().equals(tcrId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.tcrId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.tcrId + this.tcrPoint;
	}

	public void setChangePerson(Account changePerson) {
		this.changePerson = changePerson;
	}

	public Account getChangePerson() {
		return changePerson;
	}

	public void setCheckPerson(Account checkPerson) {
		this.checkPerson = checkPerson;
	}

	public Account getCheckPerson() {
		return checkPerson;
	}

}