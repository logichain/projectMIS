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
 * ProjectScheduleTask entity. @author MyEclipse Persistence Tools
 */

public class ProjectScheduleTask extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pstId;
	private Integer pstProjectScheduleStage;
	private Integer pstPreTask;
	private String pstName;
	private String pstResponsiblePerson;
	private String pstDescription;
	private String pstBeginDate;
	private String pstEndDate;
	private Integer pstWorkPeriod;
	private String pstCreateUser;
	private Date pstCreateTime;
	private Integer pstFinishPercentry;
	private String pstFinishDescription;
	private Date pstFinishChangeTime;
	private Integer pstFlag;
	
	private ProjectScheduleTask preTask;
	
	private Account responsiblePerson;
	
	private ArrayList<TaskChangeRecord> changeRecordList = new ArrayList<TaskChangeRecord>();
	private ArrayList<TaskImplementRecord> implementRecordList = new ArrayList<TaskImplementRecord>();
	
	private float beginDatePercent = 0.0f;
	private float endDatePercent = 0.0f;
	private Date displayBeginDate = new Date();
	
	private TaskImplementRecord currentImplementRecord;
	
	private String recordExpendStatus = "+";
	
	private boolean modified = false;
	
	// Constructors

	/** default constructor */
	public ProjectScheduleTask() {				
		this.displayBeginDate = CommonServiceImpl.transformDisplayDate(displayBeginDate);
	}

	/** full constructor */
	public ProjectScheduleTask(Integer pstProjectScheduleStage,
			Integer pstPreTask, String pstName, String pstResponsiblePerson,
			String pstDescription, String pstBeginDate, String pstEndDate,
			Integer pstWorkPeriod, String pstCreateUser, Date pstCreateTime,
			Integer pstFinishPercentry, String pstFinishDescription,
			Date pstFinishChangeTime, Integer pstFlag) {
		this.pstProjectScheduleStage = pstProjectScheduleStage;
		this.pstPreTask = pstPreTask;
		this.pstName = pstName;
		this.pstResponsiblePerson = pstResponsiblePerson;
		this.pstDescription = pstDescription;
		this.pstBeginDate = pstBeginDate;
		this.pstEndDate = pstEndDate;
		this.pstWorkPeriod = pstWorkPeriod;
		this.pstCreateUser = pstCreateUser;
		this.pstCreateTime = pstCreateTime;
		this.pstFinishPercentry = pstFinishPercentry;
		this.pstFinishDescription = pstFinishDescription;
		this.pstFinishChangeTime = pstFinishChangeTime;
		this.pstFlag = pstFlag;
	}

	// Property accessors

	public Integer getPstId() {
		return this.pstId;
	}

	public void setPstId(Integer pstId) {
		this.pstId = pstId;
	}

	public Integer getPstProjectScheduleStage() {
		return this.pstProjectScheduleStage;
	}

	public void setPstProjectScheduleStage(Integer pstProjectScheduleStage) {
		this.pstProjectScheduleStage = pstProjectScheduleStage;
	}

	public Integer getPstPreTask() {
		if(pstPreTask!= null && pstPreTask == 0)
		{
			return null;
		}
		return this.pstPreTask;
	}

	public void setPstPreTask(Integer pstPreTask) {
		this.pstPreTask = pstPreTask;
	}

	public String getPstName() {
		return this.pstName;
	}

	public void setPstName(String pstName) {
		this.pstName = pstName;
	}

	public String getPstResponsiblePerson() {
		return this.pstResponsiblePerson;
	}

	public void setPstResponsiblePerson(String pstResponsiblePerson) {
		this.pstResponsiblePerson = pstResponsiblePerson;
	}

	public String getPstDescription() {
		return this.pstDescription;
	}

	public void setPstDescription(String pstDescription) {
		this.pstDescription = pstDescription;
	}

	public String getPstBeginDate() {
		return this.pstBeginDate;
	}

	public void setPstBeginDate(String pstBeginDate) {
		this.pstBeginDate = pstBeginDate;
		this.calculateBeginDatePercent();
		this.calculateEndDatePercent();		
	}
	
	private void calculateBeginDatePercent()
	{
		if(pstBeginDate != null && !pstBeginDate.isEmpty())
		{
			try {
				Date bd = DateFormat.getDateInstance().parse(pstBeginDate);
				
				this.beginDatePercent = (bd.getTime() - this.displayBeginDate.getTime()) *1.0f/(365 * CommonService.DAY_MILLISECOND);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}				
	}
	
	private void calculateEndDatePercent()
	{
		if(pstBeginDate != null && !pstBeginDate.isEmpty() && pstEndDate != null && !pstEndDate.isEmpty())
		{
			try {
				Date bd = DateFormat.getDateInstance().parse(pstBeginDate);
				Date ed = DateFormat.getDateInstance().parse(pstEndDate);
				
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

	public String getBeginDatePercentStr() {				 
		return NumberFormat.getPercentInstance().format(this.getBeginDatePercent());		
	}
	
	public String getPstEndDate() {
		return this.pstEndDate;
	}

	public void setPstEndDate(String pstEndDate) {
		this.pstEndDate = pstEndDate;
		this.calculateEndDatePercent();				
	}

	public Integer getPstWorkPeriod() {
		return this.pstWorkPeriod;
	}

	public void setPstWorkPeriod(Integer pstWorkPeriod) {
		this.pstWorkPeriod = pstWorkPeriod;
	}

	public String getPstCreateUser() {
		return this.pstCreateUser;
	}

	public void setPstCreateUser(String pstCreateUser) {
		this.pstCreateUser = pstCreateUser;
	}

	public Date getPstCreateTime() {
		return this.pstCreateTime;
	}

	public void setPstCreateTime(Date pstCreateTime) {
		this.pstCreateTime = pstCreateTime;
	}

	public Integer getPstFinishPercentry() {
		return this.pstFinishPercentry;
	}
	
	public String getPstFinishPercentryStr() {
		if(pstFinishPercentry == null)
		{
			return "";
		}
		return NumberFormat.getPercentInstance().format(pstFinishPercentry/100.0f);
	}

	public void setPstFinishPercentry(Integer pstFinishPercentry) {
		this.pstFinishPercentry = pstFinishPercentry;
	}

	public String getPstFinishDescription() {
		return this.pstFinishDescription;
	}

	public void setPstFinishDescription(String pstFinishDescription) {
		this.pstFinishDescription = pstFinishDescription;
	}

	public Date getPstFinishChangeTime() {
		return this.pstFinishChangeTime;
	}

	public void setPstFinishChangeTime(Date pstFinishChangeTime) {
		this.pstFinishChangeTime = pstFinishChangeTime;
	}

	public Integer getPstFlag() {
		return this.pstFlag;
	}

	public void setPstFlag(Integer pstFlag) {
		this.pstFlag = pstFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof ProjectScheduleTask)
		{
			ProjectScheduleTask pst = (ProjectScheduleTask) arg0;
			if(pst.getPstId().equals(pstId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.pstId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.pstId + this.pstName;
	}

	public Set<TaskChangeRecord> getChangeRecordSet() {
//		return changeRecordSet;
		Set<TaskChangeRecord> changeRecordSet = new HashSet<TaskChangeRecord>();
		changeRecordSet.addAll(changeRecordList);
		
		return changeRecordSet;
	}
		
	public void setChangeRecordSet(Set<TaskChangeRecord> changeRecordSet) {
		changeRecordList.clear();
		changeRecordList.addAll(changeRecordSet);
	}

	public void setChangeRecordList(ArrayList<TaskChangeRecord> changeRecordList) {
		this.changeRecordList = changeRecordList;
	}

	public ArrayList<TaskChangeRecord> getChangeRecordList() {
		return changeRecordList;
	}

	public void setPreTask(ProjectScheduleTask preTask) {
		this.preTask = preTask;
	}

	public ProjectScheduleTask getPreTask() {
		return preTask;
	}

	public void setResponsiblePerson(Account responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public Account getResponsiblePerson() {
		return responsiblePerson;
	}
	
	public void setBeginDatePercent(float beginDatePercent) {
		this.beginDatePercent = beginDatePercent;
	}

	public float getBeginDatePercent() {				 
		if(beginDatePercent > 1)
		{
			return 100.0f;
		}
		return ((int)(beginDatePercent *10000))*1.0f/100;		
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
			return ((int)((1- beginDatePercent) *10000))*1.0f/100;
		}
		 
		return ((int)(endDatePercent *10000))*1.0f/100;	
	}

	public String getEndDatePercentStr() {		
		return NumberFormat.getPercentInstance().format(this.getEndDatePercent());
	}
	public void setDisplayBeginDate(Date displayBeginDate) {
		this.displayBeginDate = displayBeginDate;
		this.calculateBeginDatePercent();
		this.calculateEndDatePercent();		
		
		this.calculateImplementRecordDisplayBeginDate();
	}

	public Date getDisplayBeginDate() {
		return displayBeginDate;
	}

	public void setImplementRecordList(ArrayList<TaskImplementRecord> implementRecordList) {
		this.implementRecordList = implementRecordList;
	}

	public ArrayList<TaskImplementRecord> getImplementRecordList() {
		return implementRecordList;
	}

	public Set<TaskImplementRecord> getImplementRecordSet() {
//		return implementRecordSet;
		Set<TaskImplementRecord> implementRecordSet = new HashSet<TaskImplementRecord>();
		implementRecordSet.addAll(implementRecordList);
		
		return implementRecordSet;
	}
		
	public void setImplementRecordSet(Set<TaskImplementRecord> implementRecordSet) {
		implementRecordList.clear();
		implementRecordList.addAll(implementRecordSet);
		
		this.calculateImplementRecordDisplayBeginDate();
	}
	
	private void calculateImplementRecordDisplayBeginDate()
	{
		Date lastEndDate = null;
		float fontDatePercent = 0.0f;
		int firstIndex = 0;
		for(int i=0;i<implementRecordList.size();i++)
		{
			TaskImplementRecord r = implementRecordList.get(i);
			
			if(r.getTirFlag().equals(CommonService.DELETE_FLAG))
			{
				firstIndex++;
				continue;
			}
			
			if(i == firstIndex)
			{
				r.setDisplayBeginDate(displayBeginDate);
				r.setFontDatePercent(0.0f);
			}
			else
			{
				if(lastEndDate.getTime() > displayBeginDate.getTime())
				{
					r.setDisplayBeginDate(lastEndDate);
				}
				else
				{
					r.setDisplayBeginDate(displayBeginDate);
				}
				r.setFontDatePercent(fontDatePercent/100.0f);
			}	
			
			fontDatePercent += r.getBeginDatePercent() + r.getEndDatePercent(); 
			
			try {
				lastEndDate = DateFormat.getDateInstance().parse(r.getTirEndDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setCurrentImplementRecord(TaskImplementRecord currentImplementRecord) {
		this.currentImplementRecord = currentImplementRecord;
	}

	public TaskImplementRecord getCurrentImplementRecord() {
		return currentImplementRecord;
	}


	public void setRecordExpendStatus(String recordExpendStatus) {
		this.recordExpendStatus = recordExpendStatus;
	}

	public String getRecordExpendStatus() {
		return recordExpendStatus;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public boolean isModified() {
		return modified;
	}


}