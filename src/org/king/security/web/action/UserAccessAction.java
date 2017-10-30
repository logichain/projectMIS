/**
 * 
 */
package org.king.security.web.action;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.king.framework.web.action.BaseAction;
import org.king.security.domain.FirstpageAccess;
import org.king.security.service.UserAccessService;

/**
 * @author jackey
 *
 */
public class UserAccessAction extends BaseAction{

	private UserAccessService userAccessService;

	public void setUserAccessService(UserAccessService userAccessService) {
		this.userAccessService = userAccessService;
	}

	public ActionForward saveFirst(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession(true);
		}
		FirstpageAccess firstpageAccess=new FirstpageAccess();
		try {
			java.util.Date now = new java.util.Date();
			
			String dateStr = DateFormat.getDateInstance().format(now);
			String timeStr=DateFormat.getTimeInstance().format(now);
			
			firstpageAccess.setAccessDate(new java.sql.Date(Integer.parseInt(dateStr.substring(0,4))-1900,Integer.parseInt(dateStr.substring(5,7))-1,Integer.parseInt(dateStr.substring(8))));		    
			firstpageAccess.setAccessTime(new Time(Integer.parseInt(timeStr.substring(0,2)),Integer.parseInt(timeStr.substring(3,5)),0));
		    firstpageAccess.setAccessIp(request.getRemoteAddr());
			
 		    // 数据库验证
		
			 if(userAccessService==null)
				 System.out.println("service is null");
			
			 userAccessService.saveFirst(firstpageAccess);
			 
			 //System.out.println("add success");			 
			 return mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("add error");			
			return mapping.findForward("faile");
		}

	}
	
	public ActionForward tongjiFirst(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession(true);
		}

		ActionMessages errors = new ActionMessages();
		
				
 		// 数据库验证
		try {
		  if(userAccessService==null)
				 System.out.println("service is null");
			 			 
		  if(request.getParameter("hbeginDate")!=null&&request.getParameter("hbeginDate")!=""&&request.getParameter("hbeginDate").length()!=0){
			 
		     
			  String beginDate=request.getParameter("hbeginDate");
			  String endDate=request.getParameter("hendDate");
	
			 List sl=new ArrayList();
			 List tl=new ArrayList();
			 
			 java.util.Date sd=DateFormat.getDateInstance().parse(beginDate);
			 java.util.Date ed=DateFormat.getDateInstance().parse(endDate);
			 
			 if(!sd.after(ed)){
	
				 //给定时段的访问量
				 String sqlstring="select new Map(uai.accessDate as accessDate,count(uai.id) as accessNum) from FirstpageAccess uai where uai.accessDate between '"+beginDate+"' and '"+endDate+"' group by uai.accessDate order by uai.accessDate";
			     sl=userAccessService.findFirst(sqlstring);
			     
			     sqlstring="select new Map(count(uai.id) as totalNum) from FirstpageAccess uai where uai.accessDate between '"+beginDate+"' and '"+endDate+"'";
			     tl=userAccessService.findFirst(sqlstring);
			     		 
			 }
			
			 if(sl!=null&&sl.size()!=0){
				 request.setAttribute("tjlist",sl);
				 request.setAttribute("tjlisttotal",tl);
				 request.setAttribute("beginDate",beginDate);
				 request.setAttribute("endDate",endDate);				
				 
				 return mapping.findForward("tongjifirst");
			 }
			 else{
				 request.setAttribute("msg","0");
				 return mapping.findForward("tongjifirst");
			 } 
	  }	
	  else
			return mapping.findForward("tongjifirst");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("action tongji list error");
			return mapping.findForward("faile");
		}

	}
	
	public ActionForward firstAccessview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession(true);
		}

		ActionMessages errors = new ActionMessages();
		
		try {
			 if(userAccessService==null)
				 System.out.println("service is null");
			 String viewDate=request.getParameter("viewDate");
          	
			 List firstAccessList=userAccessService.findFirst("select new Map(uai.accessDate as accessDate,uai.accessTime as accessTime,uai.accessIp as accessIp) from FirstpageAccess uai where uai.accessDate='"+viewDate+"' order by uai.accessTime");
				 
			if(firstAccessList!=null&&firstAccessList.size()!=0){
				 
				 request.setAttribute("accesslist",firstAccessList);
            
				 return mapping.findForward("accesslist");
			 }
			 else
				 return mapping.findForward("accesslist");
			 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("action list error");
			return mapping.findForward("faile");
		}

	}
			
	public ActionForward mainAccessview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession(true);
		}

		ActionMessages errors = new ActionMessages();
		
		try {
			 if(userAccessService==null)
				 System.out.println("service is null");
			 String viewDate=request.getParameter("viewDate");
			 String userName=request.getParameter("huserName");
			 String trueName=request.getParameter("htrueName");
			 
			 String sqlstring="select new Map(uai.accessDate as accessDate,uai.accessTime as accessTime,uai.userName as userName,uai.trueName as trueName,uai.ipAddress as accessIp) from MainpageAccess uai where uai.accessDate='"+viewDate+"'";
          	 
			 if(request.getParameter("huserName")!=null&&request.getParameter("huserName").length()!=0)
				 sqlstring=sqlstring+" and uai.userName like '%"+request.getParameter("huserName")+"%'";
			 
			 if(request.getParameter("htrueName")!=null&&request.getParameter("htrueName").length()!=0)
				 sqlstring=sqlstring+" and uai.trueName like '%"+request.getParameter("htrueName")+"%'";
			 
			 sqlstring=sqlstring+" order by uai.accessTime";
			 
			 List firstAccessList=userAccessService.findFirst(sqlstring);
				 
			if(firstAccessList!=null&&firstAccessList.size()!=0){
				 
				 sqlstring="select new Map(ac.name as userName,s.fromProvince as fromProvince) from Studentmodel s,Account ac,Person ps where ac.id=ps.id and ps.personCode=s.studentNo";
				 List rs=userAccessService.findFirst(sqlstring);
				 
				 for(int i=0;i<firstAccessList.size();i++){
					 Map m=(Map)firstAccessList.get(i);
					 String un=(String)m.get("userName");
					 
					 String fp="";
					 if(rs!=null&&rs.size()>0){
						 int j=0;
					     for(j=0;j<rs.size();j++){
					    	 Map n=(Map)rs.get(j);
					    	 String un2=(String)n.get("userName");
					    	 if(un2.equals(un)){
					             m.put("fromProvince",n.get("fromProvince"));
					             break;
					    	 }
					     }
					     if(j==rs.size()||j>rs.size())
					    	 m.put("fromProvince","");
					 }
				 }
				 ChangeProvinceValue(firstAccessList);
				 request.setAttribute("accesslist",firstAccessList);
            
				 return mapping.findForward("mainaccesslist");
			 }
			 else
				 return mapping.findForward("mainaccesslist");
			 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("action list error");
			return mapping.findForward("faile");
		}

	}
	
	public void ChangeProvinceValue(List list){
		List majorList=getprovinceList();
		for(int j=0;j<list.size();j++){
			 Map n=(Map)list.get(j);
			 if(n.get("fromProvince")!=null){
			   String mj=((String)n.get("fromProvince")).trim();
			   for(int k=0;k<majorList.size();k++){
				 Map n1=(Map)majorList.get(k);
				 String mj1=((String)n1.get("dictValue")).trim();
				 if(mj.equals(mj1)){
					 n.put("fromProvinceName",((String)n1.get("dictCaption")).trim());
				 }
			   }
			 }
			 else
				 n.put("fromProvinceName","");
		 }
		
	}
	
	public List getprovinceList(){
		List provinceList=userAccessService.findFirst("select new Map(di.id.dictNo as dictNo,di.id.dictValue as dictValue,di.dictCaption as dictCaption) from Dictionary di where di.id.dictNo=10 order by di.id.dictValue");
		return provinceList;
	}
	
}
