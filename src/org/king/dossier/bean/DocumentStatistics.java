package org.king.dossier.bean;

import org.king.common.attachmentcategory.AttachmentCategory;

public class DocumentStatistics {
	private AttachmentCategory documentCategory;
	private long documentCount;
	
	public void setDocumentCategory(AttachmentCategory documentCategory) {
		this.documentCategory = documentCategory;
	}
	public AttachmentCategory getDocumentCategory() {
		return documentCategory;
	}
	public void setDocumentCount(long documentCount) {
		this.documentCount = documentCount;
	}
	public long getDocumentCount() {
		return documentCount;
	}	
	
}
