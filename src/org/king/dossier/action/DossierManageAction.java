package org.king.dossier.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.DynaValidatorForm;
import org.king.common.service.CommonService;
import org.king.department.service.DepartmentService;
import org.king.dossier.service.DossierManageService;
import org.king.framework.web.action.BaseAction;
import org.king.projectmanage.bean.ProjectAttachment;
import org.king.security.domain.Account;
import org.king.utils.FileUtil;

public class DossierManageAction extends BaseAction{
	private DossierManageService dossierManageService;
	private CommonService commonService;
	private DepartmentService departmentService;

	
	public ActionForward createDocument(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		DynaValidatorForm dform = (DynaValidatorForm) form;
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		
		ProjectAttachment pa = dossierManageService.createProjectAttachment();
		pa.setPaCreateUser(user.getId());
		pa.setCreateUser(user);
		pa.setPaInputInterface(CommonService.INPUT_INTERFACE_INPUT);
		
		dform.set("documentInfo", pa);
		
		this.prepareMetaData(request);
								
		return  mapping.findForward("input");		
	}
	
	public ActionForward modifyDocument(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		
		dform.set("documentInfo", dossierManageService.getProjectAttachmentById(Integer.valueOf(id)));
		
		this.prepareMetaData(request);
								
		return  mapping.findForward("edit");		
	}
	
	private void prepareMetaData(HttpServletRequest request)
	{
		request.setAttribute("attachmentCategoryList", commonService.getAttachmentCategoryList());

		request.setAttribute("departmentList", commonService.getDepartmentList());
	}
	
	public ActionForward saveDocument(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if(!uploadPath.endsWith("\\"))
		{
			uploadPath = uploadPath + "\\";
		}
		
		ProjectAttachment doc = (ProjectAttachment) dform.get("documentInfo");
		
		dossierManageService.saveProjectDocument(doc, uploadPath);
		
		if("create".equals(request.getParameter("optype")))
		{
			return  this.createDocument(mapping, dform, request, response);
		}
		
		doc.setCategory(commonService.getAttachmentCategoryById(doc.getPaAttachmentCategory()));
			
		return  this.searchDocument(mapping, dform, request, response);
	}
	
	public ActionForward saveEditDocument(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if(!uploadPath.endsWith("\\"))
		{
			uploadPath = uploadPath + "\\";
		}
		
		ProjectAttachment doc = (ProjectAttachment) dform.get("documentInfo");
		
		dossierManageService.saveProjectDocument(doc, uploadPath);
		
		return this.searchDocument(mapping, dform, request, response);
	}
	
	public ActionForward deleteDocument(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String id = request.getParameter("id");		
		
		ProjectAttachment c = dossierManageService.getProjectAttachmentById(Integer.valueOf(id));
		dossierManageService.deleteProjectAttachment(c);
								
		return this.searchDocument(mapping, form, request, response);			
	}
	
	public ActionForward searchDocument(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		ProjectAttachment searchInfo = (ProjectAttachment) dform.get("searchInfo");
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		
		Object[] args = {searchInfo, page,user};
		
		ArrayList<ProjectAttachment> gList = dossierManageService.searchDocument(args);
		Integer count = dossierManageService.searchDocumentCount(args);
		if(count > 0)		
		{
			request.setAttribute("documentList",gList);	
			request.setAttribute("documentCount",count);
		}
		
		this.prepareMetaData(request);
				
		return mapping.findForward("list");		
	}
	
	public ActionForward resetSearchDocument(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;		
		ProjectAttachment searchInfo = dossierManageService.createProjectAttachment();
		searchInfo.setPaSubmitDate(null);
		searchInfo.setPaSubmitDepartment(null);
		
		dform.set("searchInfo", searchInfo);		
				
		return this.searchDocument(mapping, dform, request, response);		
	}
								
	public ActionForward downloadAttachment(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{					
		String id = request.getParameter("id");
		ProjectAttachment pa = dossierManageService.getProjectAttachmentById(Integer.parseInt(id));
						
		String fileName = pa.getPaUrl();
				
		try {
			response.setContentType(FileUtil.getContentType(fileName));
//			response.setHeader("Content-Disposition", "attachment;filename=" + fileName.substring(fileName.lastIndexOf("\\")+1));
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(pa.getPaName().getBytes("GB2312"), "ISO_8859_1") + fileName.substring(fileName.lastIndexOf(".")));
			
			InputStream file = new FileInputStream(fileName);
			byte[] bit = new byte[1024];
			int len = file.read(bit);
			OutputStream out = response.getOutputStream();

			while (len != -1) {
				out.write(bit, 0, len);
				len = file.read(bit);
			}
			out.close();
			file.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			ActionMessages errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.download"));
			saveErrors(request, errors);	
			
			return mapping.findForward("fail");
		}    
	
		return null;
	}

	public void setDossierManageService(DossierManageService dossierManageService) {
		this.dossierManageService = dossierManageService;
	}

	public DossierManageService getDossierManageService() {
		return dossierManageService;
	} 
	
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}


	public CommonService getCommonService() {
		return commonService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}


}
