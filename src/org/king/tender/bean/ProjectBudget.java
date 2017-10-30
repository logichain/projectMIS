package org.king.tender.bean;

import java.math.BigDecimal;

import com.ibm.icu.text.NumberFormat;

/**
 * ProjectBudget entity. @author MyEclipse Persistence Tools
 */

public class ProjectBudget extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {
	
	public static int BUDGET_TYPE_TENDER = 1;
	public static int BUDGET_TYPE_PROJECT = 2;//·ÏÆú
	public static int BUDGET_TYPE_MONTH_BUDGET = 3;//·ÏÆú
	public static int BUDGET_TYPE_MONTH_SETTLEMENT = 4;//·ÏÆú
	public static int BUDGET_TYPE_APPLY = 5;
	// Fields
	public static int BUDGET_STATUS_INIT = 0;
	public static int BUDGET_STATUS_CHECKED = 1;
	public static int BUDGET_STATUS_SEND = 2;
	public static int BUDGET_STATUS_PROJECT_SEND = 3;
	public static int BUDGET_STATUS_MARKET_SEND = 4;
	
	public static int BUDGET_CHECK_STATUS_CHECKING = 1;// ÆÀÉóÖÐ
	public static int BUDGET_CHECK_STATUS_RETURN = 2;// ²µ»Ø
	public static int BUDGET_CHECK_STATUS_CHECKED = 3;// Í¨¹ý
	
	public static int BUDGET_PAYMENT_TYPE_DELAYPAY = 1;//µæ¸¶
	public static int BUDGET_PAYMENT_TYPE_PREPAY = -1;//Ô¤¸¶
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pbId;
	private Integer pbE7;
	private Integer pbE8;
	private Integer pbE9;
	private Integer pbE10;
	private Integer pbE11;
	private Integer pbE12;
	private Integer pbE13;
	private Integer pbE14;
	private Integer pbE15;
	private Integer pbE16;
	private Integer pbE17;
	private Integer pbE18;
	private Integer pbE19;
	private Integer pbE20;
	private Integer pbE21;
	private Integer pbE22;
	private Integer pbE23;
	private Integer pbE24;
	private Integer pbE25;
	private Integer pbE26;
	private Integer pbE27;
	private Integer pbE28;
	private Integer pbE29;
	private Integer pbE30;
	private Integer pbE31;
	private Integer pbE32;
	private Integer pbE33;
	private Integer pbE34;
	private Integer pbE35;
	private Integer pbE36;
	private Integer pbE37;
	private Integer pbE38;
	private Integer pbE39;
	private Integer pbE40;
	private Integer pbE41;
	private Integer pbE42;
	private Integer pbE43;
	private Integer pbE44;
	private Integer pbE45;
	private Integer pbE46;
	private Integer pbE47;
	private Integer pbProject;
	private String pbMonth;
	private Integer pbType;
	private Integer pbStatus = 0;
	private Integer pbCheckResult = 0;
	
	private Integer pbOnAccount = -1;
	
	private BudgetItem e7;	
	private BudgetItem e8;
	private BudgetItem e9;
	private BudgetItem e10;
	private BudgetItem e11;
	private BudgetItem e12;
	private BudgetItem e13;
	private BudgetItem e14;
	private BudgetItem e15;
	private BudgetItem e16;
	private BudgetItem e17;
	private BudgetItem e18;
	private BudgetItem e19;
	private BudgetItem e20;
	private BudgetItem e21;
	private BudgetItem e22;
	private BudgetItem e23;
	private BudgetItem e24;
	private BudgetItem e25;
	private BudgetItem e26;
	private BudgetItem e27;
	private BudgetItem e28;
	private BudgetItem e29;
	private BudgetItem e30;
	private BudgetItem e31;
	private BudgetItem e32;
	private BudgetItem e33;
	private BudgetItem e34;
	private BudgetItem e35;
	private BudgetItem e36;
	private BudgetItem e37;
	private BudgetItem e38;
	private BudgetItem e39;
	private BudgetItem e40;
	private BudgetItem e41;
	private BudgetItem e42;
	
	private BudgetItem e43;
	private BudgetItem e44;
	private BudgetItem e45;
	private BudgetItem e46;
	private BudgetItem e47;
	// Constructors
	
		
	/** default constructor */
	public ProjectBudget() {
	}

	/** full constructor */
	public ProjectBudget(Integer pbE7, Integer pbE8,
			Integer pbE9, Integer pbE10, Integer pbE11, Integer pbE12,
			Integer pbE13, Integer pbE14, Integer pbE15, Integer pbE16,
			Integer pbE17, Integer pbE18, Integer pbE19, Integer pbE20,
			Integer pbE21, Integer pbE22, Integer pbE23, Integer pbE24,
			Integer pbE25, Integer pbE26, Integer pbE27, Integer pbE28,
			Integer pbE29, Integer pbE30, Integer pbE31, Integer pbE32,
			Integer pbE33, Integer pbE34, Integer pbE35, Integer pbE36,
			Integer pbE37, Integer pbE38, Integer pbE39, Integer pbE40,
			Integer pbE41, Integer pbE42, Integer pbE43, Integer pbE44,
			Integer pbE45, Integer pbE46, Integer pbE47) {

		this.pbE7 = pbE7;
		this.pbE8 = pbE8;
		this.pbE9 = pbE9;
		this.pbE10 = pbE10;
		this.pbE11 = pbE11;
		this.pbE12 = pbE12;
		this.pbE13 = pbE13;
		this.pbE14 = pbE14;
		this.pbE15 = pbE15;
		this.pbE16 = pbE16;
		this.pbE17 = pbE17;
		this.pbE18 = pbE18;
		this.pbE19 = pbE19;
		this.pbE20 = pbE20;
		this.pbE21 = pbE21;
		this.pbE22 = pbE22;
		this.pbE23 = pbE23;
		this.pbE24 = pbE24;
		this.pbE25 = pbE25;
		this.pbE26 = pbE26;
		this.pbE27 = pbE27;
		this.pbE28 = pbE28;
		this.pbE29 = pbE29;
		this.pbE30 = pbE30;
		this.pbE31 = pbE31;
		this.pbE32 = pbE32;
		this.pbE33 = pbE33;
		this.pbE34 = pbE34;
		this.pbE35 = pbE35;
		this.pbE36 = pbE36;
		this.pbE37 = pbE37;
		this.pbE38 = pbE38;
		this.pbE39 = pbE39;
		this.pbE40 = pbE40;
		this.pbE41 = pbE41;
		this.pbE42 = pbE42;
		this.pbE43 = pbE43;
		this.pbE44 = pbE44;
		this.pbE45 = pbE45;
		this.pbE46 = pbE46;
		this.pbE47 = pbE47;
	}

	// Property accessors

	public Integer getPbId() {
		return this.pbId;
	}

	public void setPbId(Integer pbId) {
		this.pbId = pbId;
	}

	public Integer getPbE7() {
		return this.pbE7;
	}

	public void setPbE7(Integer pbE7) {
		this.pbE7 = pbE7;
	}

	public Integer getPbE8() {
		return this.pbE8;
	}

	public void setPbE8(Integer pbE8) {
		this.pbE8 = pbE8;
	}

	public Integer getPbE9() {
		return this.pbE9;
	}

	public void setPbE9(Integer pbE9) {
		this.pbE9 = pbE9;
	}

	public Integer getPbE10() {
		return this.pbE10;
	}

	public void setPbE10(Integer pbE10) {
		this.pbE10 = pbE10;
	}

	public Integer getPbE11() {
		return this.pbE11;
	}

	public void setPbE11(Integer pbE11) {
		this.pbE11 = pbE11;
	}

	public Integer getPbE12() {
		return this.pbE12;
	}

	public void setPbE12(Integer pbE12) {
		this.pbE12 = pbE12;
	}

	public Integer getPbE13() {
		return this.pbE13;
	}

	public void setPbE13(Integer pbE13) {
		this.pbE13 = pbE13;
	}

	public Integer getPbE14() {
		return this.pbE14;
	}

	public void setPbE14(Integer pbE14) {
		this.pbE14 = pbE14;
	}

	public Integer getPbE15() {
		return this.pbE15;
	}

	public void setPbE15(Integer pbE15) {
		this.pbE15 = pbE15;
	}

	public Integer getPbE16() {
		return this.pbE16;
	}

	public void setPbE16(Integer pbE16) {
		this.pbE16 = pbE16;
	}

	public Integer getPbE17() {
		return this.pbE17;
	}

	public void setPbE17(Integer pbE17) {
		this.pbE17 = pbE17;
	}

	public Integer getPbE18() {
		return this.pbE18;
	}

	public void setPbE18(Integer pbE18) {
		this.pbE18 = pbE18;
	}

	public Integer getPbE19() {
		return this.pbE19;
	}

	public void setPbE19(Integer pbE19) {
		this.pbE19 = pbE19;
	}

	public Integer getPbE20() {
		return this.pbE20;
	}

	public void setPbE20(Integer pbE20) {
		this.pbE20 = pbE20;
	}

	public Integer getPbE21() {
		return this.pbE21;
	}

	public void setPbE21(Integer pbE21) {
		this.pbE21 = pbE21;
	}

	public Integer getPbE22() {
		return this.pbE22;
	}

	public void setPbE22(Integer pbE22) {
		this.pbE22 = pbE22;
	}

	public Integer getPbE23() {
		return this.pbE23;
	}

	public void setPbE23(Integer pbE23) {
		this.pbE23 = pbE23;
	}

	public Integer getPbE24() {
		return this.pbE24;
	}

	public void setPbE24(Integer pbE24) {
		this.pbE24 = pbE24;
	}

	public Integer getPbE25() {
		return this.pbE25;
	}

	public void setPbE25(Integer pbE25) {
		this.pbE25 = pbE25;
	}

	public Integer getPbE26() {
		return this.pbE26;
	}

	public void setPbE26(Integer pbE26) {
		this.pbE26 = pbE26;
	}

	public Integer getPbE27() {
		return this.pbE27;
	}

	public void setPbE27(Integer pbE27) {
		this.pbE27 = pbE27;
	}

	public Integer getPbE28() {
		return this.pbE28;
	}

	public void setPbE28(Integer pbE28) {
		this.pbE28 = pbE28;
	}

	public Integer getPbE29() {
		return this.pbE29;
	}

	public void setPbE29(Integer pbE29) {
		this.pbE29 = pbE29;
	}

	public Integer getPbE30() {
		return this.pbE30;
	}

	public void setPbE30(Integer pbE30) {
		this.pbE30 = pbE30;
	}

	public Integer getPbE31() {
		return this.pbE31;
	}

	public void setPbE31(Integer pbE31) {
		this.pbE31 = pbE31;
	}

	public Integer getPbE32() {
		return this.pbE32;
	}

	public void setPbE32(Integer pbE32) {
		this.pbE32 = pbE32;
	}

	public Integer getPbE33() {
		return this.pbE33;
	}

	public void setPbE33(Integer pbE33) {
		this.pbE33 = pbE33;
	}

	public Integer getPbE34() {
		return this.pbE34;
	}

	public void setPbE34(Integer pbE34) {
		this.pbE34 = pbE34;
	}

	public Integer getPbE35() {
		return this.pbE35;
	}

	public void setPbE35(Integer pbE35) {
		this.pbE35 = pbE35;
	}

	public Integer getPbE36() {
		return this.pbE36;
	}

	public void setPbE36(Integer pbE36) {
		this.pbE36 = pbE36;
	}

	public Integer getPbE37() {
		return this.pbE37;
	}

	public void setPbE37(Integer pbE37) {
		this.pbE37 = pbE37;
	}

	public Integer getPbE38() {
		return this.pbE38;
	}

	public void setPbE38(Integer pbE38) {
		this.pbE38 = pbE38;
	}

	public Integer getPbE39() {
		return this.pbE39;
	}

	public void setPbE39(Integer pbE39) {
		this.pbE39 = pbE39;
	}

	public Integer getPbE40() {
		return this.pbE40;
	}

	public void setPbE40(Integer pbE40) {
		this.pbE40 = pbE40;
	}

	public Integer getPbE41() {
		return this.pbE41;
	}

	public void setPbE41(Integer pbE41) {
		this.pbE41 = pbE41;
	}

	public Integer getPbE42() {
		return this.pbE42;
	}

	public void setPbE42(Integer pbE42) {
		this.pbE42 = pbE42;
	}

	public Integer getPbE43() {
		return this.pbE43;
	}

	public void setPbE43(Integer pbE43) {
		this.pbE43 = pbE43;
	}

	public Integer getPbE44() {
		return this.pbE44;
	}

	public void setPbE44(Integer pbE44) {
		this.pbE44 = pbE44;
	}

	public Integer getPbE45() {
		return this.pbE45;
	}

	public void setPbE45(Integer pbE45) {
		this.pbE45 = pbE45;
	}

	public Integer getPbE46() {
		return this.pbE46;
	}

	public void setPbE46(Integer pbE46) {
		this.pbE46 = pbE46;
	}

	public Integer getPbE47() {
		return this.pbE47;
	}

	public void setPbE47(Integer pbE47) {
		this.pbE47 = pbE47;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof ProjectBudget)
		{
			ProjectBudget pb = (ProjectBudget)arg0;
			return pb.getPbId().equals(pbId);
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return pbId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return pbId + "";
	}

	
	public BudgetItem getE7() {
		return e7;
	}

	public void setE7(BudgetItem e7) {
		this.e7 = e7;
	}

	public BudgetItem getE8() {
		return e8;
	}

	public void setE8(BudgetItem e8) {
		this.e8 = e8;
	}

	public BudgetItem getE9() {
		return e9;
	}

	public void setE9(BudgetItem e9) {
		this.e9 = e9;
	}

	public BudgetItem getE10() {
		return e10;
	}

	public void setE10(BudgetItem e10) {
		this.e10 = e10;
	}

	public BudgetItem getE11() {
		return e11;
	}

	public void setE11(BudgetItem e11) {
		this.e11 = e11;
	}

	public BudgetItem getE12() {
		return e12;
	}

	public void setE12(BudgetItem e12) {
		this.e12 = e12;
	}

	public BudgetItem getE13() {
		return e13;
	}

	public void setE13(BudgetItem e13) {
		this.e13 = e13;
	}

	public BudgetItem getE14() {
		return e14;
	}

	public void setE14(BudgetItem e14) {
		this.e14 = e14;
	}

	public BudgetItem getE15() {
		return e15;
	}

	public void setE15(BudgetItem e15) {
		this.e15 = e15;
	}

	public BudgetItem getE16() {
		return e16;
	}

	public void setE16(BudgetItem e16) {
		this.e16 = e16;
	}

	public BudgetItem getE17() {
		return e17;
	}

	public void setE17(BudgetItem e17) {
		this.e17 = e17;
	}

	public BudgetItem getE18() {
		return e18;
	}

	public void setE18(BudgetItem e18) {
		this.e18 = e18;
	}

	public BudgetItem getE19() {
		return e19;
	}

	public void setE19(BudgetItem e19) {
		this.e19 = e19;
	}

	public BudgetItem getE20() {
		return e20;
	}

	public void setE20(BudgetItem e20) {
		this.e20 = e20;
	}

	public BudgetItem getE21() {
		return e21;
	}

	public void setE21(BudgetItem e21) {
		this.e21 = e21;
	}

	public BudgetItem getE22() {
		return e22;
	}

	public void setE22(BudgetItem e22) {
		this.e22 = e22;
	}

	public BudgetItem getE23() {
		return e23;
	}

	public void setE23(BudgetItem e23) {
		this.e23 = e23;
	}

	public BudgetItem getE24() {
		return e24;
	}

	public void setE24(BudgetItem e24) {
		this.e24 = e24;
	}

	public BudgetItem getE25() {
		return e25;
	}

	public void setE25(BudgetItem e25) {
		this.e25 = e25;
	}

	public BudgetItem getE26() {
		return e26;
	}

	public void setE26(BudgetItem e26) {
		this.e26 = e26;
	}

	public BudgetItem getE27() {
		return e27;
	}

	public void setE27(BudgetItem e27) {
		this.e27 = e27;
	}

	public BudgetItem getE28() {
		return e28;
	}

	public void setE28(BudgetItem e28) {
		this.e28 = e28;
	}

	public BudgetItem getE29() {
		return e29;
	}

	public void setE29(BudgetItem e29) {
		this.e29 = e29;
	}

	public BudgetItem getE30() {
		return e30;
	}

	public void setE30(BudgetItem e30) {
		this.e30 = e30;
	}

	public BudgetItem getE31() {
		return e31;
	}

	public void setE31(BudgetItem e31) {
		this.e31 = e31;
	}

	public BudgetItem getE32() {
		return e32;
	}

	public void setE32(BudgetItem e32) {
		this.e32 = e32;
	}

	public BudgetItem getE33() {
		return e33;
	}

	public void setE33(BudgetItem e33) {
		this.e33 = e33;
	}

	public BudgetItem getE34() {
		return e34;
	}

	public void setE34(BudgetItem e34) {
		this.e34 = e34;
	}

	public BudgetItem getE35() {
		return e35;
	}

	public void setE35(BudgetItem e35) {
		this.e35 = e35;
	}

	public BudgetItem getE36() {
		return e36;
	}

	public void setE36(BudgetItem e36) {
		this.e36 = e36;
	}

	public BudgetItem getE37() {
		return e37;
	}

	public void setE37(BudgetItem e37) {
		this.e37 = e37;
	}

	public BudgetItem getE38() {
		return e38;
	}

	public void setE38(BudgetItem e38) {
		this.e38 = e38;
	}

	public BudgetItem getE39() {
		return e39;
	}

	public void setE39(BudgetItem e39) {
		this.e39 = e39;
	}

	public BudgetItem getE40() {
		return e40;
	}

	public void setE40(BudgetItem e40) {
		this.e40 = e40;
	}

	public BudgetItem getE41() {
		return e41;
	}

	public void setE41(BudgetItem e41) {
		this.e41 = e41;
	}
	public BudgetItem getE42() {
		return e42;
	}

	public void setE42(BudgetItem e42) {
		this.e42 = e42;
	}

	public BudgetItem getE43() {
		return e43;
	}

	public void setE43(BudgetItem e43) {
		this.e43 = e43;
	}

	public BudgetItem getE44() {
		return e44;
	}

	public void setE44(BudgetItem e44) {
		this.e44 = e44;
	}

	public BudgetItem getE45() {
		return e45;
	}

	public void setE45(BudgetItem e45) {
		this.e45 = e45;
	}

	public BudgetItem getE46() {
		return e46;
	}

	public void setE46(BudgetItem e46) {
		this.e46 = e46;
	}

	public BudgetItem getE47() {
		return e47;
	}

	public void setE47(BudgetItem e47) {
		this.e47 = e47;
	}
	
	public String getE7AmountStr()
	{
		e7.setBiAmount(e8.getBiAmount().add(e9.getBiAmount()));
		
		return NumberFormat.getInstance().format(e7.getBiAmount());
	}
	public String getE8AmountStr()
	{
		
		return NumberFormat.getInstance().format(e8.getBiAmount());
	}
	public String getE9AmountStr()
	{
		
		return NumberFormat.getInstance().format(e9.getBiAmount());
	}
	public String getE10AmountStr()
	{
		e10.setBiAmount(e11.getBiAmount().add(e12.getBiAmount()));
		
		return NumberFormat.getInstance().format(e10.getBiAmount());
	}
	public String getE11AmountStr()
	{
		
		return NumberFormat.getInstance().format(e11.getBiAmount());
	}
	public String getE12AmountStr()
	{
		
		return NumberFormat.getInstance().format(e12.getBiAmount());
	}
	public String getE13AmountStr()
	{
		e13.setBiAmount(e8.getBiAmount().subtract(e11.getBiAmount())
				.add(e9.getBiAmount().subtract(e12.getBiAmount())));
		
		return NumberFormat.getInstance().format(e13.getBiAmount());
	}
	public String getE14AmountStr()
	{
		e14.setBiAmount(e8.getBiAmount().subtract(e11.getBiAmount()));
		
		return NumberFormat.getInstance().format(e14.getBiAmount());
	}
	public String getE15AmountStr()
	{
		e15.setBiAmount(e9.getBiAmount().subtract(e12.getBiAmount()));
		
		return NumberFormat.getInstance().format(e15.getBiAmount());
	}
	public String getE16AmountStr()
	{
		e16.setBiAmount(e17.getBiAmount().add(e18.getBiAmount()).add(e19.getBiAmount()).add(e20.getBiAmount()).
				add(e21.getBiAmount()));
		
		return NumberFormat.getInstance().format(e16.getBiAmount());
	}
	public String getE17AmountStr()
	{
		return NumberFormat.getInstance().format(e17.getBiAmount());
	}
	public String getE18AmountStr()
	{
		
		return NumberFormat.getInstance().format(e18.getBiAmount());
	}
	public String getE19AmountStr()
	{
		
		return NumberFormat.getInstance().format(e19.getBiAmount());
	}
	
	public String getE20AmountStr()
	{
		
		return NumberFormat.getInstance().format(e20.getBiAmount());
	}
	public String getE21AmountStr()
	{
		
		return NumberFormat.getInstance().format(e21.getBiAmount());
	}
	public String getE22AmountStr()
	{
		e22.setBiAmount(e13.getBiAmount().subtract(e16.getBiAmount()));
		
		return NumberFormat.getInstance().format(e22.getBiAmount());
	}
	public String getE23AmountStr()
	{		
		return NumberFormat.getInstance().format(e23.getBiAmount());
	}
	public String getE24AmountStr()
	{
		if(this.pbType.equals(ProjectBudget.BUDGET_TYPE_APPLY))
		{
			e24.setBiAmount(e25.getBiAmount().add(e26.getBiAmount()).add(e27.getBiAmount()).add(e28.getBiAmount()).add(e29.getBiAmount())
					.add(e30.getBiAmount()).add(e31.getBiAmount()).add(e32.getBiAmount()).add(e33.getBiAmount()).add(e34.getBiAmount())
					.add(e35.getBiAmount()).add(e36.getBiAmount()));			
		}		
		
		return NumberFormat.getInstance().format(e24.getBiAmount());
	}
	public String getE25AmountStr()
	{		
		return NumberFormat.getInstance().format(e25.getBiAmount());
	}
	public String getE26AmountStr()
	{
		
		return NumberFormat.getInstance().format(e26.getBiAmount());
	}
	public String getE27AmountStr()
	{
		
		return NumberFormat.getInstance().format(e27.getBiAmount());
	}
	public String getE28AmountStr()
	{
		
		return NumberFormat.getInstance().format(e28.getBiAmount());
	}
	public String getE29AmountStr()
	{
		
		return NumberFormat.getInstance().format(e29.getBiAmount());
	}
	
	public String getE30AmountStr()
	{
		
		return NumberFormat.getInstance().format(e30.getBiAmount());
	}
	public String getE31AmountStr()
	{
		
		return NumberFormat.getInstance().format(e31.getBiAmount());
	}
	public String getE32AmountStr()
	{
		
		return NumberFormat.getInstance().format(e32.getBiAmount());
	}
	public String getE33AmountStr()
	{
		
		return NumberFormat.getInstance().format(e33.getBiAmount());
	}
	public String getE34AmountStr()
	{
		
		return NumberFormat.getInstance().format(e34.getBiAmount());
	}
	public String getE35AmountStr()
	{
		
		return NumberFormat.getInstance().format(e35.getBiAmount());
	}
	public String getE36AmountStr()
	{
		
		return NumberFormat.getInstance().format(e36.getBiAmount());
	}
	public String getE37AmountStr()
	{
		e37.setBiAmount(e38.getBiAmount().add(e39.getBiAmount()).add(e40.getBiAmount()));
		
		return NumberFormat.getInstance().format(e37.getBiAmount());
	}
	public String getE38AmountStr()
	{		
		return NumberFormat.getInstance().format(e38.getBiAmount());
	}
	public String getE39AmountStr()
	{		
		return NumberFormat.getInstance().format(e39.getBiAmount());
	}
	public String getE40AmountStr()
	{		
		return NumberFormat.getInstance().format(e40.getBiAmount());
	}
	
	public String getE41AmountStr()
	{		
		return NumberFormat.getInstance().format(e42.getBiAmount());
	}
	public String getE42AmountStr()
	{
		return NumberFormat.getInstance().format(e42.getBiAmount());
	}
	
	public String getE43AmountStr()
	{
		e43.setBiAmount(e10.getBiAmount().add(e16.getBiAmount()).add(e23.getBiAmount()).add(e24.getBiAmount()).add(e37.getBiAmount()).add(e41.getBiAmount()));
		
		return NumberFormat.getInstance().format(e43.getBiAmount());
	}
	public String getE44AmountStr()
	{
		e44.setBiAmount(e7.getBiAmount().subtract(e43.getBiAmount()));
		
		return NumberFormat.getInstance().format(e44.getBiAmount());
	}
	public String getE45AmountStr()
	{
		if(!e7.getBiAmount().equals(BigDecimal.ZERO))
		{
			e45.setBiAmount(e44.getBiAmount().divide(e7.getBiAmount(),4,BigDecimal.ROUND_HALF_UP));			
		}		
		
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits( 2 );        
		return nf.format(e45.getBiAmount());
	}

	public void setPbProject(Integer pbProject) {
		this.pbProject = pbProject;
	}

	public Integer getPbProject() {
		return pbProject;
	}

	public void setPbMonth(String pbMonth) {
		this.pbMonth = pbMonth;
	}

	public String getPbMonth() {
		return pbMonth;
	}

	public void setPbType(Integer pbType) {
		this.pbType = pbType;
	}

	public Integer getPbType() {
		return pbType;
	}

	public void setPbStatus(Integer pbStatus) {
		this.pbStatus = pbStatus;
	}

	public Integer getPbStatus() {
		return pbStatus;
	}

	public void setPbCheckResult(Integer pbCheckResult) {
		this.pbCheckResult = pbCheckResult;
	}

	public Integer getPbCheckResult() {
		return pbCheckResult;
	}
	
	public Integer getPbOnAccount() {
		return pbOnAccount;
	}

	public void setPbOnAccount(Integer pbOnAccount) {
		this.pbOnAccount = pbOnAccount;
	}


}