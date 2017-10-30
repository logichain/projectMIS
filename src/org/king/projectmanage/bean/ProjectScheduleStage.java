package org.king.projectmanage.bean;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.king.common.service.CommonService;
import org.king.common.service.impl.CommonServiceImpl;
import org.king.security.domain.Account;

/**
 * ProjectScheduleStage entity. @author MyEclipse Persistence Tools
 */

public class ProjectScheduleStage extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pssId;
	private Integer pssTenderProject;
	private String pssName;
	private String pssResponsiblePerson;
	private String pssBeginDate;
	private String pssEndDate;
	private Integer pssWorkPeriod;
	private String pssCreateUser;
	private Date pssCreateTime;
	private Integer pssFlag;
	private String pssDescription;
	
	private Account responsiblePerson;
	
	private ProjectScheduleTask currentScheduleTask;
	
	private ArrayList<ProjectScheduleTask> scheduleTaskList = new ArrayList<ProjectScheduleTask>();
	
	private float beginDatePercent = 0.0f;
	private float endDatePercent = 0.0f;
	private Date displayBeginDate = new Date();
	
	private String taskExpendStatus = "+";
	
	private float schedulePercent = 0.0f;
	
	private boolean modified = false;

	// Constructors

	/** default constructor */
	public ProjectScheduleStage() {				
		this.displayBeginDate = CommonServiceImpl.transformDisplayDate(displayBeginDate);
	}

	/** minimal constructor */
	public ProjectScheduleStage(Integer pssTenderProject, String pssName,
			String pssCreateUser, Date pssCreateTime, Integer pssFlag) {
		this.pssTenderProject = pssTenderProject;
		this.pssName = pssName;
		this.pssCreateUser = pssCreateUser;
		this.pssCreateTime = pssCreateTime;
		this.pssFlag = pssFlag;
	}

	/** full constructor */
	public ProjectScheduleStage(Integer pssTenderProject, String pssName,
			String pssResponsiblePerson, String pssBeginDate, String pssEndDate,
			Integer pssWorkPeriod, String pssCreateUser, Date pssCreateTime,
			Integer pssFlag) {
		this.pssTenderProject = pssTenderProject;
		this.pssName = pssName;
		this.pssResponsiblePerson = pssResponsiblePerson;
		this.pssBeginDate = pssBeginDate;
		this.pssEndDate = pssEndDate;
		this.pssWorkPeriod = pssWorkPeriod;
		this.pssCreateUser = pssCreateUser;
		this.pssCreateTime = pssCreateTime;
		this.pssFlag = pssFlag;
	}

	// Property accessors

	public Integer getPssId() {
		return this.pssId;
	}

	public void setPssId(Integer pssId) {
		this.pssId = pssId;
	}

	public Integer getPssTenderProject() {
		return this.pssTenderProject;
	}

	public void setPssTenderProject(Integer pssTenderProject) {
		this.pssTenderProject = pssTenderProject;
	}

	public String getPssName() {
		return this.pssName;
	}

	public void setPssName(String pssName) {
		this.pssName = pssName;
	}

	public String getPssResponsiblePerson() {
		return this.pssResponsiblePerson;
	}

	public void setPssResponsiblePerson(String pssResponsiblePerson) {
		this.pssResponsiblePerson = pssResponsiblePerson;
	}

	public String getPssBeginDate() {
		return this.pssBeginDate;
	}

	public void setPssBeginDate(String pssBeginDate) {
		this.pssBeginDate = pssBeginDate;
		
		this.calculateBeginDatePercent();
		this.calculateEndDatePercent();				
	}
	
	private void calculateBeginDatePercent()
	{
		if(pssBeginDate != null && !pssBeginDate.isEmpty())
		{
			try {
				Date bd = DateFormat.getDateInstance().parse(pssBeginDate);
				
				this.beginDatePercent = (bd.getTime() - displayBeginDate.getTime()) *1.0f/(365 * CommonService.DAY_MILLISECOND);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	private void calculateEndDatePercent()
	{
		if(pssBeginDate != null && !pssBeginDate.isEmpty() && pssEndDate != null && !pssEndDate.isEmpty())
		{
			try {
				Date bd = DateFormat.getDateInstance().parse(pssBeginDate);
				Date ed = DateFormat.getDateInstance().parse(pssEndDate);
							
				if(bd.getTime() > displayBeginDate.getTime())
				{
					this.endDatePercent = (ed.getTime() - bd.getTime())*1.0f/(365 * CommonService.DAY_MILLISECOND);
				}
				else
				{
					this.endDatePercent = (ed.getTime() - displayBeginDate.getTime())*1.0f/(365 * CommonService.DAY_MILLISECOND);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}			
	}

	public String getPssEndDate() {
		return this.pssEndDate;
	}

	public void setPssEndDate(String pssEndDate) {		
		this.pssEndDate = pssEndDate;
		this.calculateEndDatePercent();	
	}

	public Integer getPssWorkPeriod() {
		return this.pssWorkPeriod;
	}

	public void setPssWorkPeriod(Integer pssWorkPeriod) {
		this.pssWorkPeriod = pssWorkPeriod;
	}

	public String getPssCreateUser() {
		return this.pssCreateUser;
	}

	public void setPssCreateUser(String pssCreateUser) {
		this.pssCreateUser = pssCreateUser;
	}

	public Date getPssCreateTime() {
		return this.pssCreateTime;
	}

	public void setPssCreateTime(Date pssCreateTime) {
		this.pssCreateTime = pssCreateTime;
	}

	public Integer getPssFlag() {
		return this.pssFlag;
	}

	public void setPssFlag(Integer pssFlag) {
		this.pssFlag = pssFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof ProjectScheduleStage)
		{
			ProjectScheduleStage pss = (ProjectScheduleStage) arg0;
			if(pss.getPssId().equals(pssId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.pssId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.pssId + this.pssName;
	}

	public void setScheduleTaskList(ArrayList<ProjectScheduleTask> scheduleTaskList) {
		this.scheduleTaskList = scheduleTaskList;
	}

	public ArrayList<ProjectScheduleTask> getScheduleTaskList() {
		return scheduleTaskList;
	}

	public Set<ProjectScheduleTask> getScheduleTaskSet() {
//		return scheduleTaskSet;
		Set<ProjectScheduleTask> scheduleTaskSet = new HashSet<ProjectScheduleTask>();
		scheduleTaskSet.addAll(scheduleTaskList);
		
		return scheduleTaskSet;
	}
	
	public void setScheduleTaskSet(Set<ProjectScheduleTask> scheduleTaskSet) {
		scheduleTaskList.clear();
		scheduleTaskList.addAll(scheduleTaskSet);
	}

	public void setPssDescription(String pssDescription) {
		this.pssDescription = pssDescription;
	}

	public String getPssDescription() {
		return pssDescription;
	}

	public void setResponsiblePerson(Account responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public Account getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setCurrentScheduleTask(ProjectScheduleTask currentScheduleTask) {
		this.currentScheduleTask = currentScheduleTask;
	}

	public ProjectScheduleTask getCurrentScheduleTask() {
		return currentScheduleTask;
	}

	public void setBeginDatePercent(float beginDatePercent) {
		this.beginDatePercent = beginDatePercent;
	}

	public float getBeginDatePercent() {	
		if(beginDatePercent > 1)
		{
			return 100.0f;
		}
		 
		return beginDatePercent *100;		
	}
	
	public String getBeginDatePercentStr() {				 
		return NumberFormat.getPercentInstance().format(this.getBeginDatePercent());		
	}

	public void setEndDatePercent(float endDatePercent) {
		this.endDatePercent = endDatePercent;
	}

	public float getEndDatePercent() {		
		if(beginDatePercent > 1)
		{
			return 0.0f;
		}
		else if(beginDatePercent + endDatePercent > 1)
		{
			return (1- beginDatePercent) *100;
		}
		
		return endDatePercent *100;	
	}
	
	public String getEndDatePercentStr() {		
		return NumberFormat.getPercentInstance().format(this.getEndDatePercent());
	}

	public void setDisplayBeginDate(Date displayBeginDate) {
		this.displayBeginDate = displayBeginDate;
		
		this.calculateBeginDatePercent();
		this.calculateEndDatePercent();		
	}

	public Date getDisplayBeginDate() {
		return displayBeginDate;
	}

	public void setTaskExpendStatus(String taskExpendStatus) {
		this.taskExpendStatus = taskExpendStatus;
	}

	public String getTaskExpendStatus() {
		return taskExpendStatus;
	}

	public void setSchedulePercent(float schedulePercent) {
		this.schedulePercent = schedulePercent;
	}

	public float getSchedulePercent() {
		return schedulePercent *100;
	}
	
	public String getSchedulePercentStr() {
		return NumberFormat.getPercentInstance().format(schedulePercent);
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public boolean isModified() {
		return modified;
	}
	

}