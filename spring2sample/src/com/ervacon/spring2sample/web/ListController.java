package com.ervacon.spring2sample.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.ervacon.spring2sample.service.UserManager;

public class ListController extends AbstractController {

	private UserManager userManager;

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// generates model object name by convention ("userList")
		return new ModelAndView().addObject(this.userManager.findAllUsers());
	}
}
