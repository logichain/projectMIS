package org.king.common.post;

/**
 * Post entity. @author MyEclipse Persistence Tools
 */

public class Post extends org.king.framework.domain.BaseObject implements
		java.io.Serializable {

	public static int POST_PROJECT_SECRETARY = 1;//项目秘书
	public static int POST_BUSINESS_DEPT_MANAGER = 2;//业务部门经理
	public static int POST_PROJECT_MANAGER = 3;//项目经理
	public static int POST_ASSIST_DEPT_MANAGER = 4;//辅助部门经理
	public static int POST_GENERAL_MANAGER = 5;//总经理
	public static int POST_VICE_GENERAL_MANAGER = 6;//副总经理
	public static int POST_GENERAL_USER = 7;//一般用户
	public static int POST_LEGAL = 8;//法务
	public static int POST_STAMPER = 9;//印章管理专员
	public static int POST_PROJECT = 10;//项目管理专员
	public static int POST_EXPENSE = 11;//费用管理专员
	public static int POST_PROJECT_MARKET_MANAGER = 14;//项目市场经理
	
	public static int POST_PARTY_SECRETARY = 12;//党委书记
	public static int POST_CHIEF_ENGINEER = 13;//总工程师
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer PId;
	private String PName;
	private Integer PFlag;

	// Constructors

	/** default constructor */
	public Post() {
	}

	/** full constructor */
	public Post(String PName, Integer PFlag) {
		this.PName = PName;
		this.PFlag = PFlag;
	}

	// Property accessors

	public Integer getPId() {
		return this.PId;
	}

	public void setPId(Integer PId) {
		this.PId = PId;
	}

	public String getPName() {
		return this.PName;
	}

	public void setPName(String PName) {
		this.PName = PName;
	}

	public Integer getPFlag() {
		return this.PFlag;
	}

	public void setPFlag(Integer PFlag) {
		this.PFlag = PFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof Post)
		{
			Post p = (Post)arg0;
			if(p.getPId().equals(PId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return PId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.PId + this.PName;
	}

}