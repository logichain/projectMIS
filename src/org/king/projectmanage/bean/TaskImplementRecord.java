package org.king.projectmanage.bean;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

import org.king.common.service.CommonService;
import org.king.security.domain.Account;

/**
 * TaskImplementRecord entity. @author MyEclipse Persistence Tools
 */

public class TaskImplementRecord extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tirId;
	private Integer tirTask;
	private String tirBeginDate;
	private String tirEndDate;
	private Integer tirImplementPercent;
	private String tirDescription;
	private Date tirCreateDate;
	private String tirCreateUser;
	private Integer tirFlag;
	
	private Account createUser;
	
	private float beginDatePercent = 0.0f;
	private float endDatePercent = 0.0f;
	private Date displayBeginDate = new Date();
	private float fontDatePercent = 0.0f;
	
	private boolean modified = false;
	// Constructors

	/** default constructor */
	public TaskImplementRecord() {
	}

	/** minimal constructor */
	public TaskImplementRecord(Integer tirTask, String tirBeginDate,
			String tirEndDate, Integer tirImplementPercent, Date tirCreateDate,
			String tirCreateUser) {
		this.tirTask = tirTask;
		this.tirBeginDate = tirBeginDate;
		this.tirEndDate = tirEndDate;
		this.tirImplementPercent = tirImplementPercent;
		this.tirCreateDate = tirCreateDate;
		this.tirCreateUser = tirCreateUser;
	}

	/** full constructor */
	public TaskImplementRecord(Integer tirTask, String tirBeginDate,
			String tirEndDate, Integer tirImplementPercent,
			String tirDescription, Date tirCreateDate, String tirCreateUser) {
		this.tirTask = tirTask;
		this.tirBeginDate = tirBeginDate;
		this.tirEndDate = tirEndDate;
		this.tirImplementPercent = tirImplementPercent;
		this.tirDescription = tirDescription;
		this.tirCreateDate = tirCreateDate;
		this.tirCreateUser = tirCreateUser;
	}

	// Property accessors

	public Integer getTirId() {
		return this.tirId;
	}

	public void setTirId(Integer tirId) {
		this.tirId = tirId;
	}

	public Integer getTirTask() {
		return this.tirTask;
	}

	public void setTirTask(Integer tirTask) {
		this.tirTask = tirTask;
	}

	public String getTirBeginDate() {
		return this.tirBeginDate;
	}

	public void setTirBeginDate(String tirBeginDate) {
		this.tirBeginDate = tirBeginDate;
	}

	public String getTirEndDate() {
		return this.tirEndDate;
	}

	public void setTirEndDate(String tirEndDate) {
		this.tirEndDate = tirEndDate;
	}

	public Integer getTirImplementPercent() {
		return this.tirImplementPercent;
	}

	public String getTirImplementPercentStr() {
		return NumberFormat.getPercentInstance().format(tirImplementPercent/100.0f);
	}
	public void setTirImplementPercent(Integer tirImplementPercent) {
		this.tirImplementPercent = tirImplementPercent;
	}

	public String getTirDescription() {
		return this.tirDescription;
	}

	public void setTirDescription(String tirDescription) {
		this.tirDescription = tirDescription;
	}

	public Date getTirCreateDate() {
		return this.tirCreateDate;
	}

	public void setTirCreateDate(Date tirCreateDate) {
		this.tirCreateDate = tirCreateDate;
	}

	public String getTirCreateUser() {
		return this.tirCreateUser;
	}

	public void setTirCreateUser(String tirCreateUser) {
		this.tirCreateUser = tirCreateUser;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof TaskImplementRecord)
		{
			TaskImplementRecord tcr = (TaskImplementRecord) arg0;
			if(tcr.getTirId().equals(tirId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.tirId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.tirId + this.tirDescription;
	}

	public void setTirFlag(Integer tirFlag) {
		this.tirFlag = tirFlag;
	}

	public Integer getTirFlag() {
		return tirFlag;
	}

	public void setCreateUser(Account createUser) {
		this.createUser = createUser;
	}

	public Account getCreateUser() {
		return createUser;
	}
	
	public void setBeginDatePercent(float beginDatePercent) {
		this.beginDatePercent = beginDatePercent;
	}

	public float getBeginDatePercent() {				 
		if(beginDatePercent < 0)
		{
			return 0.0f;
		}
		else if(beginDatePercent > 1)
		{
			return 100.0f;
		}
			
		return ((int)(beginDatePercent *10000))*1.0f/100;		
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
		else if(fontDatePercent + beginDatePercent + endDatePercent > 1)
		{
			return ((int)((1- fontDatePercent -beginDatePercent) *10000))*1.0f/100;
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
	}

	public Date getDisplayBeginDate() {
		return displayBeginDate;
	}
	
	private void calculateBeginDatePercent()
	{
		if(tirBeginDate != null && !tirBeginDate.isEmpty())
		{
			try {
				Date bd = DateFormat.getDateInstance().parse(tirBeginDate);
				
				this.beginDatePercent = (bd.getTime() - this.displayBeginDate.getTime()) *1.0f/(365 * CommonService.DAY_MILLISECOND);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}				
	}
	
	private void calculateEndDatePercent()
	{
		if(tirBeginDate != null && !tirBeginDate.isEmpty() && tirEndDate != null && !tirEndDate.isEmpty())
		{
			try {
				Date bd = DateFormat.getDateInstance().parse(tirBeginDate);
				Date ed = DateFormat.getDateInstance().parse(tirEndDate);
				
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

	public void setFontDatePercent(float fontDatePercent) {
		this.fontDatePercent = fontDatePercent;
	}

	public float getFontDatePercent() {
		return fontDatePercent;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public boolean isModified() {
		return modified;
	}


}