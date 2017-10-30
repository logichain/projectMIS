/**
 * 
 */
package org.king.security.service.impl;

import java.util.List;

import org.king.framework.service.impl.BaseService;
import org.king.security.dao.FirstpageAccessDAO;
import org.king.security.dao.MainpageAccessDAO;
import org.king.security.domain.FirstpageAccess;
import org.king.security.domain.MainpageAccess;
import org.king.security.service.AccountService;
import org.king.security.service.UserAccessService;

/**
 * @author jackey
 *
 */
public class UserAccessServiceImpl extends BaseService implements UserAccessService{

	
    private FirstpageAccessDAO firstpageAccessDAO;
    private MainpageAccessDAO mainpageAccessDAO;
		
	public void setFirstpageAccessDAO(FirstpageAccessDAO firstpageAccessDAO) {
		this.firstpageAccessDAO = firstpageAccessDAO;
	}
	public void setMainpageAccessDAO(MainpageAccessDAO mainpageAccessDAO) {
		this.mainpageAccessDAO = mainpageAccessDAO;
	}
	
	public void saveFirst(FirstpageAccess firstpageAccess){
		try{
			System.out.println("serviceimpl");
			firstpageAccessDAO.save(firstpageAccess);
			System.out.println("firstpageAccess save ok");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("firstpageAccess serviceimpl save error");
		}
	}
	
	public void saveMain(MainpageAccess mainpageAccess){
		try{
			System.out.println("serviceimpl");
			mainpageAccessDAO.save(mainpageAccess);
			System.out.println("mainpageAccess save ok");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("mainpageAccess serviceimpl save error");
		}
	}
	
    //	�Լ��ķ���
	public List findFirst(String query){
		List result=null;
		try{
			result=firstpageAccessDAO.find(query);
			System.out.println("find ok");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("find error");
		}
		if(result==null)
			System.out.println("findresult is null");
		return result;
	}
	
	public List findMain(String query){
		List result=null;
		try{
			result=mainpageAccessDAO.find(query);
			System.out.println("find ok");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("find error");
		}
		if(result==null)
			System.out.println("findresult is null");
		return result;
	}

	public MainpageAccess getLastAccess(String userId) {
		MainpageAccess rtn = null;
		List<MainpageAccess> list = mainpageAccessDAO.find("from MainpageAccess a where a.userName = '" + userId + "' order by accessDate,accessTime");
		if(list.size() > 0)
		{
			rtn = list.get(list.size() -1);
		}
		
		return rtn;
	}
}
