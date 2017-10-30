package org.king.notice.service;

import java.util.ArrayList;

import org.king.framework.service.Service;
import org.king.notice.bean.InfomationNotice;
import org.king.notice.bean.NoticeAttachment;

public interface NoticeService extends Service {
	public void saveNotice(InfomationNotice c,String uploadPath);
	public void saveNotice(InfomationNotice c);
	public void deleteNotice(InfomationNotice c);
	
	public ArrayList<InfomationNotice> searchNotice(Object[] args);
	public int searchNoticeCount(Object[] args);
	
	public ArrayList<InfomationNotice> getAllNotice();
			
	public InfomationNotice getNoticeById(int id);
	public NoticeAttachment getNoticeAttachmentById(int id);
}
