package org.king.notice.action;

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
import org.king.framework.web.action.BaseAction;
import org.king.notice.bean.InfomationNotice;
import org.king.notice.bean.NoticeAttachment;
import org.king.notice.service.NoticeService;
import org.king.utils.FileUtil;

/**
 * @author xy91126
 *
 */
public class InfomationNoticeAction extends BaseAction {
	private NoticeService noticeService;
	private CommonService commonService;

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String userId = (String) request.getSession().getAttribute("account");

		InfomationNotice tp = (InfomationNotice) dform.get("noticeInfo");

		NoticeAttachment pa = new NoticeAttachment();
		pa.setNaCreateUser(userId);
		pa.setNaInfomationNotice(tp.getInId());
		tp.setCurrentAttachment(pa);

		request.setAttribute("attachmentCategoryList", commonService.getAttachmentCategoryList());

		return mapping.findForward("uploadAttachment");
	}

	public ActionForward deleteAttachmentByIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		InfomationNotice tp = (InfomationNotice) dform.get("noticeInfo");
		String index = request.getParameter("index");

		tp.getAttachmentList().remove(Integer.parseInt(index));

		this.prepareMetaData(request);

		return mapping.findForward("input");
	}

	public ActionForward confirmAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		InfomationNotice tp = (InfomationNotice) dform.get("noticeInfo");
		NoticeAttachment pa = tp.getCurrentAttachment();

		tp.getAttachmentList().add(pa);

		return mapping.findForward("refreshInput");
	}

	public ActionForward downloadAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		String id = request.getParameter("id");
		NoticeAttachment pa = noticeService.getNoticeAttachmentById(Integer.parseInt(id));

		String fileName = pa.getNaUrl();

		try {
			response.setContentType(FileUtil.getContentType(fileName));
//			response.setHeader("Content-Disposition", "attachment;filename=" + fileName.substring(fileName.lastIndexOf("\\") + 1));
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(pa.getNaName().getBytes("GB2312"), "ISO_8859_1") + fileName.substring(fileName.lastIndexOf(".")));
			
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

	public ActionForward refreshNoticeInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		this.prepareMetaData(request);

		return mapping.findForward("input");
	}

	public ActionForward createNotice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		InfomationNotice c = new InfomationNotice();

		dform.set("noticeInfo", c);

		this.prepareMetaData(request);

		return mapping.findForward("input");
	}

	public ActionForward searchNotice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		InfomationNotice searchInfo = (InfomationNotice) dform.get("searchInfo");
		String userId = (String) request.getSession().getAttribute("account");

		Object[] args = { searchInfo, page,userId };

		ArrayList<InfomationNotice> gList = noticeService.searchNotice(args);
		Integer count = noticeService.searchNoticeCount(args);
		if (count > 0) {
			request.setAttribute("noticeList", gList);
			request.setAttribute("noticeCount", count);
		}

		this.prepareMetaData(request);

		return mapping.findForward("list");
	}

	public ActionForward aboutSearchNotice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		InfomationNotice searchInfo = (InfomationNotice) dform.get("searchInfo");
		String userId = (String) request.getSession().getAttribute("account");
		
		Object[] args = { searchInfo, null,userId };

		ArrayList<InfomationNotice> gList = noticeService.searchNotice(args);
		Integer count = noticeService.searchNoticeCount(args);
		if (count > 0) {
			request.setAttribute("noticeList", gList);
		}

		this.prepareMetaData(request);

		return mapping.findForward("aboutList");
	}

	public ActionForward resetSearchNotice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		InfomationNotice searchInfo = new InfomationNotice();
		dform.set("searchInfo", searchInfo);

		return this.searchNotice(mapping, dform, request, response);
	}

	public ActionForward saveNotice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		InfomationNotice c = (InfomationNotice) dform.get("noticeInfo");

		String userId = (String) request.getSession().getAttribute("account");
		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if (!uploadPath.endsWith("\\")) {
			uploadPath = uploadPath + "\\";
		}

		if (c.getInId() == null || c.getInId() == 0) {
			c.setInCreateUser(userId);
		}

		try {
			noticeService.saveNotice(c, uploadPath);
		} catch (Exception e) {
			ActionMessages errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.opererror"));
			saveErrors(request, errors);

			this.prepareMetaData(request);

			e.printStackTrace();

			return mapping.findForward("input");
		}

		c.setNoticeType(commonService.getNoticeTypeById(c.getInType()));

		if ("create".equals(request.getParameter("optype"))) {
			return this.createNotice(mapping, dform, request, response);
		}

		return this.searchNotice(mapping, dform, request, response);
	}

	private void prepareMetaData(HttpServletRequest request) {
		request.setAttribute("noticeTypeList", commonService.getNoticeTypeList());
		request.setAttribute("departmentList", commonService.getNoticeSubmitDepartmentList());
	}

	public ActionForward modifyNotice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		String gId = (String) request.getParameter("id");
		InfomationNotice g = noticeService.getNoticeById(Integer.valueOf(gId));
		dform.set("noticeInfo", g);

		this.prepareMetaData(request);

		return mapping.findForward("input");

	}

	public ActionForward displayNotice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		String gId = (String) request.getParameter("id");
		InfomationNotice g = noticeService.getNoticeById(Integer.valueOf(gId));
		request.setAttribute("noticeInfo", g);

		this.prepareMetaData(request);

		return mapping.findForward("display");

	}

	public ActionForward refreshInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		this.prepareMetaData(request);

		return mapping.findForward("input");
	}

	public ActionForward deleteNotice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");

		InfomationNotice c = noticeService.getNoticeById(Integer.valueOf(id));
		noticeService.deleteNotice(c);

		return this.searchNotice(mapping, form, request, response);
	}
	
	public ActionForward cancelDeletedNotice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");

		InfomationNotice c = noticeService.getNoticeById(Integer.valueOf(id));
		c.setInFlag(CommonService.NORMAL_FLAG);
		noticeService.saveNotice(c);

		return this.searchNotice(mapping, form, request, response);
	}

	public NoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public CommonService getCommonService() {
		return commonService;
	}
}
