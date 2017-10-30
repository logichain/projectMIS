package org.king.tender.bean;

import java.math.BigDecimal;
import java.util.List;

import com.ibm.icu.text.NumberFormat;

/**
 * ProjectBudget entity. @author MyEclipse Persistence Tools
 */

public class SettlementAmount implements java.io.Serializable {
	
	
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
	public SettlementAmount(List<ProjectBudget> settlementList) {
		
		for(ProjectBudget pb:settlementList)
		{
			pb.getE7AmountStr();
			pb.getE8AmountStr();
			pb.getE9AmountStr();
			pb.getE10AmountStr();
			pb.getE11AmountStr();
			pb.getE12AmountStr();
			pb.getE13AmountStr();
			pb.getE14AmountStr();
			pb.getE15AmountStr();
			pb.getE16AmountStr();
			pb.getE17AmountStr();
			pb.getE18AmountStr();
			pb.getE19AmountStr();
			pb.getE20AmountStr();
			pb.getE21AmountStr();
			pb.getE22AmountStr();
			pb.getE23AmountStr();
			pb.getE24AmountStr();
			pb.getE25AmountStr();
			pb.getE26AmountStr();
			pb.getE27AmountStr();
			pb.getE28AmountStr();
			pb.getE29AmountStr();
			pb.getE30AmountStr();
			pb.getE31AmountStr();
			pb.getE32AmountStr();
			pb.getE33AmountStr();
			pb.getE34AmountStr();
			pb.getE35AmountStr();
			pb.getE36AmountStr();
			pb.getE37AmountStr();
			pb.getE38AmountStr();
			pb.getE39AmountStr();
			pb.getE40AmountStr();
			pb.getE41AmountStr();
			pb.getE42AmountStr();
			pb.getE43AmountStr();
			pb.getE44AmountStr();
			pb.getE45AmountStr();
			e7 = e7.add(pb.getE7().getBiAmount());
			e8 = e8.add(pb.getE8().getBiAmount());
			e9 = e9.add(pb.getE9().getBiAmount());
			e10 = e10.add(pb.getE10().getBiAmount());
			e11 = e11.add(pb.getE11().getBiAmount());
			e12 = e12.add(pb.getE12().getBiAmount());
			e13 = e13.add(pb.getE13().getBiAmount());
			e14 = e14.add(pb.getE14().getBiAmount());
			e15 = e15.add(pb.getE15().getBiAmount());
			e16 = e16.add(pb.getE16().getBiAmount());
			e17 = e17.add(pb.getE17().getBiAmount());
			e18 = e18.add(pb.getE18().getBiAmount());
			e19 = e19.add(pb.getE19().getBiAmount());
			e20 = e20.add(pb.getE20().getBiAmount());
			e21 = e21.add(pb.getE21().getBiAmount());
			e22 = e22.add(pb.getE22().getBiAmount());
			e23 = e23.add(pb.getE23().getBiAmount());
			e24 = e24.add(pb.getE24().getBiAmount());
			e25 = e25.add(pb.getE25().getBiAmount());
			e26 = e26.add(pb.getE26().getBiAmount());
			e27 = e27.add(pb.getE27().getBiAmount());
			e28 = e28.add(pb.getE28().getBiAmount());
			e29 = e29.add(pb.getE29().getBiAmount());
			e30 = e30.add(pb.getE30().getBiAmount());
			e31 = e31.add(pb.getE31().getBiAmount());
			e32 = e32.add(pb.getE32().getBiAmount());
			e33 = e33.add(pb.getE33().getBiAmount());
			e34 = e34.add(pb.getE34().getBiAmount());
			e35 = e35.add(pb.getE35().getBiAmount());
			e36 = e36.add(pb.getE36().getBiAmount());
			e37 = e37.add(pb.getE37().getBiAmount());
			e38 = e38.add(pb.getE38().getBiAmount());
			e39 = e39.add(pb.getE39().getBiAmount());
			e40 = e40.add(pb.getE40().getBiAmount());
			e41 = e41.add(pb.getE41().getBiAmount());
			e42 = e42.add(pb.getE42().getBiAmount());
			e43 = e43.add(pb.getE43().getBiAmount());
			e44 = e44.add(pb.getE44().getBiAmount());
			e45 = e45.add(pb.getE45().getBiAmount());
		}
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