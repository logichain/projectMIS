package org.king.common.post;

/**
 * Post entity. @author MyEclipse Persistence Tools
 */

public class Post extends org.king.framework.domain.BaseObject implements
		java.io.Serializable {

	public static int POST_PROJECT_SECRETARY = 1;//��Ŀ����
	public static int POST_BUSINESS_DEPT_MANAGER = 2;//ҵ���ž���
	public static int POST_PROJECT_MANAGER = 3;//��Ŀ����
	public static int POST_ASSIST_DEPT_MANAGER = 4;//�������ž���
	public static int POST_GENERAL_MANAGER = 5;//�ܾ���
	public static int POST_VICE_GENERAL_MANAGER = 6;//���ܾ���
	public static int POST_GENERAL_USER = 7;//һ���û�
	public static int POST_LEGAL = 8;//����
	public static int POST_STAMPER = 9;//ӡ�¹���רԱ
	public static int POST_PROJECT = 10;//��Ŀ����רԱ
	public static int POST_EXPENSE = 11;//���ù���רԱ
	public static int POST_PROJECT_MARKET_MANAGER = 14;//��Ŀ�г�����
	
	public static int POST_PARTY_SECRETARY = 12;//��ί���
	public static int POST_CHIEF_ENGINEER = 13;//�ܹ���ʦ
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