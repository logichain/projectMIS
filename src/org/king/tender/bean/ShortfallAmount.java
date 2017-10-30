package org.king.tender.bean;

import java.math.BigDecimal;
import java.util.List;

import com.ibm.icu.text.NumberFormat;

/**
 * ProjectBudget entity. @author MyEclipse Persistence Tools
 */

public class ShortfallAmount implements java.io.Serializable {
	
	
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private BigDecimal e7 = BigDecimal.ZERO;	
	private BigDecimal e8 = BigDecimal.ZERO;
	private BigDecimal e9 = BigDecimal.ZERO;
	private BigDecimal e10 = BigDecimal.ZERO;
	private BigDecimal e11 = BigDecimal.ZERO;
	private BigDecimal e12 = BigDecimal.ZERO;
	private BigDecimal e13 = BigDecimal.ZERO;
	private BigDecimal e14 = BigDecimal.ZERO;
	private BigDecimal e15 = BigDecimal.ZERO;
	private BigDecimal e16 = BigDecimal.ZERO;
	private BigDecimal e17 = BigDecimal.ZERO;
	private BigDecimal e18 = BigDecimal.ZERO;
	private BigDecimal e19 = BigDecimal.ZERO;
	private BigDecimal e20 = BigDecimal.ZERO;
	private BigDecimal e21 = BigDecimal.ZERO;
	private BigDecimal e22 = BigDecimal.ZERO;
	private BigDecimal e23 = BigDecimal.ZERO;
	private BigDecimal e24 = BigDecimal.ZERO;
	private BigDecimal e25 = BigDecimal.ZERO;
	private BigDecimal e26 = BigDecimal.ZERO;
	private BigDecimal e27 = BigDecimal.ZERO;
	private BigDecimal e28 = BigDecimal.ZERO;
	private BigDecimal e29 = BigDecimal.ZERO;
	private BigDecimal e30 = BigDecimal.ZERO;
	private BigDecimal e31 = BigDecimal.ZERO;
	private BigDecimal e32 = BigDecimal.ZERO;
	private BigDecimal e33 = BigDecimal.ZERO;
	private BigDecimal e34 = BigDecimal.ZERO;
	private BigDecimal e35 = BigDecimal.ZERO;
	private BigDecimal e36 = BigDecimal.ZERO;
	private BigDecimal e37 = BigDecimal.ZERO;
	private BigDecimal e38 = BigDecimal.ZERO;
	private BigDecimal e39 = BigDecimal.ZERO;
	private BigDecimal e40 = BigDecimal.ZERO;
	private BigDecimal e41 = BigDecimal.ZERO;
	private BigDecimal e42 = BigDecimal.ZERO;
	
	private BigDecimal e43 = BigDecimal.ZERO;
	private BigDecimal e44 = BigDecimal.ZERO;
	private BigDecimal e45 = BigDecimal.ZERO;
	private BigDecimal e46 = BigDecimal.ZERO;
	private BigDecimal e47 = BigDecimal.ZERO;
	// Constructors
	
		
	/** default constructor */
	public ShortfallAmount(ProjectBudget budget,ProjectBudget settlement) {		
			budget.getE7AmountStr();
			budget.getE8AmountStr();
			budget.getE9AmountStr();
			budget.getE10AmountStr();
			budget.getE11AmountStr();
			budget.getE12AmountStr();
			budget.getE13AmountStr();
			budget.getE14AmountStr();
			budget.getE15AmountStr();
			budget.getE16AmountStr();
			budget.getE17AmountStr();
			budget.getE18AmountStr();
			budget.getE19AmountStr();
			budget.getE20AmountStr();
			budget.getE21AmountStr();
			budget.getE22AmountStr();
			budget.getE23AmountStr();
			budget.getE24AmountStr();
			budget.getE25AmountStr();
			budget.getE26AmountStr();
			budget.getE27AmountStr();
			budget.getE28AmountStr();
			budget.getE29AmountStr();
			budget.getE30AmountStr();
			budget.getE31AmountStr();
			budget.getE32AmountStr();
			budget.getE33AmountStr();
			budget.getE34AmountStr();
			budget.getE35AmountStr();
			budget.getE36AmountStr();
			budget.getE37AmountStr();
			budget.getE38AmountStr();
			budget.getE39AmountStr();
			budget.getE40AmountStr();
			budget.getE41AmountStr();
			budget.getE42AmountStr();
			budget.getE43AmountStr();
			budget.getE44AmountStr();
			budget.getE45AmountStr();
			
			settlement.getE7AmountStr();
			settlement.getE8AmountStr();
			settlement.getE9AmountStr();
			settlement.getE10AmountStr();
			settlement.getE11AmountStr();
			settlement.getE12AmountStr();
			settlement.getE13AmountStr();
			settlement.getE14AmountStr();
			settlement.getE15AmountStr();
			settlement.getE16AmountStr();
			settlement.getE17AmountStr();
			settlement.getE18AmountStr();
			settlement.getE19AmountStr();
			settlement.getE20AmountStr();
			settlement.getE21AmountStr();
			settlement.getE22AmountStr();
			settlement.getE23AmountStr();
			settlement.getE24AmountStr();
			settlement.getE25AmountStr();
			settlement.getE26AmountStr();
			settlement.getE27AmountStr();
			settlement.getE28AmountStr();
			settlement.getE29AmountStr();
			settlement.getE30AmountStr();
			settlement.getE31AmountStr();
			settlement.getE32AmountStr();
			settlement.getE33AmountStr();
			settlement.getE34AmountStr();
			settlement.getE35AmountStr();
			settlement.getE36AmountStr();
			settlement.getE37AmountStr();
			settlement.getE38AmountStr();
			settlement.getE39AmountStr();
			settlement.getE40AmountStr();
			settlement.getE41AmountStr();
			settlement.getE42AmountStr();
			settlement.getE43AmountStr();
			settlement.getE44AmountStr();
			settlement.getE45AmountStr();
			
			e7 = budget.getE7().getBiAmount().subtract(settlement.getE7().getBiAmount());
			e8 = budget.getE8().getBiAmount().subtract(settlement.getE8().getBiAmount());
			e9 = budget.getE9().getBiAmount().subtract(settlement.getE9().getBiAmount());
			e10 = budget.getE10().getBiAmount().subtract(settlement.getE10().getBiAmount());
			e11 = budget.getE11().getBiAmount().subtract(settlement.getE11().getBiAmount());
			e12 = budget.getE12().getBiAmount().subtract(settlement.getE12().getBiAmount());
			e13 = budget.getE13().getBiAmount().subtract(settlement.getE13().getBiAmount());
			e14 = budget.getE14().getBiAmount().subtract(settlement.getE14().getBiAmount());
			e15 = budget.getE15().getBiAmount().subtract(settlement.getE15().getBiAmount());
			e16 = budget.getE16().getBiAmount().subtract(settlement.getE16().getBiAmount());
			e17 = budget.getE17().getBiAmount().subtract(settlement.getE17().getBiAmount());
			e18 = budget.getE18().getBiAmount().subtract(settlement.getE18().getBiAmount());
			e19 = budget.getE19().getBiAmount().subtract(settlement.getE19().getBiAmount());
			e20 = budget.getE20().getBiAmount().subtract(settlement.getE20().getBiAmount());
			e21 = budget.getE21().getBiAmount().subtract(settlement.getE21().getBiAmount());
			e22 = budget.getE22().getBiAmount().subtract(settlement.getE22().getBiAmount());
			e23 = budget.getE23().getBiAmount().subtract(settlement.getE23().getBiAmount());
			e24 = budget.getE24().getBiAmount().subtract(settlement.getE24().getBiAmount());
			e25 = budget.getE25().getBiAmount().subtract(settlement.getE25().getBiAmount());
			e26 = budget.getE26().getBiAmount().subtract(settlement.getE26().getBiAmount());
			e27 = budget.getE27().getBiAmount().subtract(settlement.getE27().getBiAmount());
			e28 = budget.getE28().getBiAmount().subtract(settlement.getE28().getBiAmount());
			e29 = budget.getE29().getBiAmount().subtract(settlement.getE29().getBiAmount());
			e30 = budget.getE30().getBiAmount().subtract(settlement.getE30().getBiAmount());
			e31 = budget.getE31().getBiAmount().subtract(settlement.getE31().getBiAmount());
			e32 = budget.getE32().getBiAmount().subtract(settlement.getE32().getBiAmount());
			e33 = budget.getE33().getBiAmount().subtract(settlement.getE33().getBiAmount());
			e34 = budget.getE34().getBiAmount().subtract(settlement.getE34().getBiAmount());
			e35 = budget.getE35().getBiAmount().subtract(settlement.getE35().getBiAmount());
			e36 = budget.getE36().getBiAmount().subtract(settlement.getE36().getBiAmount());
			e37 = budget.getE37().getBiAmount().subtract(settlement.getE37().getBiAmount());
			e38 = budget.getE38().getBiAmount().subtract(settlement.getE38().getBiAmount());
			e39 = budget.getE39().getBiAmount().subtract(settlement.getE39().getBiAmount());
			e40 = budget.getE40().getBiAmount().subtract(settlement.getE40().getBiAmount());
			e41 = budget.getE41().getBiAmount().subtract(settlement.getE41().getBiAmount());
			e42 = budget.getE42().getBiAmount().subtract(settlement.getE42().getBiAmount());
			e43 = budget.getE43().getBiAmount().subtract(settlement.getE43().getBiAmount());
			e44 = budget.getE44().getBiAmount().subtract(settlement.getE44().getBiAmount());
			e45 = budget.getE45().getBiAmount().subtract(settlement.getE45().getBiAmount());
		
	}

	

	// Property accessors
	
	
	public String getE7AmountStr()
	{		
		return NumberFormat.getInstance().format(e7);
	}
	public String getE8AmountStr()
	{
		
		return NumberFormat.getInstance().format(e8);
	}
	public String getE9AmountStr()
	{
		
		return NumberFormat.getInstance().format(e9);
	}
	public String getE10AmountStr()
	{		
		return NumberFormat.getInstance().format(e10);
	}
	public String getE11AmountStr()
	{		
		return NumberFormat.getInstance().format(e11);
	}
	public String getE12AmountStr()
	{		
		return NumberFormat.getInstance().format(e12);
	}
	public String getE13AmountStr()
	{
		return NumberFormat.getInstance().format(e13);
	}
	public String getE14AmountStr()
	{
		return NumberFormat.getInstance().format(e14);
	}
	public String getE15AmountStr()
	{		
		return NumberFormat.getInstance().format(e15);
	}
	public String getE16AmountStr()
	{
		return NumberFormat.getInstance().format(e16);
	}
	public String getE17AmountStr()
	{
		return NumberFormat.getInstance().format(e17);
	}
	public String getE18AmountStr()
	{		
		return NumberFormat.getInstance().format(e18);
	}
	public String getE19AmountStr()
	{		
		return NumberFormat.getInstance().format(e19);
	}
	
	public String getE20AmountStr()
	{		
		return NumberFormat.getInstance().format(e20);
	}
	public String getE21AmountStr()
	{		
		return NumberFormat.getInstance().format(e21);
	}
	public String getE22AmountStr()
	{		
		return NumberFormat.getInstance().format(e22);
	}
	public String getE23AmountStr()
	{		
		return NumberFormat.getInstance().format(e23);
	}
	public String getE24AmountStr()
	{		
		return NumberFormat.getInstance().format(e24);
	}
	public String getE25AmountStr()
	{
		return NumberFormat.getInstance().format(e25);
	}
	public String getE26AmountStr()
	{		
		return NumberFormat.getInstance().format(e26);
	}
	public String getE27AmountStr()
	{		
		return NumberFormat.getInstance().format(e27);
	}
	public String getE28AmountStr()
	{		
		return NumberFormat.getInstance().format(e28);
	}
	public String getE29AmountStr()
	{		
		return NumberFormat.getInstance().format(e29);
	}
	
	public String getE30AmountStr()
	{		
		return NumberFormat.getInstance().format(e30);
	}
	public String getE31AmountStr()
	{		
		return NumberFormat.getInstance().format(e31);
	}
	public String getE32AmountStr()
	{		
		return NumberFormat.getInstance().format(e32);
	}
	public String getE33AmountStr()
	{		
		return NumberFormat.getInstance().format(e33);
	}
	public String getE34AmountStr()
	{		
		return NumberFormat.getInstance().format(e34);
	}
	public String getE35AmountStr()
	{		
		return NumberFormat.getInstance().format(e35);
	}
	public String getE36AmountStr()
	{		
		return NumberFormat.getInstance().format(e36);
	}
	public String getE37AmountStr()
	{		
		return NumberFormat.getInstance().format(e37);
	}
	public String getE38AmountStr()
	{
		return NumberFormat.getInstance().format(e38);
	}
	public String getE39AmountStr()
	{		
		return NumberFormat.getInstance().format(e39);
	}

	
	public String getE40AmountStr()
	{
		return NumberFormat.getInstance().format(e40);
	}
	public String getE41AmountStr()
	{
		return NumberFormat.getInstance().format(e41);
	}
	public String getE42AmountStr()
	{
		return NumberFormat.getInstance().format(e42);
	}
	
	public String getE43AmountStr()
	{		
		return NumberFormat.getInstance().format(e43);
	}
	public String getE44AmountStr()
	{		
		return NumberFormat.getInstance().format(e44);
	}
	
	public String getE45AmountStr()
	{
		return NumberFormat.getInstance().format(e45);
	}
	public String getE46AmountStr()
	{		
		return NumberFormat.getInstance().format(e46);
	}
	public String getE47AmountStr()
	{
		if(!e7.equals(BigDecimal.ZERO))
		{
			e47 = e46.divide(e7,4,BigDecimal.ROUND_HALF_UP);			
		}		
		
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits( 2 );        
		return nf.format(e47);
	}
	
}