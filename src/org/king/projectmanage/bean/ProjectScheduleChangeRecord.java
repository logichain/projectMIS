package org.king.projectmanage.bean;

import java.util.Date;

import org.king.security.domain.Account;

/**
 * ProjectScheduleChangeRecord entity. @author MyEclipse Persistence Tools
 */

public class ProjectScheduleChangeRecord extends
		org.king.framework.domain.BaseObject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pscrId;
	private Integer pscrStageTask;
	private String pscrPoint;
	private String pscrReason;
	private Integer pscrFlag;
	private String pscrChangePerson;
	private Date pscrChangeTime;
	private String pscrCheckPerson;
	private Date pscrCheckTime;
	
	private Account changePerson;
	private Account checkPerson;

	// Constructors

	/** default constructor */
	public ProjectScheduleChangeRecord() {
	}

	/** minimal constructor */
	public ProjectScheduleChangeRecord(Integer pscrStageTask,
			String pscrReason, Integer pscrFlag, String pscrChangePerson,
			Date pscrChangeTime) {
		this.pscrStageTask = pscrStageTask;
		this.pscrReason = pscrReason;
		this.pscrFlag = pscrFlag;
		this.pscrChangePerson = pscrChangePerson;
		this.pscrChangeTime = pscrChangeTime;
	}

	/** full constructor */
	public ProjectScheduleChangeRecord(Integer pscrStageTask, String pscrPoint,
			String pscrReason, Integer pscrFlag, String pscrChangePerson,
			Date pscrChangeTime, String pscrCheckPerson, Date pscrCheckTime) {
		this.pscrStageTask = pscrStageTask;
		this.pscrPoint = pscrPoint;
		this.pscrReason = pscrReason;
		this.pscrFlag = pscrFlag;
		this.pscrChangePerson = pscrChangePerson;
		this.pscrChangeTime = pscrChangeTime;
		this.pscrCheckPerson = pscrCheckPerson;
		this.pscrCheckTime = pscrCheckTime;
	}

	// Property accessors

	public Integer getPscrId() {
		return this.pscrId;
	}

	public void setPscrId(Integer pscrId) {
		this.pscrId = pscrId;
	}

	public Integer getPscrStageTask() {
		return this.pscrStageTask;
	}

	public void setPscrStageTask(Integer pscrStageTask) {
		this.pscrStageTask = pscrStageTask;
	}

	public String getPscrPoint() {
		return this.pscrPoint;
	}

	public void setPscrPoint(String pscrPoint) {
		this.pscrPoint = pscrPoint;
	}

	public String getPscrReason() {
		return this.pscrReason;
	}

	public void setPscrReason(String pscrReason) {
		this.pscrReason = pscrReason;
	}

	public Integer getPscrFlag() {
		return this.pscrFlag;
	}

	public void setPscrFlag(Integer pscrFlag) {
		this.pscrFlag = pscrFlag;
	}

	public String getPscrChangePerson() {
		return this.pscrChangePerson;
	}

	public void setPscrChangePerson(String pscrChangePerson) {
		this.pscrChangePerson = pscrChangePerson;
	}

	public Date getPscrChangeTime() {
		return this.pscrChangeTime;
	}

	public void setPscrChangeTime(Date pscrChangeTime) {
		this.pscrChangeTime = pscrChangeTime;
	}

	public String getPscrCheckPerson() {
		return this.pscrCheckPerson;
	}

	public void setPscrCheckPerson(String pscrCheckPerson) {
		this.pscrCheckPerson = pscrCheckPerson;
	}

	public Date getPscrCheckTime() {
		return this.pscrCheckTime;
	}

	public void setPscrCheckTime(Date pscrCheckTime) {
		this.pscrCheckTime = pscrCheckTime;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof ProjectScheduleChangeRecord)
		{
			ProjectScheduleChangeRecord pscr = (ProjectScheduleChangeRecord) arg0;
			if(pscr.getPscrId().equals(pscrId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.pscrId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.pscrId + this.pscrStageTask + this.pscrPoint;
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