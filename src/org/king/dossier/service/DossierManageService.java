package org.king.dossier.service;

import java.util.ArrayList;

import org.king.dossier.bean.DocumentStatistics;
import org.king.framework.service.Service;
import org.king.projectmanage.bean.ProjectAttachment;

public interface DossierManageService extends Service {		
	public ArrayList<ProjectAttachment> searchDocument(Object[] args);
	public Integer searchDocumentCount(Object[] args);
	public ProjectAttachment getProjectAttachmentById(Integer id);
	public void deleteProjectAttachment(ProjectAttachment pa);
			
	public ArrayList<DocumentStatistics> calculateDocumentStatistics();
	public void saveProjectDocument(ProjectAttachment pa,String uploadPath);
	public ProjectAttachment createProjectAttachment();
}
