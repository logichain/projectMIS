/* ============================================================
 * 版权：    king 版权所有 (c) 2006
 * 文件：    org.king.security.service.impl.AccountServiceImpl.java
 * 创建日期： 2006-4-20 10:45:37
 * 功能：    {具体要实现的功能}
 * 所含类:   {包含的类}
 * 修改记录：
 * 日期                    作者         内容
 * =============================================================
 * 2006-4-20 10:45:37      ljf        创建文件，实现基本功能
 * ============================================================
 */

/**
 * 
 */
package org.king.security.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.king.common.post.Post;
import org.king.common.service.CommonService;
import org.king.framework.dao.MyQuery;
import org.king.framework.exception.BusinessException;
import org.king.framework.service.impl.BaseService;
import org.king.security.dao.AccountDAO;
import org.king.security.dao.UsrPostDAO;
import org.king.security.domain.Account;
import org.king.security.domain.UsrPost;
import org.king.security.exception.AccountAlreadyExistException;
import org.king.security.service.AccountService;

/**
 * <p> AccountServiceImpl.java </p>
 * <p> {功能说明} </p>
 *
 * <p><a href="AccountServiceImpl.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:m_ljf@msn.com">ljf</a>
 * @version 0.1
 * @since 2006-4-20
 * 
 *
 */
public class AccountServiceImpl extends BaseService implements AccountService {

	private AccountDAO accountDAO;

	private UsrPostDAO usrPostDAO;
	
	
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	/* (non-Javadoc)
	 * @see org.king.security.service.AccountService#findAccountById(java.io.Serializable)
	 */
	public Account findAccountById(Serializable id) {
		return accountDAO.get(id);
	}

	/* (non-Javadoc)
	 * @see org.king.security.service.AccountService#findAccountByName(java.lang.String)
	 */
	public Account findAccountByName(String name) throws BusinessException {
		return accountDAO.findAccountByName(name);
	}

	/* (non-Javadoc)
	 * @see org.king.security.service.AccountService#findAllAccount()
	 */
	public List<Account> findAllAccount() throws BusinessException {
		return accountDAO.getAll();
	}

	/* (non-Javadoc)
	 * @see org.king.security.service.AccountService#findAccount(java.lang.String[])
	 */
	public List<Account> findAccount(Object[] args) throws BusinessException {
    	Account accountInfo = (Account) args[0];
    	String page = (String) args[1];
    	Account user = (Account)args[2];
        
    	String hqlStr = "";
    	if(user != null && user.getId().equals("0"))
    	{
    		hqlStr = "select distinct a from Account a,UsrPost b where a.id=b.usrId";
    	}
    	else
    	{
    		hqlStr = "select distinct a from Account a,UsrPost b where a.id=b.usrId and a.flag != " + CommonService.DELETE_FLAG;
    	}
    	 
    	hqlStr += (StringUtils.isNotEmpty(accountInfo.getName())?" and a.name like '%" + accountInfo.getName() + "%'" : "");
    	
    	hqlStr += (StringUtils.isNotEmpty(accountInfo.getPerson().getPersonName())?" and a.person.personName like '%" + accountInfo.getPerson().getPersonName() + "%'" : "");
    	
    	hqlStr += (accountInfo.getPerson().getDept() != null)?" and b.dept = " + accountInfo.getPerson().getDept() : "";
    	hqlStr += (accountInfo.getPerson().getPost() != null)?" and b.post = " + accountInfo.getPerson().getPost() : "";
    	
    	MyQuery myQuery = new MyQuery();
    	myQuery.setQueryString(hqlStr);
    	myQuery.setPageSize(accountInfo.getPageItemCount());
    	 
        if (StringUtils.isNumeric(page)) {
        	myQuery.setPageStartNo(Integer.parseInt(page));
        }else {
        	myQuery.setPageStartNo(0);
        }
        myQuery.setOrderby(" order by a.name");
       
        myQuery.setOffset(true);
        
        return accountDAO.find(myQuery);        
	}
	
	/* (non-Javadoc)
	 * @see org.king.security.service.AccountService#findAccount(java.lang.String[])
	 */
	public List<Account> findTaskCheckableAccount(Account accountInfo) throws BusinessException 
	{        
    	String hqlStr = "select distinct a from Account a,UsrPost b where a.id=b.usrId and a.flag != " + CommonService.DELETE_FLAG;
    	
    	hqlStr = hqlStr + " and b.dept = " + accountInfo.getPerson().getDept();
    	if(accountInfo.getPerson().getPost().equals(Post.POST_BUSINESS_DEPT_MANAGER))
    	{
    		hqlStr = hqlStr + " and b.post in (" + Post.POST_BUSINESS_DEPT_MANAGER + "," + Post.POST_ASSIST_DEPT_MANAGER + ")";
    	}
    	else
    	{
    		hqlStr = hqlStr + " and b.post = " + accountInfo.getPerson().getPost();
    	}    	
    	
    	MyQuery myQuery = new MyQuery();
    	myQuery.setQueryString(hqlStr);      
        myQuery.setOrderby(" order by a.name"); 
        myQuery.setOffset(false);
        
        return accountDAO.find(myQuery);        
	}

	/* (non-Javadoc)
	 * @see org.king.security.service.AccountService#saveAccount(org.king.security.domain.Account)
	 */
	public void saveAccount(Account account) throws BusinessException,
			AccountAlreadyExistException {
		
		if(account==null){
			throw new BusinessException("account can not be null");
		}
		
		if((Account)accountDAO.findAccountByName(account.getName())!=null){
			throw new AccountAlreadyExistException("account already exist!");
		}
		
		ArrayList<UsrPost> usrPostList = account.getUsrPostList();
		
		account.setUsrPostList(new ArrayList<UsrPost>());
		accountDAO.save(account);
		
		for(UsrPost up:usrPostList)
		{	
			up.setUsrId(account.getId());
			usrPostDAO.save(up);					
		}
		
		account.setUsrPostList(usrPostList);

	}

	/* (non-Javadoc)
	 * @see org.king.security.service.AccountService#updateAccount(org.king.security.domain.Account)
	 */
	public void updateAccount(Account account) throws BusinessException {
		
		for(UsrPost up:account.getUsrPostList())
		{			
			if(up.getId() == null)
			{
				up.setUsrId(account.getId());
				usrPostDAO.save(up);
			}
			else
			{
				usrPostDAO.update(up);
			}			
		}
		
		accountDAO.update(account);

	}

	/* (non-Javadoc)
	 * @see org.king.security.service.AccountService#deleteAccount(java.io.Serializable)
	 */
	public void deleteAccount(Serializable id) throws BusinessException {
		if (id == null) {
            throw new BusinessException("account can't be null");
        }
		accountDAO.delete(findAccountById(id));			
	}
	

	/* (non-Javadoc)
	 * @see org.king.security.service.AccountService#deleteAccount(java.io.Serializable[])
	 */
	public void deleteAccount(Serializable[] ids) throws BusinessException {
		if (ids == null) {
            throw new BusinessException("accounts can't be null");
        }

        for (int i = 0, n = ids.length; i < n; i++) {
        	if(this.isDeletable(ids[i]))
        	{
        		deleteAccount(ids[i]);
        	}        	
        }


	}
	
	private boolean isDeletable(Serializable id)
	{
		boolean rtn = true;
		MyQuery myQuery = new MyQuery();
		
		String hqlStr = "select count(*) from SaleOrderMaster a where a.somCreateUser = '" + id + "' or a.somUpdateUser = '" + id + "'";
		myQuery.setQueryString(hqlStr);
	        
		List countList = accountDAO.find(myQuery);
        if(countList!=null && (Integer)countList.get(0) > 0){
        	return false;
        }
        
        hqlStr = "select count(*) from SaleOrderDetail a where a.sodCreateUser = '" + id + "' or a.sodUpdateUser = '" + id + "'";
		myQuery.setQueryString(hqlStr);
	        
		countList = accountDAO.find(myQuery);
        if(countList!=null && (Integer)countList.get(0) > 0){
        	return false;
        }
        
        
		return rtn;
	}

	/* (non-Javadoc)
	 * @see org.king.security.service.AccountService#getAccountCount(java.lang.String[])
	 */
	public Integer getAccountCount(Object[] args) {
		Account accountInfo = (Account) args[0];
		Account user = (Account) args[2];
		
    	List countList = null;
        Integer accountCount = new Integer("0");
        
        String hqlStr = "";
    	if(user != null && user.getId().equals("0"))
    	{
    		hqlStr = "select count(distinct a) from Account a,UsrPost b where a.id=b.usrId";
    	}
    	else
    	{
    		hqlStr = "select count(distinct a) from Account a,UsrPost b where a.id=b.usrId and a.flag != " + CommonService.DELETE_FLAG;
    	}    	
    	hqlStr += (StringUtils.isNotEmpty(accountInfo.getName())?" and a.name like '%" + accountInfo.getName() + "%'": "");
    	
    	hqlStr += (StringUtils.isNotEmpty(accountInfo.getPerson().getPersonName())?" and a.person.personName like '%" + accountInfo.getPerson().getPersonName() + "%'": "");
    	hqlStr += (accountInfo.getPerson().getDept() != null)?" and b.dept = " + accountInfo.getPerson().getDept(): "";
    	hqlStr += (accountInfo.getPerson().getPost() != null)?" and b.post = " + accountInfo.getPerson().getPost() : "";
    	
    	MyQuery myQuery = new MyQuery();
    	
        myQuery.setQueryString(hqlStr);
        
        countList = accountDAO.find(myQuery);
        if(countList!=null){
        	accountCount = (Integer)countList.get(0);
        }
        return accountCount;
	}
	

	public UsrPostDAO getUsrPostDAO() {
		return usrPostDAO;
	}

	public void setUsrPostDAO(UsrPostDAO usrPostDAO) {
		this.usrPostDAO = usrPostDAO;
	}

}
