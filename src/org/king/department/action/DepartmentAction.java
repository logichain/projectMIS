package org.king.department.action;

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
import org.king.department.bean.Department;
import org.king.department.service.DepartmentService;
import org.king.framework.web.action.BaseAction;
import org.king.security.domain.Account;

public class DepartmentAction extends BaseAction {
	private DepartmentService departmentService;
	private CommonService commonService;

	public ActionForward createDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		Department c = departmentService.createDepartment();

		dform.set("departmentInfo", c);

		return mapping.findForward("input");
	}

	public ActionForward searchDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		String name = dform.getString("name");

		String[] args = { name, page };

		ArrayList<Department> gList = departmentService.searchDepartment(args);
		Integer count = departmentService.searchDepartmentCount(args);
		if (count > 0) {
			request.setAttribute("departmentList", gList);
			request.setAttribute("departmentCount", count);
		}

		return mapping.findForward("list");
	}

	public ActionForward resetSearchDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		dform.set("name", null);

		String[] args = { null, null };

		ArrayList<Department> gList = departmentService.searchDepartment(args);
		Integer count = departmentService.searchDepartmentCount(args);
		if (count > 0) {
			request.setAttribute("departmentList", gList);
			request.setAttribute("departmentCount", count);
		}

		return mapping.findForward("list");
	}

	public ActionForward saveDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		if (this.isCancelled(request)) {
			return this.searchDepartment(mapping, dform, request, response);
		}

		Department c = (Department) dform.get("departmentInfo");

		String userId = (String) request.getSession().getAttribute("account");
		if (c.getDId() == 0) {
			c.setDCreateUser(userId);
		}

		try {
			departmentService.saveDepartment(c);
		} catch (Exception e) {
			ActionMessages errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.opererror"));
			saveErrors(request, errors);

			e.printStackTrace();

			return mapping.findForward("input");
		}

		if (request.getRequestURI().contains("DepartmentInput.do")) {
			return this.createDepartment(mapping, dform, request, response);
		}

		return this.searchDepartment(mapping, dform, request, response);
	}

	public ActionForward modifyDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		String gId = (String) request.getParameter("id");
		Department g = departmentService.getDepartmentById(Integer.valueOf(gId));
		dform.set("departmentInfo", g);

		return mapping.findForward("input");
	}

	
	public ActionForward refreshInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		return mapping.findForward("input");
	}

	public ActionForward deleteDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");

		Department c = departmentService.getDepartmentById(Integer.valueOf(id));
		departmentService.deleteDepartment(c);

		return this.searchDepartment(mapping, form, request, response);
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public CommonService getCommonService() {
		return commonService;
	}
}
