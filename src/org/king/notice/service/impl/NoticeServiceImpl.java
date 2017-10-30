package org.king.notice.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.king.common.service.CommonService;
import org.king.framework.dao.MyQuery;
import org.king.framework.service.impl.BaseService;
import org.king.notice.bean.InfomationNotice;
import org.king.notice.bean.NoticeAttachment;
import org.king.notice.dao.InfomationNoticeDAO;
import org.king.notice.dao.NoticeAttachmentDAO;
import org.king.notice.service.NoticeService;
import org.king.utils.FileUtil;

public class NoticeServiceImpl extends BaseService implements NoticeService {
	private InfomationNoticeDAO noticeDAO;
	private NoticeAttachmentDAO noticeAttachmentDAO;

	public void deleteNotice(InfomationNotice c) {
		// TODO Auto-generated method stub
		noticeDAO.delete(c);
	}

	public ArrayList<InfomationNotice> getAllNotice() {
		// TODO Auto-generated method stub
		return (ArrayList<InfomationNotice>) noticeDAO.find("from InfomationNotice a where a.CFlag != '-1'");
	}

	public InfomationNotice getNoticeById(int id) {
		// TODO Auto-generated method stub
		return noticeDAO.get(id);
	}

	public void saveNotice(InfomationNotice tp,String uploadPath) {
		// TODO Auto-generated method stub
		ArrayList<NoticeAttachment> attachmentList = tp.getAttachmentList();
		tp.setAttachmentList(new ArrayList<NoticeAttachment>());
		
		Date createTime = new Date();
		if(tp.getInId() == null)
		{			
			noticeDAO.save(tp);
		}
		else
		{
			noticeDAO.update(tp);
		}
		tp.setAttachmentList(attachmentList);
		
		for(NoticeAttachment pa:attachmentList)
		{
			if(pa.getNaId() == null)
			{
				String fileName = FileUtil.saveUploadFile(pa.getAttachmentFile(),uploadPath + tp.getInId());
				
				pa.setNaInfomationNotice(tp.getInId());
				pa.setNaCreateTime(createTime);
				pa.setNaUrl(uploadPath + tp.getInId() + "\\" + fileName);
								
				noticeAttachmentDAO.save(pa);
			}	
			else if(pa.isModified())
			{
				noticeAttachmentDAO.update(pa);
				pa.setModified(false);
			}
		}		
	}
	
	public void saveNotice(InfomationNotice tp) {
		// TODO Auto-generated method stub				
	
		if(tp.getInId() == null)
		{			
			noticeDAO.save(tp);
		}
		else
		{
			noticeDAO.update(tp);
		}		
	}

	public ArrayList<InfomationNotice> searchNotice(Object[] args) {
		// TODO Auto-generated method stub
		InfomationNotice searchInfo = (InfomationNotice) args[0];
    	String page = (String) args[1];
    	String userId = (String) args[2];
    	
    	String hqlStr = "";
      	if(userId.equals("0"))
      	{
      		hqlStr = "from InfomationNotice a where 1=1";
      	}
      	else
      	{
      		hqlStr = "from InfomationNotice a where a.inFlag != " + CommonService.DELETE_FLAG;
      	}
    	
    	hqlStr = this.processSeachInfo(searchInfo, hqlStr);
    	
    	MyQuery myQuery = new MyQuery();
    	myQuery.setPageSize(searchInfo.getPageItemCount());
        if (StringUtils.isNumeric(page)) {
        	myQuery.setPageStartNo(Integer.parseInt(page));
        }else {
        	myQuery.setPageStartNo(0);
        }
      
        if(searchInfo.getOrderColumn().isEmpty())
        {
        	 myQuery.setOrderby(" order by a.inReleaseDate desc");
        }
        else
        {
        	if(searchInfo.isAscFlag())
        	{
        		myQuery.setOrderby(" order by a." + searchInfo.getOrderColumn());
        	}
        	else
        	{
        		myQuery.setOrderby(" order by a." + searchInfo.getOrderColumn() + " desc");
        	}
        }
        
        myQuery.setQueryString(hqlStr);

        myQuery.setOffset(true);
        
		return (ArrayList<InfomationNotice>) noticeDAO.find(myQuery);
	}
	
	private String processSeachInfo(InfomationNotice searchInfo,String hqlStr)
	{
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getInTheme())?" and a.inTheme like '%" + searchInfo.getInTheme() + "%'" : "");
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getInReleaseDate())?" and a.inReleaseDate = '" + searchInfo.getInReleaseDate() + "'" : "");
    	
    	hqlStr += ((searchInfo.getInDept() != null && searchInfo.getInDept() != 0)?" and a.inDept = " + searchInfo.getInDept() : "");
    	hqlStr += ((searchInfo.getInType() != null && searchInfo.getInType() != 0)?" and a.inType = " + searchInfo.getInType() : "");
    	hqlStr += ((searchInfo.getInSecretDegree() != null && searchInfo.getInSecretDegree() != 0)?" and a.inSecretDegree = " + searchInfo.getInSecretDegree() : "");
    	    	
    	return hqlStr;
	}

	public int searchNoticeCount(Object[] args) {
		// TODO Auto-generated method stub
		InfomationNotice searchInfo = (InfomationNotice) args[0];
		String userId = (String) args[2];
    	
    	String hqlStr = "";
      	if(userId.equals("0"))
      	{
      		hqlStr = "select count(*) from InfomationNotice a where 1=1";
      	}
      	else
      	{
      		hqlStr = "select count(*) from InfomationNotice a where a.inFlag != " + CommonService.DELETE_FLAG;
      	}        
      	
    	hqlStr = this.processSeachInfo(searchInfo, hqlStr);
    	
    	MyQuery myQuery = new MyQuery();
              
        myQuery.setQueryString(hqlStr);       
        
		return noticeDAO.getFindCount(myQuery);
	}

	public InfomationNoticeDAO getNoticeDAO() {
		return noticeDAO;
	}

	public void setNoticeDAO(InfomationNoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	public void setNoticeAttachmentDAO(NoticeAttachmentDAO noticeAttachmentDAO) {
		this.noticeAttachmentDAO = noticeAttachmentDAO;
	}

	public NoticeAttachmentDAO getNoticeAttachmentDAO() {
		return noticeAttachmentDAO;
	}

	@Override
	public NoticeAttachment getNoticeAttachmentById(int id) {
		// TODO Auto-generated method stub
		return noticeAttachmentDAO.get(id);
	}
}
