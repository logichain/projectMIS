package org.king.security.domain;

import org.king.common.education.EducationBackground;
import org.king.common.personstatus.PersonStatus;
import org.king.common.politicalstatus.PoliticalStatus;

/**
 * AbstractPerson generated by MyEclipse - Hibernate Tools
 */

public class Person extends org.king.framework.domain.BaseObject implements
		java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String personCode;
	private String personName;
	private String sex;
	private String email;

	private String phone;

	private String birthday;
	private Integer dept;
	private Integer post;
	private Integer status;
	private Integer politicalStatus;
	

	private String address;
	private Integer education;

	private String createdate;
	private String lastdate;

	
	private String entryDate;
	private String mobile;
	
	private EducationBackground educationObject;
	private PoliticalStatus politicalStatusObject;
	private PersonStatus personStatusObject;

	// Constructors

	/** default constructor */
	public Person() {
	}

	/** minimal constructor */
	public Person(String personCode, String personName) {
		this.personCode = personCode;
		this.personName = personName;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonCode() {
		return this.personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		if("".equals(birthday))
		{
			return null;
		}
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getDept() {
		if (dept != null && dept.equals(0)) {
			return null;
		}
		return this.dept;
	}

	public void setDept(Integer dept) {
		this.dept = dept;
	}

	public Integer getPost() {
		if (post != null && post.equals(0)) {
			return null;
		}
		return this.post;
	}

	public void setPost(Integer post) {
		this.post = post;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getEducation() {
		if (education != null && education.equals(0)) {
			return null;
		}
		return this.education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public String getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getLastdate() {
		return this.lastdate;
	}

	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (object != null && object instanceof Person) {
			Person p = (Person) object;
			if (p.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return id.hashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.personCode + this.personName;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		if (status != null && status.equals(0)) {
			return null;
		}
		return status;
	}

	public void setPoliticalStatus(Integer politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public Integer getPoliticalStatus() {
		if (politicalStatus != null && politicalStatus.equals(0)) {
			return null;
		}
		return politicalStatus;
	}


	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getEntryDate() {
		if("".equals(entryDate))
		{
			return null;
		}
		return entryDate;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setEducationObject(EducationBackground educationObject) {
		this.educationObject = educationObject;
	}

	public EducationBackground getEducationObject() {
		return educationObject;
	}

	public void setPoliticalStatusObject(PoliticalStatus politicalStatusObject) {
		this.politicalStatusObject = politicalStatusObject;
	}

	public PoliticalStatus getPoliticalStatusObject() {
		return politicalStatusObject;
	}

	public void setPersonStatusObject(PersonStatus personStatusObject) {
		this.personStatusObject = personStatusObject;
	}

	public PersonStatus getPersonStatusObject() {
		return personStatusObject;
	}

}